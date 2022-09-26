package com.revature;

import com.revature.annotations.AuthRestriction;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void save_user_test() {
        User user = new User(0, "test@test", "test", "Jim", "Henson","", AuthRestriction.USER);
        User savedUser = this.userRepository.save(user);
        Assertions.assertNotEquals(0, savedUser.getId());
    }

    @Test
    void find_user_by_credentials_test() {
        User user = new User(0, "test@test", "test", "Jim", "Henson","", AuthRestriction.USER);
        this.userRepository.save(user);
        Optional<User> foundUser = this.userRepository.findByEmailAndPassword("test@test", "test");
        Assertions.assertEquals(user.getEmail(), foundUser.get().getEmail());
    }

    @Test
    void find_nonexistent_credentials_test() {
        Optional<User> foundUser = this.userRepository.findByEmailAndPassword("sdasdasds", "asdsdasd");
        Assertions.assertFalse(foundUser.isPresent());
    }

    @Test
    void find_user_by_email_test() {
        User user = new User(0, "test@test", "test", "Jim", "Henson","", AuthRestriction.USER);
        this.userRepository.save(user);
        Optional<User> foundUser = this.userRepository.findByEmail("test@test");
        Assertions.assertEquals(user.getEmail(), foundUser.get().getEmail());
    }

    @Test
    void find_nonexistent_email_test() {
        Optional<User> foundUser = this.userRepository.findByEmail("sdasdasdsasdsdasd");
        Assertions.assertFalse(foundUser.isPresent());
    }

}
