package com.revature.advice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.revature.annotations.AuthRestriction;
import com.revature.annotations.Authorized;
import com.revature.exceptions.NotAuthorizedException;
import com.revature.services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AuthAspect {

    @Autowired
    JwtService jwtService;

    @Autowired
    HttpServletRequest request;
    @Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(requiredAuths)")
    public void checkAuth(JoinPoint jp, Authorized requiredAuths)
    {
        String jwt = request.getHeader("auth");
        if(!jwtService.validateJWT(jwt))
        {
            throw new NotAuthorizedException("jwt failed validation");
        }

        DecodedJWT decodedJWT = JWT.decode(jwt);
        String role = decodedJWT.getClaim("authority").asString();

        try
        {
            log.info("role from JWT: " + role + ". Valid roles: " + requiredAuths);
            AuthRestriction auth = AuthRestriction.valueOf(role);

            if (!Arrays.stream(requiredAuths.authorities()).anyMatch(m -> auth == m))
            {
                // user does NOT have a valid auth
                throw new NotAuthorizedException("User is not authorized for endpoint: " + request.getRequestURL());
            }
        }
        catch (IllegalArgumentException e)
        {
            // failure
            throw new NotAuthorizedException("Unrecognized auth value");
        }
    }
}