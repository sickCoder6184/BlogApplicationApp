package com.dhapola.blog.services;

import java.util.List;
import com.dhapola.blog.payload.CategoryDto;

public interface CategoryService {
    
    // Create
    CategoryDto createCategory(CategoryDto categoryDto);
    
    // Update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    
    // Get
    CategoryDto getCategory(Integer categoryId);
    
    // Get all
    List<CategoryDto> getCategories();
    
    // Delete
    void deleteCategory(Integer categoryId);
    
    // Delete all
    void deleteAllCategories();

}
