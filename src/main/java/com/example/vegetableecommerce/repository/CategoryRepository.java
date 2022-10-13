package com.example.vegetableecommerce.repository;

import com.example.vegetableecommerce.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    List<Category> findAllByIdAndStatus(Long id, Integer status);

    Category getByIdAndStatus(Long id, Integer status);

    Page<Category> getAllByStatus(Pageable pageable, Integer status);
}
