package com.revature.modeldtos;

import com.revature.dtos.*;
import com.revature.models.Address;
import com.revature.models.Payment;
import com.revature.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DtoTests {

    @Test
    void jwt_dto_test(){
        Jwt jwt = new Jwt("jwt", "access");

        jwt.setJwt("jwt2");
        jwt.setUserAccess("access2");

        Assertions.assertEquals("jwt2", jwt.getJwt());
        Assertions.assertEquals("access2", jwt.getUserAccess());

        Assertions.assertEquals("Jwt{jwt='jwt2', userAccess='access2'}", jwt.toString());
    }

    @Test
    void loginrequest_dto_test(){
        LoginRequest lr = new LoginRequest();

        lr.setEmail("email");
        lr.setPassword("pass");

        Assertions.assertEquals("email", lr.getEmail());
        Assertions.assertEquals("pass", lr.getPassword());

        Assertions.assertEquals("LoginRequest(email=email, password=pass)", lr.toString());
    }

    @Test
    void password_change_dto_test(){
        PasswordChange pc = new PasswordChange();

        pc.setUsername("user");
        pc.setNewPass("npass");

        Assertions.assertEquals("user", pc.getUsername());
        Assertions.assertEquals("npass", pc.getNewPass());

        Assertions.assertEquals("PasswordChange(username=user, newPass=npass)", pc.toString());

        PasswordChange pc2 = new PasswordChange("user2", "npass2");
    }

    @Test
    void product_info_dto_test(){
        ProductInfo pi = new ProductInfo();

        pi.setId(1);
        pi.setQuantity(1);

        Assertions.assertEquals(1, pi.getId());
        Assertions.assertEquals(1, pi.getQuantity());

        Assertions.assertEquals("ProductInfo(id=1, quantity=1)", pi.toString());

        ProductInfo pi2 = new ProductInfo(1, 2);
    }

    @Test
    void profile_info_dto_test(){
        User u = new User();
        Payment pay = new Payment();
        Address addy = new Address();

        ProfileInfo pi = new ProfileInfo();

        pi.setUser(u);
        pi.setPayment(pay);
        pi.setAddress(addy);

        Assertions.assertTrue(u.equals(pi.getUser()));
        Assertions.assertTrue(pay.equals(pi.getPayment()));
        Assertions.assertTrue(addy.equals(pi.getAddress()));

        ProfileInfo pi2 = new ProfileInfo(u,pay,addy);
    }

    @Test
    void readable_review_dto_test(){
        ReadableReview rr = new ReadableReview();
        ReadableReview rr2 = new ReadableReview("user", 1, "desc", 1);

        rr.setUsername("user");
        rr.setTimestamp(1);
        rr.setDescription("desc");
        rr.setRating(1);

        Assertions.assertEquals("user", rr.getUsername());
        Assertions.assertEquals(1,rr.getTimestamp());
        Assertions.assertEquals("desc", rr.getDescription());
        Assertions.assertEquals(1,rr.getRating());
    }

    @Test
    void register_request_dto_test(){
        RegisterRequest rr = new RegisterRequest();
        RegisterRequest rr2 = new RegisterRequest("email", "pass", "first", "last", "img");

        rr.setEmail("email");
        rr.setPassword("pass");
        rr.setFirstName("first");
        rr.setLastName("last");
        rr.setImageUrl("img");

        Assertions.assertEquals("email", rr.getEmail());
        Assertions.assertEquals("pass", rr.getPassword());
        Assertions.assertEquals("first", rr.getFirstName());
        Assertions.assertEquals("last", rr.getLastName());
        Assertions.assertEquals("img", rr.getImageUrl());
     }
}
