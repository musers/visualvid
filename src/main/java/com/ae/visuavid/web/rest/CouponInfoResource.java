package com.ae.visuavid.web.rest;

import com.ae.visuavid.service.CouponInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coupon")
public class CouponInfoResource {

    @Autowired
    private CouponInfoService couponInfoService;

}
