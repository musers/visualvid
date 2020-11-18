package com.ae.visuavid.web.rest;

import com.ae.visuavid.service.AdminUploadService;
import com.ae.visuavid.service.dto.AdminMediaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/project-upload")
    public ResponseEntity<List<AdminMediaDTO>> getUploads() {
        return ResponseEntity.ok(adminUploadService.getAdminUploads());
    }
    @GetMapping("/project-upload/{id}")
    public ResponseEntity<AdminMediaDTO> getAdminUpload(@PathVariable("id") String id) {
        return ResponseEntity.ok(adminUploadService.getAdminUpload(id));
    }
}
