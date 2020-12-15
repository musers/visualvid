package com.ae.visuavid.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class NumberUtility {

    public Long convertToLongNonNullable(String str) {
        Long l = null;
        if (StringUtils.isNotBlank(str)) {
            try {
                l = Long.parseLong(str.trim());
            } catch (NumberFormatException nfe) {
                // silent
            }
        }
        return convertToLongNonNullable(l);
    }

    public Long convertToLongNonNullable(Long l) {
        return l == null ? 0l : l;
    }

    public BigDecimal add(BigDecimal... arr) {
        BigDecimal sum = null;
        if (arr.length > 0) {
            for (BigDecimal n : arr) {
                if (n != null) {
                    sum = sum == null ? n : sum.add(n);
                }
            }
        }
        return sum;
    }

    public BigDecimal subtract(BigDecimal n1, BigDecimal n2) {
        BigDecimal val;
        if (n1 == null) {
            val = n2 == null ? null : BigDecimal.ZERO.subtract(n2);
        } else {
            val = n2 == null ? n1 : n1.subtract(n2);
        }
        return val;
    }

    public BigDecimal percentage(BigDecimal n, Integer gst) {
        BigDecimal val = BigDecimal.ZERO;
        if (gst == null) {
            gst = 0;
        }
        if (n != null) {
            val = n.multiply(BigDecimal.valueOf(gst)).divide(BigDecimal.valueOf(100));
        }
        return val;
    }
}
