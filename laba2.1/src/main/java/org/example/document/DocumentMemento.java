package org.example.document;

public abstract class DocumentMemento {
    public abstract void restore(Document doc);
    public abstract String getInfo();
}