package org.example.command;

import org.example.document.TextDocument;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("changeTextCommand")
@Scope("prototype")
public class ChangeTextCommand implements DocumentCommand {

    private final TextDocument document;
    private String text;

    public ChangeTextCommand(TextDocument document) {
        this.document = document;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void execute() {
        document.setContent(text);
    }
}