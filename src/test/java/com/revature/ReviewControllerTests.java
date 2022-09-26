package com.revature;

import com.revature.controllers.ReviewController;
import com.revature.dtos.ReadableReview;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.services.ReviewService;
import com.revature.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ReviewControllerTests {

    @InjectMocks
    ReviewController reviewController;

    @Mock
    ReviewService reviewServiceMock;

    @Mock
    UserService userServiceMock;

    @Test
    void get_reviews_by_product_test(){
        //theres nothing to test
        ReadableReview rr = new ReadableReview("user", 1, "desc", 1);
        List<ReadableReview> reviews = new ArrayList<>();
        reviews.add(rr);
        when(reviewServiceMock.getReviewsForProduct(1)).thenReturn(reviews);
        List<ReadableReview> ret = reviewController.getReviewsByProdId("jwt", 1);
        Assertions.assertFalse(ret.isEmpty());
    }

    @Test
    void register_review_works_test(){
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkpvaG4gRG9lIiwiYXV0aG9yaXR5IjoiVVNFUiJ9.FgtdhAOtZ__UXTd0rLuHGL2j4gV8mOjr-kDe-r7RSVc";
        Review r = new Review(1,1,1,1,"desc", 1);
        User u = new User();
        when(userServiceMock.findByUsername("John Doe")).thenReturn(Optional.of(u));

        ResponseEntity<Review> re = reviewController.registerReview(jwt, r);
        Assertions.assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }
    @Test
    void review_user_not_found_test(){
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkpvaG4gRG9lIiwiYXV0aG9yaXR5IjoiVVNFUiJ9.FgtdhAOtZ__UXTd0rLuHGL2j4gV8mOjr-kDe-r7RSVc";
        Review r = new Review(1,1,1,1,"desc", 1);
        User u = new User();
        when(userServiceMock.findByUsername("John Doe")).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            ResponseEntity<Review> re = reviewController.registerReview(jwt, r);
        });
    }
}
