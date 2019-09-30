package item;

public class Item {

    private boolean caminhavel;
    private boolean portatil;

    public Item(boolean caminhavel, boolean portatil) {
        this.caminhavel = caminhavel;
        this.portatil = portatil;
    }

    public boolean isCaminhavel() {
        return caminhavel;
    }

    public void setCaminhavel(boolean caminhavel) {
        this.caminhavel = caminhavel;
    }

    public boolean isPortatil() {
        return portatil;
    }

    public void setPortatil(boolean portatil) {
        this.portatil = portatil;
    }

    public String print() {
        return "";
    }

}
