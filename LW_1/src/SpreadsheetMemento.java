import java.util.HashMap;
import java.util.Map;

public class SpreadsheetMemento extends DocumentMemento {
    private final Map<String, String> cells;

    public SpreadsheetMemento(Map<String, String> cells) {
        super("SpreadsheetDocument");
        this.cells = new HashMap<>(cells);
    }

    public Map<String, String> getCells() {
        return new HashMap<>(cells);
    }
}
