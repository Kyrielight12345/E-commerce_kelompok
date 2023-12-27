package com.ecommerce.serverapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.serverapp.models.Detail_User;
import com.ecommerce.serverapp.models.dto.request.Detail_UserRequest;
import com.ecommerce.serverapp.services.Detail_userService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/detail_user")
@PreAuthorize("hasAnyRole('BUYER','ADMIN')")
public class Detail_userController {

    public Detail_userService detail_userService;

    @GetMapping
    public List<Detail_User> getAll() {
        return detail_userService.getAll();
    }

    @GetMapping("/{id}")
    public Detail_User getById(@PathVariable Integer id) {
        return detail_userService.getById(id);
    }

    @PostMapping
    public Detail_User create(@RequestBody Detail_User detailUser) {
        return detail_userService.create(detailUser);
    }

    @PutMapping("/{id}")
    public Detail_User update(@PathVariable Integer id, @RequestBody Detail_User detailUser) {
        return detail_userService.update(id, detailUser);
    }

    @DeleteMapping("/{id}")
    public Detail_User delete(@PathVariable Integer id) {
        return detail_userService.delete(id);
    }

    @GetMapping("/user")
    public ResponseEntity<?> findUserWithEmployee(@RequestParam String name) {
        Optional<Detail_UserRequest> detail_user = detail_userService.findUser(name);
        if (detail_user.isPresent()) {
            return ResponseEntity.ok(detail_user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
