import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SpreadsheetDocument extends Document {
    private final Map<String, String> cells;

    public SpreadsheetDocument() {
        super();
        this.cells = new HashMap<>();
    }

    public String getCell(String cellReference) {
        return cells.getOrDefault(cellReference, "");
    }

    public void setCell(String cellReference, String content) {
        cells.put(cellReference, content);
        saveStateToHistory();
        notifyObserver();
    }

    @Override
    public String getContent() {
        return cells.toString();
    }

    @Override
    public void setContent(String content) {
        // Не используется, так как таблица работает с ячейками
    }

    public String[][] getData() {
        // Преобразование данных таблицы в двумерный массив
        int rowCount = cells.size(); // Количество строк
        int columnCount = 3; // Предполагаемое количество столбцов
        String[][] data = new String[rowCount][columnCount];

        int rowIndex = 0;
        for (Map.Entry<String, String> entry : cells.entrySet()) {
            // Разбиваем ссылку на ячейку на номер строки и столбца
            String[] cellReference = entry.getKey().split("-");
            int columnIndex = Integer.parseInt(cellReference[1]) - 1; // Нумерация столбцов начинается с 0

            // Заполняем ячейку таблицы данными
            data[rowIndex][columnIndex] = entry.getValue();
            rowIndex++;
        }

        return data;
    }

    public void setData(String[][] data) {
        // Преобразование двумерного массива в данные таблицы
        cells.clear();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                String cellReference = (i + 1) + "-" + (j + 1); // Формируем ссылку на ячейку
                String content = data[i][j];
                cells.put(cellReference, content);
            }
        }
        saveStateToHistory();
        notifyObserver();
    }

    @Override
    protected DocumentMemento createMemento() {
        return new SpreadsheetMemento(new HashMap<>(cells));
    }

    @Override
    protected void restoreFromMemento(DocumentMemento memento) {
        if (memento.getType().equals("SpreadsheetDocument")) {
            this.cells.clear();
            this.cells.putAll(((SpreadsheetMemento) memento).getCells());
        }
    }
}
