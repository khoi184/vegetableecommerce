package com.example.vegetableecommerce.service.impl;

import com.example.vegetableecommerce.common.Constants;
import com.example.vegetableecommerce.dto.ProductDto;
import com.example.vegetableecommerce.entity.Product;
import com.example.vegetableecommerce.exception.NotFoundIdentityException;
import com.example.vegetableecommerce.exception.ItemNotFoundException;
import com.example.vegetableecommerce.exception.UsernameExistedException;
import com.example.vegetableecommerce.repository.ProductRepository;
import com.example.vegetableecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
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
            product.setDiscount(productDto.getDiscount());
            product.setProducer(productDto.getProducer());
            product.setAvatarImage(productDto.getAvatarImage());
            product.setStatus(Constants.ENABLE_STATUS);
            product.setUpdatedBy(productDto.getUpdatedBy());
            productRepository.save(product);
        } else {
            throw new UsernameExistedException();
        }
        return productDto;
    }

    @SneakyThrows
    @Override
    public ProductDto updateProduct(ProductDto productDto, Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Optional<Product> optional = productRepository.findByName(productDto.getName());
            Product product = optionalProduct.get();
            if (optional.isEmpty() || product.getId().equals(optional.get().getId())
                    || optional.get().getName().length() > 0) {
                product.setName(productDto.getName());
                product.setPrice(productDto.getPrice());
                product.setDescription(productDto.getDescription());
                product.setQuantity(productDto.getQuantity());
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
        List<Product> productList = productRepository.findAllByIdAndStatus(id, Constants.ENABLE_STATUS);
        if (CollectionUtils.isEmpty(productList)) {
            throw new ItemNotFoundException();
        }
        for (Product product : productList) {
            product.setStatus(Constants.UNABLE_STATUS);
            product.setUpdateAt(LocalDate.now());
            productRepository.save(product);
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
