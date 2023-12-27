package com.ecommerce.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.clientapp.models.Role;

@Service
public class RoleService {
    @Value("${server.base.url}/role")
    private String url;
    
    private RestTemplate restTemplate;

    public List<Role> getAll() {
        return restTemplate.exchange(
            url, 
            HttpMethod.GET, 
            null, 
            new ParameterizedTypeReference<List<Role>>() {}
            )
        .getBody();
    }

    public Role getById(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.GET,
            null, Role.class, 
            id)
        .getBody();
    }

    public Role create(Role role) {
        return restTemplate.exchange(
            url, 
            HttpMethod.POST,
            new HttpEntity<>(role), Role.class)
            .getBody();
    }

    public Role update(Integer id, Role role) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.PUT,
            new HttpEntity<>(role), 
            Role.class)
            .getBody();
    }

    public Role delete(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id), 
            HttpMethod.DELETE, 
            null, 
            Role.class)
            .getBody();
    }
}
