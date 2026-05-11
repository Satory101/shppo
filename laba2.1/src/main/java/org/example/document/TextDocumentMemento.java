package org.example.document;

public class TextDocumentMemento extends DocumentMemento {

    private final String text;

    public TextDocumentMemento(String text) {
        this.text = text;
    }

    @Override
    public void restore(Document doc) {
        ((TextDocument) doc).setInternal(text);
    }

    @Override
    public String getInfo() {
        return text;
    }
}