package com.ecommerce.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.clientapp.models.Privilege;

@Service
public class PrivilegeService {
    @Value("${server.base.url}/privilege")
    private String url;
    
    private RestTemplate restTemplate;

    public List<Privilege> getAll() {
        return restTemplate.exchange(
            url, 
            HttpMethod.GET, 
            null, 
            new ParameterizedTypeReference<List<Privilege>>() {}
            )
        .getBody();
    }

    public Privilege getById(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.GET,
            null, Privilege.class, 
            id)
        .getBody();
    }

    public Privilege create(Privilege privilege) {
        return restTemplate.exchange(
            url, 
            HttpMethod.POST,
            new HttpEntity<>(privilege), Privilege.class)
            .getBody();
    }

    public Privilege update(Integer id, Privilege privilege) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.PUT,
            new HttpEntity<>(privilege), 
            Privilege.class)
            .getBody();
    }

    public Privilege delete(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id), 
            HttpMethod.DELETE, 
            null, 
            Privilege.class)
            .getBody();
    }
}
