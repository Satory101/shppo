package org.example.document;

import org.springframework.stereotype.Component;

@Component
public class SimpleObserver implements DocumentObserver {

    @Override
    public void update(Document document) {
        System.out.println("Updated: " + document.getContent());
    }
}