package Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    private static final int PORT = 1234;
    private static byte[] buffer = new byte[1024];

    public static void main(String[] args) throws IOException {

        // Crée un DatagramSocket pour la communication avec le serveur
        DatagramSocket ds = new DatagramSocket();

        // lire l'entrée de l'utilisateur
        Scanner sc = new Scanner(System.in);
        System.out.println("nom prenom :");
        String s = sc.nextLine();

        // Crée un DatagramPacket contenant les données à envoyer au serveur
        DatagramPacket dp = new DatagramPacket(s.getBytes(), s.length(), InetAddress.getByName("localhost"), PORT);

        // Envoie les données au serveur
        ds.send(dp);

        // Crée un DatagramPacket pour recevoir la réponse du serveur
        DatagramPacket dp2 = new DatagramPacket(buffer, buffer.length);
        ds.receive(dp2);

        // la réponse du serveur
        String msg = new String(dp.getData(), 0, dp.getLength());

        // Affiche la réponse du serveur, l'adresse du serveur et le numéro de port
        System.out.println("Bienvenu " + msg);
        System.out.println("l’adresse de serveur : " + dp2.getAddress().getHostAddress());
        System.out.println("le numéro de port :" + dp2.getPort());


    }

}
