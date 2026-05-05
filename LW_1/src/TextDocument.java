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
public void SetContent(String content){

}
     @Override
    public void setContent(String content) {
        saveStateToHistory();
        this.content = content;
        notifyObserver();
    }

    public void restoreContent(String content) {
        this.content = content;
    }

    @Override
    public DocumentMemento createMemento() {
        return new TextDocumentMemento(this, content);
    }
}