package com.ecommerce.serverapp.services;

import com.ecommerce.serverapp.models.Cart;
import com.ecommerce.serverapp.models.Detail_User;
import com.ecommerce.serverapp.models.Role;
import com.ecommerce.serverapp.models.User;
import com.ecommerce.serverapp.models.dto.request.Detail_UserRequest;
import com.ecommerce.serverapp.repositories.Detail_userRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.serverapp.models.dto.request.LoginRequest;
import com.ecommerce.serverapp.models.dto.Response.LoginResponse;
import com.ecommerce.serverapp.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Service
@AllArgsConstructor
public class AuthService {

        private AuthenticationManager authenticationManager;
        private UserRepository usersRepository;
        private AppUserDetailService appUserDetailService;
        private Detail_userRepository detail_userRepository;
        private ModelMapper modelMapper;
        private RoleService roleService;
        private PasswordEncoder passwordEncoder;

        public Detail_User registration(Detail_UserRequest detailUserRequest) {
                Detail_User detailUser = modelMapper.map(detailUserRequest, Detail_User.class);
                User user = modelMapper.map(detailUserRequest, User.class);
                Cart cart = modelMapper.map(detailUserRequest, Cart.class);

                user.setPassword(passwordEncoder.encode(detailUserRequest.getPassword()));

                List<Role> roles = new ArrayList<>();
                roles.add(roleService.getById(1));
                user.setRoles(roles);

                detailUser.setUser(user);
                user.setDetailuser(detailUser);
                user.setCart(cart);
                return detail_userRepository.save(detailUser);
        }

        public LoginResponse login(LoginRequest loginRequest) {
                // set login request
                UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword());

                // set principle
                Authentication auth = authenticationManager.authenticate(authReq);
                SecurityContextHolder.getContext().setAuthentication(auth);

                // set login response
                User user = usersRepository
                                .findByUsernameOrDetailuserEmail(
                                                loginRequest.getUsername(),
                                                loginRequest.getUsername())
                                .get();

                UserDetails userDetails = appUserDetailService.loadUserByUsername(
                                loginRequest.getUsername());

                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setUsername(user.getUsername());
                loginResponse.setEmail(user.getDetailuser().getEmail());
                loginResponse.setAuthorities(
                                userDetails
                                                .getAuthorities()
                                                .stream()
                                                .map(authority -> authority.getAuthority())
                                                .collect(Collectors.toList()));

                return loginResponse;
        }
}