package com.onlineorder.foodiefleet.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AroundLoggingAdvice {
    Logger log = LoggerFactory.getLogger(AroundLoggingAdvice.class);

    @Pointcut(value="execution(* com.onlineorder.foodiefleet.controller.CustomerOrderController..*(..))")
    public void myPointcut(){ }

    @Around("myPointcut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Logging using Around: ");

        ObjectMapper mapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();

       log.info("method invoked "+ className + " : " + methodName + "()" + " arguments : "
        + mapper.writeValueAsString(array));

        //return
        Object object = pjp.proceed();
        log.info(className + " : " + methodName + "()" + "Response : "
                + mapper.writeValueAsString(object));

        return object;
    }
}
