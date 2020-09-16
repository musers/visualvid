package com.ae.visuavid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public S3Client s3Client() {
        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(
            AwsBasicCredentials.create("AKIARHUCCYF37ZMSRMVY", "G/V7ju4MinT4h2LiFK/YsTvaBUIypYlnyrDFbt9F")
        );
        return S3Client.builder().credentialsProvider(credentialsProvider).region(Region.of("ap-south-1")).build();
    }
}
