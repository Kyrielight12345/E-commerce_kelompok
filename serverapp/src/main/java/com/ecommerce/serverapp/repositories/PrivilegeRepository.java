package com.ecommerce.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.serverapp.models.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
    
}
