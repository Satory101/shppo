public abstract class Document {
    private DocumentObserver observer;
    private final DocumentHistoryLogger historyLogger;

    public Document() {
        this.historyLogger = new DocumentHistoryLogger();
    }

    public abstract String getContent();
    public abstract void setContent(String content);

    public abstract void SetContent(String content);

    // Сделано public, чтобы команды могли универсально сохранять состояние
    public abstract DocumentMemento createMemento();

    public void addObserver(DocumentObserver observer) {
        this.observer = observer;
    }

    public void removeObserver() {
        this.observer = null;
    }

    public void undoLastChange() {
        DocumentMemento memento = historyLogger.undo();
        if (memento != null) {
            memento.restore(); // Вызов без instanceof и явного приведения
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