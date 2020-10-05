package com.ae.visuavid.service;

import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.domain.MediaSlideEntity;
import com.ae.visuavid.domain.SlideItemEntity;
import com.ae.visuavid.repository.AdminUploadFormRepository;
import com.ae.visuavid.repository.S3InfoRepository;
import com.ae.visuavid.service.dto.AdminMediaDTO;
import com.ae.visuavid.service.mapper.AdminMediaMapper;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
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
            updateMedia(media);
            adminUploadFormRepository.save(media);
            log.info("successfully saved adminUploadForm");
        } catch (Exception e) {
            log.error("error while saving project-upload-form : {} ", e);
            throw new ApiRuntimeException(e.getMessage());
        }
    }

    private void updateMedia(AdminMediaEntity media) {
        List<MediaSlideEntity> slides = media.getSlides();
        if (!CollectionUtils.isEmpty(slides)) {
            int order = 0;
            for (MediaSlideEntity slide : slides) {
                slide.setMedia(media);
                slide.setSlideOrder(order++);
                updateSlide(slide);
            }
        }
    }

    private void updateSlide(MediaSlideEntity slide) {
        List<SlideItemEntity> slideItems = slide.getSlideItems();
        if (!CollectionUtils.isEmpty(slideItems)) {
            int order = 0;
            for (SlideItemEntity slideItem : slideItems) {
                slideItem.setMediaSlide(slide);
                slideItem.setOrder(order++);
            }
        }
    }

    public List<AdminMediaDTO> getAdminUploads() {
        return mediaMapper.toDtosBasic(adminUploadFormRepository.findAll());
    }

    public AdminMediaDTO getAdminUpload(String id) {
        return mediaMapper.toDto(adminUploadFormRepository.findById(UUID.fromString(id)).get());
    }
}
