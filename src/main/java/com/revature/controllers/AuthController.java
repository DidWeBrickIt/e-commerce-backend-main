package com.revature.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.revature.annotations.AuthRestriction;
import com.revature.annotations.Authorized;
import com.revature.dtos.PasswordChange;
import com.revature.dtos.Jwt;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200", "https://green-plant-0ac64be10.1.azurestaticapps.net"}, allowCredentials = "true")

public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

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

    @PatchMapping("/reset")
    public ResponseEntity<User> changeCredential(@RequestBody PasswordChange passwordChange){
        Optional<User> retrieved = userService.findByUsername(passwordChange.getUsername());

        if(retrieved.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(authService.update(passwordChange));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Authorized(authorities = {AuthRestriction.USER})
    @GetMapping
    public ResponseEntity<Integer> getUserIdByJwt(@RequestHeader("auth") String jwt){
        DecodedJWT decodedJWT = JWT.decode(jwt);
        String email = decodedJWT.getClaim("username").asString();
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByUsername(email).get().getId());
    }
}
