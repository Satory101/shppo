public class TextDocumentMemento extends DocumentMemento {
    private final TextDocument document;
    private final String text;

    public TextDocumentMemento(TextDocument document, String text) {
        super("TextDocument");
        this.document = document;
        this.text = text;
    }

    @Override
    public void restore() {
        document.restoreContent(this.text);
    }

    @Override
    public String getDetails() {
        return text;
    }
}