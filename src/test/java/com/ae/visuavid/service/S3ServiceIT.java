package com.ae.visuavid.service;

import com.ae.visuavid.VisualvidApp;
import com.ae.visuavid.client.S3Service;
import com.ae.visuavid.service.dto.S3InfoDTO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Integration tests for {@link UserService}.
 */
@SpringBootTest(classes = VisualvidApp.class)
@ActiveProfiles(profiles = "default")
public class S3ServiceIT {
    @Autowired
    private S3Service s3Service;

    @Test
    public void uploadFile() throws IOException {
        String fileName = "angular.json";
        String path = "C:\\Users\\baddanki\\code\\pden\\visualvid\\angular.json";
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        S3InfoDTO obj = s3Service.uploadAdminFile(bytes, fileName);
    }
}
