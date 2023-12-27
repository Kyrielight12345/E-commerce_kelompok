package com.ecommerce.clientapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {

  @GetMapping
  public String home(Model model, Authentication auth) {
    // model.addAttribute("name", "SIBKM 05 Java");
    // model.addAttribute("product", productService.getAll());
    model.addAttribute("info", auth.getName());
    model.addAttribute("isActive", "home");
    return "pages/home-page/index";
  }

  @GetMapping("/home")
  public String dashboard(Model model, Authentication auth) {
    // model.addAttribute("name", "SIBKM 05 Java");
    model.addAttribute("name", auth.getName());
    model.addAttribute("isActive", "home");
    return "pages/home-page/index";
  }

}