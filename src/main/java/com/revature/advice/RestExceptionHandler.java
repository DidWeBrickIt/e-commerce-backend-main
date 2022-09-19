package com.revature.advice;

import com.revature.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotLoggedInException.class)
    public ResponseEntity<Object> handleNotLoggedInException(HttpServletRequest request, NotLoggedInException notLoggedInException) {

        String errorMessage = "Must be logged in to perform this action";

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<Object> handlePasswordMismatchException(HttpServletRequest request, PasswordMismatchException passwordMismatchException)
    {
        String errorMessage = "Incorrect Password";

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<Object> handleNotAuthorizedException(HttpServletRequest request, NotAuthorizedException notAuthorizedException)
    {
        String errorMessage = "Forbidden";
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(HttpServletRequest request, UserNotFoundException userNotFoundException)
    {
        String errorMessaage = "user not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessaage);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Object> handleUserExistsException(HttpServletRequest request, UserExistsException userExistsException)
    {
        String errorMessage = "username already exists";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Object> handInvalidInputException(HttpServletRequest request, InvalidInputException invalidInputException)
    {
        String errorMessage = "Invalid Input given for request";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }


}
