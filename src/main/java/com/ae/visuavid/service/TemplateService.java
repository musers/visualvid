package com.ae.visuavid.service;

import com.ae.visuavid.domain.*;
import com.ae.visuavid.repository.TemplateRepository;
import com.ae.visuavid.service.dto.TemplateDTO;
import com.ae.visuavid.service.mapper.TemplateMapper;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
public class TemplateService {
    private final Logger log = LoggerFactory.getLogger(TemplateService.class);

    @Autowired
    private TemplateMapper templateMapper;

    @Autowired
    private TemplateRepository templateRepository;

    public TemplateService() {}

    public void saveTemplate(TemplateDTO templateDTO) {
        log.info("saving user-template");
        try {
            TemplateEntity template = templateMapper.toEntity(templateDTO);
            updateSlide(template);
            templateRepository.save(template);
            log.info("successfully saved user-template");
        } catch (Exception e) {
            log.error("error while saving user-template : {} ", e);
            throw new ApiRuntimeException(e.getMessage());
        }
    }

    private void updateSlide(TemplateEntity template) {
        List<TemplateSlideEntity> slides = template.getUserSlides();
        if (!CollectionUtils.isEmpty(slides)) {
            for (TemplateSlideEntity slide : slides) {
                slide.setTemplate(template);
                slide.setSlideOrder(slide.getSlideOrder());
                updateSlideItem(slide);
            }
        }
    }

    private void updateSlideItem(TemplateSlideEntity slide) {
        List<TemplateSlideItemEntity> slideItems = slide.getUserSlideItems();
        if (!CollectionUtils.isEmpty(slideItems)) {
            for (TemplateSlideItemEntity slideItem : slideItems) {
                slideItem.setTemplateSlide(slide);
                slideItem.setItemOrder(slideItem.getItemOrder());
            }
        }
    }

    public TemplateDTO getTemplate(UUID id) {
        log.info("template-service: Getting template for ID: {} ", id);
        Optional<TemplateEntity> template = templateRepository.findById(id);
        if (template.isPresent()) {
            return templateMapper.toDto(template.get());
        }
        throw new ApiRuntimeException("No template found for ID: " + id);
    }

    public List<TemplateDTO> getTemplates() {
        log.info("template-service: Getting all templates");
        try {
            List<TemplateEntity> templates = templateRepository.findAll();
            return templateMapper.toDtos(templates);
        } catch (Exception e) {
            log.error("error while getting templates");
            throw new ApiRuntimeException("error while getting templates");
        }
    }
}
