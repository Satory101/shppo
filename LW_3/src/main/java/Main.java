package org.example;

import org.example.document.Document;
import org.example.document.TextDocument;
import org.example.gui.DocumentEditorGUI;
import org.example.logger.DocumentHistoryLogger;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        DocumentHistoryLogger logger =
                new DocumentHistoryLogger();

        Document document =
                new TextDocument(logger);

        SwingUtilities.invokeLater(() ->
                new DocumentEditorGUI(document)
        );
    }
}