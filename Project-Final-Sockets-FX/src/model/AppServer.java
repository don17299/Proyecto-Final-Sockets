package model;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class AppServer {
    public static final int PORT = 3400;
    private static HashMap<String, CuentaAhorros> cuentas;
    private ServerSocket listener;
    private Socket serverSideSocket;

    public AppServer() {
        System.out.println("El servidor del Banco esta corriendo en: " + PORT);
        cuentas = new HashMap<String, CuentaAhorros>();
    }

    public static void main(String args[]) throws Exception {
        AppServer es = new AppServer();
        es.init();
    }

    private void init() throws Exception {
        listener = new ServerSocket(PORT);
        while (true) {
            serverSideSocket = listener.accept();
            AppServerProtocol.protocol(serverSideSocket, cuentas);
        }
    }
}
