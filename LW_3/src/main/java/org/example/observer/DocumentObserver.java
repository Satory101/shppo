package org.example.observer;

import org.example.document.Document;

public interface DocumentObserver {
    void update(Document document);
}