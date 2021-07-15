package model;

import java.io.BufferedReader;
import java.io.IOException;
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
	//private static boolean isLogin=true;
	
	public static void protocol(Socket socket, HashMap<String,CuentaAhorros> cuentas) throws IOException{
		createStreams(socket);
		
		String message= fromNetwork.readLine();
		System.out.println("[Server] from Client: "+ message);
		
		String opcion = message.split(ESPACIO)[0];
		switch (opcion) {
		case "ABRIR_CUENTA": 
			String nombre = message.split(ESPACIO)[1]+ESPACIO+message.split(ESPACIO)[2];
			if(cuentas.containsKey(nombre)) {
				toNetwork.println("Error al crear la cuenta, nombre de cuenta repetido");
			}else {
				CuentaAhorros cuenta= new CuentaAhorros(numeroCuenta+"",nombre);
				cuentas.put(nombre,cuenta);
				numeroCuenta++;
				toNetwork.println("Transaccion exitosa su numero de cuenta es el :"+(numeroCuenta-1)+cuentas.toString());
			}
			
			break;
		case "INFORMEDETALLADO":
			break;
		case "LOGIN":
				
			break;
			default:toNetwork.println("Ocurrio un error");
			
		}
		
		
		
	}
	
	public static void createStreams(Socket socket) throws IOException{
		toNetwork= new PrintWriter(socket.getOutputStream(), true);
		fromNetwork= new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
}
