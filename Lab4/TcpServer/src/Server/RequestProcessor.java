package Server;

import ClientType.Client;
import Messages.Message;
import Messages.MessageType;

import java.io.*;
import java.net.Socket;

public class RequestProcessor implements Runnable {
    private final Socket socket; //Точка установленного соединения
    private final TcpServer server;
    private ObjectOutputStream writer;
    private String clientName;

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
        writer.writeObject(message);
    }

    public void run() {
        try {
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());
            this.writer = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                Message message = (Message) reader.readObject();
                if (message.getType() == MessageType.REGISTRATION_ATTEMPT) {
                    if (server.isValidName(message.getMessage())) {
                        clientName = message.getMessage();
                        server.addClient(new Client(clientName, this));
                        writer.writeObject(new Message(MessageType.REGISTRATION_SUCCESS, "Вы успешно зарегистрировались"));
                        server.sendMessageToClients(new Message(MessageType.TEXT_RESPONSE, clientName + " has entered the chat!"));
                    } else {
                        writer.writeObject(new Message(MessageType.REGISTRATION_FAILURE, "Неверное имя. Повторите попытку"));
                    }
                }
                if (message.getType() == MessageType.TEXT_REQUEST) {
                    server.sendMessageToClients(message);
                }
                if (message.getType() == MessageType.EXIT_ATTEMPT) {
                    server.removeClient(clientName);
                    server.sendMessageToClients(new Message(MessageType.TEXT_RESPONSE, message.getMessage() + " has left the chat!"));
                }
                if (message.getType() == MessageType.COMMAND) {
                    if (message.getMessage().equals("users")) {
                        String names = server.getUsersNames();
                        writer.writeObject(new Message(MessageType.TEXT_RESPONSE, names));
                    }
                }
                writer.flush();
            }
        } catch (Exception ex) {
            server.removeClient(clientName);
            server.sendMessageToClients(new Message(MessageType.TEXT_RESPONSE, clientName + " has left the chat!"));
        }
    }
}
