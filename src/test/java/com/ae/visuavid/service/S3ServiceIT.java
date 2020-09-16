package com.ae.visuavid.service;

import com.ae.visuavid.VisualvidApp;
import com.ae.visuavid.client.S3Service;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration tests for {@link UserService}.
 */
@SpringBootTest(classes = VisualvidApp.class)
public class S3ServiceIT {
    @Autowired
    private S3Service s3Service;

    @Test
    public void readFile() throws IOException {
        s3Service.readFile();
    }
}
