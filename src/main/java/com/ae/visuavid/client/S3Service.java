package com.ae.visuavid.client;

import com.ae.visuavid.config.ApplicationProperties;
import com.ae.visuavid.service.dto.S3FileDTO;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3Service {
    private static final String ADMIN_UPLOADS_PATH = "adminuploads";

    @Autowired
    private S3Client s3Client;

    @Autowired
    private ApplicationProperties applicationProperties;

    public S3FileDTO uploadAdminFile(File file, String fileName) throws IOException {
        return uploadFile(file, ADMIN_UPLOADS_PATH, fileName);
    }

    private S3FileDTO uploadFile(File file, String path, String fileName) throws IOException {
        String key = generateKey(path, fileName);
        s3Client.putObject(
            PutObjectRequest.builder().bucket(getBucketName()).key(key).acl(ObjectCannedACL.PUBLIC_READ).build(),
            RequestBody.fromFile(file)
        );
        GetUrlRequest request = GetUrlRequest.builder().bucket(getBucketName()).key(key).build();
        String url = s3Client.utilities().getUrl(request).toExternalForm();
        return constructS3FileDTO(fileName, key, url);
    }

    private String generateKey(String path, String fileName) {
        String extn = StringUtils.getFilenameExtension(fileName);
        String filenameWithoutExtn = StringUtils.stripFilenameExtension(fileName);
        return new StringBuilder(path)
            .append("/")
            .append(UUID.randomUUID().toString())
            .append("_")
            .append(filenameWithoutExtn.replaceAll("\\W+", "_"))
            .append(".")
            .append(extn)
            .toString();
    }

    private S3FileDTO constructS3FileDTO(String fileName, String key, String url) {
        S3FileDTO obj = new S3FileDTO();
        obj.setFileName(fileName);
        obj.setKey(key);
        obj.setUrl(url);
        return obj;
    }

    private String getBucketName() {
        return applicationProperties.getS3().getBucketName();
    }
}
