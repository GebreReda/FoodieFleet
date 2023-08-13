package com.onlineorder.foodiefleet.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterReturnAdvice {

    Logger logger = LoggerFactory.getLogger(AfterReturnAdvice.class);

    //@Pointcut(value="execution(* com.onlineorder.foodiefleet..*(..))")
    @Pointcut(value="execution(* com.onlineorder.foodiefleet.controller.CustomerOrderController..*(..))")
    public void myPointcut(){}

    @AfterReturning(value = "myPointcut()",returning = "result")
    public void afterReturnMethod(JoinPoint jp, Object result) throws JsonProcessingException {
        System.out.println("Logging using AfterReturning ");

        ObjectMapper mapper = new ObjectMapper();
        String className = jp.getTarget().getClass().toString();
        String signature = jp.getSignature().getName();
        Object[] args = jp.getArgs();

        logger.info("Class Name: "+className + " Signature: "+signature + " Arguments: "+mapper.writeValueAsString(args));
        logger.info("Returned result is: "+result.toString());


        System.out.println("Returned value: " + result.getClass().getName());
    }
}
