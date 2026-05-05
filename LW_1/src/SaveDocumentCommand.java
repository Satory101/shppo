public class SaveDocumentCommand implements DocumentCommand {
    private final Document document;
    private final DocumentHistoryLogger historyLogger;

    public SaveDocumentCommand(Document document, DocumentHistoryLogger historyLogger) {
        this.document = document;
        this.historyLogger = historyLogger;
    }

   @Override
    public void execute() {
        historyLogger.addMemento(document.createMemento());
    }

    @Override
    public void undo() {
        document.undoLastChange();
    }
}