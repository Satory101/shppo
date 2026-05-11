package org.example.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.document.Document;
import org.example.history.DocumentHistoryLogger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DocumentAspect {

    private final DocumentHistoryLogger historyLogger;

    public DocumentAspect(DocumentHistoryLogger historyLogger) {
        this.historyLogger = historyLogger;
    }

    @Before("execution(* org.example.document.Document+.setContent(String)) && target(doc)")
    public void autoSave(Document doc) {
        historyLogger.addMemento(doc.createMemento());
    }

    @After("execution(* org.example.document.Document+.setContent(String)) && target(doc)")
    public void notifyUpdate(Document doc) {
        System.out.println("Document state changed to: " + doc.getContent());
    }
}