package org.example.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UndoTrackingAspect {

    @AfterReturning("execution(* org.example.document.Document.undo())")
    public void trackUndo() {
        System.out.println("Undo operation was processed");
    }
}