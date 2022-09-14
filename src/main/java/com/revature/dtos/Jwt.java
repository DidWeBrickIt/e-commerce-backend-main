package com.revature.dtos;

public class Jwt
{
    private String jwt;

    private String userAccess;


    public Jwt(String jwt, String userAccess) {
        this.jwt = jwt;
        this.userAccess = userAccess;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getUserAccess() {
        return userAccess;
    }

    public void setUserAccess(String userAccess) {
        this.userAccess = userAccess;
    }

    @Override
    public String toString() {
        return "Jwt{" +
                "jwt='" + jwt + '\'' +
                ", userAccess='" + userAccess + '\'' +
                '}';
    }
}
