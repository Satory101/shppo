package org.example.ui;

import org.example.command.DocumentCommand;
import org.example.document.DocumentObserver;
import org.example.document.TextDocument;
import org.example.history.DocumentHistoryLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class DocumentEditorGUI {

    private final ApplicationContext context;

    public DocumentEditorGUI(TextDocument doc,
                             DocumentObserver observer,
                             ApplicationContext context,
                             DocumentHistoryLogger history) {

        this.context = context;

        doc.addObserver(observer);

        JFrame frame = new JFrame("Editor");
        JTextArea area = new JTextArea(20, 40);

        JButton editBtn = new JButton("Edit");
        JButton undoBtn = new JButton("Undo");
        JButton historyBtn = new JButton("History");

        editBtn.addActionListener(e -> {
            String text = JOptionPane.showInputDialog("Enter text:");

            if (text != null) {
                DocumentCommand cmd =
                        context.getBean("changeTextCommand", DocumentCommand.class);

                cmd.setText(text);
                cmd.execute();

                area.setText(doc.getContent());
            }
        });

        undoBtn.addActionListener(e -> {
            doc.undo();
            area.setText(doc.getContent());
        });

        historyBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("History:\n");

            for (var m : history.getHistory()) {
                sb.append(m.getInfo()).append("\n");
            }

            JOptionPane.showMessageDialog(frame, sb.toString());
        });

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.add(new JScrollPane(area));
        frame.add(editBtn);
        frame.add(undoBtn);
        frame.add(historyBtn);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}