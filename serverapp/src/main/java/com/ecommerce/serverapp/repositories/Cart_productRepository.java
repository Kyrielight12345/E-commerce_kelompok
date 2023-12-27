package com.ecommerce.serverapp.repositories;

import com.ecommerce.serverapp.models.Cart_product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cart_productRepository extends JpaRepository<Cart_product, Integer> {
}
