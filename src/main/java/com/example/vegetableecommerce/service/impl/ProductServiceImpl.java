package com.example.vegetableecommerce.service.impl;

import com.example.vegetableecommerce.common.Constants;
import com.example.vegetableecommerce.dto.ProductDto;
import com.example.vegetableecommerce.entity.Product;
import com.example.vegetableecommerce.exception.NotFoundIdentityException;
import com.example.vegetableecommerce.exception.UsernameExistedException;
import com.example.vegetableecommerce.repository.ProductRepository;
import com.example.vegetableecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) throws UsernameExistedException {
        Optional<Product> optionalProduct = productRepository.findByName(productDto.getName());
        Product product = new Product();
        if (optionalProduct.isEmpty()) {
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setQuantity(productDto.getQuantity());
            product.setCategory(productDto.getCategory());
            product.setDiscount(productDto.getDiscount());
            product.setProducer(productDto.getProducer());
            product.setAvatarImage(productDto.getAvatarImage());
            product.setStatus(Constants.ENABLE_STATUS);
            product.setUpdatedBy(productDto.getUpdatedBy());
            productRepository.save(product);
        }
        else {
            throw new UsernameExistedException();
        }
        return productDto;
    }

    @SneakyThrows
    @Override
    public ProductDto updateProduct(ProductDto productDto, Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Optional<Product> optional =  productRepository.findByName(productDto.getName());
            Product product = optionalProduct.get();
            if (optional.isEmpty() || product.getId().equals(optional.get().getId())) {
                product.setName(productDto.getName());
                product.setPrice(productDto.getPrice());
                product.setDescription(productDto.getDescription());
                product.setQuantity(productDto.getQuantity());
                product.setCategory(productDto.getCategory());
                product.setDiscount(productDto.getDiscount());
                product.setProducer(productDto.getProducer());
                product.setAvatarImage(productDto.getAvatarImage());
                product.setUpdatedBy(productDto.getUpdatedBy());
                productRepository.save(product);
            } else {
                throw new UsernameExistedException();
            }
        } else {
            throw new NotFoundIdentityException(id);
        }
        return productDto;
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Optional<Product> optional =  productRepository.findByIdAndStatus(id, Constants.ENABLE_STATUS);
            Product product = optionalProduct.get();
            if (optional.isPresent()) {
                product.setStatus(Constants.UNABLE_STATUS);
                product.setUpdateAt(LocalDate.now());
                productRepository.save(product);
            } else {
                throw new NotFoundIdentityException(id);
            }
        } else {
            throw new NotFoundIdentityException(id);
        }
    }

    @Override
    public Page<Product> getAllProduct(Pageable pageable) {
        return productRepository.findAllByStatus(pageable, Constants.ENABLE_STATUS);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.getByIdAndStatus(id, Constants.ENABLE_STATUS);
    }
}
