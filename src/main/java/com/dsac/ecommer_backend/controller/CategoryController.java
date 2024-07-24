package com.dsac.ecommer_backend.controller;

import com.dsac.ecommer_backend.exception.ResourceFoundException;
import com.dsac.ecommer_backend.model.Category;
import com.dsac.ecommer_backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public Category saveCategory(@RequestBody Category category) throws ResourceFoundException {
        return categoryService.addCategory(category);
    }

    @GetMapping("/id/{id}")
    public Category getCategoryById(@PathVariable Long id) throws ResourceFoundException {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/all-categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/all")
    public Page<Category> getAllCategories(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return categoryService.getAllCategories(pageable);
    }

    @GetMapping("/search/{name}")
    public List<Category> searchCategoriesByName(@PathVariable String name, @PageableDefault(page = 1, size = 10) Pageable pageable) {
        return categoryService.getCategoryByName(name, pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/total")
    public Long getTotalCategories() {
        return categoryService.getTotalCategories();
    }

    @GetMapping("/popular")
    public List<Category> getPopularCategories(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return categoryService.getPopularCategories(pageable.getPageNumber(), pageable.getPageSize());
    }

    @GetMapping("/unpopular")
    public List<Category> getUnPopularCategories(@PageableDefault(page = 1, size = 10) Pageable pageable) {
        return categoryService.getUnPopularCategories(pageable.getPageNumber(), pageable.getPageSize());
    }

    @PutMapping("/update/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) throws ResourceFoundException {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

}