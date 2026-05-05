public class ConcreteDocumentObserver implements DocumentObserver {
    private final SaveDocumentCommand saveCommand;

    public ConcreteDocumentObserver(SaveDocumentCommand saveCommand) {
        this.saveCommand = saveCommand;
    }

    @Override
    public void update(Document document) {
        System.out.println("Документ был обновлен:");
        System.out.println("Новое содержимое: " + document.getContent());
        saveCommand.execute();
    }
}