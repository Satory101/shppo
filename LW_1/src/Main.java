import javax.swing.SwingUtilities;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String targetType = "TextDocument";
        List<String> validTypes = Arrays.asList("TextDocument", "SpreadsheetDocument");

        if (!validTypes.contains(targetType)) {
            throw new IllegalArgumentException("Unknown document type requested: " + targetType);
        }

        DocumentFactory factory;
        if ("TextDocument".equals(targetType)) {
            factory = new TextDocumentFactory();
        } else {
            factory = new SpreadsheetDocumentFactory();
        }

        SwingUtilities.invokeLater(() -> new DocumentEditorGUI(factory));
    }
}