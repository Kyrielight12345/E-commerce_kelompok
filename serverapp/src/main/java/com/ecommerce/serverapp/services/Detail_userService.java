package com.ecommerce.serverapp.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.serverapp.models.Detail_User;
import com.ecommerce.serverapp.models.User;
import com.ecommerce.serverapp.models.dto.request.Detail_UserRequest;
import com.ecommerce.serverapp.repositories.Detail_userRepository;
import com.ecommerce.serverapp.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Detail_userService {

    private Detail_userRepository detail_userRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public List<Detail_User> getAll() {
        return detail_userRepository.findAll();
    }

    public Detail_User getById(Integer id) {
        return detail_userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Detail User not found!!!"));
    }

    public Detail_User create(Detail_User detailUser) {
        return detail_userRepository.save(detailUser);
    }

    public Detail_User update(Integer id_user, Detail_User detailUser) {
        getById(id_user);
        detailUser.setId_user(id_user);
        return detail_userRepository.save(detailUser);
    }

    public Detail_User delete(Integer id) {
        Detail_User detailUser = getById(id);
        detail_userRepository.delete(detailUser);
        return detailUser;
    }

    public Optional<Detail_UserRequest> findUser(String name) {
        Optional<User> user = userRepository.findByUsername(name);
        if (user.isPresent()) {
            Optional<Detail_User> detail_user = detail_userRepository.findByUserUsername(name);
            if (detail_user.isPresent()) {
                Detail_UserRequest detail_UserRequest = modelMapper.map(detail_user.get(), Detail_UserRequest.class);
                return Optional.of(detail_UserRequest);
            }
        }
        return Optional.empty();
    }
}
