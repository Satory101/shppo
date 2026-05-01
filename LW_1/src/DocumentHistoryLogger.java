import java.util.Stack;

public class DocumentHistoryLogger {
    private final Stack<DocumentMemento> history;

    public DocumentHistoryLogger() {
        this.history = new Stack<>();
    }

    public void addMemento(DocumentMemento memento) {
        history.push(memento);
    }

    public DocumentMemento undo() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }

    public Stack<DocumentMemento> getHistory() {
        return history;
    }
}
