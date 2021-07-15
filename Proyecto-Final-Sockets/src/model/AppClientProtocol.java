package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AppClientProtocol {
    private static final Scanner SCANNER= new Scanner(System.in);
    private static final String ESPACIO=" ";

    private static PrintWriter toNetwork;
    private static BufferedReader fromNetwork;

    public static void protocol(Socket socket) throws Exception{
        createStreams(socket);

        System.out.println("Bienvenido al Banco, elija una opcion:\n"
                + "1)Abrir Cuenta\n"
                + "2)Crear un Bolsillo\n"
                + "3)Cancelar Bolsillo\n"
                + "4)Cancelar Cuenta de Ahorros\n"
                + "5)Depositar Dinero en una Cuenta de Ahorros\n"
                + "6)Retirar Dinero de una Cuenta de Ahorros\n"
                + "7)Trasladar Dinero a un Bolsillo\n"
                + "8)Consultar Saldo\n"
                + "9)Salir");

        int opcion= Integer.parseInt(SCANNER.nextLine());


        String mensaje="";
        switch(opcion) {

            case 1:
                mensaje="ABRIR_CUENTA"+ESPACIO+abrirCuenta();
                break;
            case 2:
                mensaje="ABRIR_BOLSILLO";
                break;

            default:System.out.println("Error");

        }

        toNetwork.println(mensaje);

        String fromServer = fromNetwork.readLine();
        System.out.println("[Client] from server: "+ fromServer);
    }

    public static void createStreams(Socket socket) throws Exception{
        toNetwork= new PrintWriter(socket.getOutputStream(), true);
        fromNetwork= new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public static String abrirCuenta() {
        System.out.println("Ingrese su nombre y apellido:\n");
        String nombre= SCANNER.nextLine();

        return nombre;

    }
}
