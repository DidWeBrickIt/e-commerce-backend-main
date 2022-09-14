package com.revature.annotations;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface Authorized {
    AuthRestriction[] authorities();
}
