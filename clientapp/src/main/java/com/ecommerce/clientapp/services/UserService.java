package com.ecommerce.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.clientapp.models.User;

@Service
public class UserService {
    @Value("${server.base.url}/user")
    private String url;
    
    private RestTemplate restTemplate;

    public List<User> getAll() {
        return restTemplate.exchange(
            url, 
            HttpMethod.GET, 
            null, 
            new ParameterizedTypeReference<List<User>>() {}
            )
        .getBody();
    }

    public User getById(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.GET,
            null, User.class, 
            id)
        .getBody();
    }

    public User create(User user) {
        return restTemplate.exchange(
            url, 
            HttpMethod.POST,
            new HttpEntity<>(user), User.class)
            .getBody();
    }

    public User update(Integer id, User user) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.PUT,
            new HttpEntity<>(user), 
            User.class)
            .getBody();
    }

    public User delete(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id), 
            HttpMethod.DELETE, 
            null, 
            User.class)
            .getBody();
    }
}
