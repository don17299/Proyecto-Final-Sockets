package model.interfaz;

import view.ControladorPrincipal;

import java.net.Socket;

public class AppClientG {
    public static final int PORT = 3400;
    public static final String SERVER = "localhost";

    private Socket clientSideSocket;

    public AppClientG() {
        System.out.println("Cliente del Banco se ejecuto...");
    }

    public void init(int opcion, String mensaje, ControladorPrincipal c) throws Exception {
        clientSideSocket = new Socket(SERVER, PORT);

        AppClientProtocolG.setControladorPrincipal(c);
        AppClientProtocolG.protocol(clientSideSocket, opcion, mensaje);
        clientSideSocket.close();
    }
}
