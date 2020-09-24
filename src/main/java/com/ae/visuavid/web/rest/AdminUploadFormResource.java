package com.ae.visuavid.web.rest;

import com.ae.visuavid.service.AdminUploadService;
import com.ae.visuavid.service.dto.AdminMediaDto;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/")
public class AdminUploadFormResource {
    @Autowired
    AdminUploadService adminUploadService;

    @PostMapping("/project-upload")
    public ResponseEntity<Void> saveUploadForm(@RequestBody @Valid AdminMediaDto mediaDto) {
        adminUploadService.saveUploadForm(mediaDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
