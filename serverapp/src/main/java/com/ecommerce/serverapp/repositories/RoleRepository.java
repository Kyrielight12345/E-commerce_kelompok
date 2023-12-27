package com.ecommerce.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.serverapp.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    
}
