package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AppClientProtocolG {
    private static final String ESPACIO = " ";

    private static PrintWriter toNetwork;
    private static BufferedReader fromNetwork;

    public static void protocol(Socket socket, int opcion, String mensaje) throws Exception {
        createStreams(socket);

        String mensaje = "";
        switch (opcion) {

            case 1:
                mensaje = "ABRIR_CUENTA" + ESPACIO + mensaje;
                break;
            case 2:
                mensaje = "ABRIR_BOLSILLO" + ESPACIO + mensaje;
                break;
            case 3:
                mensaje = "CANCELAR_BOLSILLO" + ESPACIO + mensaje;
                break;
            case 4:
                mensaje = "CANCELAR_CUENTA" +ESPACIO + mensaje;
                break;
            case 5:
                mensaje = "DEPOSITAR" +ESPACIO + mensaje;
                break;
            case 6:
                mensaje = "RETIRAR" +ESPACIO + mensaje;
                break;
            case 7:

                mensaje= "TRASLADAR"+ESPACIO+ mensaje;
                break;
            case 9:
                mensaje = "CONSULTAR_NUMERO_CUENTAS";
                break;
            default:
        }


        toNetwork.println(mensaje);

        String fromServer = fromNetwork.readLine();
        System.out.println("[Client] from server: " + fromServer);
    }

    public static void createStreams(Socket socket) throws Exception {
        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

}
