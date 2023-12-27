package com.ecommerce.serverapp.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.serverapp.models.Detail_User;

public interface Detail_userRepository extends JpaRepository<Detail_User, Integer> {
    Optional<Detail_User> findByUserUsername(String username);
}
