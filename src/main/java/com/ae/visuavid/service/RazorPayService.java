package com.ae.visuavid.service;

import com.ae.visuavid.VisualvidApp;
import com.ae.visuavid.config.ApplicationProperties;
import com.ae.visuavid.service.dto.OrderDTO;
import com.ae.visuavid.service.dto.PaymentOrderDTO;
import com.ae.visuavid.utils.NumberUtility;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.security.SignatureException;
import java.util.List;
import java.util.UUID;

/**
 * Look for https://razorpay.com/docs/payment-gateway/web-integration/custom/#step-5-verify-the-signature more details
 */
@Service
public class RazorPayService {
    private static final Logger log = LoggerFactory.getLogger(VisualvidApp.class);

    @Autowired
    private NumberUtility numberUtility;

    private RazorpayClient razorpayClient;

    @Autowired
    private ApplicationProperties applicationProperties;

    public RazorPayService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }


    public PaymentOrderDTO createPaymentOrder(List<OrderDTO> orders) {
        String paymentOrderId = UUID.randomUUID().toString();
        String currencyCode = null;
        BigDecimal amount = null;
        for (OrderDTO order : orders) {
            currencyCode = order.getCurrencyCode();
            amount = numberUtility.add(amount, order.getTotalAmount());
        }
        PaymentOrderDTO paymentOrder = createPaymentOrder(amount, currencyCode, paymentOrderId);
        paymentOrder.setRazorPayKey(applicationProperties.getRazorpay().getKey());
        return paymentOrder;
    }

    /**
     * "amount" -> {Integer@3739}
     * "amount_paid" -> {Integer@3741}
     * "notes" -> {JSONArray@3743} "[]"
     * "created_at" -> {Integer@3745}
     * "amount_due" -> {Integer@3747}
     * "currency" -> "INR"
     * "receipt" -> ""
     * "id" -> "order_G899w0WgKl7zQA"
     * "entity" -> "order"
     * "offer_id" -> {JSONObject$Null@3757} "null"
     * "status" -> "created"
     * "attempts" -> {Integer@3741} 0
     *
     * @param amount
     * @param currency
     * @param orderId
     * @return
     */
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
            paymentOrder.setPaymentOrderId((String) orderJson.get("receipt"));
            paymentOrder.setRazorPayOrderId((String) orderJson.get("id"));
            log.info("razorPayOrder {}  " + paymentOrder.getRazorPayOrderId());
            return paymentOrder;
        } catch (RazorpayException e) {
            log.error("Exception for the order {} with message : ", orderId, e.getMessage());
            throw new ApiRuntimeException("Error occured in creating payment order");
        }
    }


    /**
     * Validates razorpay signature
     *
     * @param razorPayOrderId
     * @param razorPayPaymentId
     * @param razorPaySignature
     */
    public void validateRazorPayResponse(String razorPayOrderId, String razorPayPaymentId, String razorPaySignature) {
        String data = new StringBuilder(razorPayOrderId).append("|").append(razorPayPaymentId).toString();
        try {
            String generatedSignature = RazorPaySignatureValidator.calculateRFC2104HMAC(data, applicationProperties.getRazorpay().getSecret());
            if (!razorPaySignature.endsWith(generatedSignature)) {
                throw new ApiRuntimeException("Error occured while validating signature: " + razorPayOrderId);
            }
            log.info("Razor pay signature validation has been successfull: " + razorPayOrderId);
        } catch (SignatureException e) {
            throw new ApiRuntimeException("Error occured while validating signature: " + razorPayOrderId);
        }
    }
}
