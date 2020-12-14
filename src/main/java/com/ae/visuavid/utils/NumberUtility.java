package com.ae.visuavid.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
}
