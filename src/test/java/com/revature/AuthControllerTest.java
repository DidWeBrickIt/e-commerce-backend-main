package com.revature;

import com.revature.annotations.AuthRestriction;
import com.revature.controllers.AuthController;
import com.revature.dtos.Jwt;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.PasswordChange;
import com.revature.dtos.RegisterRequest;
import com.revature.models.Question;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.QuestionService;
import com.revature.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @InjectMocks
    AuthController authController;

    @Mock
    AuthService authServiceMock;

    @Mock
    UserService userServiceMock;

    @Mock
    QuestionService questionServiceMock;

    @Test
    void login_test(){
        Jwt jwt = new Jwt("jwt", "access");
        LoginRequest lr = new LoginRequest("email", "pass");
        when(authServiceMock.authenticateUser(lr)).thenReturn(jwt);

        Jwt retVal = authController.login(lr);

        Assertions.assertEquals(jwt, retVal);
    }

    @Test
    void register_test(){
        User mockUser = new User(0, "email", "pass", "first", "last", "img", AuthRestriction.USER);
        Question q = new Question();
        RegisterRequest rr = new RegisterRequest(mockUser, q);

        when(authServiceMock.register(mockUser, q)).thenReturn(mockUser);

        ResponseEntity<User> re = authController.register(rr);

        Assertions.assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    void change_credential_works_test(){
        PasswordChange ps = new PasswordChange("user", "newPass");
        User testUser = new User();
        testUser.setId(1);
        Question testQuestion = new Question();
        testQuestion.setAnswer("answer");
        when(userServiceMock.findByUsername("user")).thenReturn(Optional.of(testUser));
        when(questionServiceMock.findByUserid(1)).thenReturn(Optional.of(testQuestion));

        Map<String,String> m = new HashMap<>();
        m.putIfAbsent("username", "user");
        m.putIfAbsent("password", "newPass");
        m.putIfAbsent("answer", "answer");
        ResponseEntity<User> re = authController.changeCredential(m);
        Assertions.assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    void change_credential_fails_test(){
        PasswordChange ps = new PasswordChange("user", "newPass");
        User testUser = new User();
        testUser.setId(1);
        Question testQuestion = new Question();
        testQuestion.setAnswer("badAnswer");
        when(userServiceMock.findByUsername("user")).thenReturn(Optional.of(testUser));
        when(questionServiceMock.findByUserid(1)).thenReturn(Optional.of(testQuestion));

        Map<String,String> m = new HashMap<>();
        m.putIfAbsent("username", "user");
        m.putIfAbsent("password", "newPass");
        m.putIfAbsent("answer", "answer");
        ResponseEntity<User> re = authController.changeCredential(m);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, re.getStatusCode());
    }
    @Test
    void change_credential_not_found_test(){
        PasswordChange ps = new PasswordChange("user", "newPass");
        User testUser = new User();
        testUser.setId(1);
        Question testQuestion = new Question();
        testQuestion.setAnswer("answer");
        when(userServiceMock.findByUsername("user")).thenReturn(Optional.empty());

        Map<String,String> m = new HashMap<>();
        m.putIfAbsent("username", "user");
        m.putIfAbsent("password", "newPass");
        m.putIfAbsent("answer", "answer");
        ResponseEntity<User> re = authController.changeCredential(m);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    void get_id_by_jwt_test(){
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkpvaG4gRG9lIiwiYXV0aG9yaXR5IjoiVVNFUiJ9.FgtdhAOtZ__UXTd0rLuHGL2j4gV8mOjr-kDe-r7RSVc";
        User u = new User();
        when(userServiceMock.findByUsername("John Doe")).thenReturn(Optional.of(u));

        ResponseEntity<Integer> re = authController.getUserIdByJwt(jwt);
        Assertions.assertEquals(HttpStatus.OK, re.getStatusCode());

    }
}
