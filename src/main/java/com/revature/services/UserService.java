package com.revature.services;

import com.revature.dtos.ProfileInfo;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> findByCredentials(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public Optional<User> findUserById(int id) {return this.userRepository.findById(id);}

    public Optional<User> findByUsername(String email) {
        return this.userRepository.findByEmail(email);
    }


    public User save(User user) {
    //    user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User save(User user, ProfileInfo profileInfo) {

        User updated = user;
        updated.setFirstName(profileInfo.getFirstname());
        updated.setLastName(profileInfo.getLastname());
        return userRepository.save(updated);
    }

}
