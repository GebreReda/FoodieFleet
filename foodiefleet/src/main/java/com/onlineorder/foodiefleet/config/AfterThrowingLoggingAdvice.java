package com.onlineorder.foodiefleet.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterThrowingLoggingAdvice {
    Logger logger = LoggerFactory.getLogger(AfterThrowingLoggingAdvice.class);

    @Pointcut(value = "execution(* com.onlineorder.foodiefleet.service.RestaurantService.createRestaurantMenu(..))")
    public void myPointcut() {}

    @AfterThrowing(pointcut = "myPointcut()", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint jp, Exception ex) throws JsonProcessingException {
        System.out.println("Logging using AfterThrowing ");
        ObjectMapper mapper = new ObjectMapper();
        String className = jp.getTarget().getClass().toString();
        String methodName = jp.getSignature().getName();
        Object[] array = jp.getArgs();

        logger.info("Class name: " + className + " Method Name " + methodName + " Arguments: "
                + mapper.writeValueAsString(array));
        logger.info("Exception is " + ex.getMessage());
    }
}
