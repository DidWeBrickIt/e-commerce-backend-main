package com.revature.controllers;

import com.revature.dtos.ProfileInfo;
import com.revature.exceptions.QuestionNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Question;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.services.QuestionService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins = {"http://localhost:4200", "https://green-plant-0ac64be10.1.azurestaticapps.net"}, allowCredentials = "true")
public class QuestionController {
    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;
    @PostMapping
    public Map<String, String>  retrieveByUserName(@RequestBody Map<String, String> requestBody){
        try{
            String userName = requestBody.get("username");
            Optional<User> possibleUser = userService.findByUsername(userName);
            if (possibleUser.isPresent()) {
                Optional<Question> question = questionService.findByUserid(possibleUser.get().getId());
                Map<String, String> result = new HashMap<>();
                if(question.isPresent()){
                    result.put("question", question.get().getSecurityQuestion());
                    return result;
                } else{
                    throw new QuestionNotFoundException();
                }

            } else {
                throw new UserNotFoundException();
            }
        } catch (NullPointerException e){
            throw new UserNotFoundException();
        }
    }
}
