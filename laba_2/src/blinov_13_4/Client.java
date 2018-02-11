package blinov_13_4;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        BufferedReader socketReader = null;
        try (Socket socket = new Socket("localhost", 12900);) {
            String inMsg = null;
            StringBuilder sb = new StringBuilder();
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("You have connected to server ...\n ");

            while((inMsg = socketReader.readLine())!=null){
                sb.append(inMsg);
                sb.append("\n");
            }

            System.out.println(sb.toString());
            System.out.println(); // Print a blank line

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
