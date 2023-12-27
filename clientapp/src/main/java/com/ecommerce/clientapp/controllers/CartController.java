package com.ecommerce.clientapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.clientapp.models.Cart_product;
import com.ecommerce.clientapp.services.Cart_productService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private Cart_productService cart_productService;

    @GetMapping
    public String getCart(Cart_product cart_product, Model model, Authentication auth) {
        model.addAttribute("cartProduct", cart_productService.getAll());
        model.addAttribute("name", auth.getName());
        model.addAttribute("isActive", "home");
        return "pages/cart/index";
    }
}
