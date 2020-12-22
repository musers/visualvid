package com.ae.visuavid.repository.custom;

import java.util.UUID;

public interface OrderRepositoryCustom {
    String generateOrderId();

    String generateSalesId();

    void updateRazorPayOrderId(UUID id, String paymentOrderId, String orderStatus);

    void updateRazorPayPaymentIdAndSalesId(UUID id, String razorpayPaymentId, String name);
}
