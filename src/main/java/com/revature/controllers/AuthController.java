package com.revature.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.revature.annotations.AuthRestriction;
import com.revature.annotations.Authorized;
import com.revature.dtos.PasswordChange;
import com.revature.dtos.Jwt;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.models.Question;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.QuestionService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200", "https://green-plant-0ac64be10.1.azurestaticapps.net"}, allowCredentials = "true")

public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @PostMapping("/login")
    public Jwt login(@RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        User user = registerRequest.getUser();
        Question question = registerRequest.getQuestion();
        user.setAuthRestriction(AuthRestriction.USER);
        user.setId(0);
        user.setImageurl("../../../assets/images/profile_pic/the_infomaniac-head.png");
        question.setId(0);
        question.setUserid(0);
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(user, question));
    }

    @PatchMapping("/reset")
    public ResponseEntity<User> changeCredential(@RequestBody Map<String, String> requestBody){
        PasswordChange passwordChange = new PasswordChange(requestBody.get("username"), requestBody.get("password"));
        String answer = requestBody.get("answer");
        Optional<User> retrieved = userService.findByUsername(passwordChange.getUsername());
        if(retrieved.isPresent()){
            Optional<Question> retrievedQuestion = questionService.findByUserid(retrieved.get().getId());
            if(retrievedQuestion.isPresent()) {
                if (retrievedQuestion.get().getAnswer().equals(answer)) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(authService.update(passwordChange));
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @Authorized(authorities = {AuthRestriction.USER, AuthRestriction.ADMIN})
    @GetMapping
    public ResponseEntity<Integer> getUserIdByJwt(@RequestHeader("auth") String jwt){
        DecodedJWT decodedJWT = JWT.decode(jwt);
        String email = decodedJWT.getClaim("username").asString();
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByUsername(email).get().getId());
    }
}
