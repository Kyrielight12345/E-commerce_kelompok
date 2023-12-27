package com.ecommerce.clientapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.clientapp.models.Profile;

@Service
public class ProfileService {

        @Value("${server.base.url}/detail_user")
        private String url;

        @Autowired
        private RestTemplate restTamplate;

        public Profile getByUser(String username) {
                return restTamplate
                                .exchange(
                                                // url.concat("/" + id),
                                                url + "/user?name=" + username,
                                                HttpMethod.GET,
                                                null,
                                                Profile.class)
                                .getBody();
        }

        public Profile update(Integer id, Profile Profile) {
                return restTamplate
                                .exchange(
                                                url.concat("/" + id),
                                                HttpMethod.PUT,
                                                new HttpEntity<>(Profile),
                                                Profile.class)
                                .getBody();
        }
}