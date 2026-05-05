import java.util.HashMap;
import java.util.Map;

public class SpreadsheetMemento extends DocumentMemento {
    private final SpreadsheetDocument document;
    private final Map<String, String> cells;

    public SpreadsheetMemento(SpreadsheetDocument document, Map<String, String> cells) {
        super("SpreadsheetDocument");
        this.document = document;
        this.cells = new HashMap<>(cells);
    }

     @Override
    public void restore() {
        document.restoreCells(this.cells);
    }

     @Override
    public String getDetails() {
        return cells.toString();
    }
}