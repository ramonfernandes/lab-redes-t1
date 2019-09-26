package jogo;

import item.Chao;
import item.Item;
import item.Parede;
import jogador.Jogador;
import jogador.Position;

import java.util.ArrayList;
import java.util.List;

public class Sala {

    private int id;
    private static int idAux = 0;
    private Item[][] matriz;
    private List<Jogador> jogadores;

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void addJogador(Jogador jogador) {
        this.jogadores.add(jogador);
    }

    public void removeJogador(Jogador jogador) {
        this.jogadores.remove(jogador);
    }

    public Sala(int ver, int hor) {
        this.jogadores = new ArrayList<>();
        id = idAux;
        idAux++;
        this.matriz = new Item[ver][hor];
        for (int localVer = 0; localVer < matriz.length; localVer++)
            for (int localHor = 0; localHor < matriz[localVer].length; localHor++)
                if (localVer == 0 || localHor == 0 || localVer == matriz.length - 1 || localHor == matriz[localVer].length - 1)
                    matriz[localVer][localHor] = new Parede();
                else
                    matriz[localVer][localHor] = new Chao();
    }

    public void setItem(Item item, int ver, int hor) {
        matriz[ver][hor] = item;
    }

    public String printMatriz() {
        StringBuilder matrizStr = new StringBuilder();
        for (int ver = 0; ver < matriz.length; ver++) {
            for (int hor = 0; hor < matriz[ver].length; hor++) {
                for (Jogador jogador : jogadores)
                    if (jogador.getPosition().getVer() == ver && jogador.getPosition().getHor() == hor)
                        matrizStr.append(jogador.getId());
                    else
                        matrizStr.append(matriz[ver][hor].print());
            }
            matrizStr.append("\n");
        }
        return matrizStr.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getIdAux() {
        return idAux;
    }

    public static void setIdAux(int idAux) {
        Sala.idAux = idAux;
    }

    public Item[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Item[][] matriz) {
        this.matriz = matriz;
    }

    public void setItemOnPosition(Position position, Item item) {
        matriz[position.getVer()][position.getHor()] = item;
    }

    public void listItens() {
        for (int ver = 0; ver < matriz.length; ver++)
            for (int hor = 0; hor < matriz[ver].length; hor++)
                if(!(matriz[ver][hor] instanceof Chao || matriz[ver][hor] instanceof Parede))
                    System.out.println(matriz[ver][hor] + "\nvertical: " + ver + "\nhorizontal: " + hor +"\n\n");
    }
}
