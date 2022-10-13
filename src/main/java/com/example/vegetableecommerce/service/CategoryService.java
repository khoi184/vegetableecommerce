package com.example.vegetableecommerce.service;

import com.example.vegetableecommerce.dto.CategoryDto;
import com.example.vegetableecommerce.entity.Category;
import com.example.vegetableecommerce.exception.UsernameExistedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto) throws UsernameExistedException;

    CategoryDto updateCategory(CategoryDto categoryDto, Long id) throws UsernameExistedException;

    void deleteCategory(Long id);

    Category getById(Long id);

    Page<Category> getAllCategory(Pageable pageable);
}
