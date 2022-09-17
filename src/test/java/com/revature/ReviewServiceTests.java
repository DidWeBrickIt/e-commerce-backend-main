package com.revature;

import com.revature.annotations.AuthRestriction;
import com.revature.dtos.ReadableReview;
import com.revature.models.Product;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.repositories.ProductRepository;
import com.revature.repositories.UserRepository;
import com.revature.services.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class ReviewServiceTests
{
    @Autowired
    ReviewService reviewService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;


    @Test
    void register_review_test()
    {
        int userId = userRepository.save(new User(0, "", "", "", "", AuthRestriction.USER)).getId();

        int pId1 = productRepository.save(new Product(0, 1, 1, "", "", "Test Product 1")).getId();

        Review savedReview = this.reviewService.registerReview(new Review(0, userId,pId1,1,"Great product",1));

        Assertions.assertEquals("Great product", savedReview.getDescription());

    }

    @Test
    void get_reviews_for_product_test()
    {
        int userId = userRepository.save(new User(0, "", "", "", "", AuthRestriction.USER)).getId();

        int pId1 = productRepository.save(new Product(0, 1, 1, "", "", "Test Product 1")).getId();
        int pId2 = productRepository.save(new Product(0, 1, 1, "", "", "Test Product 1")).getId();

        reviewService.registerReview( new Review(0, userId, pId1, 1, "good", 1));
        reviewService.registerReview( new Review(0, userId, pId1, 1, "good", 1));
        reviewService.registerReview( new Review(0, userId, pId1, 1, "good", 1));
        reviewService.registerReview( new Review(0, userId, pId2, 1, "good", 1));
        reviewService.registerReview( new Review(0, userId, pId2, 1, "good", 1));


        List<ReadableReview> product1Reviews = this.reviewService.getReviewsForProduct(pId1);

        Assertions.assertEquals(3, product1Reviews.size());
    }

}
