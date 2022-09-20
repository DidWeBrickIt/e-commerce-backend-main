package com.revature.services;

import com.revature.dtos.CredentialChange;
import com.revature.dtos.Jwt;
import com.revature.dtos.LoginRequest;
import com.revature.exceptions.PasswordMismatchException;
import com.revature.exceptions.UserExistsException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    public Optional<User> findByCredentials(String email, String password) {
        return userService.findByCredentials(email, password);
    }

    public Jwt authenticateUser(LoginRequest credentials)
    {
        log.info("entered authenticateUser()");
        Optional<User> possibleUser = this.userService.findByUsername(credentials.getEmail());

        if (!possibleUser.isPresent())
        {
            // username doesn't exist throw 404
            throw new UserNotFoundException();
        }

        User user = possibleUser.get();

        if (!user.getPassword().equals(credentials.getPassword()))
        {
            // throw password mismatch exception
            throw new PasswordMismatchException();
        }

        // safe to build jwt
        return this.jwtService.createJWT(user.getEmail(), user.getAuthRestriction());
    }


    public User register(User user)
    {
       if (this.userService.findByUsername(user.getEmail()).isPresent())
       {
           // user already exists
           throw new UserExistsException();
       }
        return userService.save(user);
    }

    public User update(CredentialChange credentialChange){

        Optional<User> updated = userService.findByUsername(credentialChange.getUsername());

        if(!credentialChange.getNewPass().isEmpty()){
            updated.get().setPassword(credentialChange.getNewPass());
        }
        if(!credentialChange.getNewEmail().isEmpty()){
            updated.get().setEmail(credentialChange.getNewEmail());
        }

        return userService.save(updated.get());
    }
}
