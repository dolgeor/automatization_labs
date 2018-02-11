package blinov_13_3.server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    // экземпляр нашего сервера
    private Server server;
    // исходящее сообщение

    private BufferedReader inMessage = null;
    private BufferedWriter outMessage = null;

    private static final String HOST = "localhost";
    private static final int PORT = 12900;
    // клиентский сокет
    private Socket clientSocket = null;
    // количество клиента в чате, статичное поле
    private static int clients_count = 0;

    // конструктор, который принимает клиентский сокет и сервер
    public ClientHandler(Socket socket, Server server) {
        try {
            clients_count++;
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.inMessage = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Переопределяем метод run(), который вызывается когда
    // мы вызываем new Thread(client).start();
    @Override
    public void run() {
        try {
//            server.sendMessageToAllClients(String.format("New user entered ... %nTotal users = %d%n", clients_count));

            while (true) {
                String clientMessage = inMessage.readLine();
                if (clientMessage.equalsIgnoreCase("bye")) {
                    break;
                }
                System.out.println(clientMessage);
                server.sendMessageToAllClients(clientMessage);
                Thread.sleep(100);
            }
        } catch (InterruptedException | IOException ex) {
            ex.printStackTrace();
        } finally {
            this.close();
        }
    }

    // отправляем сообщение
    public void sendMsg(String msg) {
        try {
            outMessage.write(msg);
            outMessage.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // клиент выходит из чата
    public void close() {
        // удаляем клиента из списка
        server.removeClient(this);
        clients_count--;
        server.sendMessageToAllClients(String.format("Total users = %d%n", clients_count));
    }
}

