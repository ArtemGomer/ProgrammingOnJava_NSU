package Client;

import Messages.Message;
import Messages.MessageType;
import Observers.Observable;

import java.io.*;
import java.net.Socket;

public class TcpClient extends Observable implements Runnable {
    private final Socket socket;
    private final ObjectOutputStream writer;
    private final ObjectInputStream reader;
    private String name;
    private boolean serverWorking;

    public TcpClient(String ip, int port) throws IOException {
        this.socket = new Socket(ip, port);
        this.writer = new ObjectOutputStream(socket.getOutputStream());
        this.reader = new ObjectInputStream(socket.getInputStream());
    }

    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }

    public boolean connect(Message message) {
        try {
            writer.writeObject(message);
            Message reply = (Message) reader.readObject();
            if (reply.getType() == MessageType.REGISTRATION_SUCCESS) {
                this.name = message.getMessage();
                Thread messageReader = new Thread(this);
                messageReader.start();
                return true;
            } else {
                serverWorking = true;
                return false;
            }
        }catch (Exception ex) {
            this.message = new Message(MessageType.TEXT_RESPONSE, "Server is not working!");
            this.notifyObservers();
            serverWorking = false;
            return false;
        }
    }

    public boolean sendMessageToServer(String line) {
        try {
            if (!line.startsWith("/")) {
                writer.writeObject(new Message(MessageType.TEXT_REQUEST, name + ": " + line));
            } else {
                if (line.substring(1).equalsIgnoreCase("exit")) {
                    writer.writeObject(new Message(MessageType.EXIT_ATTEMPT, name));
                    writer.flush();
                    this.close();
                    return false;
                }
                writer.writeObject(new Message(MessageType.COMMAND, line.substring(1)));
            }
            writer.flush();
            return true;
        } catch (Exception ex){
            this.message = new Message(MessageType.TEXT_RESPONSE, "Server is not working!");
            this.notifyObservers();
            return false;
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.message = (Message) reader.readObject();
                this.notifyObservers();
            }
        } catch (Exception ignored) {
        }
    }

    public boolean isServerWorking() {
        return serverWorking;
    }
}

