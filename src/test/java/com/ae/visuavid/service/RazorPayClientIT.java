package com.ae.visuavid.service;

import com.ae.visuavid.constants.Currency;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class RazorPayClientIT {

    @Test
    public void createPaymentOrderTest() throws RazorpayException {
        RazorpayClient rclient = new RazorpayClient("rzp_test_HzOLLgHiAjScZp", "j1duzeYoUxfGTLVX39Q7pIBl");
        RazorPayService rService = new RazorPayService(rclient);
        BigDecimal amount = new BigDecimal(500);
        rService.createPaymentOrder(amount, Currency.INR.name(), "test2");
    }
}
