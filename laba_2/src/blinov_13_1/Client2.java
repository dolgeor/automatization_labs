package blinov_13_1;

import java.io.*;
import java.net.Socket;

/*Get messages from Client 1*/

public class Client2 {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader socketReader = null;

        try {
            socket = new Socket("localhost", 12900);
            System.out.println("Started client2 socket at " + socket.getLocalSocketAddress());

            socketReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String inMsg;
            while ((inMsg = socketReader.readLine()) != null){
                System.out.println("SMS from Client1: " + inMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
