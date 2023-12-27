package com.ecommerce.serverapp.repositories;

import com.ecommerce.serverapp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameContainingOrCategoryContaining(String name, String category);
}