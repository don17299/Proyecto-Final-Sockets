package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

public class AppServerProtocol {
    private static final String ESPACIO = " ";
    private static PrintWriter toNetwork;
    private static BufferedReader fromNetwork;
    private static int numeroCuenta = 0;
    private static boolean isLogged = false;

    public static void protocol(Socket socket, HashMap<String, CuentaAhorros> cuentas) throws Exception {
        createStreams(socket);

        String message = fromNetwork.readLine();
        System.out.println("[Server] from Client: " + message);
        String mensajeAlServidor = "";
        String opcion = message.split(ESPACIO)[0];
        switch (opcion) {

            case "ABRIR_CUENTA":
                String nombre = message.split(ESPACIO)[1] + ESPACIO + message.split(ESPACIO)[2];
                Set<String> keys = cuentas.keySet();
                for (String key : keys) {
                    if (cuentas.get(key).getNombreUsuario().equals(nombre)) {
                        isLogged = true;
                        break;
                    }
                }
                if (isLogged) {
                    mensajeAlServidor = "Error al crear la cuenta, nombre de cuenta repetido";
                } else {
                    CuentaAhorros cuenta = new CuentaAhorros(nombre);
                    cuentas.put(numeroCuenta + "", cuenta);
                    numeroCuenta++;
                    mensajeAlServidor = "Transaccion exitosa su numero de cuenta es el :" + (numeroCuenta - 1) + cuentas.toString();
                }

                break;

            case "ABRIR_BOLSILLO":

                String numeroCuenta = message.split(ESPACIO)[1];

                if (numeroCuenta.length() == 0 || !(Character.isDigit(numeroCuenta.charAt(0)))) {
                    mensajeAlServidor = "Informacion insuficiente";
                } else {
                    CuentaAhorros cuentaUsuario = cuentas.get(numeroCuenta);
                    if (cuentaUsuario == null) {
                        mensajeAlServidor = "La cuenta no existe";
                    } else {
                        if (cuentaUsuario.getCuentaBolsillo().getDisponible() == false) {
                            CuentaBolsillo micuentaBolsillo = cuentaUsuario.getCuentaBolsillo();
                            micuentaBolsillo.setDisponible(true);
                            micuentaBolsillo.setNombreCuenta(numeroCuenta + "b");
                            cuentaUsuario.setCuentaBolsillo(micuentaBolsillo);
                            cuentas.replace(numeroCuenta, cuentaUsuario);
                            mensajeAlServidor = "Bolsillo abierto exitosamente ";
                        } else if (cuentaUsuario.getCuentaBolsillo().getDisponible() == true) {
                            mensajeAlServidor = "Bolsillo ya existente ";
                        }
                    }


                }

                break;
            case "INFORMEDETALLADO":

                break;
            case "LOGIN":

                break;

            case "CONSULTAR_NUMERO_CUENTAS":

                mensajeAlServidor = mostrarNumeroCuentas(cuentas);
                break;

            default:
                mensajeAlServidor = "Ocurrio un error";

                break;


        }
        enviarAlServidor(mensajeAlServidor);


    }

    public static void enviarAlServidor(String texto) {
        toNetwork.println(texto);
    }

    public static String mostrarNumeroCuentas(HashMap<String, CuentaAhorros> cuentas) {
        return cuentas.toString();
    }

    public static void createStreams(Socket socket) throws Exception {
        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
