package model;

import java.net.Socket;

public class AppClientG {
    public static final int PORT = 3400;
    public static final String SERVER = "localhost";

    private Socket clientSideSocket;

    public AppClientG() {

<<<<<<< HEAD
        System.out.println("Cliente del Banco en ejecución...");
    }

    public void init(int opcion, String mensaje) throws Exception {
        clientSideSocket = new Socket(SERVER, PORT);

        AppClientProtocolG.protocol(clientSideSocket,opcion, mensaje);
=======
    public void init(int opcion, String mensaje) throws Exception {
        clientSideSocket = new Socket(SERVER, PORT);

        AppClientProtocolG.protocol(clientSideSocket, opcion, mensaje);
>>>>>>> a89a6d8c6e9422bea8a75dd548a3a7b1770b4a7c
        clientSideSocket.close();
    }
}
