package org.example.gui;

import org.example.command.ChangeTextCommand;
import org.example.document.Document;
import org.example.observer.DocumentObserver;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class DocumentEditorGUI extends JFrame
        implements DocumentObserver {

    private final Document document;

    private final JTextArea textArea;

    private final Semaphore semaphore =
            new Semaphore(3);

    public DocumentEditorGUI(Document document) {

        this.document = document;

        this.document.addObserver(this);

        setTitle("Редактор документов");

        setSize(700, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        textArea = new JTextArea();

        add(new JScrollPane(textArea),
                BorderLayout.CENTER);

        JPanel controls = new JPanel();

        JButton saveButton =
                new JButton("Сохранить");

        JButton historyButton =
                new JButton("История");

        JButton undoButton =
                new JButton("Удалить последнее");

        JButton testButton =
                new JButton("10 потоков");

        controls.add(saveButton);

        controls.add(historyButton);

        controls.add(undoButton);

        controls.add(testButton);

        add(controls, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> {

            String text =
                    textArea.getText();

            if (text.isBlank()) {
                return;
            }

            ChangeTextCommand command =
                    new ChangeTextCommand(
                            document,
                            text
                    );

            command.execute();

            textArea.setText("");
        });

        historyButton.addActionListener(e ->
                showHistory()
        );

        undoButton.addActionListener(e ->
                document.undoLastChange()
        );

        testButton.addActionListener(e ->
                runMultithreadedTest()
        );

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void showHistory() {

        List<String> history =
                document.getHistory();

        JTextArea historyArea =
                new JTextArea();

        historyArea.setEditable(false);

        if (history.isEmpty()) {

            historyArea.setText(
                    "История пуста"
            );

        } else {

            int index = 1;

            for (String entry : history) {

                historyArea.append(
                        index
                                + ". "
                                + entry
                                + "\n\n"
                );

                index++;
            }
        }

        JScrollPane scrollPane =
                new JScrollPane(historyArea);

        scrollPane.setPreferredSize(
                new Dimension(450, 300)
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "История изменений",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void runMultithreadedTest() {

        ExecutorService executor =
                Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {

            int threadId = i;

            executor.submit(() -> {

                try {

                    if (semaphore.tryAcquire(
                            5,
                            TimeUnit.SECONDS
                    )) {

                        try {

                            Thread.sleep(1000);

                            String text =
                                    "Поток "
                                            + threadId
                                            + " изменил документ";

                            ChangeTextCommand command =
                                    new ChangeTextCommand(
                                            document,
                                            text
                                    );

                            command.execute();

                            System.out.println(
                                    "[ПОТОК " + threadId + "] "
                                            + "новое содержимое: "
                                            + text
                            );

                        } finally {

                            semaphore.release();
                        }
                    }

                } catch (InterruptedException e) {

                    Thread.currentThread()
                            .interrupt();
                }
            });
        }

        executor.shutdown();
    }

    @Override
    public void update(Document document) {

        SwingUtilities.invokeLater(() ->
                textArea.setText(
                        document.getContent()
                )
        );
    }
}