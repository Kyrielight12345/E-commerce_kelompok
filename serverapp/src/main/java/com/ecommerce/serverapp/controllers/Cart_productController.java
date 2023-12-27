package com.ecommerce.serverapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.serverapp.models.Cart_product;
import com.ecommerce.serverapp.services.Cart_productService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/cart_product")
@PreAuthorize("hasAnyRole('BUYER','ADMIN')")
public class Cart_productController {

    private Cart_productService cart_productService;

    @GetMapping
    public List<Cart_product> getAll() {
        return cart_productService.getAll();
    }

    @GetMapping("/{id}")
    public Cart_product getById(@PathVariable Integer id) {
        return cart_productService.getById(id);
    }

    @PostMapping
    public Cart_product create(@RequestBody Cart_product cart_product) {
        return cart_productService.create(cart_product);

    }

    @PutMapping("/{id}")
    public Cart_product update(@PathVariable Integer id, @RequestBody Cart_product cart_product) {
        return cart_productService.update(id, cart_product);
    }

    @DeleteMapping("/{id}")
    public Cart_product delete(@PathVariable Integer id) {
        return cart_productService.delete(id);
    }
}