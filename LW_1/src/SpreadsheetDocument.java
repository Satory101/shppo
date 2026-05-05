import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpreadsheetDocument extends Document {
    private final Map<String, String> cells = new HashMap<>();

    public SpreadsheetDocument() {
        super();
    }

    @Override
    public String getContent() {
        return cells.toString();
    }

    @Override
    public void setContent (String content){

}

    @Override
    public void SetContent (String content){

    }
    public void setCell(String cellReference, String content) {
        cells.put(cellReference, content);
        saveStateToHistory();
        notifyObserver();
    }

    public List<List<String>> getData() {
        if (cells.isEmpty()) return new ArrayList<>();

        int maxRow = 0;
        int maxCol = 0;

        for (String ref : cells.keySet()) {
            String[] parts = ref.split("-");
            maxRow = Math.max(maxRow, Integer.parseInt(parts[0]));
            maxCol = Math.max(maxCol, Integer.parseInt(parts[1]));
        }

        List<List<String>> data = new ArrayList<>();
        for (int i = 0; i < maxRow; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < maxCol; j++) {
                row.add(cells.getOrDefault((i + 1) + "-" + (j + 1), ""));
            }
            data.add(row);
        }
        return data;
    }

    public void restoreCells(Map<String, String> mementoCells) {
        this.cells.clear();
        this.cells.putAll(mementoCells);
    }

     @Override
    public DocumentMemento createMemento() {
        return new SpreadsheetMemento(this, new HashMap<>(cells));
    }
}