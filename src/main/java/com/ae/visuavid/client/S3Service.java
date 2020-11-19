package com.ae.visuavid.client;

import com.ae.visuavid.config.ApplicationProperties;
import com.ae.visuavid.domain.S3InfoEntity;
import com.ae.visuavid.enumeration.S3MediaStatusType;
import com.ae.visuavid.repository.S3InfoRepository;
import com.ae.visuavid.service.dto.S3InfoDTO;
import com.ae.visuavid.service.mapper.S3InfoMapper;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(S3Service.class);

    private static final String ADMIN_UPLOADS_PATH = "adminuploads";

    @Autowired
    protected S3Client s3Client;

    @Autowired
    protected ApplicationProperties applicationProperties;

    @Autowired
    protected S3InfoRepository s3InfoRepository;

    @Autowired
    protected S3InfoMapper s3InfoMapper;

    public S3InfoDTO uploadAdminFile(byte[] bytes, String fileName) throws IOException {
        log.info("Uploading media fileName {} to s3 ", fileName);
        S3InfoDTO s3InfoDTO = uploadFile(bytes, ADMIN_UPLOADS_PATH, fileName);
        s3InfoDTO.setSize(bytes.length);
        log.info("Uploaded media  fileName {} : s3Key {} :  to s3 ", fileName, s3InfoDTO.getS3Key());
        saveS3Info(s3InfoDTO, S3MediaStatusType.IN_PROGRESS.name());
        return s3InfoDTO;
    }

    public void saveS3Info(S3InfoDTO s3InfoDTO, String status) {
        log.info("Saving s3Info with s3key: {} ", s3InfoDTO.getS3Key());
        S3InfoEntity s3InfoEntity = s3InfoMapper.toEntity(s3InfoDTO);
        s3InfoEntity.setStatus(status);
        s3InfoRepository.save(s3InfoEntity);
        log.info("Saved s3Info with s3key: {} ", s3InfoEntity.getS3Key());
    }

    public void updateS3InfoStatus(List<String> s3Keys, String status) {
        log.info("Updating  s3Info with s3keys: {} ", s3Keys);
        List<S3InfoEntity> s3InfoEntities = s3InfoRepository.findByS3KeyIn(s3Keys);
        s3InfoEntities.forEach(s3Info -> s3Info.setStatus(S3MediaStatusType.COMPLETED.name()));
        s3InfoRepository.saveAll(s3InfoEntities);
        log.info("Updated s3Info with s3keys: {} ", s3Keys);
    }

    private S3InfoDTO uploadFile(byte[] bytes, String path, String fileName) throws IOException {
        String key = generateKey(path, fileName);
        s3Client.putObject(
            PutObjectRequest.builder().bucket(getBucketName()).key(key).acl(ObjectCannedACL.PUBLIC_READ).build(),
            RequestBody.fromBytes(bytes)
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

    private S3InfoDTO constructS3FileDTO(String fileName, String key, String url) {
        S3InfoDTO obj = new S3InfoDTO();
        obj.setName(fileName);
        obj.setS3Key(key);
        obj.setUrl(url);
        return obj;
    }

    private String getBucketName() {
        return applicationProperties.getS3().getBucketName();
    }
}
