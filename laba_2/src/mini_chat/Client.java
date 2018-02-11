package mini_chat;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        BufferedReader socketReader = null;
        BufferedWriter socketWriter = null;
        try (Socket socket = new Socket("localhost", 12900);) {

            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String promptMsg = "Please enter a message (Bye to quit):";
            String outMsg = null;

            System.out.println("Started  socket ...\n ");
            System.out.print(promptMsg);
            while ((outMsg = consoleReader.readLine()) != null) {
                if (outMsg.equalsIgnoreCase("bye")) {
                    break;
                }
                // Add a new line to the message to the server,
                // because the server reads one line at a time.
                socketWriter.write(String.format("%s%n", outMsg));
                socketWriter.flush();
                // Read and display the message from the server
                String inMsg = socketReader.readLine();

                System.out.println(inMsg);
                System.out.println(); // Print a blank line
                System.out.print(promptMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
