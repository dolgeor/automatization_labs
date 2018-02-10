package blinov1;

import java.io.*;
import java.net.*;


class Server {
    public static void main(String[] args) {
        try {
            // Create a Server socket
            ServerSocket serverSocket = new ServerSocket(12900, 100,
                    InetAddress.getByName("localhost"));
            System.out.println("Server started at: " + serverSocket);
            // Keep accepting client connections in an infinite loop

            final Socket c1 = serverSocket.accept();
            System.out.println("Received a connection from c1 :" + c1);

            final Socket c2 = serverSocket.accept();
            System.out.println("Received a connection from c2 :" + c2);

            BufferedReader socketReader1 = null;
            BufferedWriter socketWriter2 = null;
            try {
                // Create a buffered reader and writer for teh socket
                socketReader1 = new BufferedReader(
                        new InputStreamReader(c1.getInputStream()));
                socketWriter2 = new BufferedWriter(
                        new OutputStreamWriter(c2.getOutputStream()));
                String inMsg = null;
                while ((inMsg = socketReader1.readLine()) != null) {
                    System.out.println("Received from client1: " + inMsg);
                    // Echo the received message to the client
                    String outMsg = inMsg;
                    socketWriter2.write(outMsg);
                    socketWriter2.write("\n");
                    socketWriter2.flush();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    c1.close();
                    c2.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}