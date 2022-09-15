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
    AddressService addressService;
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;


    @Authorized(authorities = {AuthRestriction.LoggedIn , AuthRestriction.EMPLOYEE, AuthRestriction.ADMIN})
    @PutMapping
    public ResponseEntity<ProfileInfo> update(@RequestHeader("auth") String jwt, @RequestBody ProfileInfo profileInfo){

        if(jwtService.validateJWT(jwt)){

            DecodedJWT decodedJWT = JWT.decode(jwt);
            String username = decodedJWT.getClaim("username").asString();
            Optional<User> foundUser = userService.findByUsername(username);

            if(foundUser.isPresent()){

                Address updatedAddress = addressService.save(foundUser.get().getId(), profileInfo);
                User updatedUser = userService.save(foundUser.get(), profileInfo);

            return ResponseEntity.status(HttpStatus.CREATED).body(profileInfo);

            }else{
                throw new UserNotFoundException();
            }

        }else{
            throw new NotAuthorizedException();
        }

    }


    @Authorized(authorities = {AuthRestriction.LoggedIn , AuthRestriction.EMPLOYEE, AuthRestriction.ADMIN})
    @GetMapping
    public ResponseEntity<ProfileInfo> retrieve(@RequestHeader("auth") String jwt){

        if(jwtService.validateJWT(jwt)){

            DecodedJWT decodedJWT = JWT.decode(jwt);
            String username = decodedJWT.getClaim("username").asString();
            Optional<User> foundUser = userService.findByUsername(username);

            if(foundUser.isPresent()){

                Optional<Address> foundAddress = addressService.findByUserid(foundUser.get().getId());

                ProfileInfo profileInfo = new ProfileInfo();
                profileInfo.setFirstname(foundUser.get().getFirstName());
                profileInfo.setLastname(foundUser.get().getLastName());


                if(foundAddress.isPresent()){

                    profileInfo.setAddress1(foundAddress.get().getAddress1());
                    profileInfo.setAddress2(foundAddress.get().getAddress2());
                    profileInfo.setCity(foundAddress.get().getCity());
                    profileInfo.setCountry(foundAddress.get().getCountry());
                    profileInfo.setState(foundAddress.get().getState());
                    profileInfo.setZip(
                            String.valueOf(foundAddress.get().getZip()) );

                    return ResponseEntity.status(HttpStatus.OK).body(profileInfo);

                }

                return ResponseEntity.status(HttpStatus.OK).body(profileInfo);

            }else{throw new UserNotFoundException();}

        }else{throw new NotAuthorizedException();}

    }


}
