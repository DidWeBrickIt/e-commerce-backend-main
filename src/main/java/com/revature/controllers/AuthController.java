package com.revature.controllers;

import com.revature.annotations.AuthRestriction;
import com.revature.dtos.Jwt;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.models.User;
import com.revature.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200", "https://green-plant-0ac64be10.1.azurestaticapps.net"}, allowCredentials = "true")

public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public Jwt login(@RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        User created = new User(0,
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                AuthRestriction.USER);

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(created));
    }
}
