package com.ae.visuavid.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class ApplicationConfiguration {
    @Autowired
    private ApplicationProperties applicationProperties;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder().build();
    }

    @Bean
    public List<String> s3KeyList() {
        return new ArrayList<String>();
    }

    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
        return new RazorpayClient(applicationProperties.getRazorpay().getKey(), applicationProperties.getRazorpay().getSecret());
    }
}
