package com.ae.visuavid.web.rest;

import com.ae.visuavid.service.CategoryService;
import com.ae.visuavid.service.dto.CategoryDTO;
import com.ae.visuavid.service.dto.SubCategoryDTO;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
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

    @PostMapping("/sub-category")
    public ResponseEntity<SubCategoryDTO> create(@RequestBody SubCategoryDTO dto) {
        log.info("saving sub-categories");
        return new ResponseEntity<SubCategoryDTO>(categoryService.saveSubCategory(dto), HttpStatus.OK);
    }

    @PutMapping("/sub-category")
    public ResponseEntity<SubCategoryDTO> update(@RequestBody SubCategoryDTO dto) {
        log.info("updating sub-categories");
        if (dto.getId() == null) {
            throw new ApiRuntimeException("sub-categoryId cannot be null");
        }
        return new ResponseEntity<SubCategoryDTO>(categoryService.saveSubCategory(dto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable("id") String id) {
        return new ResponseEntity<CategoryDTO>(categoryService.findCategoryById(UUID.fromString(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/sub-categories")
    public ResponseEntity<List<SubCategoryDTO>> findSubCategoriesByCategoryId(@PathVariable("id") String categoryId) {
        return new ResponseEntity<List<SubCategoryDTO>>(categoryService.findSubCategoriesByCategoryId(categoryId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") String categoryUUID) {
        categoryService.deleteCategory(categoryUUID);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/sub-category/{id}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable("id") String subCategoryUUID) {
        categoryService.deleteSubCategory(subCategoryUUID);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
