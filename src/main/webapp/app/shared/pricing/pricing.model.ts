export interface Pricing {
  currencyCode?: String;
  basicAmount?: number;
  discountAmount?: number;
  advancedCustomizationAmount?: number;
  premiumDeliveryAmount?: number;
  totalAmount?: number;
  couponDiscountAmount?: number;
  couponDiscountPercentage?: number;
  gstAmount?: number;
  gstPercentage?: number;
  couponCode?: String;
}
