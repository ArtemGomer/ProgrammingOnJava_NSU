package Client;

import Views.ConsoleView;


public class ClientMain {
    public static void main(String[] args) {
        try {
            var tcpClient = new TcpClient("127.0.0.1", 2048);
            ConsoleView view = new ConsoleView(tcpClient);
            view.connect();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
