public abstract class Document {
    private DocumentObserver observer;
    private final DocumentHistoryLogger historyLogger;

    public Document() {
        this.historyLogger = new DocumentHistoryLogger();
    }

    public abstract String getContent();
    public abstract void setContent(String content);
    protected abstract DocumentMemento createMemento();
    protected abstract void restoreFromMemento(DocumentMemento memento);

    public void addObserver(DocumentObserver observer) {
        this.observer = observer;
    }

    public void removeObserver() {
        this.observer = null;
    }

    public void undoLastChange() {
        DocumentMemento memento = historyLogger.undo();
        if (memento != null) {
            restoreFromMemento(memento);
            notifyObserver();
        }
    }

    protected void notifyObserver() {
        if (observer != null) {
            observer.update(this);
        }
    }

    protected void saveStateToHistory() {
        historyLogger.addMemento(createMemento());
    }
}
