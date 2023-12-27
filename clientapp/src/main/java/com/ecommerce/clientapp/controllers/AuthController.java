package com.ecommerce.clientapp.controllers;

import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ecommerce.clientapp.models.dto.Request.LoginRequest;
import com.ecommerce.clientapp.services.AuthService;

@Controller
@AllArgsConstructor
@RequestMapping("/login")
public class AuthController {
  private AuthService authService;

  @GetMapping
  public String loginView(LoginRequest loginRequest) {
    Authentication auth = SecurityContextHolder
        .getContext()
        .getAuthentication();

    if (auth instanceof AnonymousAuthenticationToken) {
      return "auth/login";
    }
    return "redirect:/home";
  }

  @PostMapping
  public String login(LoginRequest loginRequest) {
    if (!authService.login(loginRequest)) {
      return "redirect:/login?error=true";
    }
    return "redirect:/home";
  }

}
