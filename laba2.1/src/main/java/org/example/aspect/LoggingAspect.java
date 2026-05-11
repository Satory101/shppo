package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* org.example..*(..))")
    public void logBefore(JoinPoint jp) {
        System.out.println("Executing: " + jp.getSignature().toShortString());
    }
}