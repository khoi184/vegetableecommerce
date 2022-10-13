package com.example.vegetableecommerce.restcontroller;

import com.example.vegetableecommerce.dto.CategoryDto;
import com.example.vegetableecommerce.entity.Category;
import com.example.vegetableecommerce.exception.UsernameExistedException;
import com.example.vegetableecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryApi {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto) throws UsernameExistedException {
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDto categoryDto,
                                            @PathVariable("id") Long id) throws UsernameExistedException {
        categoryService.updateCategory(categoryDto, id);
        return new ResponseEntity<>("Update Successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Category deleted!", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") Long id) {
        Category category = categoryService.getById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<Page<Category>> getAllCategory(Pageable pageable) {
        Page<Category> categories = categoryService.getAllCategory(pageable);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
