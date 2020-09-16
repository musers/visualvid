package com.ae.visuavid.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Component
public class S3Service {
    @Autowired
    private S3Client s3Client;

    public void readFile() throws IOException {
        GetObjectRequest request = GetObjectRequest.builder().bucket("visualvid").key("pvid/Preview video 01.mp4").build();
        ResponseBytes<GetObjectResponse> objectResponse = s3Client.getObjectAsBytes(request);
        byte[] bytes = objectResponse.asByteArray();

        File file = new File("File_Name");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            /* Handle Exception */
        }
    }
}
