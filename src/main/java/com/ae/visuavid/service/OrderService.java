package com.ae.visuavid.service;

import com.ae.visuavid.constants.OrderStatus;
import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.domain.OrderEntity;
import com.ae.visuavid.domain.User;
import com.ae.visuavid.repository.AdminUploadFormRepository;
import com.ae.visuavid.repository.OrderRepository;
import com.ae.visuavid.repository.UserRepository;
import com.ae.visuavid.service.dto.*;
import com.ae.visuavid.service.mapper.OrderMapper;
import com.ae.visuavid.service.mapper.OrderSlideMapper;
import com.ae.visuavid.utils.NumberUtility;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import io.swagger.annotations.Api;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    protected RazorPayService razorPayService;

    @Autowired
    protected UserRepository userRepository;

    public OrderService() {}

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
            order
                .getOrderSlides()
                .forEach(
                    orderSlide -> {
                        orderSlide.setOrder(order);
                        if (orderSlide.getOrderSlideItems() != null) {
                            orderSlide
                                .getOrderSlideItems()
                                .forEach(
                                    slideItem -> {
                                        slideItem.setOrderSlide(orderSlide);
                                    }
                                );
                        }
                    }
                );
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
        if (adminMediaEntity.getSlides().size() > 0) {
            List<OrderSlideDTO> orderSlides = orderSlideMapper.toDTOsFromAdminMediaEntities(adminMediaEntity.getSlides());
            orderDTO.setOrderSlides(orderSlides);
        }
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
        orders.forEach(
            order -> {
                orderRepository.updateRazorPayOrderId(
                    order.getId(),
                    paymentOrder.getRazorPayOrderId(),
                    OrderStatus.PAYMENT_INITIATED.name()
                );
            }
        );
    }

    public void updateRazorPayTransaction(RazorPayResponseDTO razorPayResponse) {
        // TODO should not be used, need to get order id from ui and use that
        String razorPayOrderId = razorPayResponse.getRazorpayOrderId();
        String razorPayPaymentId = razorPayResponse.getRazorpayPaymentId();
        String razorPaySignature = razorPayResponse.getRazorpaySignature();
        razorPayService.validateRazorPayResponse(razorPayOrderId, razorPayPaymentId, razorPaySignature);
        List<OrderDTO> orderDtos = getOrdersByRazorPayOrderId(razorPayOrderId);
        orderDtos.forEach(
            order -> {
                orderRepository.updateRazorPayPaymentIdAndSalesId(
                    order.getId(),
                    razorPayResponse.getRazorpayPaymentId(),
                    OrderStatus.PAYMENT_COMPLETED.name()
                );
            }
        );
    }

    public List<OrderDTO> getOrdersByRazorPayOrderId(String razorPayOrderId) {
        Optional<List<OrderEntity>> optOrderEntities = orderRepository.findByRazorPayOrderId(razorPayOrderId);
        if (optOrderEntities.isPresent()) {
            return orderMapper.toDtos(optOrderEntities.get());
        } else {
            throw new ApiRuntimeException("No orders found for the given razorPayOrderId: " + razorPayOrderId);
        }
    }

    public OrderDTO saveCustomerUpload(OrderDTO orderDTO) {
        String orderStatus = orderRepository.getOrderStatus(orderDTO.getId());
        if (orderStatus != null && orderStatus.equalsIgnoreCase(OrderStatus.PAYMENT_COMPLETED.name())) {
            OrderEntity orderEntity = orderMapper.toEntity(orderDTO);
            orderEntity.setOrderStatus(OrderStatus.DATA_UPLOADED.name());
            updateParentChildReferences(orderEntity);
            return orderMapper.toDto(orderRepository.save(orderEntity));
        } else {
            throw new ApiRuntimeException("Invalid order status for the order : " + orderDTO.getId());
        }
    }

    public void assignOrderToEmployee(String orderId, String userId) {
        Optional<OrderEntity> optOrder = orderRepository.findById(UUID.fromString(orderId));
        if (optOrder.isPresent()) {
            OrderEntity orderEntity = optOrder.get();
            Optional<User> optUser = userRepository.findById(UUID.fromString(userId));
            if (optUser.isPresent()) {
                orderEntity.setAssignedUserId(optUser.get().getId().toString());
                orderEntity.setAssignedUserName(optUser.get().getLogin());
                orderRepository.save(orderEntity);
            }
        }
    }
}
