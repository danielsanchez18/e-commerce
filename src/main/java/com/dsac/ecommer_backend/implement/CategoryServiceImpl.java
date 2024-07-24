package com.dsac.ecommer_backend.implement;

import com.dsac.ecommer_backend.exception.ResourceFoundException;
import com.dsac.ecommer_backend.model.Category;
import com.dsac.ecommer_backend.repository.CategoryRepository;
import com.dsac.ecommer_backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) throws ResourceFoundException {
        Category category1 = categoryRepository.findCategoryByName(category.getNameCategory());

        if (category1 != null) {
            throw new ResourceFoundException("Category already exists");
        } else {
            return categoryRepository.save(category);
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category getCategoryById(Long id) throws ResourceFoundException {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceFoundException("Category not found")
        );
    }

    @Override
    public List<Category> getCategoryByName(String name, int page, int size) {
        return categoryRepository.searchCategoryByName(name, page, size);
    }

    @Override
    public Long getTotalCategories() {
        return categoryRepository.count();
    }

    @Override
    public List<Category> getPopularCategories(int page, int size) {
        return categoryRepository.findPopularCategories(page, size);
    }

    @Override
    public List<Category> getUnPopularCategories(int page, int size) {
        return categoryRepository.findUnPopularCategories(page, size);
    }

    @Override
    public Category updateCategory(Long id, Category category) throws ResourceFoundException {

        Category existingCategory = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceFoundException("Category not found")
        );

        Category categoryWhitSameName = categoryRepository.findCategoryByName(category.getNameCategory());

        if (categoryWhitSameName != null && !categoryWhitSameName.getIdCategory().equals(id)) {
            throw new ResourceFoundException("Category whit same name already exists");
        } else {
            existingCategory.setNameCategory(category.getNameCategory());
            return categoryRepository.save(existingCategory);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
