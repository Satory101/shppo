package org.example.document;

import org.example.history.DocumentHistoryLogger;

public abstract class Document {
    protected final DocumentHistoryLogger history;

    protected Document(DocumentHistoryLogger history) {
        this.history = history;
    }

    public abstract String getContent();
    public abstract void setContent(String content);
    public abstract DocumentMemento createMemento();

    public void undo() {
        DocumentMemento m = history.undo();
        if (m != null) {
            m.restore(this);
        }
    }
}