package jogador;

import item.Chave;

public class Jogador {

    private int id;
    private static int idAux = 0;
    private Chave chave;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    private Position position;

    public Jogador(int ver, int hor) {
        this.id = idAux;
        idAux++;
        this.chave = null;
        this.position = new Position(ver, hor);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    public void walkNorth(){
        this.position = position.north();
    }
    public void walkSouth(){
        this.position = position.south();
    }
    public void walkEast(){
        this.position = position.east();
    }
    public void walkWest(){
        this.position = position.west();
    }

}
