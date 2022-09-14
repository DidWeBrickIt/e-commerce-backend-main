package com.revature.advice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.revature.annotations.AuthRestriction;
import com.revature.annotations.Authorized;
import com.revature.exceptions.NotAuthorizedException;
import com.revature.services.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AuthAspect {

    @Autowired
    JwtService jwtService;

    @Autowired
    HttpServletRequest request;
    @Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(requiredAuths)")
    public void checkAuth(JoinPoint jp, Authorized requiredAuths)
    {
        String jwt = request.getHeader("auth");
        if(!jwtService.validateJWT(jwt))
        {
            throw new RuntimeException("jwt failed validation");
        }

        DecodedJWT decodedJWT = JWT.decode(jwt);
        String role = decodedJWT.getClaim("authority").asString();

        try
        {
            log.info("role from JWT: " + role + ". Valid roles: " + requiredAuths);
            AuthRestriction auth = AuthRestriction.valueOf(role);

            if (!Arrays.stream(requiredAuths.authorities()).anyMatch(m -> auth == m))
            {
                // user does NOT have a valid auth
                throw new NotAuthorizedException("User is not authorized for endpoint: " + request.getRequestURL());
            }
        }
        catch (IllegalArgumentException e)
        {
            // failure
            throw new NotAuthorizedException("Unrecognized auth value");
        }



    }


//    @Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(authorities)")
//    public void checkAuthorization(final JoinPoint joinPoint, final Authorized authorities) {
//        final SecurityContext securityContext = SecurityContextHolder.getContext();
//
//        if (Objects.isNull(securityContext)) {
//            log.error("Security context null when checking endpoint access for user request");
//            // throw forbidden instead of return
//            throw new NotAuthorizedException("Replace with something else to say forbidden");
//        }
//
//        final Authentication authentication = securityContext.getAuthentication();
//
//        if (Objects.isNull(authentication)) {
//            log.error("Authentication is null when checking end point access for user request");
//            // throw unauthorized instead of return
//            throw new NotAuthorizedException("Incorrect authorization");
//        }
//
//        String username = authentication.getName(); // would be used for logging
//        final Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();
//
//        if (Stream.of(authorities.authorities())
//                .noneMatch(authorityName -> userAuthorities.stream()
//                        .anyMatch(userAuthority -> authorityName.name().equals(userAuthority.getAuthority())))) {
//            log.error("User {} does not have the correct authorities required by endpoint", username);
//            // does not have correct authority. throw http status for FORBIDDEN
//            throw new NotAuthorizedException("Replace with something else to say forbidden");
//        }
//    }

// Spring will autowire a proxy object for the request
// It isn't a request object itself, but if there is an active request
// the proxy will pass method calls to the real request
//    private final HttpServletRequest req;
//
//    public AuthAspect(HttpServletRequest req) {
//        this.req = req;
//    }

// This advice will execute around any method annotated with @Authorized
// If the user is not logged in, an exception will be thrown and handled
// Otherwise, the original method will be invoked as normal.
// In order to expand upon this, you just need to add additional
// values to the AuthRestriction enum.
// Examples might be "Manager" or "Customer" along with suitable Role
// values in the User class.
// Then this method can be expanded to throw exceptions if the user does
// not have the matching role.
// Example:
// User loggedInUser = (User) session.getAttribute("user");
// Role userRole = loggedInUser.getRole();
// if(authorized.value().equals(AuthRestriction.Manager) && !Role.Manager.equals(userRole)) {
//     throw new InvalidRoleException("Must be logged in as a Manager to perform this action");
// }
// Then the RestExceptionHandler class can be expanded to include
// @ExceptionHandler(InvalidRoleException.class)
// which should return a 403 Forbidden such as:
// String errorMessage = "Missing required role to perform this action";
// return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorMessage);
//    @Around("@annotation(authorized)")
//    public Object authenticate(ProceedingJoinPoint pjp, Authorized authorized) throws Throwable {
//
//        HttpSession session = req.getSession(); // Get the session (or create one)
//
//        // If the user is not logged in
//        if(session.getAttribute("user") == null) {
//            throw new NotLoggedInException("Must be logged in to perform this action");
//        }
//
//        return pjp.proceed(pjp.getArgs()); // Call the originally intended method
//    }
}