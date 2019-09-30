package item;

public class Chao extends Item {

    public Chao() {
        super(true, false);
    }

    @Override
    public String print() {
        return ".";
    }

    @Override
    public String toString() {
        return "Chão";
    }
}
