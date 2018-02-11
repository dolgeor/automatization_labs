package blinov_13_3.client;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.net.Socket;

public class Client {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12900;

    private Socket clientSocket;
    private BufferedReader inMessage = null;
    private BufferedWriter outMessage = null;
    private String clientName = "";

    BufferedReader consoleReader = null;
    String promptMsg = "Please enter a message (Bye to quit)\n";
    String outMsg = null;

    public String getClientName() {
        return this.clientName;
    }

    public Client() {
        try {
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            this.consoleReader = new BufferedReader(new InputStreamReader(System.in));
            this.outMessage = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            this.inMessage = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Enter your name: ");
                    clientName = consoleReader.readLine();
                    System.out.println("Hello, " + clientName);
                    System.out.print(promptMsg);
                    listen();
                    while (true) {
                        System.out.println(clientName + " : ");
                        outMsg = consoleReader.readLine();
                        if (outMsg.equalsIgnoreCase("bye")) {
                            break;
                        }
                        sendMsg();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // добавляем обработчик события закрытия окна клиентского приложения
    }

    private void listen() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
//                        String inMsg = inMessage.readLine();
//                        if (inMsg != null) {
//                            System.out.println(inMsg);
//                            System.out.println();
//                        }
                        System.out.println("aaaaa");
                        Thread.sleep(2000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    // отправка сообщения
    public void sendMsg() throws Exception {
        outMessage.write(String.format("%s : %s%n", clientName, outMsg));
        outMessage.flush();
    }
}
