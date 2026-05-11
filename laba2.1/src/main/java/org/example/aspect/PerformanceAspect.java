package org.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    @Around("execution(* org.example.command.DocumentCommand+.execute())")
    public Object measureTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object result = pjp.proceed();
        long end = System.nanoTime();
        System.out.println(pjp.getSignature().getName() + " executed in " + (end - start) / 1000 + " mks");
        return result;
    }
}