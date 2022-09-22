package com.revature.services;

import com.revature.dtos.PasswordChange;
import com.revature.dtos.Jwt;
import com.revature.dtos.LoginRequest;
import com.revature.exceptions.PasswordMismatchException;
import com.revature.exceptions.UserExistsException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Question;
import com.revature.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

@Service
@Slf4j
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @Autowired
    private TransactionTemplate template;

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


    @Transactional
    public User register(User user, Question question)
    {
       if (this.userService.findByUsername(user.getEmail()).isPresent())
       {
           // user already exists
           throw new UserExistsException();
       }

//        int uId= template.execute(status ->  {
//            User newUser = userService.save(user);
//            return newUser.getId();
//        });
       User newUser = userService.save(user);
       question.setUserid(newUser.getId());
       questionService.save(question);
       return newUser;
    }

    public User update(PasswordChange passwordChange){

        Optional<User> updated = userService.findByUsername(passwordChange.getUsername());
        updated.get().setPassword(passwordChange.getNewPass());
        return userService.save(updated.get());

    }
}
