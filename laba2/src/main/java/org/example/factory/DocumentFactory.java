package org.example.factory;

import org.example.document.Document;

public interface DocumentFactory {
    Document createDocument(String type);
}