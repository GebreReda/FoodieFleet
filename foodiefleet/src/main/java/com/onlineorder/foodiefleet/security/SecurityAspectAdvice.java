package com.onlineorder.foodiefleet.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.*;

@Aspect
@Component
public class SecurityAspectAdvice {
    @Pointcut(value = "execution(* com.onlineorder.foodiefleet.controller.UserController.deleteUser(..))")
    public void mySecurityCheck(){}

    @Before("mySecurityCheck()")
    public void checkAuthorization(JoinPoint jp) throws IllegalAccessException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Object[] args = jp.getArgs();
        Long userId = 0L;
        try {
            JsonNode jsonNode = mapper.valueToTree(args[0]);
            // Extract individual keys and values
            userId = jsonNode.get("id").asLong();
            //String firstName = jsonNode.get("firstName").asText();
            //String lastName = jsonNode.get("lastName").asText();
            //String email = jsonNode.get("email").asText();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!isAuthorized(userId)){
            throw new SecurityException("Unauthorized access!");
        }
        else{
            System.out.println("User is authorized to access!");
        }
    }

    private boolean isAuthorized(Long userId) {
        // In a real-world scenario, you would typically retrieve user details
        // from your authentication system or database and perform proper authorization checks
        // For demonstration purposes, we'll use a simple map to store user roles

        // Simulate a map of user roles (replace with your actual data source)
        Map<Long, Set<String>> userRolesMap = new HashMap<>();
        userRolesMap.put(1L, new HashSet<>(Arrays.asList("ROLE_ADMIN")));
        userRolesMap.put(2L, new HashSet<>(Arrays.asList("ROLE_USER")));

        // Get user roles based on userId (assuming userId is the key in the map)
        Set<String> userRoles = userRolesMap.get(userId);

        // Perform authorization check based on user roles
        return userRoles != null && userRoles.contains("ROLE_ADMIN");
    }
}
