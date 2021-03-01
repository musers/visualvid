package com.ae.visuavid.service;

import com.ae.visuavid.config.ApplicationProperties;
import com.ae.visuavid.constants.OrderStatus;
import com.ae.visuavid.domain.SubscriptionEntity;
import com.ae.visuavid.domain.User;
import com.ae.visuavid.domain.UserSubscriptionEntity;
import com.ae.visuavid.enumeration.CountryCodeType;
import com.ae.visuavid.enumeration.SubscriptionStatusType;
import com.ae.visuavid.enumeration.SubscriptionType;
import com.ae.visuavid.repository.UserRepository;
import com.ae.visuavid.repository.UserSubscriptionRepository;
import com.ae.visuavid.security.SecurityUtils;
import com.ae.visuavid.service.dto.PaymentOrderDTO;
import com.ae.visuavid.service.dto.RazorPayResponseDTO;
import com.ae.visuavid.service.dto.UserSubscriptionDTO;
import com.ae.visuavid.service.mapper.UserSubscriptionMapper;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class UserSubscriptionService {
    private static final Logger log = LoggerFactory.getLogger(UserSubscriptionService.class);

    @Autowired
    UserSubscriptionMapper mapper;

    @Autowired
    UserSubscriptionRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    RazorPayService razorPayService;

    @Autowired
    private ApplicationProperties applicationProperties;

    private UserSubscriptionEntity prepareUserSubscription(UserSubscriptionDTO dto) {
        UserSubscriptionEntity entity = mapper.toEntity(dto);
        SubscriptionEntity subscriptionEntity = subscriptionService.findOne(dto.getSubscriptionId());
        if (subscriptionEntity != null) {
            entity.setSubscription(subscriptionEntity);
            String subscriptionType = subscriptionEntity.getType();
            Optional<User> userOptional = SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin);
            if (userOptional.isPresent()) {
                entity.setUser(userOptional.get());
                entity.setUserName(userOptional.get().getLogin());
            }
            entity.setStartDate(Instant.now());
            entity.setStatus(SubscriptionStatusType.ACTIVE.name());
            setEndDateAndBasicAmount(entity, subscriptionEntity, subscriptionType);
            entity.setDiscountAmount(subscriptionEntity.getDiscountAmount());
        }
        return entity;
    }

    private void setEndDateAndBasicAmount(UserSubscriptionEntity entity, SubscriptionEntity subscriptionEntity, String subscriptionType) {
        String country = entity.getUser().getCountry();
        if (subscriptionType.equals(SubscriptionType.MONTHlY.name())) {
            entity.setEndDate(calculateSubscriptionExpirationDate(SubscriptionType.MONTHlY));
            setBasicAmountMonthly(entity, subscriptionEntity, country);
        }
        if (subscriptionType.equals(SubscriptionType.YEARLY.name())) {
            entity.setEndDate(calculateSubscriptionExpirationDate(SubscriptionType.YEARLY));
            setBasicAmountYearly(entity, subscriptionEntity, country);
        }
    }

    private void setBasicAmountMonthly(UserSubscriptionEntity entity, SubscriptionEntity subscriptionEntity, String country) {
        if (!StringUtils.isEmpty(country) && country.equalsIgnoreCase(CountryCodeType.IND.name())) {
            entity.setBasicAmount(subscriptionEntity.getMonthlyPriceLocal());
        } else {
            entity.setBasicAmount(subscriptionEntity.getMonthlyPriceUsd());
        }
    }

    private void setBasicAmountYearly(UserSubscriptionEntity entity, SubscriptionEntity subscriptionEntity, String country) {
        if (!StringUtils.isEmpty(country) && country.equalsIgnoreCase(CountryCodeType.IND.name())) {
            entity.setBasicAmount(subscriptionEntity.getYearlyPriceLocal());
        } else {
            entity.setBasicAmount(subscriptionEntity.getYearlyPriceUsd());
        }
    }

    private Instant calculateSubscriptionExpirationDate(SubscriptionType subscriptionType) {
        if (subscriptionType.equals(SubscriptionType.MONTHlY)) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, 1);
            return c.getTime().toInstant();
        }
        if (subscriptionType.equals(SubscriptionType.YEARLY)) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.YEAR, 1);
            return c.getTime().toInstant();
        }
        return null;
    }

    public UserSubscriptionDTO createUserSubscription(UserSubscriptionDTO dto) {
        UserSubscriptionEntity entity = prepareUserSubscription(dto);
        entity = repository.save(entity);
        log.info("saved user-subscription {} , for user {} ", entity.getId(), entity.getUser().getId());
        PaymentOrderDTO paymentOrderDTO = createPaymentOrder(dto, entity);
        entity.setRazorPayOrderId(paymentOrderDTO.getRazorPayOrderId());
        //confirm with bala...payOrderId & paymentId
        entity.setRazorPayPaymentId(paymentOrderDTO.getPaymentOrderId());
        entity.setTotalAmount(paymentOrderDTO.getAmount());
        entity.setCurrencyCode(paymentOrderDTO.getCurrencyCode());
        entity.setStatus(OrderStatus.PAYMENT_INITIATED.name());
        entity = repository.save(entity);
        log.info("updated user-subscription {}  with status as payment initiated", entity.getId());
        return mapper.toDto(entity);
    }

    public PaymentOrderDTO createPaymentOrder(UserSubscriptionDTO dto, UserSubscriptionEntity entity) {
        String paymentOrderId = UUID.randomUUID().toString();

        PaymentOrderDTO paymentOrderDTO = razorPayService.createPaymentOrder(dto.getAmountPaid(), dto.getCurrencyCode(), paymentOrderId);
        log.info("razor-payment initiated and pay-order is {} ", paymentOrderDTO.getRazorPayOrderId());
        paymentOrderDTO.setRazorPayKey(applicationProperties.getRazorpay().getKey());
        return paymentOrderDTO;
    }

    public UserSubscriptionDTO updateRazorPayTransaction(RazorPayResponseDTO razorPayResponse) {
        // TODO should not be used, need to get order id from ui and use that
        log.info("updating payment status as PAYMENT_COMPLETED for razorPayOrderId {} ", razorPayResponse.getRazorpayOrderId());
        String razorPayOrderId = razorPayResponse.getRazorpayOrderId();
        String razorPayPaymentId = razorPayResponse.getRazorpayPaymentId();
        String razorPaySignature = razorPayResponse.getRazorpaySignature();
        razorPayService.validateRazorPayResponse(razorPayOrderId, razorPayPaymentId, razorPaySignature);
        UserSubscriptionEntity userSubscriptionEntity = getUserSubscriptionByRazorPayOrderId(razorPayOrderId);
        userSubscriptionEntity.setStatus(OrderStatus.PAYMENT_COMPLETED.name());
        userSubscriptionEntity = repository.save(userSubscriptionEntity);
        log.info(
            "payment completed successfully and status updated PAYMENT_COMPLETED for user-subscription {} , razorPayOrderId {}",
            userSubscriptionEntity.getId(),
            razorPayOrderId
        );
        return mapper.toDto(userSubscriptionEntity);
    }

    public UserSubscriptionEntity getUserSubscriptionByRazorPayOrderId(String razorPayOrderId) {
        UserSubscriptionEntity userSubscriptionEntity = repository.findByRazorPayOrderId(razorPayOrderId);
        if (userSubscriptionEntity != null) {
            return userSubscriptionEntity;
        } else {
            throw new ApiRuntimeException("No user-subscription found for the given razorPayOrderId: " + razorPayOrderId);
        }
    }

    public List<UserSubscriptionDTO> searchByUserName(String userName) {
        List<UserSubscriptionEntity> userSubscriptionEntities = repository.findByUserNameContains(userName);
        return mapper.toDtos(userSubscriptionEntities);
    }
}
