export interface OrderModel {
    salesId?: string;
    orderId?: string;
    name?: string;
    assignTo?: string;
    orderStatus?: string;
    timeLeft?: Number;
    action?: string;
}
// this.orders = [
//       {
//         saleId: '0001',
//         orderId: '0001',
//         name: 'RestaurantPromoVideo',
//         assignTo: 'Bobba Anil',
//         status: 'Assigned',
//         timeLeft: 120,
//         action: 'select',
//       },
//     ];

//
//  private UUID id;
//     private String orderId;
//     private String ;
//     private String orderStatus;
//     private UUID adminMediaId;
//     private String assignedUserId;
//     private String assignedUserName;
//     private String name;
//     private String description;
//     private String categoryId;
//     private String categoryName;
//     private String previewVideoS3Url;
//     private String previewVideoS3Key;
//     private String thumbNailS3Url;
//     private String thumbNailS3Key;
//     private String mediaPlaceholder;
//     private String textPlaceholder;
//     private long turnAroundTime;
//     private String tags;
//     private String couponCode;
//     // Pricing info
//     private String razorPayOrderId;
//     private String razorPayPaymentId;
//     private String currencyCode;
//     private BigDecimal basicAmount;
//     private BigDecimal discountAmount;
//     private BigDecimal advancedCustomizationAmount;
//     private BigDecimal premiumDeliveryAmount;
//     private BigDecimal gstAmount;
//     private BigDecimal totalAmount;
//     private BigDecimal couponDiscountAmount;
//     private Integer couponDiscountPercentage;
//     private Integer gstPercentage;
//     private List<OrderSlideDTO> orderSlides;
//     private String paymentOrderId;
