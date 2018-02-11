package mini_chat;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static ServerSocket serverSocket;
    static char c = 'A';

    static {
        try {
            serverSocket = new ServerSocket(12900, 100, InetAddress.getByName("localhost"));
            System.out.println("Chat started ...\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            while (true) {
                final Socket s = serverSocket.accept();
                System.out.println(c + " entered in chat");
                Runnable runnable =
                        () -> handleClientRequest(s);
                new Thread(runnable).start(); // start a new thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handleClientRequest(Socket socket) {
        String name = c + "";
        c++;
        BufferedReader socketReader = null;
        BufferedWriter socketWriter = null;
        try {
            socketReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            socketWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));


            String inMsg = null;
            while ((inMsg = socketReader.readLine()) != null) {
                System.out.println( name + " : " + inMsg);

                // Echo the received message to the client
                String outMsg = String.format("%s : %s%n",name,inMsg);

                socketWriter.write(outMsg);
                socketWriter.flush();
            }
            System.out.println(name + " exited from chat");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}