package com.revature.services;


import com.revature.models.Payment;
import com.revature.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public Optional<Payment> findByUserid(int id) { return paymentRepository.findByUserid(id);}
    public Optional<Payment> findById(int id ) {return paymentRepository.findById(id);}
    public Payment save(Payment payment){
        return paymentRepository.save(payment);
    }



}
