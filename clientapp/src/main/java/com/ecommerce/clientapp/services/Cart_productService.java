package com.ecommerce.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.clientapp.models.Cart_product;

@Service
public class Cart_productService {
    @Value("${server.base.url}/cart_product")
    private String url;
    
    @Autowired
    private RestTemplate restTemplate;

    public List<Cart_product> getAll() {
        return restTemplate.exchange(
            url, 
            HttpMethod.GET, 
            null, 
            new ParameterizedTypeReference<List<Cart_product>>() {}
            )
        .getBody();
    }

    public Cart_product getById(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.GET,
            null, Cart_product.class, 
            id)
        .getBody();
    }

    public Cart_product create(Cart_product cart_product) {
        return restTemplate.exchange(
            url, 
            HttpMethod.POST,
            new HttpEntity<>(cart_product), Cart_product.class)
            .getBody();
    }

    public Cart_product update(Integer id, Cart_product cart_product) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.PUT,
            new HttpEntity<>(cart_product), 
            Cart_product.class)
            .getBody();
    }

    public Cart_product delete(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id), 
            HttpMethod.DELETE, 
            null, 
            Cart_product.class)
            .getBody();
    }
}
