package com.ecommerce.clientapp.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.clientapp.models.Cart;
import com.ecommerce.clientapp.models.Cart_product;
import com.ecommerce.clientapp.services.CartService;
import com.ecommerce.clientapp.services.Cart_productService;

@RestController
@RequestMapping("/api/restcart")
public class RestcartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private Cart_productService cartProductService;

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAll();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Integer id) {
        Cart cart = cartService.getById(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/carts")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        Cart createdCart = cartService.create(cart);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @PutMapping("/carts/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Integer id, @RequestBody Cart cart) {
        Cart updatedCart = cartService.update(id, cart);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("/carts/{id}")
    public ResponseEntity<Cart> deleteCart(@PathVariable Integer id) {
        Cart deletedCart = cartService.delete(id);
        return new ResponseEntity<>(deletedCart, HttpStatus.OK);
    }

    // CART_PRODUCTS

    @GetMapping("/cart_products")
    public ResponseEntity<List<Cart_product>> getAllCartProducts() {
        List<Cart_product> cartProducts = cartProductService.getAll();
        return new ResponseEntity<>(cartProducts, HttpStatus.OK);
    }

    @GetMapping("/cart_products/{id}")
    public ResponseEntity<Cart_product> getCartProductById(@PathVariable Integer id) {
        Cart_product cartProduct = cartProductService.getById(id);
        return new ResponseEntity<>(cartProduct, HttpStatus.OK);
    }

    @PostMapping("/cart_products")
    public ResponseEntity<Cart_product> createCartProduct(@RequestBody Cart_product cartProduct) {
        Cart_product createdCartProduct = cartProductService.create(cartProduct);
        return new ResponseEntity<>(createdCartProduct, HttpStatus.CREATED);
    }

    @PutMapping("/cart_products/{id}")
    public ResponseEntity<Cart_product> updateCartProduct(@PathVariable Integer id, @RequestBody Cart_product cartProduct) {
        Cart_product updatedCartProduct = cartProductService.update(id, cartProduct);
        return new ResponseEntity<>(updatedCartProduct, HttpStatus.OK);
    }

    @DeleteMapping("/cart_products/{id}")
    public ResponseEntity<Cart_product> deleteCartProduct(@PathVariable Integer id) {
        Cart_product deletedCartProduct = cartProductService.delete(id);
        return new ResponseEntity<>(deletedCartProduct, HttpStatus.OK);
    }
}

