package com.example.onliner.security;

import com.example.onliner.domain.User;
import com.example.onliner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtUserDetailService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public JwtUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.retrieveByUsername(username);
        return new JwtUser(user.getId(), user.getUsername(), user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().name())));
    }
}
