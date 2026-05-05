public abstract class DocumentMemento {
    private final String type;

    protected DocumentMemento(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    // Полиморфные методы, избавляющие от instanceof и приведения типов
    public abstract void restore();
    public abstract String getDetails();
}