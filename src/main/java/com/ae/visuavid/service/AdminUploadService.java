package com.ae.visuavid.service;

import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.domain.S3InfoEntity;
import com.ae.visuavid.repository.AdminUploadFormRepository;
import com.ae.visuavid.repository.S3InfoRepository;
import com.ae.visuavid.service.dto.AdminMediaDTO;
import com.ae.visuavid.service.mapper.AdminMediaMapper;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AdminUploadService {
    private static Logger log = LoggerFactory.getLogger(AdminUploadService.class);

    @Autowired
    AdminMediaMapper mediaMapper;

    @Autowired
    AdminUploadFormRepository adminUploadFormRepository;

    @Autowired
    S3InfoRepository s3InfoRepository;

    public void saveUploadForm(AdminMediaDTO mediaDto) {
        try {
            log.info("saving adminUploadForm : projectUploadForm");
            AdminMediaEntity media = mediaMapper.toEntity(mediaDto);
            adminUploadFormRepository.save(media);
            log.info("successfully saved adminUploadForm");
        } catch (Exception e) {
            log.error("error while saving project-upload-form : {} ", e);
            throw new ApiRuntimeException(e.getMessage());
        }
    }
}
