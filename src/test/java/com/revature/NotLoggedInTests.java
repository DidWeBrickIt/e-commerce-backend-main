package com.revature;

import com.revature.exceptions.NotLoggedInException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotLoggedInTests {

    @Test
    void message_test(){
        NotLoggedInException e = new NotLoggedInException();
        NotLoggedInException ex = new NotLoggedInException("message");
        Assertions.assertEquals("message", ex.getMessage());
    }
    @Test
    void cause_test(){
        RuntimeException e = new RuntimeException("cause");
        NotLoggedInException ex = new NotLoggedInException(e);
        Assertions.assertEquals(e, ex.getCause());
    }
    @Test
    void cause_and_message_test(){
        RuntimeException e = new RuntimeException("cause");
        NotLoggedInException ex = new NotLoggedInException("message",e);
        Assertions.assertEquals("message", ex.getMessage());
        Assertions.assertEquals(e, ex.getCause());
    }
    @Test
    void all_params_test(){
        RuntimeException e = new RuntimeException("cause");
        NotLoggedInException ex = new NotLoggedInException("message",e,true,true);
        Assertions.assertEquals("message", ex.getMessage());
        Assertions.assertEquals(e, ex.getCause());
    }
}
