package blinov_13_9;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

public class MultiThreadChatServerSync {

    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;

    private static final int maxClientsCount = 10;
    private static final clientThread[] threads = new clientThread[maxClientsCount];

    public static void main(String args[]) {

        int portNumber = 2222;

        System.out.println("Usage: java MultiThreadChatServerSync <portNumber>\n"
                + "Now using port number=" + portNumber);

        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println(e);
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                int i = 0;
                for (i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == null) {
                        (threads[i] = new clientThread(clientSocket, threads)).start();
                        break;
                    }
                }
                if (i == maxClientsCount) {
                    PrintStream os = new PrintStream(clientSocket.getOutputStream());
                    os.println("Server too busy. Try later.");
                    os.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}

/*
 * The chat client thread. This client thread opens the input and the output
 * streams for a particular client, ask the client's name, informs all the
 * clients connected to the server about the fact that a new client has joined
 * the chat room, and as long as it receive data, echos that data back to all
 * other clients. The thread broadcast the incoming messages to all clients and
 * routes the private message to the particular client. When a client leaves the
 * chat room this thread informs also all the clients about that and terminates.
 */
class clientThread extends Thread {

    private String clientName = null;
    private DataInputStream is = null;
    private PrintStream os = null;
    private Socket clientSocket = null;
    private final clientThread[] threads;
    private int maxClientsCount;

    public clientThread(Socket clientSocket, clientThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        maxClientsCount = threads.length;
    }

    public void run() {
        int maxClientsCount = this.maxClientsCount;
        clientThread[] threads = this.threads;

        try {

            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());
            String name;
            while (true) {
                os.println("Enter your name.");
                name = is.readLine().trim();
                if (name.indexOf('@') == -1) {
                    break;
                } else {
                    os.println("The name should not contain '@' character.");
                }
            }

            /* Welcome the new the client. */
            os.println("==================================================\n"
                    + "Welcome " + name
                    + " to our chat room.\nTo leave enter /quit in a new line.\n"
                    + "To sent a private message use @Name"
                    + "To sent a file use @Name fileName.txt\n(They are in source folder)\n"
                    + "==================================================\n");
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null && threads[i] == this) {
                        clientName = "@" + name;
                        break;
                    }
                }
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null && threads[i] != this) {
                        threads[i].os.println("*** A new user " + name
                                + " entered the chat room !!! ***");

                    }
                }
            }
            System.out.println("*** A new user " + name
                    + " entered the chat room !!! ***");
            /* Start the conversation. */
            while (true) {
                String line = is.readLine();
                if (line.startsWith("/quit")) {
                    break;
                }
                /* If the message is private sent it to the given client. */
                if (line.startsWith("@")) {
                    String[] words = line.split("\\s", 2);
                    if (words.length > 1 && words[1] != null) {
                        words[1] = words[1].trim();
                        if (!words[1].isEmpty()) {
                            synchronized (this) {
                                for (int i = 0; i < maxClientsCount; i++) {
                                    if (threads[i] != null && threads[i] != this
                                            && threads[i].clientName != null
                                            && threads[i].clientName.equals(words[0])) {
                                        threads[i].os.println("<" + name + "> " + words[1]);
                                        /*
                                         * Echo this message to let the client know the private
                                         * message was sent.
                                         */
                                        // this.os.println(">" + name + "> " + words[1]);
                                        try {
                                            Path originalPath = Paths.get("src/blinov_13_9/source/" + words[1]);
                                            Path copied = Paths.get(String.format("src/blinov_13_9/destination/from_%s_to_%s.txt", clientName, words[0]));
                                            Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
                                        } catch (IOException e) {
                                            System.out.println("This file doesn't exist");
                                        }

                                        break;
                                    }


                                }
                            }
                        }
                    }
                } else {
                    /* The message is public, broadcast it to all other clients. */
                    synchronized (this) {
                        for (int i = 0; i < maxClientsCount; i++) {
                            if (threads[i] != null && threads[i].clientName != null && threads[i] != this) {
                                threads[i].os.println("<" + name + "> " + line);
                            }
                        }
                    }
                }
            }
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null && threads[i] != this
                            && threads[i].clientName != null) {
                        threads[i].os.println("*** The user " + name
                                + " is leaving the chat room !!! ***");

                    }
                }

            }
            os.println("*** Bye " + name + " ***");
            System.out.println("*** The user " + name
                    + " is leaving the chat room !!! ***");
            /*
             * Clean up. Set the current thread variable to null so that a new client
             * could be accepted by the server.
             */
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == this) {
                        threads[i] = null;
                    }
                }
            }
            /*
             * Close the output stream, close the input stream, close the socket.
             */
            is.close();
            os.close();
            clientSocket.close();
        } catch (IOException e) {
        }
    }
}