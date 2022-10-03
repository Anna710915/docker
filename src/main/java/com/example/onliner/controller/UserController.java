package com.example.onliner.controller;

import com.example.onliner.controller.dto.AuthenticationRequestDto;
import com.example.onliner.controller.dto.AuthenticationResponseDto;
import com.example.onliner.controller.dto.UserDto;
import com.example.onliner.domain.User;
import com.example.onliner.security.JwtUtil;
import com.example.onliner.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/login", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponseDto login(@RequestBody AuthenticationRequestDto requestDto){
        String username = requestDto.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
        User user = userService.retrieveByUsername(username);
        String token = jwtUtil.createToken(username, List.of(user.getRole()));
        return new AuthenticationResponseDto(username, token, user.getRole());
    }

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDto userDto) {
        String password = userDto.getPassword();
        userDto.setPassword(passwordEncoder.encode(password));
        userService.create(userBuilder(userDto));
    }

    private User userBuilder(UserDto userDto) {
        User user = new User();
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setRole(User.UserRole.USER);
        return user;
    }
}
