package blinov_13_4;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12900, 100, InetAddress.getByName("localhost"));
            System.out.println("Server started ...\n");
            while (true) {
                final Socket s = serverSocket.accept();
                System.out.println("New user entered in chat : " + s);
                Runnable runnable =
                        () -> handleClientRequest(s);
                new Thread(runnable).start(); // start a new thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handleClientRequest(Socket socket) {
        BufferedWriter socketWriter = null;
        try {
            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String outMsg = getSonet();

            socketWriter.write(outMsg);
            socketWriter.flush();
            Thread.sleep(5000);
            System.out.println("User exited from chat : " + socket);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getSonet() throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/blinov_13_4/sonets/" + (new SecureRandom().nextInt(5) + 1) + ".txt")));
    }
}
