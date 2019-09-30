package item;

public class Parede extends Item {

    public Parede() {
        super(false, false);
    }

    @Override
    public String print() {
        return "|";
    }


    @Override
    public String toString() {
        return "Parede";
    }
}
