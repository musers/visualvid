package com.ae.visuavid.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ProfileUtils {
    @Autowired
    private Environment env;

    public boolean isProfileLocal() {
        String[] activeProfiles = env.getActiveProfiles();
        if (activeProfiles.length > 0) {
            return Arrays.stream(activeProfiles).anyMatch(p -> p.equalsIgnoreCase("local"));
        } else {
            return true;
        }
    }
}
