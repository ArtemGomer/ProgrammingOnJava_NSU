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

    public boolean connect(Message message) throws Exception {
        writer.writeObject(message);
        Message reply = (Message) reader.readObject();
        if (reply.getType() == MessageType.REGISTRATION_SUCCESS){
            this.name = message.getMessage();
            return true;
        } else {
            return false;
        }
    }

    public boolean sendMessageToServer(String line) throws IOException {
        if (line.equalsIgnoreCase("exit")){
            writer.writeObject(new Message(MessageType.EXIT_ATTEMPT, name));
            writer.flush();
            this.close();
            return false;
        } else if (!line.startsWith("/")){
            writer.writeObject(new Message(MessageType.TEXT_REQUEST, name + ": " + line));
            writer.flush();
        } else {
            writer.writeObject(new Message(MessageType.COMMAND, line.substring(1)));
            writer.flush();
        }
        return true;
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.message = (Message) reader.readObject();
                this.notifyObservers();
            }
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }
    }
}

