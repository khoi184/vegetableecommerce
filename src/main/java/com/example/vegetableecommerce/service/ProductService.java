package com.example.vegetableecommerce.service;

import com.example.vegetableecommerce.dto.ProductDto;
import com.example.vegetableecommerce.entity.Product;
import com.example.vegetableecommerce.exception.UsernameExistedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto) throws UsernameExistedException;

    ProductDto updateProduct(ProductDto productDto, Long id);

    void deleteProduct(Long id);

    Page<Product> getAllProduct(Pageable pageable);

    Product getProduct(Long id);
}
