package com.revature.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.revature.annotations.AuthRestriction;
import com.revature.annotations.Authorized;
import com.revature.dtos.Jwt;
import com.revature.dtos.ReadableReview;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Product;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.services.JwtService;
import com.revature.services.ReviewService;
import com.revature.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/reviews")
@CrossOrigin(origins = {"http://localhost:4200", "https://green-plant-0ac64be10.1.azurestaticapps.net"}, allowCredentials = "true")
public class ReviewController
{
    @Autowired
    ReviewService reviewService;

    @Autowired
    UserService userService;



    @Authorized(authorities = {AuthRestriction.USER, AuthRestriction.EMPLOYEE, AuthRestriction.ADMIN})
    @GetMapping("/{id}")
    public List<ReadableReview> getReviewsByProdId(@RequestHeader("auth") String jwt, @PathVariable("id") int prodId)
    {
        return this.reviewService.getReviewsForProduct(prodId);
    }

    @Authorized(authorities = {AuthRestriction.USER, AuthRestriction.EMPLOYEE, AuthRestriction.ADMIN})
    @PostMapping("/register")
    public ResponseEntity<Review> registerReview(@RequestHeader("auth") String jwt, @RequestBody Review review)
    {
        // Expecting review in body to contain everything we need except userId.

       // jwt already validated by auth aspect by this point. Can safely assume jwt is valid.
        DecodedJWT decodedJWT = JWT.decode(jwt);
        String username = decodedJWT.getClaim("username").asString();

        log.info("Username pulled from claim: " + username);

        Optional<User> possibleUser = userService.findByUsername(username);


        if (!possibleUser.isPresent())
        {
            // should always be present if the JWT validated successfully. If we're in here. Something went horribly wrong
            throw new UserNotFoundException();
        }
        int userId = possibleUser.get().getId();
        log.info("retrieved userId: " + userId);
        review.setUserId(userId);

        log.info(("Review about to be saved" + review));
        return new ResponseEntity<Review>(reviewService.registerReview(review), HttpStatus.CREATED);

    }


}
