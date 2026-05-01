import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class DocumentEditorGUI extends JFrame {

    private TextDocument document;
    private DocumentHistoryLogger historyLogger;
    private SaveTextDocumentCommand saveTextDocumentCommand;

    public DocumentEditorGUI() {
        super("Document Editor");

        document = new TextDocument();
        historyLogger = new DocumentHistoryLogger();
        saveTextDocumentCommand = new SaveTextDocumentCommand(document, historyLogger);
        document.addObserver(new ConcreteDocumentObserver(saveTextDocumentCommand));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        JButton editButton = new JButton("Edit Text");
        JButton undoButton = new JButton("Undo");
        JButton historyButton = new JButton("View History");

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = document.getContent();
                String newText = JOptionPane.showInputDialog(DocumentEditorGUI.this, "Enter new text:", currentText);
                if (newText != null) {
                    DocumentCommand command = new ChangeTextCommand(document, newText);
                    command.execute();
                    textArea.setText(document.getContent());
                }
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                document.undoLastChange();
                textArea.setText(document.getContent());
                JOptionPane.showMessageDialog(DocumentEditorGUI.this, "Changes undone.");
            }
        });

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder history = new StringBuilder("Change History:\n");
                Stack<DocumentMemento> mementos = historyLogger.getHistory();
                for (DocumentMemento memento : mementos) {
                    history.append(memento.getType()).append(": ").append(((TextDocumentMemento) memento).getText()).append("\n");
                }
                JOptionPane.showMessageDialog(DocumentEditorGUI.this, history.toString());
            }
        });

        buttonPanel.add(editButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(historyButton);
        add(buttonPanel, BorderLayout.WEST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
