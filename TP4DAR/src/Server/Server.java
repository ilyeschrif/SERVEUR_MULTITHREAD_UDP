package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;

public class Server {
    private static final int PORT = 1234;
    private static byte[] buffer = new byte[1024];

    public static void main(String[] args) throws IOException {
        try {

            // Crée un DatagramSocket pour écouter sur le port spécifié
            DatagramSocket ds = new DatagramSocket(PORT);

            while (true) {

                // Crée un DatagramPacket pour recevoir les données entrantes
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
                ds.receive(dp);

                // Recevoir les données reçues et l'adresse du client
                String s = new String(dp.getData(), 0, dp.getLength());
                InetAddress adressClient = dp.getAddress();
                System.out.println("nomClient :" + s + " adresseClient" + adressClient);

                // Affiche les données du client et son adresse
                String msg = "Bienvennue " + s;
                DatagramPacket packetToSend = new DatagramPacket(msg.getBytes(), msg.length(), adressClient, dp.getPort());

                // Envoie la réponse au client
                ds.send(packetToSend);

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}




