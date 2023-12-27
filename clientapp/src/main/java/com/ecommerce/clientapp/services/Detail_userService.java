package com.ecommerce.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.clientapp.models.Detail_User;

@Service
public class Detail_userService {
    @Value("${server.base.url}/DetailUser")
    private String url;
    
    private RestTemplate restTemplate;

    public List<Detail_User> getAll() {
        return restTemplate.exchange(
            url, 
            HttpMethod.GET, 
            null, 
            new ParameterizedTypeReference<List<Detail_User>>() {}
            )
        .getBody();
    }

    public Detail_User getById(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.GET,
            null, Detail_User.class, 
            id)
        .getBody();
    }

    public Detail_User create(Detail_User detail_User) {
        return restTemplate.exchange(
            url, 
            HttpMethod.POST,
            new HttpEntity<>(detail_User), Detail_User.class)
            .getBody();
    }

    public Detail_User update(Integer id, Detail_User detail_User) {
        return restTemplate.exchange(
            url.concat("/" + id),
            HttpMethod.PUT,
            new HttpEntity<>(detail_User), 
            Detail_User.class)
            .getBody();
    }

    public Detail_User delete(Integer id) {
        return restTemplate.exchange(
            url.concat("/" + id), 
            HttpMethod.DELETE, 
            null, 
            Detail_User.class)
            .getBody();
    }
}
