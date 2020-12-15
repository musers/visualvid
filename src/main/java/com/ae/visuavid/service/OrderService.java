package com.ae.visuavid.service;

import com.ae.visuavid.constants.OrderStatus;
import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.domain.OrderEntity;
import com.ae.visuavid.repository.AdminUploadFormRepository;
import com.ae.visuavid.repository.OrderRepository;
import com.ae.visuavid.service.dto.*;
import com.ae.visuavid.service.mapper.OrderMapper;
import com.ae.visuavid.service.mapper.OrderSlideMapper;
import com.ae.visuavid.utils.NumberUtility;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class OrderService {
    private final Logger log = LoggerFactory.getLogger(OrderService.class);


    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected CategoryService categoryService;

    @Autowired
    protected PricingService pricingService;

    @Autowired
    protected AdminUploadFormRepository adminUploadFormRepository;

    @Autowired
    protected OrderSlideMapper orderSlideMapper;

    @Autowired
    protected OrderMapper orderMapper;

    @Autowired
    protected NumberUtility numberUtility;


    public OrderService() {
    }

    public List<OrderDTO> create(@NotNull OrderRequestDTO orderRequest) {
        List<OrderDTO> orders = new ArrayList<>();
        for (ItemCustomizationDTO itemCustomization : orderRequest.getItemCustomizations()) {
            itemCustomization.setCouponCode(orderRequest.getCouponCode());
            itemCustomization.setCurrencyCode(orderRequest.getCurrencyCode());
            orders.add(create(itemCustomization));
        }
        return orders;
    }

    public OrderDTO create(ItemCustomizationDTO itemCustomization) {
        UUID adminMediaId = itemCustomization.getAdminMediaId();
        Optional<AdminMediaEntity> obj = adminUploadFormRepository.findById(adminMediaId);
        if (obj.isPresent()) {
            AdminMediaEntity adminMediaEntity = obj.get();
            OrderDTO orderDTO = prepareOrderFromAdminMediaEntity(adminMediaEntity, itemCustomization);
            orderDTO = create(orderDTO);
            // No need to send order slides back
            orderDTO.setOrderSlides(null);
            return orderDTO;
        } else {
            throw new ApiRuntimeException("Admin media is not found");
        }
    }

    public OrderDTO findOrderById(UUID id) {
        Optional<OrderEntity> optOrder = orderRepository.findById(id);
        if (optOrder.isPresent()) {
            return orderMapper.toDto(optOrder.get());
        }
        return null;
    }

    public List<OrderDTO> findAll() {
        //TODO Should not be used, it should be replaced by search and pagination method
        return orderMapper.toDtos(orderRepository.findAll());
    }

    public OrderDTO create(@NotNull OrderDTO orderDTO) {
        OrderEntity e = orderMapper.toEntity(orderDTO);
        updateParentChildReferences(e);
        OrderEntity entity = orderRepository.save(e);
        return orderMapper.toDto(entity);
    }

    private void updateParentChildReferences(OrderEntity order) {
        if (order.getOrderSlides() != null) {
            order.getOrderSlides().forEach(orderSlide -> {
                orderSlide.setOrder(order);
                if (orderSlide.getOrderSlideItems() != null) {
                    orderSlide.getOrderSlideItems().forEach(slideItem -> {
                        slideItem.setOrderSlide(orderSlide);
                    });
                }
            });
        }
    }

    private OrderDTO prepareOrderFromAdminMediaEntity(@NotNull AdminMediaEntity adminMediaEntity, ItemCustomizationDTO itemCustomization) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(generateOrderId());
        orderDTO.setOrderStatus(OrderStatus.DRAFTED.name());
        orderDTO.setAdminMediaId(adminMediaEntity.getId());
        orderDTO.setName(adminMediaEntity.getName());
        orderDTO.setDescription(orderDTO.getDescription());
        orderDTO.setCategoryId(adminMediaEntity.getCategoryId());
        orderDTO.setCategoryName(categoryService.getName(adminMediaEntity.getCategoryId()));
        orderDTO.setPreviewVideoS3Url(adminMediaEntity.getPreviewVideoS3Url());
        orderDTO.setPreviewVideoS3Key(adminMediaEntity.getPreviewVideoS3Key());
        orderDTO.setThumbNailS3Url(adminMediaEntity.getThumbNailS3Url());
        orderDTO.setThumbNailS3Key(adminMediaEntity.getThumbNailS3Key());
        orderDTO.setMediaPlaceholder(adminMediaEntity.getMediaPlaceholder());
        orderDTO.setTextPlaceholder(adminMediaEntity.getTextPlaceholder());
        orderDTO.setTurnAroundTime(numberUtility.convertToLongNonNullable(adminMediaEntity.getTurnAroundTime()));
        orderDTO.setTags(adminMediaEntity.getTags());

        // Pricing info
        PricingDTO pricing = pricingService.computePrice(adminMediaEntity, itemCustomization);
        orderDTO.setCurrencyCode(pricing.getCurrencyCode());
        orderDTO.setBasicAmount(pricing.getBasicAmount());
        orderDTO.setDiscountAmount(pricing.getDiscountAmount());
        orderDTO.setAdvancedCustomizationAmount(pricing.getAdvancedCustomizationAmount());
        orderDTO.setPremiumDeliveryAmount(pricing.getPremiumDeliveryAmount());
        orderDTO.setCouponDiscountPercentage(pricing.getCouponDiscountPercentage());
        orderDTO.setCouponDiscountAmount(pricing.getCouponDiscountAmount());
        orderDTO.setGstPercentage(pricing.getGstPercentage());
        orderDTO.setGstAmount(pricing.getGstAmount());
        orderDTO.setTotalAmount(pricing.getTotalAmount());
        orderDTO.setCouponCode(pricing.getCouponCode());

        // Slide & slide item info
        List<OrderSlideDTO> orderSlides = orderSlideMapper.toDTOsFromAdminMediaEntities(adminMediaEntity.getSlides());
        orderDTO.setOrderSlides(orderSlides);
        return orderDTO;
    }

    private String generateOrderId() {
        String orderId = orderRepository.generateOrderId();
        Optional<OrderEntity> exitingOrder = orderRepository.findByOrderId(orderId);
        while (exitingOrder.isPresent()) {
            orderId = orderRepository.generateOrderId();
            exitingOrder = orderRepository.findByOrderId(orderId);
        }
        return orderId;
    }

    public void updatePaymentOrder(List<OrderDTO> orders, PaymentOrderDTO paymentOrder) {
        orders.forEach(order -> {
            // TODO we can update only two fields instead of complete object
            order.setPaymentOrderId(paymentOrder.getPaymentOrderId());
            order.setOrderStatus(OrderStatus.PAYMENT_INITIATED.name());
            OrderEntity orderEntity = orderMapper.toEntity(order);
            orderRepository.save(orderEntity);
        });
    }
}
