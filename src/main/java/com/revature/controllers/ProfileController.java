package com.revature.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.revature.annotations.AuthRestriction;
import com.revature.annotations.Authorized;
import com.revature.dtos.ProfileInfo;
import com.revature.exceptions.NotAuthorizedException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Address;
import com.revature.models.User;
import com.revature.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/profile")
@CrossOrigin (origins = {"http://localhost:4200", "https://green-plant-0ac64be10.1.azurestaticapps.net"}, allowCredentials = "true")
public class ProfileController {

    @Autowired
    ProfileService profileService;
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;


    @Authorized(authorities = {AuthRestriction.LoggedIn, AuthRestriction.USER, AuthRestriction.EMPLOYEE, AuthRestriction.ADMIN})
    @PatchMapping
    public ResponseEntity<ProfileInfo> update(@RequestHeader("auth") String jwt, @RequestBody ProfileInfo profileInfo){

        System.out.println("Inside Profile Controller");
        if(jwtService.validateJWT(jwt)){

            DecodedJWT decodedJWT = JWT.decode(jwt);
            String username = decodedJWT.getClaim("username").asString();
            Optional<User> foundUser = userService.findByUsername(username);

            if(foundUser.isPresent()){

                ProfileInfo updated = this.profileService.save(foundUser.get().getId(), profileInfo);
                return ResponseEntity.status(HttpStatus.CREATED).body(updated);

            }else{throw new UserNotFoundException();}
        }else{throw new NotAuthorizedException();}
    }


    @Authorized(authorities = {AuthRestriction.LoggedIn, AuthRestriction.USER, AuthRestriction.EMPLOYEE, AuthRestriction.ADMIN})
    @GetMapping
    public ResponseEntity<ProfileInfo> retrieve(@RequestHeader("auth") String jwt){

        if(jwtService.validateJWT(jwt)){

            DecodedJWT decodedJWT = JWT.decode(jwt);
            String username = decodedJWT.getClaim("username").asString();
            Optional<User> foundUser = userService.findByUsername(username);

            if(foundUser.isPresent()){

                ProfileInfo retrieved = this.profileService.get(foundUser.get().getId());
                return ResponseEntity.status(HttpStatus.OK).body(retrieved);

            }else{throw new UserNotFoundException();}

        }else{throw new NotAuthorizedException();}

    }


}
