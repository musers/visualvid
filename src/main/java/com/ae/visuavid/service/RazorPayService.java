package com.ae.visuavid.service;

import com.ae.visuavid.VisualvidApp;
import com.ae.visuavid.constants.Currency;
import com.ae.visuavid.service.dto.PaymentOrderDTO;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Service
public class RazorPayService {
    private static final Logger log = LoggerFactory.getLogger(VisualvidApp.class);
    private RazorpayClient razorpayClient;

    public RazorPayService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    public PaymentOrderDTO createPaymentOrder(@NotNull String orderId) {
        //TODO Make a call to order service and get amount and currency
        BigDecimal amount = new BigDecimal(399);
        Currency currency = Currency.INR;
        return createPaymentOrder(amount, currency.name(), orderId);
    }

    public PaymentOrderDTO createPaymentOrder(@NotNull BigDecimal amount, String currency, String orderId) {
        try {
            log.info("createPaymentOrder for " + orderId);
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount.multiply(new BigDecimal(100)));
            orderRequest.put("currency", currency);
            orderRequest.put("receipt", orderId);

            Order order = razorpayClient.Orders.create(orderRequest);
            JSONObject orderJson = order.toJson();
            PaymentOrderDTO paymentOrder = new PaymentOrderDTO();
            paymentOrder.setAmount(new BigDecimal((Integer) orderJson.get("amount")));
            paymentOrder.setCurrencyCode((String) orderJson.get("currency"));
            paymentOrder.setOrderIdRef((String) orderJson.get("receipt"));
            paymentOrder.setRazorPayOrderId((String) orderJson.get("id"));
            log.info("razorPayOrder {}  " + paymentOrder.getRazorPayOrderId());
            return paymentOrder;
        } catch (RazorpayException e) {
            log.error("Exception for the order {} with message : ", orderId, e.getMessage());
            throw new ApiRuntimeException("Error occured in creating payment order");
        }
    }
}
