package com.ae.visuavid.web.rest;

import com.ae.visuavid.service.TemplateService;
import com.ae.visuavid.service.dto.TemplateDTO;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TemplateResource {
    private Logger log = LoggerFactory.getLogger(TemplateResource.class);

    @Autowired
    TemplateService templateService;

    @PostMapping("/template")
    public ResponseEntity<Void> saveTemplate(@RequestBody @Valid TemplateDTO templateDTO) {
        log.info("template-resource: saving user-template");
        templateService.saveTemplate(templateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/template")
    public ResponseEntity<Void> updateTemplate(@RequestBody @Valid TemplateDTO templateDTO) {
        log.info("template-resource: saving user-template");
        if (templateDTO.getId() == null) {
            throw new ApiRuntimeException("ID cannot be null to update template");
        }
        templateService.saveTemplate(templateDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/template/{id}")
    public ResponseEntity<TemplateDTO> getTemplate(@PathVariable("id") String id) {
        log.info("template-resource: getting user-template for ID: {}", id);
        if (StringUtils.isEmpty(id)) {
            throw new ApiRuntimeException("ID cannot be null to get template");
        }
        return ResponseEntity.ok(templateService.getTemplate(UUID.fromString(id)));
    }

    @GetMapping("/templates")
    public ResponseEntity<List<TemplateDTO>> getTemplates() {
        log.info("template-resource: saving user-template");
        return ResponseEntity.ok(templateService.getTemplates());
    }
}
