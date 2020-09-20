package com.ae.visuavid.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Visualvid.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private S3 s3 = new S3();

    public S3 getS3() {
        return s3;
    }

    public static class S3 {
        private String bucketName;

        public String getBucketName() {
            return bucketName;
        }

        public void setBucketName(String bucketName) {
            this.bucketName = bucketName;
        }
    }
}
