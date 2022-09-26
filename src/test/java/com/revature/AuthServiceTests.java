package com.revature;

import com.revature.annotations.AuthRestriction;
import com.revature.dtos.LoginRequest;
import com.revature.exceptions.PasswordMismatchException;
import com.revature.exceptions.UserExistsException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Question;
import com.revature.models.User;
import com.revature.services.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AuthServiceTests {

    @Autowired
    AuthService authService;

    @Test
    void authenticate_user_user_does_not_exist_test() {
        User user = new User(0, "test@test", "test", "test", "testson","", AuthRestriction.USER);
        Question question = new Question(0, 0, "Name of my dog?", "candy");
        this.authService.register(user, question);
        LoginRequest loginRequest = new LoginRequest("tee@tee", "test");
        Assertions.assertThrows(UserNotFoundException.class, () -> this.authService.authenticateUser(loginRequest));
    }

    @Test
    void authenticate_user_password_mismatch_test() {
        User user = new User(0, "test@test", "test", "test", "testson","", AuthRestriction.USER);
        Question question = new Question(0, 0, "Name of my dog?", "candy");
        this.authService.register(user, question);
        LoginRequest loginRequest = new LoginRequest("test@test", "tee");
        Assertions.assertThrows(PasswordMismatchException.class, () -> this.authService.authenticateUser(loginRequest));
    }

    @Test
    void authenticate_user_test() {
        User user = new User(0, "test@test", "test", "test", "testson","", AuthRestriction.USER);
        Question question = new Question(0, 0, "Name of my dog?", "candy");
        this.authService.register(user, question);
        LoginRequest loginRequest = new LoginRequest("test@test", "test");
        Assertions.assertNotNull(this.authService.authenticateUser(loginRequest));
    }

    @Test
    void find_by_credentials_test()
    {
        Question question = new Question(0, 0, "Name of my dog?", "candy");
        int id = authService.register(new User(0, "test@test", "test", "test", "test","", AuthRestriction.USER), question).getId();
      Assertions.assertEquals(id, authService.findByCredentials("test@test", "test").get().getId());
    }

    @Test
    void throw_user_already_exist_test()
    {
        Question question = new Question(0, 0, "Name of my dog?", "candy");
        User testUser = new User(0, "test@test", "test", "test", "test","", AuthRestriction.USER);
        authService.register(testUser, question);
        Assertions.assertThrows(UserExistsException.class, () ->authService.register(testUser, question));
    }


}
