package com.ae.visuavid.web.rest;

import com.ae.visuavid.service.CouponInfoService;
import com.ae.visuavid.service.dto.CouponInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coupon")
public class CouponInfoResource {

    @Autowired
    private CouponInfoService couponInfoService;

    @GetMapping("/validate/{couponCode}")
    public ResponseEntity<CouponInfoDTO> validateCoupon(@PathVariable("couponCode") String couponCode) {
        return ResponseEntity.ok(couponInfoService.validateCoupon(couponCode));
    }
}
