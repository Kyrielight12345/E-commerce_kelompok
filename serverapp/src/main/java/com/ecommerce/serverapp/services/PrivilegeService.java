package com.ecommerce.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.serverapp.models.Privilege;
import com.ecommerce.serverapp.repositories.PrivilegeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PrivilegeService {
    
    private PrivilegeRepository privilegeRepository;

    public List<Privilege> getAll() {
        return privilegeRepository.findAll();
    }

    public Privilege getById(Integer id) {
        return privilegeRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Privilege not found!!!"));
    }

    public Privilege create(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    public Privilege update(Integer id, Privilege privilege) {
        getById(id);
        privilege.setId(id);
        return privilegeRepository.save(privilege);
    }

    public Privilege delete(Integer id) {
        Privilege privilege = getById(id);
        privilegeRepository.delete(privilege);
        return privilege;
    }
}
