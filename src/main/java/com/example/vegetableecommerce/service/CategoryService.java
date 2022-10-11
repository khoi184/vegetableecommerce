package com.example.vegetableecommerce.service;

import com.example.vegetableecommerce.dto.CategoryDto;
import com.example.vegetableecommerce.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Long id);

    CategoryDto deleteCategory(Long id);

    Page<Category> getAllCategory(Pageable pageable, Integer status);
}
