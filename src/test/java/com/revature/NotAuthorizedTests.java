package com.revature;

import com.revature.exceptions.NotAuthorizedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NotAuthorizedTests {

    @Test
    void message_test(){
        NotAuthorizedException ex = new NotAuthorizedException("message");
        Assertions.assertEquals("message", ex.getMessage());
    }
    @Test
    void cause_test(){
        RuntimeException e = new RuntimeException("cause");
        NotAuthorizedException ex = new NotAuthorizedException(e);
        Assertions.assertEquals(e, ex.getCause());
    }
    @Test
    void cause_and_message_test(){
        RuntimeException e = new RuntimeException("cause");
        NotAuthorizedException ex = new NotAuthorizedException("message",e);
        Assertions.assertEquals("message", ex.getMessage());
        Assertions.assertEquals(e, ex.getCause());
    }
    @Test
    void all_params_test(){
        RuntimeException e = new RuntimeException("cause");
        NotAuthorizedException ex = new NotAuthorizedException("message",e,true,true);
        Assertions.assertEquals("message", ex.getMessage());
        Assertions.assertEquals(e, ex.getCause());
    }
}
