package com.revature;

import com.revature.exceptions.PasswordMismatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PasswordMismatchTests {

    @Test
    void message_test(){
        PasswordMismatchException ex = new PasswordMismatchException("message");
        Assertions.assertEquals("message", ex.getMessage());
    }
    @Test
    void cause_test(){
        RuntimeException e = new RuntimeException("cause");
        PasswordMismatchException ex = new PasswordMismatchException(e);
        Assertions.assertEquals(e, ex.getCause());
    }
    @Test
    void cause_and_message_test(){
        RuntimeException e = new RuntimeException("cause");
        PasswordMismatchException ex = new PasswordMismatchException("message",e);
        Assertions.assertEquals("message", ex.getMessage());
        Assertions.assertEquals(e, ex.getCause());
    }
    @Test
    void all_params_test(){
        RuntimeException e = new RuntimeException("cause");
        PasswordMismatchException ex = new PasswordMismatchException("message",e,true,true);
        Assertions.assertEquals("message", ex.getMessage());
        Assertions.assertEquals(e, ex.getCause());
    }
}
