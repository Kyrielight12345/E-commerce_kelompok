package com.ecommerce.serverapp.services;

import com.ecommerce.serverapp.repositories.CartRepository;
import lombok.AllArgsConstructor;

import com.ecommerce.serverapp.models.Cart;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CartService {

    private CartRepository cartRepository;

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    public Cart getById(Integer id) {
        return cartRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found!!!"));
    }

    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart update(Integer id, Cart cart) {
        getById(id);
        cart.setId(id);
        return cartRepository.save(cart);
    }

    public Cart delete(Integer id) {
        Cart cart = getById(id);
        cartRepository.delete(cart);
        return cart;
    }
}
