package com.ecommerce.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.clientapp.models.Cart;

@Service
public class CartService {
    @Value("${server.base.url}/cart")
    private String url;
    
    @Autowired
    private RestTemplate restTemplate;

    public List<Cart> getAll() {
        return restTemplate.exchange(
            url, 
            HttpMethod.GET, 
            null, 
            new ParameterizedTypeReference<List<Cart>>() {}
            )
        .getBody();
    }

    public Cart getById(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.GET,
            null, Cart.class, 
            id)
        .getBody();
    }

    public Cart create(Cart cart) {
        return restTemplate.exchange(
            url, 
            HttpMethod.POST,
            new HttpEntity<>(cart), Cart.class)
            .getBody();
    }

    public Cart update(Integer id, Cart cart) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.PUT,
            new HttpEntity<>(cart), 
            Cart.class)
            .getBody();
    }

    public Cart delete(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id), 
            HttpMethod.DELETE, 
            null, 
            Cart.class)
            .getBody();
    }
}
