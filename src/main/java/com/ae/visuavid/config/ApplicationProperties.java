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
    private Integer gst;
    private Razorpay razorpay = new Razorpay();

    public S3 getS3() {
        return s3;
    }

    public void setS3(S3 s3) {
        this.s3 = s3;
    }

    public Razorpay getRazorpay() {
        return razorpay;
    }

    public void setRazorpay(Razorpay razorpay) {
        this.razorpay = razorpay;
    }

    public Integer getGst() {
        return gst;
    }

    public void setGst(Integer gst) {
        this.gst = gst;
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

    public static class Razorpay {
        private String key;
        private String secret;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }
}
