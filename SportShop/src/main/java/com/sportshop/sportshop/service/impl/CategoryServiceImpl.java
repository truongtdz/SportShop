package com.sportshop.sportshop.service.impl;

import com.sportshop.sportshop.dto.request.CategoryRequest;
import com.sportshop.sportshop.dto.response.CategoryResponse;
import com.sportshop.sportshop.entity.CategoryEntity;
import com.sportshop.sportshop.mapper.CategoryMapper;
import com.sportshop.sportshop.repository.CategoryRepository;
import com.sportshop.sportshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAllCategory() {

        List<CategoryResponse> categories = new ArrayList<>();
        for(CategoryEntity category : categoryRepository.findAll()){
            categories.add(categoryMapper.toCategoryResponse(category));
        }

        return categories;
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId){
        return categoryMapper.toCategoryResponse(categoryRepository.getCategoryById(categoryId));
    }

    @Override
    public void createCategory(CategoryRequest request) {
        CategoryEntity newCategory = new CategoryEntity();

        newCategory.setName(request.getName().toUpperCase());

        categoryRepository.save(newCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
