package com.ecommerce.serverapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.serverapp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUsernameOrDetailuserEmail(String username, String email);

    public Optional<User> findByUsername(String username);
}
