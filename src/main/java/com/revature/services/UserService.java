package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> findByCredentials(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public Optional<User> findByUsername(String email) {
        return this.userRepository.findByEmail(email);
    }
    public Optional<User> findById(int id){ return this.userRepository.findById(id);}
    public User save(User user) {
        return userRepository.save(user);
    }


}
