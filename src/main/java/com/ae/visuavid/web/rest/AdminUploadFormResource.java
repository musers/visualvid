package com.ae.visuavid.web.rest;

import com.ae.visuavid.service.AdminUploadService;
import com.ae.visuavid.service.dto.AdminMediaDTO;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/")
public class AdminUploadFormResource {
    @Autowired
    AdminUploadService adminUploadService;

    @PostMapping("/project-upload")
    public ResponseEntity<Void> saveUploadForm(@RequestBody @Valid AdminMediaDTO mediaDto) {
        adminUploadService.saveUploadForm(mediaDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PutMapping("/project-upload")
    public ResponseEntity<Void> updateUploadForm(@RequestBody @Valid AdminMediaDTO mediaDto) {
        if (mediaDto.getId() != null) {
            adminUploadService.updateUploadForm(mediaDto);
            return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
        }
        throw new ApiRuntimeException("ID cannot be NULL to update");
    }

    @GetMapping("/project-upload")
    public ResponseEntity<List<AdminMediaDTO>> getUploads() {
        return ResponseEntity.ok(adminUploadService.getAdminUploads());
    }

    @GetMapping("/project-upload/{id}")
    public ResponseEntity<AdminMediaDTO> getAdminUpload(@PathVariable("id") String id) {
        return ResponseEntity.ok(adminUploadService.getAdminUpload(id));
    }

    @GetMapping("project-upload/category/{id}")
    public ResponseEntity<List<AdminMediaDTO>> findMediasByCategory(@PathVariable("id") String categoryId) {
        return ResponseEntity.ok(adminUploadService.findByCategory(categoryId));
    }

    @GetMapping("project-upload/page/category/{id}")
    public ResponseEntity<Page> findMediasByCategory(Pageable pageable, @PathVariable("id") String categoryId) {
        return ResponseEntity.ok(adminUploadService.findByCategory(pageable, categoryId));
    }

    @GetMapping("project-upload/page/search/{media-name}")
    public ResponseEntity<Page> searchMediaByMediaName(Pageable pageable, @PathVariable("media-name") String mediaName) {
        return ResponseEntity.ok(adminUploadService.searchByMediaName(pageable, mediaName));
    }

    @GetMapping("/project-upload/page")
    public ResponseEntity<Page> findAllByPage(Pageable pageable) {
        return ResponseEntity.ok(adminUploadService.findAllByPage(pageable));
    }
}
