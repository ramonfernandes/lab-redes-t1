package item;

import jogador.Jogador;
import jogador.Position;

public class Porta extends Item {

    private boolean aberta;
    private String combinacao;
    private int idOut;


    public Porta(boolean aberta, String combinacao, int idOut) {
        super(true, false);
        this.aberta = aberta;
        this.combinacao = combinacao;
        this.idOut = idOut;
    }

    public void use(Jogador jogador) {
        try {
            String temp = combinacao.replaceAll(jogador.getId() + jogador.getChave().getId(), "");
            if (aberta)
                System.out.println("Porta já está aberta");
            else if (temp.isEmpty()) {
                combinacao = temp;
                aberta = true;
                System.out.println("Você abriu a porta");
            } else {
                System.out.println("Porta continua trancada");
            }
        }catch (NullPointerException e) {
            System.out.println("Jogador não possui chave");
        }
    }

    @Override
    public String print() {
        if (aberta)
            return ".";
        else
            return "P";
    }

    public boolean isAberta() {
        return aberta;
    }

    public void setAberta(boolean aberta) {
        this.aberta = aberta;
    }

    public String getCombinacao() {
        return combinacao;
    }

    public void setCombinacao(String combinacao) {
        this.combinacao = combinacao;
    }

    public int getIdOut() {
        return idOut;
    }

    public void setIdOut(int idOut) {
        this.idOut = idOut;
    }

    @Override
    public String toString() {
        return "Porta combinação:" + combinacao;
    }
}
