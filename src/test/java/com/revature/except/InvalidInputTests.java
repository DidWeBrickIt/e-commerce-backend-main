package com.revature.except;


import com.revature.exceptions.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InvalidInputTests {

    @Test
    void message_test(){
        InvalidInputException e = new InvalidInputException();
        InvalidInputException ex = new InvalidInputException("test");
        Assertions.assertEquals("test", ex.getMessage());
    }
    @Test
    void throwable_test(){
        RuntimeException e = new RuntimeException("cause");
        InvalidInputException ex = new InvalidInputException(e);
        Assertions.assertEquals(e, ex.getCause());
    }
    @Test
    void throwable_and_message_test(){
        RuntimeException e = new RuntimeException("cause");
        InvalidInputException ex = new InvalidInputException("message",e);
        Assertions.assertEquals("message", ex.getMessage());
        Assertions.assertEquals(e, ex.getCause());
    }
    @Test
    void all_params_test(){
        RuntimeException e = new RuntimeException("cause");
        InvalidInputException ex = new InvalidInputException("message",e, true, true);
        Assertions.assertEquals("message", ex.getMessage());
        Assertions.assertEquals(e, ex.getCause());
    }
}
