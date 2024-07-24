package com.dsac.ecommer_backend.service;

import com.dsac.ecommer_backend.exception.ResourceFoundException;
import com.dsac.ecommer_backend.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    Category addCategory (Category category) throws ResourceFoundException;

    List<Category> getAllCategories();

    Page<Category> getAllCategories(Pageable pageable);

    Category getCategoryById(Long id) throws ResourceFoundException;

    List<Category> getCategoryByName(String name, int page, int size);

    Long getTotalCategories();

    List<Category> getPopularCategories(int page, int size);

    List<Category> getUnPopularCategories(int page, int size);

    Category updateCategory(Long id, Category category) throws ResourceFoundException;

    void deleteCategory(Long id);

}
