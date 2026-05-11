package org.example.factory;
import org.example.document.Document;
import org.example.document.TextDocument;
import org.example.logger.DocumentHistoryLogger;
import org.springframework.stereotype.Component;

@Component
public class TextDocumentFactory implements DocumentFactory {
    private final DocumentHistoryLogger historyLogger;
    public TextDocumentFactory(DocumentHistoryLogger historyLogger) { this.historyLogger = historyLogger; }
    @Override public Document createDocument() { return new TextDocument(historyLogger); }
}