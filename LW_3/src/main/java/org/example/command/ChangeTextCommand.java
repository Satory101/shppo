package org.example.command;

import org.example.document.Document;

public class ChangeTextCommand
        implements DocumentCommand {

    private final Document document;

    private final String newText;

    public ChangeTextCommand(
            Document document,
            String newText
    ) {
        this.document = document;
        this.newText = newText;
    }

    @Override
    public void execute() {

        document.setContent(newText);

        document.saveToHistory(newText);
    }

    @Override
    public void undo() {

        document.undoLastChange();
    }
}