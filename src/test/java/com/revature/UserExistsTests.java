package com.revature;

import com.revature.exceptions.UserExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserExistsTests {

    @Test
    void message_test(){
        UserExistsException ex = new UserExistsException("message");
        Assertions.assertEquals("message", ex.getMessage());
    }
    @Test
    void cause_test(){
        RuntimeException e = new RuntimeException("cause");
        UserExistsException ex = new UserExistsException(e);
        Assertions.assertEquals(e, ex.getCause());
    }
    @Test
    void cause_and_message_test(){
        RuntimeException e = new RuntimeException("cause");
        UserExistsException ex = new UserExistsException("message",e);
        Assertions.assertEquals("message", ex.getMessage());
        Assertions.assertEquals(e, ex.getCause());
    }
    @Test
    void all_params_test(){
        RuntimeException e = new RuntimeException("cause");
        UserExistsException ex = new UserExistsException("message",e,true,true);
        Assertions.assertEquals("message", ex.getMessage());
        Assertions.assertEquals(e, ex.getCause());
    }
}
