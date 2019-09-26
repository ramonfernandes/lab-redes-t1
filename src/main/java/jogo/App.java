package jogo;

import item.Chao;
import item.Chave;
import item.Item;
import item.Porta;
import jogador.Jogador;
import jogador.Position;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Sala sala = new Sala(10, 10);
        sala.setItem(new Porta(false, "11", 1),3, 0);
        sala.setItem(new Chave("1"), 1, 1);
        sala.setItem(new Chave("2"), 1, 6);
        sala.addJogador(new Jogador(1, 8));

        while (!((Porta) sala.getMatriz()[3][0]).isAberta()) {
            System.out.println(sala.printMatriz());
            sala = listeningCommand(sala, sala.getJogadores().get(0));
        }
    }

    private static Sala listeningCommand(Sala sala, Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        switch (scanner.next()) {
            case "a":
                if (getItemOnPosition(sala, jogador.getPosition().west()).isCaminhavel()) {
                    jogador.walkWest();
                }
                break;
            case "d":
                if (getItemOnPosition(sala, jogador.getPosition().east()).isCaminhavel()) {
                    jogador.walkEast();
                }
                break;
            case "w":
                if (getItemOnPosition(sala, jogador.getPosition().north()).isCaminhavel()) {
                    jogador.walkNorth();
                }
                break;
            case "s":
                if (getItemOnPosition(sala, jogador.getPosition().south()).isCaminhavel()) {
                    jogador.walkSouth();
                }
                break;
            case "use":
                Item item = getItemOnPosition(sala, jogador.getPosition());
                if (item instanceof Chave) {
                    Item drop = new Chao();
                    if (jogador.getChave() != null) {
                        drop = jogador.getChave();
                    }
                    jogador.setChave((Chave) item);
                    sala.setItemOnPosition(jogador.getPosition(), drop);
                }
                if (item instanceof Porta) {
                    Chave chave = jogador.getChave();
                    if(chave != null)
                        ((Porta) item).use(jogador);
                }
                break;
            case "inventario":
                if(jogador.getChave() != null)
                    System.out.println("O jogador " + jogador.getId() + " esta com a chave " + jogador.getChave().getId());
                else
                    System.out.println("O jogador " + jogador.getId() + " esta sem chave");
                break;
            case "investigar":
                sala.listItens();
                break;
        }
        sala.removeJogador(sala.getJogadores().get(0));
        sala.addJogador(jogador);

        return sala;
    }

    private static Item getItemOnPosition(Sala sala, Position position) {
        System.out.println("Pegando item na posição: \n Vertical: " +position.getVer()+ "\n Hor: "+position.getHor());
        return sala.getMatriz()[position.getVer()][position.getHor()];
    }


}