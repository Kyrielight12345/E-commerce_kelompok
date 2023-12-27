package com.ecommerce.serverapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.serverapp.models.Detail_User;
import com.ecommerce.serverapp.models.dto.request.Detail_UserRequest;
import com.ecommerce.serverapp.models.dto.request.LoginRequest;
import com.ecommerce.serverapp.models.dto.Response.LoginResponse;
import com.ecommerce.serverapp.services.AuthService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping
public class AuthController {
    private AuthService authService;

    @PostMapping("/auth")
    public Detail_User registration(@RequestBody Detail_UserRequest detail_UserRequest) {
        return authService.registration(detail_UserRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}