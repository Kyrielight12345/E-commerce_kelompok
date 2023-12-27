package com.ecommerce.clientapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/search")
public class searchResultController {
  @GetMapping
  public String search(Model model, Authentication auth){
    model.addAttribute("name", auth.getName());
    model.addAttribute("isActive", "home");
    return "pages/search-result-page/index";
  }
}
