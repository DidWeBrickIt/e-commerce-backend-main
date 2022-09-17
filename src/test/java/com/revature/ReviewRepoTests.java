package com.revature;

import com.revature.annotations.AuthRestriction;
import com.revature.models.Product;
import com.revature.models.Review;
import com.revature.models.User;
import com.revature.repositories.ProductRepository;
import com.revature.repositories.ReviewRepository;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class ReviewRepoTests {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void abstract_find_all_with_prod_id_test() {

        int userId = userRepository.save(new User(0, "notnull@gmail.com", "notNull", "first", "last", AuthRestriction.USER)).getId();

        int pId1 = productRepository.save(new Product(0, 1, 1, "", "", "Test Product 1")).getId();
        int pId2 = productRepository.save(new Product(0, 1, 1, "", "", "Test Product 1")).getId();

        reviewRepository.save( new Review(0, userId, pId1, 1, "good", 1));
        reviewRepository.save( new Review(0, userId, pId1, 1, "good", 1));
        reviewRepository.save( new Review(0, userId, pId1, 1, "good", 1));
        reviewRepository.save( new Review(0, userId, pId2, 1, "good", 1));
        reviewRepository.save( new Review(0, userId, pId2, 1, "good", 1));

        List<Review> product1Reviews = this.reviewRepository.findByProdIdOrderByTimestampDesc(pId1);

        Assertions.assertEquals(3, product1Reviews.size());
    }

    @Test
    void abstract_find_all_with_prod_sort_test()
    {
        int userId = userRepository.save(new User(0, "notnull@gmail.com", "notNull", "first", "last", AuthRestriction.USER)).getId();

        int pId1 = productRepository.save(new Product(0, 1, 1, "", "", "Test Product 1")).getId();

        reviewRepository.save( new Review(0, userId, pId1, 1, "good", 1));
        reviewRepository.save( new Review(0, userId, pId1, 1000, "good", 1));
        reviewRepository.save( new Review(0, userId, pId1, 10000, "good", 1));
        reviewRepository.save( new Review(0, userId, pId1, 100000, "good", 1));
       Review shouldBeFirst = reviewRepository.save( new Review(0, userId, pId1, 1000000, "good", 1));

        List<Review> product1Reviews = this.reviewRepository.findByProdIdOrderByTimestampDesc(pId1);

        Assertions.assertEquals(shouldBeFirst, product1Reviews.get(0));

    }


}
