package Server;

import ClientType.Client;
import Messages.Message;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TcpServer implements Runnable {

    private final int port;
    private ServerSocket socket;
    private final List<Client> clients = Collections.synchronizedList(new LinkedList<>());

    boolean isValidName(String name) {
        for (Client client : clients) {
            if (name.equals(client.getName())) {
                return false;
            }
        }
        return true;
    }

    public void addClient(Client client) {
        this.clients.add(client);
    }

    public void removeClient(String name) {
        for (Client client : clients) {
            if (client.getName().equals(name)) {
                clients.remove(client);
                break;
            }
        }
    }

    public String getUsersNames(){
        StringBuilder usersList = new StringBuilder();
        for (Client client : clients) {
            usersList.append(client.getName()).append("\n");
        }
        return usersList.toString();
    }


    public TcpServer(int port){
        this.port = port;
    }

    public void sendMessageToClients(Message message){
        for (Client client : clients){
            try {
                client.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close()  {
        if (this.socket != null){
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Client client : clients){
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {
            this.socket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = this.socket.accept();
                System.out.println("Получено соединение от:" + clientSocket.getInetAddress()
                        + ":" + clientSocket.getPort());
                RequestProcessor client = new RequestProcessor(clientSocket, this);
                Thread processor = new Thread(client);
                System.out.println("Запуск обработчика...");
                processor.start();
            }
        } catch (IOException ex) {
            this.close();
            ex.printStackTrace();
        }
    }
}
