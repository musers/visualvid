package com.ae.visuavid.web.rest;

import com.ae.visuavid.service.CategoryService;
import com.ae.visuavid.service.dto.CategoryDTO;
import com.ae.visuavid.service.dto.CategoryNodeDTO;
import com.ae.visuavid.service.dto.SubCategoryDTO;
import com.ae.visuavid.web.rest.errors.ApiRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
public class CategoryResource {
    private static final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    @Autowired
    CategoryService categoryService;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") String categoryUUID) {
        categoryService.deleteCategory(categoryUUID);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable("id") String id) {
        return new ResponseEntity<CategoryDTO>(categoryService.findCategoryById(UUID.fromString(id)), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDTO>> findAll() {
        log.info("fetching categories");
        return new ResponseEntity<>(categoryService.findCategories(false), HttpStatus.OK);
    }

    @GetMapping("/tree")
    public ResponseEntity<List<CategoryNodeDTO>> getTree() {
        log.info("fetching categories");
        List<CategoryDTO> categories = categoryService.findCategories(true);
        List<CategoryNodeDTO> nodes = constructAndGetTree(categories);
        return new ResponseEntity<>(nodes, HttpStatus.OK);
    }

    @GetMapping("/subcategory/list")
    public ResponseEntity<List<SubCategoryDTO>> findAllSubCtegories() {
        return new ResponseEntity<List<SubCategoryDTO>>(categoryService.getAllSubCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}/subcategory/list")
    public ResponseEntity<List<SubCategoryDTO>> findSubCategoriesByCategoryId(@PathVariable("id") String categoryId) {
        return new ResponseEntity<List<SubCategoryDTO>>(categoryService.findSubCategoriesByCategoryId(categoryId), HttpStatus.OK);
    }

    @PostMapping("{id}/subcategory")
    public ResponseEntity<SubCategoryDTO> create(@PathVariable("id") String categoryId, @RequestBody SubCategoryDTO dto) {
        log.info("saving sub-categories");
        return new ResponseEntity<SubCategoryDTO>(categoryService.saveSubCategory(dto), HttpStatus.OK);
    }

    @PutMapping("{id}/subcategory")
    public ResponseEntity<SubCategoryDTO> update(@PathVariable("id") String categorUUID, @RequestBody SubCategoryDTO dto) {
        log.info("updating sub-categories");
        if (dto.getId() == null) {
            throw new ApiRuntimeException("sub-categoryId cannot be null");
        }
        return new ResponseEntity<SubCategoryDTO>(categoryService.saveSubCategory(dto), HttpStatus.OK);
    }

    @DeleteMapping("subcategory/{subCategoryId}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable("subCategoryId") String subCategoryUUID) {
        categoryService.deleteSubCategory(subCategoryUUID);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("rename/{type}/{id}/{newName}")
    public ResponseEntity<Void> rename(@PathVariable("type") String type, @PathVariable("id") String id, @PathVariable("newName") String newName) {
        categoryService.rename(type, id, newName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private List<CategoryNodeDTO> constructAndGetTree(List<CategoryDTO> categories) {
        List<CategoryNodeDTO> nodes = new ArrayList<>();
        if (categories != null && categories.size() > 0) {
            for (CategoryDTO category : categories) {
                nodes.add(new CategoryNodeDTO(category));
            }
        }
        return nodes;
    }

}
