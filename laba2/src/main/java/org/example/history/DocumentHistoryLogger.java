package org.example.history;

import org.example.document.DocumentMemento;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Component
public class DocumentHistoryLogger {

    private final Deque<DocumentMemento> history = new ArrayDeque<>();

    public void addMemento(DocumentMemento m) {
        history.push(m);
    }

    public DocumentMemento undo() {
        return history.isEmpty() ? null : history.pop();
    }

    public List<DocumentMemento> getHistory() {
        return new ArrayList<>(history);
    }
}