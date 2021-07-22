package model.interfaz;

import view.ControladorPrincipal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AppClientProtocolG {
    private static final String ESPACIO = " ";

    private static PrintWriter toNetwork;
    private static BufferedReader fromNetwork;
    private static ControladorPrincipal controladorPrincipal;
    public static void protocol(Socket socket, int opcion, String mensaje) throws Exception {
        createStreams(socket);

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
            case 8:
                mensaje="CONSULTAR"+ESPACIO+mensaje;
                break;
            case 9:
                mensaje = "CONSULTAR_NUMERO_CUENTAS";
                break;
            default:
        }


        toNetwork.println(mensaje);

        String fromServer = fromNetwork.readLine();
        controladorPrincipal.mostrarMensajeServidor(fromServer);
    }

    public static void createStreams(Socket socket) throws Exception {
        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static ControladorPrincipal getControladorPrincipal() {
        return controladorPrincipal;
    }

    public static void setControladorPrincipal(ControladorPrincipal controladorPrincipal) {
        AppClientProtocolG.controladorPrincipal = controladorPrincipal;
    }
}
