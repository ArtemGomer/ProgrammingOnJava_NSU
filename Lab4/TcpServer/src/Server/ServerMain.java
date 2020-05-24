package Server;

public class ServerMain {
    public static void main(String[] args) {
        try {
            Thread server = new Thread(new TcpServer(2048));
            server.start();
            server.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
