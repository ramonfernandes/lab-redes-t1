import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class Main {


    private static final String servidor = "localhost";
    private static final int porta = 9876;
    private static String idJogador;
    private static String listenServString;

    public static void main(String[] args) {
        try {

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            String modifiedSentence = "0-GetJogador";
            DatagramSocket clientSocket = new DatagramSocket();

            sendData = modifiedSentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(servidor), porta);

            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            idJogador = new String(receivePacket.getData()).replaceAll("\u0000.*", "");

            while (true) {

                sendData = new byte[1024];
                receiveData = new byte[1024];
                clientSocket = new DatagramSocket();

                InetAddress IPAddress = InetAddress.getByName(servidor);

                modifiedSentence = listenningCommand();
                sendData = modifiedSentence.getBytes();
                sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);

                clientSocket.send(sendPacket);

                receivePacket = new DatagramPacket(receiveData, receiveData.length);

                clientSocket.receive(receivePacket);

                listenServString = new String(receivePacket.getData());
                System.out.println(listenServString);

                printResponse(listenServString);
                clientSocket.close();
            }
        } catch (Exception e) {
            System.out.println("Erro de conexão");
        }

    }

    private static String listenningCommand() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        String fala = "";
        if (command.equals("falar")) {
            System.out.println("Digite a frase desejada");
            fala = in.nextLine();
        }
        return idJogador + "-" + command + "-" + fala;
    }

    private static void printResponse(String receivedData) {

        if (receivedData.split("-")[0].equals("M")) {

            int vertical = Integer.parseInt(receivedData.split("-")[1]);
            int horizontal = Integer.parseInt(receivedData.split("-")[2]);
            char[] matriz = receivedData.split("-")[3].toCharArray();

            int aux = 0;

            for (int i = 0; i < vertical; i++) {
                for (int j = 0; j < horizontal; j++) {
                    System.out.print(matriz[aux]);
                    aux++;
                }
                System.out.println();
            }
        } else {
            System.out.println(receivedData.split("-")[1].replaceAll("\u0000.*", ""));
        }
    }

}
