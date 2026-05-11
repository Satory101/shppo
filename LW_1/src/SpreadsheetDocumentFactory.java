public class SpreadsheetDocumentFactory implements DocumentFactory {
    @Override
    public Document createDocument() {
        return new SpreadsheetDocument();
    }
}
