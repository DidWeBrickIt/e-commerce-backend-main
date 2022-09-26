package com.revature.dtos;

public class Jwt
{
    private String jwtData;

    private String userAccess;


    public Jwt(String jwtData, String userAccess) {
        this.jwtData = jwtData;
        this.userAccess = userAccess;
    }

    public String getJwtData() {
        return jwtData;
    }

    public void setJwtData(String jwtData) {
        this.jwtData = jwtData;
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
                "jwt='" + jwtData + '\'' +
                ", userAccess='" + userAccess + '\'' +
                '}';
    }
}
