public class ChangeTextCommand implements DocumentCommand {
    private TextDocument textDocument;
    private String newText;
    private String previousText;

    public ChangeTextCommand(TextDocument textDocument, String newText) {
        this.textDocument = textDocument;
        this.newText = newText;
    }

    @Override
    public void execute() {
        previousText = textDocument.getContent();
        textDocument.setContent(newText);
    }

    @Override
    public void undo() {
        textDocument.setContent(previousText);
    }
}
