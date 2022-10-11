package com.example.vegetableecommerce.repository;

import com.example.vegetableecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    Optional<Product> findByIdAndStatus(Long id, Integer status);

    Product getByIdAndStatus(Long id, Integer status);

    Page<Product> findAllByStatus(Pageable pageable, Integer status);
}
