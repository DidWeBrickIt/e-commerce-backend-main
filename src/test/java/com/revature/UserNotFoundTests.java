package com.revature;

import com.revature.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserNotFoundTests {

    @Test
    void message_test(){
        UserNotFoundException ex = new UserNotFoundException("message");
        Assertions.assertEquals("message", ex.getMessage());
    }
    @Test
    void cause_test(){
        RuntimeException e = new RuntimeException("cause");
        UserNotFoundException ex = new UserNotFoundException(e);
        Assertions.assertEquals(e, ex.getCause());
    }
    @Test
    void cause_and_message_test(){
        RuntimeException e = new RuntimeException("cause");
        UserNotFoundException ex = new UserNotFoundException("message",e);
        Assertions.assertEquals("message", ex.getMessage());
        Assertions.assertEquals(e, ex.getCause());
    }
    @Test
    void all_params_test(){
        RuntimeException e = new RuntimeException("cause");
        UserNotFoundException ex = new UserNotFoundException("message",e,true,true);
        Assertions.assertEquals("message", ex.getMessage());
        Assertions.assertEquals(e, ex.getCause());
    }
}
