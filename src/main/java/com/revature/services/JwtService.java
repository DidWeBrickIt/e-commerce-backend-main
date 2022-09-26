package com.revature.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.revature.annotations.AuthRestriction;
import com.revature.dtos.Jwt;
import org.springframework.stereotype.Service;

@Service
public class JwtService
{
    private String secret = "Secret. Replace hard coded with something more secure";
    private final Algorithm algorithm = Algorithm.HMAC256(secret);

    public Jwt createJWT(String username, AuthRestriction role)
    {
        String jwt = JWT.create()
                .withClaim("username", username)
                .withClaim("authority", role.name())
                .sign(algorithm);
        return new Jwt(jwt, role.name());
    }

    public boolean validateJWT(String jwt)
    {
        JWTVerifier verifier = JWT.require(algorithm).build();

        try
        {
            verifier.verify(jwt);
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

}
