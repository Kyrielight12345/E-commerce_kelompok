package com.ecommerce.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.serverapp.models.User;
import com.ecommerce.serverapp.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!!!"));
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(Integer id, User user) {
        getById(id);
        user.setId(id);
        return userRepository.save(user);
    }

    public User delete(Integer id) {
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }
}
