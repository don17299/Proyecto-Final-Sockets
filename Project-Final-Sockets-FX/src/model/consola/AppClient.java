package model.consola;

import java.net.Socket;

public class AppClient {
    public static final int PORT = 3400;
    public static final String SERVER = "localhost";

    private Socket clientSideSocket;

    public AppClient() {
        System.out.println("Cliente del Banco en ejecución...");
    }

    public static void main(String args[]) throws Exception {
        AppClient ec = new AppClient();
        ec.init();
    }

    public void init() throws Exception {
        clientSideSocket = new Socket(SERVER, PORT);

        AppClientProtocol.protocol(clientSideSocket);
        clientSideSocket.close();
    }
}
