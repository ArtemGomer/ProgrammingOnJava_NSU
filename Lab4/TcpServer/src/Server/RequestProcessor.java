package Server;

import ClientType.Client;
import Messages.Message;
import Messages.MessageType;

import java.io.*;
import java.net.Socket;

public class RequestProcessor implements Runnable {
    private final Socket socket; //Точка установленного соединения
    private final TcpServer server;
    ObjectOutputStream writer;

    RequestProcessor(Socket socket, TcpServer server) {
        this.socket = socket;
        this.server = server;
    }

    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }

    public void sendMessageToThisClient(Message message) throws IOException {
//        System.out.println("CLIENT IS WRITING5");
        writer.writeObject(message);
    }

    public void run() {
        try {
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            this.writer = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                Message message = (Message) reader.readObject();
                System.out.println(message.getMessage());
                if (message.getType() == MessageType.REGISTRATION_ATTEMPT) {
                    if (server.isValidName(message.getMessage())) {
                        server.addClient(new Client(message.getMessage(), this));
                        writer.writeObject(new Message(MessageType.REGISTRATION_SUCCESS, "Вы успешно зарегистрировались"));
                    } else {
                        writer.writeObject(new Message(MessageType.REGISTRATION_FAILURE, "Неверное имя. Повторите попытку"));
                    }
                    writer.flush();
                }
                if (message.getType() == MessageType.TEXT_REQUEST) {
//                    System.out.println("WHY IS THIS???");
                    server.sendMessageToClients(message);
                }
                if (message.getType() == MessageType.EXIT_ATTEMPT){
                    server.removeClient(message.getMessage());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

