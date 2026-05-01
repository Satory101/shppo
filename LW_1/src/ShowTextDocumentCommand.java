import javax.swing.*;

public class ShowTextDocumentCommand implements DocumentCommand {
    private final TextDocument document;

    public ShowTextDocumentCommand(TextDocument document) {
        this.document = document;
    }

    @Override
    public void execute() {
        JFrame frame = new JFrame("Содержимое текстового документа");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea(document.getContent());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane);

        frame.setSize(400, 300);
        frame.setVisible(true);

        System.out.println("Содержимое текстового документа:");
        System.out.println("Текст: " + document.getContent());
    }

    @Override
    public void undo() {
        // Не требуется для этой команды
    }
}
