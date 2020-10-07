package com.ae.visuavid.web.rest;

import com.ae.visuavid.service.CategoryService;
import com.ae.visuavid.service.dto.CategoryDTO;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/categories")
public class CategoryResource {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> findAll() {
        log.info("fetching categories");
        return new ResponseEntity<>(categoryService.findCategories(), HttpStatus.OK);
    }
}
