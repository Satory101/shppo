public class SaveTextDocumentCommand implements DocumentCommand {
    private final TextDocument document;
    private final DocumentHistoryLogger historyLogger;

    public SaveTextDocumentCommand(TextDocument document, DocumentHistoryLogger historyLogger) {
        this.document = document;
        this.historyLogger = historyLogger;
    }

    @Override
    public void execute() {
        historyLogger.addMemento(new TextDocumentMemento(document.getContent()));
    }

    @Override
    public void undo() {
        DocumentMemento memento = historyLogger.undo();
        if (memento != null) {
            document.restoreFromMemento(memento);
        }
    }
}
