package com.ecommerce.serverapp.services;

import com.ecommerce.serverapp.models.AppUserDetail;
import com.ecommerce.serverapp.models.User;
import com.ecommerce.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository
                .findByUsernameOrDetailuserEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Username or Email not found!!!"));

        return new AppUserDetail(user);
    }
}