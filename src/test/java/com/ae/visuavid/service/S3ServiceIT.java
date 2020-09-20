package com.ae.visuavid.service;

import com.ae.visuavid.VisualvidApp;
import com.ae.visuavid.client.S3Service;
import com.ae.visuavid.service.dto.S3FileDTO;
import java.io.File;
import java.io.IOException;
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
        String fileName = "test $$&$.txt";
        File file = new File("C:\\Users\\baddanki\\code\\pden\\visualvid\\README.md");
        S3FileDTO obj = s3Service.uploadAdminFile(file, fileName);
    }
}
