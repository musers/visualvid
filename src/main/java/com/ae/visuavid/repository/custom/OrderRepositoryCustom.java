package com.ae.visuavid.repository.custom;

import java.util.UUID;

public interface OrderRepositoryCustom {
    String generateOrderId();

    String generateSalesId();

    void updatePaymentOrderId(UUID id, String paymentOrderId, String orderStatus);
}
