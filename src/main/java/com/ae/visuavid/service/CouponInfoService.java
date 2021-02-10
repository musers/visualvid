package com.ae.visuavid.service;

import com.ae.visuavid.repository.CouponInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponInfoService {

    @Autowired
    private CouponInfoRepository couponInfoRepository;
}
