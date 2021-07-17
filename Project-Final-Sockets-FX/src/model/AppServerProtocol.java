package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

public class AppServerProtocol {
    private static PrintWriter toNetwork;
    private static BufferedReader fromNetwork;
    private static final String ESPACIO=" ";
    private static int numeroCuenta=0;
    private static boolean isLogged=false;
    public static void protocol(Socket socket, HashMap<String,CuentaAhorros> cuentas) throws Exception{
        createStreams(socket);

        String message= fromNetwork.readLine();
        System.out.println("[Server] from Client: "+ message);

        String opcion = message.split(ESPACIO)[0];
        switch (opcion) {

            case "ABRIR_CUENTA":
                String nombre = message.split(ESPACIO)[1]+ESPACIO+message.split(ESPACIO)[2];
                Set<String> keys=cuentas.keySet();
                for(String key:keys){
                    if(cuentas.get(key).getNombreUsuario().equals(nombre)){
                        isLogged=true;
                        break;
                    }
                }
                if(isLogged) {
                    toNetwork.println("Error al crear la cuenta, nombre de cuenta repetido");
                }else {
                    CuentaAhorros cuenta= new CuentaAhorros(nombre);
                    cuentas.put(numeroCuenta+"",cuenta);
                    numeroCuenta++;
                    toNetwork.println("Transaccion exitosa su numero de cuenta es el :"+(numeroCuenta-1)+cuentas.toString());
                }

                break;

            case "ABRIR_BOLSILLO":

                String numeroCuenta = message.split(ESPACIO)[1];
                System.out.println("El numero de cuenta que llego es de "+numeroCuenta);
                CuentaAhorros  cuentaUsuario=cuentas.get(numeroCuenta);
                cuentas.remove(numeroCuenta);
                CuentaBolsillo micuentaBolsillo=cuentaUsuario.getCuentaBolsillo();
                micuentaBolsillo.setDisponible(true);
                micuentaBolsillo.setNombreCuenta(numeroCuenta+"b");
                micuentaBolsillo.setSaldoBolsillo(1.0);
                cuentaUsuario.setCuentaBolsillo(micuentaBolsillo);
                cuentas.put(numeroCuenta,cuentaUsuario);
                toNetwork.println("Cuenta abierta exitosamente " + cuentas.toString());
                break;
            case "INFORMEDETALLADO":
                break;
            case "LOGIN":

                break;

            case "CONSULTAR_NUMERO_CUENTAS":

                toNetwork.println(mostrarNumeroCuentas(cuentas));
                    break;
            default:toNetwork.println("Ocurrio un error");

        }



    }



    public static String mostrarNumeroCuentas( HashMap<String,CuentaAhorros> cuentas)
    {
         return cuentas.toString();
    }

    public static void createStreams(Socket socket) throws Exception{
        toNetwork= new PrintWriter(socket.getOutputStream(), true);
        fromNetwork= new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
