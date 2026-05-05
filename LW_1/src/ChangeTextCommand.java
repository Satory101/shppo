public class ChangeTextCommand implements DocumentCommand {
    private final Document document; // Работаем через абстракцию
    private final String newText;
    private String previousText;

    public ChangeTextCommand(Document document, String newText) {
        this.document = document;
        this.newText = newText;
    }
 @Override
    public void execute() {
        previousText = document.getContent();
        document.setContent(newText);
    }

    @Override
    // Нужно чтобы не было ошибки howTextDocumentCommand is not abstract and does not override abstract method undo() in DocumentCommand
    public void undo() {
        document.setContent(previousText);
    }
}