export interface OrderModel {
  salesId?: string;
  orderId?: string;
  name?: string;
  assignTo?: string;
  orderStatus?: string;
  timeLeft?: Number;
  action?: string;
  id?: string;
  adminMediaId?: string;
  assignedUserId?: string;
  assignedUserName?: string;
  description?: string;
  categoryId?: string;
  categoryName?: string;
  previewVideoS3Url?: string;
  previewVideoS3Key?: string;
  thumbNailS3Url?: string;
  thumbNailS3Key?: string;
  mediaPlaceholder?: string;
  textPlaceholder?: string;
  turnAroundTime?: number;
  tags?: string;
  couponCode?: string;
  // Pricing info
  razorPayOrderId?: string;
  razorPayPaymentId?: string;
  currencyCode?: string;
  basicAmount?: any;
  discountAmount?: any;
  advancedCustomizationAmount?: any;
  premiumDeliveryAmount?: any;
  gstAmount?: any;
  totalAmount?: any;
  couponDiscountAmount?: any;
  couponDiscountPercentage?: any;
  gstPercentage?: any;
  paymentOrderId?: any;
  orderSlides: Array<OrderSlide>;
}
export interface OrderSlide {
  id?: string;
  screenShotS3Url?: string;
  screenShotS3Key?: string;
  slideName?: string;
  slideOrder?: any;
  adminSlideId?: string;
  orderSlideItems: Array<OrderSlideItem>;
}
export interface OrderSlideItem {
  id?: string;
  itemType?: string;
  itemLabel?: string;
  itemValue?: string;
  itemOrder?: any;
  s3Url?: string;
  s3Key?: string;
  adminSlideItemId?: string;
}
