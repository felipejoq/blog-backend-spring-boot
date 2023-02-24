package com.uncodigo.blogspringapi.service;

import com.uncodigo.blogspringapi.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategory(Long categoryId);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
    CategoryDto deleteCategory(Long categoryId);
}
