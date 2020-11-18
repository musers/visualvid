package com.ae.visuavid.web.rest;

import com.ae.visuavid.client.S3Service;
import com.ae.visuavid.service.dto.S3InfoDTO;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FileResource {
    private final Logger log = LoggerFactory.getLogger(FileResource.class);

    @Autowired
    private S3Service s3Service;

    @PostMapping(value = "/adminupload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<S3InfoDTO> uploadFile(@RequestParam MultipartFile file) throws IOException {
        S3InfoDTO fileDTO = s3Service.uploadAdminFile(file.getBytes(), file.getOriginalFilename());
        log.info(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));
        return ResponseEntity.ok(fileDTO);
    }
}
