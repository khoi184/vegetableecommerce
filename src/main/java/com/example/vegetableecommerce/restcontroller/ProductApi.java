package com.example.vegetableecommerce.restcontroller;

import com.example.vegetableecommerce.dto.ProductDto;
import com.example.vegetableecommerce.entity.Product;
import com.example.vegetableecommerce.exception.UsernameExistedException;
import com.example.vegetableecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductApi {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) throws UsernameExistedException {
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto, @PathVariable("id") Long id) {
        productService.updateProduct(productDto, id);
        return new ResponseEntity<>("Update Successfully!", HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Delete Successfully!", HttpStatus.OK);
    }

    @GetMapping("/get-list")
    public ResponseEntity<Page<Product>> getAllProduct(Pageable pageable) {
        return new ResponseEntity<>(productService.getAllProduct(pageable), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }
}
