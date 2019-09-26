package jogador;

public class Position {

    private int ver;
    private int hor;

    public Position(int ver, int hor) {
        this.ver = ver;
        this.hor = hor;
    }

    public Position north(){
        return new Position(ver - 1, hor );
    }

    public Position south() {
        return new Position(ver + 1, hor );
    }

    public Position west(){
        return new Position(ver, hor-1);
    }

    public Position east(){
        return new Position(ver, hor+1);
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public int getHor() {
        return hor;
    }

    public void setHor(int hor) {
        this.hor = hor;
    }
}
