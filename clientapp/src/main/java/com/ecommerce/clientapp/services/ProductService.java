package com.ecommerce.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecommerce.clientapp.models.Product;

@Service
public class ProductService {
    @Value("${server.base.url}/product")
    private String url;


    @Autowired
    private RestTemplate restTemplate;

    public List<Product> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                })
                .getBody();
    }

    public Product getById(Integer id) {
        return restTemplate.exchange(
                url.concat("/" + id),
                HttpMethod.GET,
                null,
                Product.class,
                id)
                .getBody();
    }

    public List<Product> searchByName(String name) {
        return restTemplate.exchange(
                url + "/search?name=" + name,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                })
                .getBody();
    }

    public List<Product> getByCategory(String category) {
        // String categoryUrl = url + "/searchByCategory?category=" + category;
        String categoryUrl = UriComponentsBuilder.fromUriString(url)
                .path("/searchByCategory")
                .queryParam("category", category)
                .build()
                .toUriString();
        return restTemplate.exchange(
                categoryUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {
                }).getBody();
    }

    public Product create(Product product) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(product), Product.class)
                .getBody();
    }

    public Product update(Integer id, Product product) {
        return restTemplate.exchange(
                url.concat("/" + id),
                HttpMethod.PUT,
                new HttpEntity<>(product),
                Product.class)
                .getBody();
    }

    public Product delete(Integer id) {
        return restTemplate.exchange(
                url.concat("/" + id),
                HttpMethod.DELETE,
                null,
                Product.class)
                .getBody();
    }

}
