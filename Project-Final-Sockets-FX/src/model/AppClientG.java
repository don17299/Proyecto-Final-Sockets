package model;

import java.net.Socket;

public class AppClientG {
    public static final int PORT = 3400;
    public static final String SERVER = "localhost";

    private Socket clientSideSocket;

    public AppClientG() {
        System.out.println("Cliente del Banco en ejecución...");
    }

    public void init(int opcion, String mensaje) throws Exception {
        clientSideSocket = new Socket(SERVER, PORT);

        AppClientProtocolG.protocol(clientSideSocket, opcion, mensaje);
        clientSideSocket.close();
    }
}
