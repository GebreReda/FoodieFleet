package com.onlineorder.foodiefleet.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BeforeLoggingAdvice {
    Logger logger = LoggerFactory.getLogger(BeforeLoggingAdvice.class);
    @Pointcut(value = "execution(* com.onlineorder.foodiefleet.controller.RestaurantController.ceateAMenu(..))")
    public void myPointcut(){

    }
    @Before("myPointcut()")
    //@Before("execution(* com.onlineorder.foodiefleet.controller.AddMenuController.ceateAMenu(..))")
    public void BeforeLogger(JoinPoint jp) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Logging using Before: ");
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().toString();
        Object[] array = jp.getArgs();

        logger.info("Class Name : "+className + " Method signature : "+methodName +"()" + "Arguments"
                + mapper.writeValueAsString(array));
    }
}
