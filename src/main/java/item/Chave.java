package item;

public class Chave extends Item {

    private String id;

    public Chave(String id) {
        super(true, true);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String print() {
        return id;
    }

    @Override
    public String toString() {
        return "Chave id:" + this.id;
    }
}
