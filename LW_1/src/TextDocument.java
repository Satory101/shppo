public class TextDocument extends Document {
    private String content;

    public TextDocument() {
        super();
        this.content = "";
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        saveStateToHistory();
        this.content = content;
        notifyObserver();
    }

    @Override
    protected DocumentMemento createMemento() {
        return new TextDocumentMemento(content);
    }

    @Override
    protected void restoreFromMemento(DocumentMemento memento) {
        if (memento instanceof TextDocumentMemento) {
            this.content = ((TextDocumentMemento) memento).getText();
        }
    }
}
