package com.ecommerce.clientapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.clientapp.models.Product;
import com.ecommerce.clientapp.services.ProductService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/product")
public class ProductPageController {
  private ProductService productService;

  @GetMapping
  public String getProduct(@RequestParam Integer id, Model model, Authentication auth) {
    Product product = productService.getById(id);
    model.addAttribute("product", product);
    model.addAttribute("name", auth.getName());
    model.addAttribute("isActive", "home");
    return "pages/product/detail-product";
  }

  @GetMapping("/update")
  public String updateProduct(@RequestParam Integer id, Model model, Authentication auth) {
    Product product = productService.getById(id);
    model.addAttribute("product", product);
    model.addAttribute("name", auth.getName());
    model.addAttribute("isActive", "home");
    return "pages/product/edit-product";
  }

  @GetMapping("/create")
  public String createProduct(Model model, Authentication auth) {
    model.addAttribute("name", auth.getName());
    model.addAttribute("isActive", "home");
    return "pages/product/create-product";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Integer id) {
    productService.delete(id);
    return "redirect:/cart";
  }
}