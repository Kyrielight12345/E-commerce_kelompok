package com.ecommerce.clientapp.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.clientapp.models.Profile;
import com.ecommerce.clientapp.services.ProfileService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/profile")
public class RestProfileController {

    private ProfileService profileService;

    @GetMapping("/user")
    public ResponseEntity<Profile> getByUser(@RequestParam String name) {
        Profile profile = profileService.getByUser(name);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public Profile update(@PathVariable Integer id, @RequestBody Profile profile) {
        return profileService.update(id, profile);
    }
}