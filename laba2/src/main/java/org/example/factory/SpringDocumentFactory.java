package org.example.factory;

import org.example.document.Document;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringDocumentFactory implements DocumentFactory {

    private final Map<String, Document> docs;

    public SpringDocumentFactory(Map<String, Document> docs) {
        this.docs = docs;
    }

    public Document createDocument(String type) {
        if (!docs.containsKey(type)) {
            throw new IllegalArgumentException("Unknown type");
        }
        return docs.get(type);
    }
}