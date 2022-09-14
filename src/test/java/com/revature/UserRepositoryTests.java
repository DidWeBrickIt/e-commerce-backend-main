package com.revature;

import com.revature.annotations.AuthRestriction;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void save_user_test() {
        User user = new User(0, "test@test", "test", "Jim", "Henson", AuthRestriction.USER);
        User savedUser = this.userRepository.save(user);
        Assertions.assertNotEquals(0, savedUser.getId());
    }

}
