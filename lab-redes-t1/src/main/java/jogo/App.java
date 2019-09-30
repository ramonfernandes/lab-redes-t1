package jogo;

import item.Chao;
import item.Chave;
import item.Item;
import item.Porta;
import jogador.Jogador;
import jogador.Position;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final int porta = 9876;
    private static final int numConn = 2;
    private static Sala sala;
    private static List<Jogador> jogadores = new ArrayList<>();

    public static void main(String[] args) {

        try {

            jogadores.add(new Jogador(3, 3));
            jogadores.add(new Jogador(3, 3));

            System.out.println("INICIANDO JOGO \n Servidor ouvindo a porta 9876");

            sala = criaSala();

            while (!((Porta) sala.getMatriz()[3][0]).isAberta()) {

                byte[] receiveData = new byte[1024];
                byte[] sendData = new byte[1024];

                DatagramSocket serverSocket = new DatagramSocket(porta);

                DatagramPacket receivePacket = new DatagramPacket(receiveData,
                        receiveData.length);
                serverSocket.receive(receivePacket);
                System.out.print("Recebendo pacote");

                String sentence = new String(receivePacket.getData());
                sentence = listeningCommand(sentence);

                InetAddress IPAddress = receivePacket.getAddress();

                int port = receivePacket.getPort();


                sendData = sentence.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData,
                        sendData.length, IPAddress, port);

                System.out.print("Enviando " + sentence + "...");

                serverSocket.send(sendPacket);
                System.out.println("OK\n");

                serverSocket.close();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static Sala criaSala() {
        Sala sala = new Sala(10, 10);
        sala.setItem(new Porta(false, "01", 1), 3, 0);
        sala.setItem(new Chave("1"), 1, 1);
        sala.setItem(new Chave("2"), 1, 6);
        return sala;
    }

    private static String listeningCommand(String command) {

        String idJogador = command.split("-")[0];
        command = command.split("-")[1].replaceAll("\u0000.*", "");
        Jogador jogador = getJogadorById(idJogador);

        switch (command) {
            case "GetJogador":
                if (jogadores.size() > 0) {
                    Jogador newJogador = jogadores.remove(0);
                    sala.addJogador(newJogador);
                    return newJogador.getId() + "";
                }
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
                    ((Porta) item).use(jogador);
                }
                break;
            case "inventario":
                if (jogador.getChave() != null)
                    return "F-O jogador " + jogador.getId() + " esta com a chave " + jogador.getChave().getId();
                else
                    return "F-O jogador " + jogador.getId() + " esta sem chave";
            case "investigar":
                return sala.listItens();
            case "falar":
                return "O Jogador " + idJogador + " falou: " + command.split("-")[2];
            case "ajuda":
                return "AWSD: Movimentacao \n use: utiliza itens \n inventario: verifica inventario";
        }
        sala.removeJogador(sala.getJogadores().get(0));
        sala.addJogador(jogador);

        return formatSala();
    }

    private static Jogador getJogadorById(String id) {
        for (Jogador jogador : sala.getJogadores()) {
            if (jogador.getId() == Integer.parseInt(id))
                return jogador;
        }
        return null;
    }

    private static Item getItemOnPosition(Sala sala, Position position) {
        System.out.println("Pegando item na posição: \n Vertical: " + position.getVer() + "\n Hor: " + position.getHor());
        return sala.getMatriz()[position.getVer()][position.getHor()];
    }

    private static String formatSala() {
        StringBuilder result = new StringBuilder("M-" +
                sala.getMatriz().length + "-" +
                sala.getMatriz()[0].length + "-");

        int i = 0;

        for (int vertical = 0; vertical < sala.getMatriz().length; vertical++) {
            for (int horizontal = 0; horizontal < sala.getMatriz()[vertical].length; horizontal++) {
                for (Jogador jogador : sala.getJogadores())
                    if (jogador.getPosition().getVer() == vertical && jogador.getPosition().getHor() == horizontal)
                        result.append(jogador.getId());
                    else
                        result.append(sala.getMatriz()[vertical][horizontal].print());
            }
        }
        return result.toString();
    }


}