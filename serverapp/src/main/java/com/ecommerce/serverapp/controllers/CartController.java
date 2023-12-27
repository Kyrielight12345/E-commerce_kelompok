package com.ecommerce.serverapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.serverapp.models.Cart;
import com.ecommerce.serverapp.services.CartService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    @GetMapping
    public List<Cart> getAll() {
        return cartService.getAll();
    }

    @GetMapping("/{id}")
    public Cart getById(@PathVariable Integer id) {
        return cartService.getById(id);
    }

    @PostMapping
    public Cart create(@RequestBody Cart cart) {
        return cartService.create(cart);

    }

    @PutMapping("/{id}")
    public Cart update(@PathVariable Integer id, @RequestBody Cart cart) {
        return cartService.update(id, cart);
    }

    @DeleteMapping("/{id}")
    public Cart delete(@PathVariable Integer id) {
        return cartService.delete(id);
    }
}
