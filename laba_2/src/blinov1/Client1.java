package blinov1;

import java.io.*;
import java.net.*;
/*Send messages to Client 2*/
class Client1 {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedWriter socketWriter = null;

        try {
            socket = new Socket("localhost", 12900);

            System.out.println("Started client1 socket at " +
                    socket.getLocalSocketAddress());
            socketWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            BufferedReader consoleReader =
                    new BufferedReader(new InputStreamReader(System.in));
            String promptMsg = "Please enter a message for Client2 (Bye to quit):";
            String outMsg = null;
            System.out.print(promptMsg);
            while ((outMsg = consoleReader.readLine()) != null) {
                if (outMsg.equalsIgnoreCase("bye")) {
                    break;
                }
                // Add a new line to the message to the server,
                // because the server reads one line at a time.
                socketWriter.write(outMsg);
                socketWriter.write("\n");
                socketWriter.flush();
                // Read and display the message from the server
                System.out.print(promptMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Finally close the socket
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