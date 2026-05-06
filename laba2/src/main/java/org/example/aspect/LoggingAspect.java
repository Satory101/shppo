package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* org.example..*(..))")
    public void before(JoinPoint jp) {
        System.out.println("Start: " + jp.getSignature());
    }
}