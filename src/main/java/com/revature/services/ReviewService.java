package com.revature.services;

import com.revature.dtos.ReadableReview;
import com.revature.exceptions.InvalidInputException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.repositories.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserService userService;

    public List<ReadableReview> getReviewsForProduct(int prodId) {
        List<Review> reviews = this.reviewRepository.findByProdIdOrderByTimestampDesc(prodId);
        List<ReadableReview> readableReviews = new ArrayList<>();
        for (Review review : reviews) {
            ReadableReview temp = new ReadableReview();
            Optional<User> possibleUser = this.userService.findById(review.getUserId());

            if (!possibleUser.isPresent()) {
                throw new UserNotFoundException(); // as a foreign key. User should ALWAYS be present if a review is referencing that id
            }
            temp.setUsername(possibleUser.get().getEmail());
            temp.setDescription(review.getDescription());
            temp.setTimestamp(review.getTimestamp());
            temp.setRating(review.getRating());
            log.info(String.valueOf(temp));
            readableReviews.add(temp);
        }
        return readableReviews;
    }

    public Review registerReview(Review review) {
        if (review.getDescription().equals(""))
        {
            throw new InvalidInputException("Review can't have null description");
        }
        if (review.getRating() >5 || review.getRating() < 1)
        {
            throw new InvalidInputException("rating must be between 1 and 5 inclusive. Given rating: " + review.getRating());
        }
        return this.reviewRepository.save(review);
    }

}
