package ClientType;

import Messages.Message;
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
        processor.sendMessageToThisClient(message);
    }
}
