package Views;

import Client.TcpClient;
import Messages.Message;
import Messages.MessageType;
import Observers.Observer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleView implements Observer {

    private final TcpClient client;

    public ConsoleView(TcpClient tcpClient){
        this.client = tcpClient;
        this.client.addObserver(this);
    }

    @Override
    public void update(Message message) {
        System.out.println(message.getMessage());
    }

    public void connect() throws Exception {
        String line;
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your name to enter the chat!");
        while (true) {
            line = consoleReader.readLine();
            if (!this.client.connect(new Message(MessageType.REGISTRATION_ATTEMPT, line))) {
                if (client.isServerWorking()) {
                    System.out.println("User with this name is already exists. Try again!");
                } else {
                    return;
                }
            } else {
                System.out.println("Registration is completed successfully. Now you can talk with users!");
                break;
            }
        }
        do {
            line = consoleReader.readLine();
        } while (client.sendMessageToServer(line));
    }
}
