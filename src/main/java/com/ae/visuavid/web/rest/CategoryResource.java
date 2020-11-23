package com.ae.visuavid.web.rest;

import com.ae.visuavid.service.CategoryService;
import com.ae.visuavid.service.dto.CategoryDTO;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryResource {
    private static final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> findAll() {
        log.info("fetching categories");
        return new ResponseEntity<>(categoryService.findCategories(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto) {
        log.info("saving categories");
        return new ResponseEntity<>(categoryService.save(dto), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO dto) {
        log.info("updating categories");
        if (dto.getId() == null) {
            throw new ApiRuntimeException("Id cannot be null");
        }
        return new ResponseEntity<>(categoryService.save(dto), HttpStatus.OK);
    }
}
