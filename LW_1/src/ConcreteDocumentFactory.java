public class ConcreteDocumentFactory implements DocumentFactory {

    public Document createDocument(String type) {
        // Проверка вынесена наверх. Фабрика только инстанцирует.
        switch (type) {
            case "TextDocument":
                return new TextDocument();
            case "SpreadsheetDocument":
                return new SpreadsheetDocument();
            default:
                return null;
        }
    }
}