package org.example.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    @Before("execution(* org.example.command.DocumentCommand+.setText(String)) && args(text)")
    public void validateInput(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Input text cannot be null");
        }
    }
}