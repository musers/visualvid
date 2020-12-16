package com.ae.visuavid.web.rest;

import com.ae.visuavid.service.RazorPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentResource {
    private static final Logger log = LoggerFactory.getLogger(PaymentResource.class);

    @Autowired
    private RazorPayService razorPayService;

    @GetMapping("/{orderId}")
    public ResponseEntity<?> createPaymentOrder(@PathVariable("orderId") String orderId, @RequestParam(value = "action", required = true) String action) {
        log.info("createPaymentOrder action: {} ", action);
//        if (action.equals("createpaymentorder")) {
//            return new ResponseEntity<>(razorPayService.createPaymentOrder(orderId), HttpStatus.OK);
//        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
