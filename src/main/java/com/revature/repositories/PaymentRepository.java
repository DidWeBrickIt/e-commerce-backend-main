package com.revature.repositories;

import com.revature.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Optional<Payment> findByUserid(int id);
    Optional<Payment> findById(int id);
}
