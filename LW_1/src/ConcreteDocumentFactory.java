public class ConcreteDocumentFactory implements DocumentFactory {

    @Override
    public Document createDocument(String type) {
        switch (type) {
            case "TextDocument":
                return new TextDocument();
            default:
                throw new IllegalArgumentException("Unknown document type: " + type);
        }
    }
}
