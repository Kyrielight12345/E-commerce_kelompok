package com.ecommerce.serverapp.services;

import com.ecommerce.serverapp.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import com.ecommerce.serverapp.models.Product;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Integer id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!!!"));
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product update(Integer id, Product product) {
        getById(id);
        product.setId(id);
        return productRepository.save(product);
    }

    public Product delete(Integer id) {
        Product product = getById(id);
        productRepository.delete(product);
        return product;
    }

    public List<Product> searchByName(String name, String category) {
        return productRepository.findByNameContainingOrCategoryContaining(name, category);
    }
}
