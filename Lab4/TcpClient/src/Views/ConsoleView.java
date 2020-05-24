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
//        System.out.println("CLIENT IS WRITING7");
        System.out.println(message.getMessage());
    }

    public void connect() throws Exception {
        String line;
        Thread tcpClient;
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("ENTER YOUR NAME");
        while (true) {
            line = consoleReader.readLine();
            if (!this.client.connect(new Message(MessageType.REGISTRATION_ATTEMPT, line))) {
                System.out.println("THIS NAME IS BUSY. TRY AGAIN");
            } else {
                System.out.println("SUCCESSFUL");
                tcpClient = new Thread(this.client);
                tcpClient.start();
                break;
            }
        }
        do {
            line = consoleReader.readLine();
//            System.out.println("CLIENT IS WRITING1");
        } while (client.sendMessageToServer(line));
//        tcpClient.interrupt();
    }
}
