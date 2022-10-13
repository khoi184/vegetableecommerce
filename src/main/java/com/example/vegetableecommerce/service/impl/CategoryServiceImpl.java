package com.example.vegetableecommerce.service.impl;

import com.example.vegetableecommerce.common.Constants;
import com.example.vegetableecommerce.dto.CategoryDto;
import com.example.vegetableecommerce.entity.Category;
import com.example.vegetableecommerce.exception.NotFoundIdentityException;
import com.example.vegetableecommerce.exception.ItemNotFoundException;
import com.example.vegetableecommerce.exception.UsernameExistedException;
import com.example.vegetableecommerce.repository.CategoryRepository;
import com.example.vegetableecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws UsernameExistedException {
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDto.getName());
        if (optionalCategory.isEmpty()) {
            Category category = new Category();
            category.setName(category.getName());
            category.setDescription(categoryDto.getDescription());
            category.setStatus(Constants.ENABLE_STATUS);
            categoryRepository.save(category);
        } else {
            throw new UsernameExistedException();
        }
        return categoryDto;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long id) throws UsernameExistedException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Optional<Category> optional = categoryRepository.findByName(categoryDto.getName());
            Category category = optionalCategory.get();
            if (optional.isEmpty() || category.getId().equals(optional.get().getId())) {
                category.setName(category.getName());
                category.setDescription(categoryDto.getDescription());
                category.setStatus(category.getStatus());
                categoryRepository.save(category);
            } else {
                throw new UsernameExistedException();
            }
        } else {
            throw new NotFoundIdentityException(id);
        }
        return categoryDto;
    }

    @Override
    public void deleteCategory(Long id) {
        List<Category> categoryList = categoryRepository.findAllByIdAndStatus(id, Constants.ENABLE_STATUS);
        if (CollectionUtils.isEmpty(categoryList)) {
            throw new ItemNotFoundException();
        }

        for (Category category : categoryList) {
            category.setStatus(Constants.UNABLE_STATUS);
            category.setUpdateAt(LocalDate.now());
            categoryRepository.save(category);
        }
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.getByIdAndStatus(id, Constants.ENABLE_STATUS);
    }

    @Override
    public Page<Category> getAllCategory(Pageable pageable) {
        return categoryRepository.getAllByStatus(pageable, Constants.ENABLE_STATUS);
    }
}
