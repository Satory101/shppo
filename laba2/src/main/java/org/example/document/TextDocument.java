
package org.example.document;

import org.example.history.DocumentHistoryLogger;
import org.springframework.stereotype.Component;

@Component
public class TextDocument extends Document {

    private String content = "";

    public TextDocument(DocumentHistoryLogger history) {
        super(history);
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        save(); // 🔥 важно
        this.content = content;
        notifyObserver();
    }

    public void setInternal(String c) {
        this.content = c;
    }

    @Override
    protected DocumentMemento createMemento() {
        return new TextDocumentMemento(content);
    }
}