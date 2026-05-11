package org.example.document;

import org.example.logger.DocumentHistoryLogger;
import org.example.observer.DocumentObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class Document {

    protected DocumentObserver observer;

    protected final DocumentHistoryLogger historyLogger;

    protected final List<String> states =
            new ArrayList<>();

    protected int currentIndex = -1;

    public Document(DocumentHistoryLogger historyLogger) {

        this.historyLogger = historyLogger;
    }

    public abstract String getContent();

    public abstract void setContent(String content);

    public void addObserver(DocumentObserver observer) {

        this.observer = observer;
    }

    public void saveToHistory(String text) {

        historyLogger.addRecord(text);

        states.add(text);

        currentIndex = states.size() - 1;
    }

    public List<String> getHistory() {

        return historyLogger.getHistory();
    }

    public void undoLastChange() {

        if (currentIndex <= 0) {

            setContent("");

            currentIndex = -1;

        } else {

            currentIndex--;

            setContent(states.get(currentIndex));
        }

        notifyObserver();
    }

    protected void notifyObserver() {

        if (observer != null) {

            observer.update(this);
        }
    }
}