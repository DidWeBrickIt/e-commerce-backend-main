package com.revature.annotations;

public enum AuthRestriction {
    LoggedIn, // means you're a guest atm? dik
    USER,
    EMPLOYEE, // support for "Tech Support Chat" stretch goal
    ADMIN
}
