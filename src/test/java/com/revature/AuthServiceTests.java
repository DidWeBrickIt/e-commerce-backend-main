package com.revature;

import com.revature.annotations.AuthRestriction;
import com.revature.dtos.LoginRequest;
import com.revature.exceptions.PasswordMismatchException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.services.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AuthServiceTests {

    @Autowired
    AuthService authService;

    @Test
    public void authenticate_user_user_does_not_exist_test() {
        User user = new User(0, "test@test", "test", "test", "testson", AuthRestriction.USER);
        this.authService.register(user);
        LoginRequest loginRequest = new LoginRequest("tee@tee", "test");
        Assertions.assertThrows(UserNotFoundException.class, () -> this.authService.authenticateUser(loginRequest));
    }

    @Test
    public void authenticate_user_password_mismatch_test() {
        User user = new User(0, "test@test", "test", "test", "testson", AuthRestriction.USER);
        this.authService.register(user);
        LoginRequest loginRequest = new LoginRequest("test@test", "tee");
        Assertions.assertThrows(PasswordMismatchException.class, () -> this.authService.authenticateUser(loginRequest));
    }

    @Test
    public void authenticate_user_test() {
        User user = new User(0, "test@test", "test", "test", "testson", AuthRestriction.USER);
        this.authService.register(user);
        LoginRequest loginRequest = new LoginRequest("test@test", "test");
        Assertions.assertNotNull(this.authService.authenticateUser(loginRequest));
    }

}
