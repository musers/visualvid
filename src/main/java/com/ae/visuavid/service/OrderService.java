package com.ae.visuavid.service;

import com.ae.visuavid.client.S3Service;
import com.ae.visuavid.repository.OrderRepository;
import com.ae.visuavid.service.dto.OrderDTO;
import com.ae.visuavid.service.mapper.OrderMapper;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {
    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    protected OrderMapper templateMapper;

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected S3Service s3Service;

    public OrderService() {
    }

    public OrderDTO create(@NotNull  OrderDTO orderDTO) {
        UUID adminMediaId = orderDTO.getAdminMediaId();
        if(adminMediaId == null){
            throw new ApiRuntimeException("adminMediaId cannot be empty");
        }
        
        // TODO
        return null;
    }

    public OrderDTO findOrderById(UUID fromString) {
        // TODO
        return null;
    }

    public List<OrderDTO> findAll() {
        // TODO
        return null;
    }

//    public void saveTemplate(TemplateDTO templateDTO) {
//        log.info("saving user-template");
//        try {
//            List<String> s3KeyList = new ArrayList<>();
//            TemplateEntity template = templateMapper.toEntity(templateDTO);
//            updateSlide(template, s3KeyList);
//            templateRepository.save(template);
//            log.info("successfully saved user-template {} : ", template.getId());
//            s3Service.updateS3InfoStatus(s3KeyList, S3MediaStatusType.COMPLETED.name());
//            s3KeyList.clear();
//        } catch (Exception e) {
//            log.error("error while saving user-template : {} ", e);
//            throw new ApiRuntimeException(e.getMessage());
//        }
//    }

//    private void updateSlide(TemplateEntity template, List<String> s3KeyList) {
//        List<TemplateSlideEntity> slides = template.getUserSlides();
//        if (!CollectionUtils.isEmpty(slides)) {
//            for (TemplateSlideEntity slide : slides) {
//                slide.setTemplate(template);
//                slide.setSlideOrder(slide.getSlideOrder());
//                if (!StringUtils.isEmpty(slide.getScreenShotS3Key())) {
//                    s3KeyList.add(slide.getScreenShotS3Key());
//                }
//                updateSlideItem(slide, s3KeyList);
//            }
//        }
//    }

//    private void updateSlideItem(TemplateSlideEntity slide, List<String> s3KeyList) {
//        List<TemplateSlideItemEntity> slideItems = slide.getUserSlideItems();
//        if (!CollectionUtils.isEmpty(slideItems)) {
//            for (TemplateSlideItemEntity slideItem : slideItems) {
//                slideItem.setTemplateSlide(slide);
//                slideItem.setItemOrder(slideItem.getItemOrder());
//                if (!StringUtils.isEmpty(slideItem.getS3Key())) {
//                    s3KeyList.add(slideItem.getS3Key());
//                }
//            }
//        }
//    }

//    public TemplateDTO getTemplate(UUID id) {
//        log.info("template-service: Getting template for ID: {} ", id);
//        Optional<TemplateEntity> template = templateRepository.findById(id);
//        if (template.isPresent()) {
//            return templateMapper.toDto(template.get());
//        }
//        throw new ApiRuntimeException("No template found for ID: " + id);
//    }

//    public List<TemplateDTO> getTemplates() {
//        log.info("template-service: Getting all templates");
//        try {
//            List<TemplateEntity> templates = templateRepository.findAll();
//            return templateMapper.toDtos(templates);
//        } catch (Exception e) {
//            log.error("error while getting templates");
//            throw new ApiRuntimeException("error while getting templates");
//        }
//    }
}
