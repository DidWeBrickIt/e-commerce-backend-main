package com.revature.repositories;

import com.revature.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByProdIdOrderByTimestampDesc(int prodId);
}
