package ClientType;

import Messages.Message;
import Messages.MessageType;
import Server.RequestProcessor;

import java.io.IOException;

public class Client {
    private final String name;
    private final RequestProcessor processor;

    public Client(String name, RequestProcessor processor) {
        this.name = name;
        this.processor = processor;
    }

    public void close() throws IOException {
        this.processor.close();
    }


    public String getName() {
        return name;
    }

    public void sendMessage(Message message) throws IOException {
//        System.out.println("CLIENT IS WRITING4");
//        message.setMessage(name + ": " + message.getMessage());
//        String namedMessage = getName() + ": " + message.getMessage();
        processor.sendMessageToThisClient(message);
    }
}
