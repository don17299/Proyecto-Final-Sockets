package model;

import java.net.Socket;

public class AppClientG {
    public static final int PORT = 3400;
    public static final String SERVER = "localhost";

    private Socket clientSideSocket;

    public AppClientG() {
        System.out.println("Cliente del Banco en ejecución...");
    }

    public void iniciar(int opcion,String mensaje) throws Exception {
        AppClientG ec = new AppClientG();
        ec.init();
    }

    public void init() throws Exception {
        clientSideSocket = new Socket(SERVER, PORT);

        AppClientProtocol.protocol(clientSideSocketopcion, mensaje);
        clientSideSocket.close();
    }
}
