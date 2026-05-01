public class ConcreteDocumentObserver implements DocumentObserver {
    private final SaveTextDocumentCommand saveCommand;

    public ConcreteDocumentObserver(SaveTextDocumentCommand saveCommand) {
        this.saveCommand = saveCommand;
    }

    @Override
    public void update(Document document) {
        System.out.println("Документ был обновлен:");
        System.out.println("Новое содержимое: " + document.getContent());
        saveCommand.execute(); // Сохраняем изменения в историю
    }
}
