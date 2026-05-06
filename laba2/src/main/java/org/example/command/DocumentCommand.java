package org.example.command;

public interface DocumentCommand {
    void execute();
    void setText(String text);
}