package com.ecommerce.serverapp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.serverapp.models.Product;
import com.ecommerce.serverapp.services.ProductService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    public ProductService productService;

    @GetMapping
    @PreAuthorize("hasAnyRole('BUYER','ADMIN')")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('BUYER','ADMIN')")
    public Product getById(@PathVariable Integer id) {
        return productService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Product create(@RequestBody Product product) {
        return productService.create(product);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('BUYER','ADMIN')")
    public Product update(@PathVariable Integer id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Product delete(@PathVariable Integer id) {
        return productService.delete(id);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('BUYER','ADMIN')")
    public ResponseEntity<?> searchProduct(@RequestParam String name) {
        List<Product> products = productService.searchByName(name, name);
        if (!products.isEmpty()) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
