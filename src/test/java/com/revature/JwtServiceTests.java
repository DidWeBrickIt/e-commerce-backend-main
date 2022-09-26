package com.revature;

import com.revature.annotations.AuthRestriction;
import com.revature.dtos.Jwt;
import com.revature.services.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtServiceTests {

    @Autowired
    JwtService jwtService;

    @Test
    void create_jwt_test() {
        Jwt jwt = this.jwtService.createJWT("test@test", AuthRestriction.USER);
        Assertions.assertTrue(this.jwtService.validateJWT(jwt.getJwtData()));
    }

    @Test
    void validate_invalid_jwt_test() {
        Assertions.assertFalse(this.jwtService.validateJWT("aaadlanfsdnvoisdn"));
    }

}
