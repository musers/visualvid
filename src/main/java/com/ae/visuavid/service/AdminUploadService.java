package com.ae.visuavid.service;

import com.ae.visuavid.client.S3Service;
import com.ae.visuavid.domain.AdminMediaEntity;
import com.ae.visuavid.domain.MediaSlideEntity;
import com.ae.visuavid.domain.SlideItemEntity;
import com.ae.visuavid.enumeration.S3MediaStatusType;
import com.ae.visuavid.repository.AdminUploadFormRepository;
import com.ae.visuavid.repository.MediaSlideRepository;
import com.ae.visuavid.repository.SlideItemRepository;
import com.ae.visuavid.service.dto.AdminMediaDTO;
import com.ae.visuavid.service.dto.MediaSlideDTO;
import com.ae.visuavid.service.dto.SlideItemDTO;
import com.ae.visuavid.service.mapper.AdminMediaMapper;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdminUploadService {
    private static final Logger log = LoggerFactory.getLogger(AdminUploadService.class);

    @Autowired
    private AdminMediaMapper mediaMapper;

    @Autowired
    private AdminUploadFormRepository adminUploadFormRepository;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private MediaSlideRepository mediaSlideRepository;

    @Autowired
    private SlideItemRepository slideItemRepository;

    @Autowired
    private CategoryService categoryService;

    public void saveUploadForm(AdminMediaDTO mediaDto) {
        try {
            log.info("saving adminUploadForm : projectUploadForm");
            List<String> s3KeyList = new ArrayList<>();
            AdminMediaEntity media = mediaMapper.toEntity(mediaDto);
            setMedia(media, s3KeyList);
            adminUploadFormRepository.save(media);
            log.info("saved adminUploadForm : projectUploadForm :", media.getId());
            updateS3InfoStatus(media, s3KeyList);
        } catch (Exception e) {
            log.error("error while saving project-upload-form : {} ", e);
            throw new ApiRuntimeException(e.getMessage());
        }
    }

    public void updateUploadForm(AdminMediaDTO mediaDTO) {
        try {
            log.info("updating adminUploadForm : projectUploadForm");
            List<String> s3KeyList = new ArrayList<>();
            updateMedia(mediaDTO, s3KeyList);
        } catch (Exception e) {
            log.error("error while saving project-upload-form : {} ", e);
            throw new ApiRuntimeException(e.getMessage());
        }
    }

    private void updateMedia(AdminMediaDTO mediaDTO, List<String> s3KeyList) {
        Optional<AdminMediaEntity> mediaEntityOptional = adminUploadFormRepository.findById(mediaDTO.getId());
        List<MediaSlideEntity> slideEntitiesToBeDeleted = new ArrayList<>();
        List<SlideItemEntity> slideItemEntitiesToBeDeleted = new ArrayList<>();
        if (mediaEntityOptional.isPresent()) {
            collectSlideAndItemsToDelete(slideEntitiesToBeDeleted, slideItemEntitiesToBeDeleted, mediaEntityOptional.get(), mediaDTO);
            collectS3KeysToDelete(mediaDTO, s3KeyList, mediaEntityOptional.get());
            AdminMediaEntity media = mediaMapper.toEntity(mediaDTO);
            setMedia(media, s3KeyList);
            adminUploadFormRepository.save(media);
            log.info("updated adminUploadForm : projectUploadForm :", media.getId());
            deleteSlideItems(slideItemEntitiesToBeDeleted);
            deleteSlides(slideEntitiesToBeDeleted);
            updateS3InfoStatus(media, s3KeyList);
        }
    }

    private void deleteSlideItems(List<SlideItemEntity> slideItemEntities) {
        log.info("Deleting slideItems:  {}", slideItemEntities);
        slideItemEntities.forEach(slideItemEntity -> slideItemRepository.delete(slideItemEntity));
        log.info("deleted slideItems: {} ", slideItemEntities);
    }

    private void deleteSlides(List<MediaSlideEntity> slideEntities) {
        log.info("Deleting slides:  {}", slideEntities);
        slideEntities.forEach(slideEntity -> mediaSlideRepository.delete(slideEntity));
        log.info("Deleted slides: {} ", slideEntities);
    }

    public void collectSlideAndItemsToDelete(
        List<MediaSlideEntity> slideEntitiesToBeDeleted,
        List<SlideItemEntity> slideItemEntitiesToBeDeleted,
        AdminMediaEntity mediaEntity,
        AdminMediaDTO mediaDTO
    ) {
        Map<UUID, MediaSlideDTO> slidesDTOMap = mediaDTO
            .getSlides()
            .stream()
            .collect(Collectors.toMap(MediaSlideDTO::getId, adminMedia -> adminMedia));
        mediaEntity
            .getSlides()
            .forEach(
                slideEntity -> {
                    if (!slidesDTOMap.containsKey(slideEntity.getId())) {
                        slideEntitiesToBeDeleted.add(slideEntity);
                    } else {
                        collectSlideItemsToDelete(slideItemEntitiesToBeDeleted, slidesDTOMap, slideEntity);
                    }
                }
            );
    }

    private void collectS3KeysToDelete(AdminMediaDTO mediaDTO, List<String> s3KeyList, AdminMediaEntity mediaEntity) {
        // if s3Key is changed at UI, add the s3Key from table to s3KeyList.
        if (!mediaEntity.getPreviewVideoS3Key().equalsIgnoreCase(mediaDTO.getPreviewVideoS3Key())) {
            s3KeyList.add(mediaEntity.getPreviewVideoS3Key());
        }
        if (!mediaEntity.getThumbNailS3Key().equalsIgnoreCase(mediaDTO.getPreviewVideoS3Key())) {
            s3KeyList.add(mediaEntity.getPreviewVideoS3Key());
        }
    }

    private void collectSlideItemsToDelete(
        List<SlideItemEntity> slideItemEntitiesToBeDeleted,
        Map<UUID, MediaSlideDTO> slidesDTOMap,
        MediaSlideEntity slideEntity
    ) {
        MediaSlideDTO slideDTO = slidesDTOMap.get(slideEntity.getId());
        Map<UUID, SlideItemDTO> slideItemDTOMap = slideDTO
            .getSlideItems()
            .stream()
            .collect(Collectors.toMap(SlideItemDTO::getId, slideItemDTO -> slideItemDTO));
        slideEntity
            .getSlideItems()
            .forEach(
                slideItemEntity -> {
                    if (!slideItemDTOMap.containsKey(slideItemEntity.getId())) {
                        slideItemEntitiesToBeDeleted.add(slideItemEntity);
                    }
                }
            );
    }

    private void setMedia(AdminMediaEntity media, List<String> s3KeyList) {
        List<MediaSlideEntity> slides = media.getSlides();
        if (!CollectionUtils.isEmpty(slides)) {
            int order = 0;
            for (MediaSlideEntity slide : slides) {
                slide.setMedia(media);
                slide.setSlideOrder(order++);
                s3KeyList.add(slide.getScreenShotS3Key());
                setSlide(slide);
            }
        }
    }

    private void setSlide(MediaSlideEntity slide) {
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
        Optional<AdminMediaEntity> mediaEntityOptional = adminUploadFormRepository.findById(UUID.fromString(id));
        if (mediaEntityOptional.isPresent()) {
            AdminMediaDTO mediaDTO = mediaMapper.toDto(mediaEntityOptional.get());
            mediaDTO.setCategoryName(categoryService.getCategoryName(mediaDTO.getCategoryId()));
            mediaDTO.setSubCategoryName(categoryService.getSubCategoryName(mediaDTO.getSubCategoryId()));
            return mediaDTO;
        }
        throw new ApiRuntimeException("could not find adminMedia for Id {} " + id);
    }

    private void updateS3InfoStatus(AdminMediaEntity media, List<String> s3KeyList) {
        s3KeyList.add(media.getPreviewVideoS3Key());
        s3KeyList.add(media.getThumbNailS3Key());
        s3Service.updateS3InfoStatus(s3KeyList, S3MediaStatusType.COMPLETED.name());
    }

    public List<AdminMediaDTO> findByCategory(String categoryId) {
        List<AdminMediaEntity> mediaEntities = adminUploadFormRepository.findByCategoryId(categoryId);
        return mediaMapper.toDtos(mediaEntities);
    }

    public Page<AdminMediaEntity> findByCategory(Pageable pageable, String categoryId) {
        Page<AdminMediaEntity> mediaEntities = adminUploadFormRepository.findByCategoryId(pageable, categoryId);
        return mediaEntities;
    }

    public Page<AdminMediaEntity> searchByMediaName(Pageable pageable, String mediaName) {
        Page<AdminMediaEntity> mediaEntities = adminUploadFormRepository.findByNameIgnoreCaseContaining(pageable, mediaName);
        return mediaEntities;
    }

    public Page<AdminMediaEntity> findAllByPage(Pageable pageable) {
        Page<AdminMediaEntity> mediaEntities = adminUploadFormRepository.findAll(pageable);
        return mediaEntities;
    }
}
