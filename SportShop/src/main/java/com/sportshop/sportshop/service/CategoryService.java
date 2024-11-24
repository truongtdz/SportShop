package com.sportshop.sportshop.service;

import com.sportshop.sportshop.dto.request.CategoryRequest;
import com.sportshop.sportshop.dto.response.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    // View all category
    public List<CategoryResponse> getAllCategory();

    // View By Id
    public CategoryResponse getCategoryById(Long categoryId);

    // Create Category
    public void createCategory(CategoryRequest request);

    // Delete Category
    public void deleteCategory(Long categoryId);
}
