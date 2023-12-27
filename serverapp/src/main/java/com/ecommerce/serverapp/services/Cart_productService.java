package com.ecommerce.serverapp.services;

import com.ecommerce.serverapp.repositories.Cart_productRepository;
import lombok.AllArgsConstructor;

import com.ecommerce.serverapp.models.Cart_product;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class Cart_productService {

    private Cart_productRepository cart_productRepository;

    public List<Cart_product> getAll() {
        return cart_productRepository.findAll();
    }

    public Cart_product getById(Integer id) {
        return cart_productRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart_product not found!!!"));
    }

    public Cart_product create(Cart_product cart_product) {
        return cart_productRepository.save(cart_product);
    }

    public Cart_product update(Integer id, Cart_product cart_product) {
        getById(id);
        cart_product.setId(id);
        return cart_productRepository.save(cart_product);
    }

    public Cart_product delete(Integer id) {
        Cart_product cart_product = getById(id);
        cart_productRepository.delete(cart_product);
        return cart_product;
    }
}
