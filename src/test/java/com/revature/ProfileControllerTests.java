package com.revature;

import com.revature.controllers.ProfileController;
import com.revature.dtos.ProfileInfo;
import com.revature.exceptions.NotAuthorizedException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.services.JwtService;
import com.revature.services.ProfileService;
import com.revature.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileControllerTests {

    @InjectMocks
    ProfileController profileController;

    @Mock
    JwtService jwtServiceMock;

    @Mock
    UserService userServiceMock;
    @Mock
    ProfileService profileServiceMock;

    @Test
    void update_valid_test(){
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkpvaG4gRG9lIiwiYXV0aG9yaXR5IjoiVVNFUiJ9.FgtdhAOtZ__UXTd0rLuHGL2j4gV8mOjr-kDe-r7RSVc";
        ProfileInfo pi = new ProfileInfo();
        User u = new User();
        u.setId(1);
        pi.setUser(u);
        when(jwtServiceMock.validateJWT(jwt)).thenReturn(true);
        when(userServiceMock.findByUsername("John Doe")).thenReturn(Optional.of(u));
        when(profileServiceMock.save(1, pi)).thenReturn(pi);

        ResponseEntity<ProfileInfo> re = profileController.update(jwt, pi);
        Assertions.assertEquals(HttpStatus.CREATED, re.getStatusCode());
    }

    @Test
    void update_invalid_jwt_test(){
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkpvaG4gRG9lIiwiYXV0aG9yaXR5IjoiVVNFUiJ9.FgtdhAOtZ__UXTd0rLuHGL2j4gV8mOjr-kDe-r7RSVc";
        ProfileInfo pi = new ProfileInfo();
        when(jwtServiceMock.validateJWT(jwt)).thenReturn(false);

        Assertions.assertThrows(NotAuthorizedException.class, () -> {
            profileController.update(jwt, pi);
        });
    }

    @Test
    void update_user_not_found_test(){
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkpvaG4gRG9lIiwiYXV0aG9yaXR5IjoiVVNFUiJ9.FgtdhAOtZ__UXTd0rLuHGL2j4gV8mOjr-kDe-r7RSVc";
        ProfileInfo pi = new ProfileInfo();
        when(jwtServiceMock.validateJWT(jwt)).thenReturn(true);
        when(userServiceMock.findByUsername("John Doe")).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            profileController.update(jwt, pi);
        });
    }

    @Test
    void retrieve_valid_test(){
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkpvaG4gRG9lIiwiYXV0aG9yaXR5IjoiVVNFUiJ9.FgtdhAOtZ__UXTd0rLuHGL2j4gV8mOjr-kDe-r7RSVc";
        ProfileInfo pi = new ProfileInfo();
        User u = new User();
        u.setId(1);
        when(jwtServiceMock.validateJWT(jwt)).thenReturn(true);
        when(userServiceMock.findByUsername("John Doe")).thenReturn(Optional.of(u));
        when(profileServiceMock.get(1)).thenReturn(pi);

        ResponseEntity<ProfileInfo> re = profileController.retrieve(jwt);
        Assertions.assertEquals(HttpStatus.OK, re.getStatusCode());
    }

    @Test
    void retrieve_invalid_jwt_test(){
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkpvaG4gRG9lIiwiYXV0aG9yaXR5IjoiVVNFUiJ9.FgtdhAOtZ__UXTd0rLuHGL2j4gV8mOjr-kDe-r7RSVc";
        when(jwtServiceMock.validateJWT(jwt)).thenReturn(false);

        Assertions.assertThrows(NotAuthorizedException.class, () -> {
            profileController.retrieve(jwt);
        });
    }

    @Test
    void retrieve_user_not_found_test(){
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6IkpvaG4gRG9lIiwiYXV0aG9yaXR5IjoiVVNFUiJ9.FgtdhAOtZ__UXTd0rLuHGL2j4gV8mOjr-kDe-r7RSVc";
        when(jwtServiceMock.validateJWT(jwt)).thenReturn(true);
        when(userServiceMock.findByUsername("John Doe")).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            profileController.retrieve(jwt);
        });
    }
}
