package com.enterprise.analytics.auth.controller;

import com.enterprise.analytics.auth.domain.entity.RoleEntity;
import com.enterprise.analytics.auth.domain.entity.UserEntity;
import com.enterprise.analytics.auth.dto.LoginRequest;
import com.enterprise.analytics.auth.dto.LoginResponse;
import com.enterprise.analytics.auth.dto.RegisterRequest;
import com.enterprise.analytics.auth.repository.RoleRepository;
import com.enterprise.analytics.auth.repository.UserRepository;
import com.enterprise.analytics.auth.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {


            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.username(),
                                    request.password()
                            )
                    );
         String token = jwtUtil.generateAccessToken(authentication);
        return ResponseEntity.ok(
                Map.of("accessToken", token)
        );


    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        if(userRepository.findByUsername(request.username()).isPresent()) {
           throw new RuntimeException("User already exists");
        }

        RoleEntity role = roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("Role not found"));


        UserEntity user = UserEntity.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .enabled(true)
                .roles(Set.of(role))
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

}
