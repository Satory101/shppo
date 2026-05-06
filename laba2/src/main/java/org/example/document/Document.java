package org.example.document;

import org.example.history.DocumentHistoryLogger;

public abstract class Document {

    protected final DocumentHistoryLogger history;
    private DocumentObserver observer;

    protected Document(DocumentHistoryLogger history) {
        this.history = history;
    }

    public abstract String getContent();
    public abstract void setContent(String content);

    protected abstract DocumentMemento createMemento();

    public void undo() {
        DocumentMemento m = history.undo();
        if (m != null) {
            m.restore(this);
            notifyObserver();
        }
    }

    protected void save() {
        history.addMemento(createMemento());
    }

    public void addObserver(DocumentObserver o) {
        observer = o;
    }

    protected void notifyObserver() {
        if (observer != null) observer.update(this);
    }
}