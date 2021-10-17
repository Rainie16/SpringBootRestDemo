package com.mercury.SpringBootRestDemo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    //inside is regular expression
    //first * whatever return
    //second * all classes
    //.. means 0 to many parameters
    @Around("execution (* com.mercury.SpringBootRestDemo.controller.*.getAll(..))")
    public Object logSomething(ProceedingJoinPoint pjp){
        System.out.println("********Something before***********");
        Object o = null;
        try {
            o = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("********Something after***********");
        return o;
    }
}
