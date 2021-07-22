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
        String mensajeAlCliente = "";
        String opcion = message.split(ESPACIO)[0];
        switch (opcion) {

            case "ABRIR_CUENTA":
                String nombre="";
                String[] entrada=message.split(ESPACIO);
                for(int i=1;i<entrada.length;i++){
                            nombre+=message.split(ESPACIO)[i] + ESPACIO;
                }
                nombre = nombre.trim();
                Set<String> keys = cuentas.keySet();
                for (String key : keys) {
                    if (cuentas.get(key).getNombreUsuario().equals(nombre)) {
                        isLogged = true;
                        break;
                    }
                }
                if (isLogged) {
                    mensajeAlCliente = "ERR:Error al crear la cuenta, nombre de cuenta repetido";
                } else {
                    CuentaAhorros cuenta = new CuentaAhorros(nombre);
                    cuentas.put(numeroCuenta + "", cuenta);
                    numeroCuenta++;
                    cuenta.getTransacciones().add(new Transaccion("ABRIR-CUENTA"));
                    mensajeAlCliente = "Transaccion exitosa su numero de cuenta es el : " + (numeroCuenta - 1);
                }

                break;

            case "ABRIR_BOLSILLO":

                String numeroCuenta = message.split(ESPACIO)[1];

                if(validarNumeroCuenta(numeroCuenta)){
                    if(validarExistenciaCuenta(numeroCuenta,cuentas)){
                        CuentaAhorros cuentaUsuario = cuentas.get(numeroCuenta);
                        if(!cuentaUsuario.isDisponible()){
                            CuentaBolsillo micuentaBolsillo = new CuentaBolsillo();
                            micuentaBolsillo.setNombreCuenta(numeroCuenta + "b");
                            cuentaUsuario.setCuentaBolsillo(micuentaBolsillo);
                            cuentaUsuario.getTransacciones().add(new Transaccion("ABRIR-BOLSILLO"));
                            mensajeAlCliente = "Bolsillo abierto exitosamente, numero de bolsillo: "+micuentaBolsillo.getNombreCuenta();

                        }else{
                            mensajeAlCliente = "ERR:Bolsillo ya existente ";
                        }
                    }else{
                        mensajeAlCliente = "ERR:La cuenta no existe";
                    }
                }else{
                    mensajeAlCliente = "ERR:Informacion insuficiente o inconsistente";
                }
                break;

            case "CANCELAR_BOLSILLO":
                String numeroBolsillo = message.split(ESPACIO)[1];
                String numCuenta=numeroBolsillo.substring(0, numeroBolsillo.length() - 1);
                if(validarNumeroBolsillo(numeroBolsillo,numCuenta)){
                    if(validarExistenciaCuenta(numCuenta, cuentas)){
                        CuentaAhorros cuentaUsuario = cuentas.get(numCuenta);
                        if(cuentaUsuario.isDisponible()){
                            CuentaBolsillo bolsillo= cuentaUsuario.getCuentaBolsillo();
                            Double saldoB = bolsillo.getSaldoBolsillo();
                            cuentaUsuario.setSaldoCuenta(cuentaUsuario.getSaldoCuenta() + saldoB);
                            bolsillo.setSaldoBolsillo(0.0);
                            cuentaUsuario.setCuentaBolsillo(null);
                            cuentaUsuario.getTransacciones().add(new Transaccion("CANCELAR-BOLSILLO"));
                            mensajeAlCliente = "El bolsillo ha sido borrado de la faz de la tierra ";
                        }else{
                            mensajeAlCliente = "ERR:El bolsillo no ha sido creado ";
                        }
                    }else {
                        mensajeAlCliente = "ERR:La cuenta del bolsillo no existe";
                    }
                }else{
                    mensajeAlCliente = "ERR:Informacion insuficiente o inconsistente";
                }

                break;
            case "CANCELAR_CUENTA":
                String numeroCuenta4 = message.split(ESPACIO)[1];
                //validaciones
                if(validarNumeroCuenta(numeroCuenta4)){
                    if(validarExistenciaCuenta(numeroCuenta4,cuentas)){
                        CuentaAhorros cuentaAhorros = cuentas.get(numeroCuenta4);
                        if(!cuentaAhorros.isDisponible()){
                            if(cuentaAhorros.getSaldoCuenta() == 0) {//debe ser igual a cero
                                cuentas.remove(numeroCuenta4,cuentaAhorros);
                                mensajeAlCliente = "Proceso exitoso, cuenta: "+ numeroCuenta4+" cancelada";
                            } else {
                                mensajeAlCliente = "ERR:Saldo existente, no se puede cancelar la cuenta";
                            }
                        } else {
                            mensajeAlCliente = "ERR:Bolsillo activo, no se puede cancelar la cuenta";
                        }
                    }else{
                        mensajeAlCliente = "ERR:Cuenta inexistente";
                    }
                } else {
                    mensajeAlCliente = "ERR:Informacion insuficiente o inconsistente";
                }

                break;

            case "DEPOSITAR":

                try {
                    String numeroCuenta5 = message.split(ESPACIO)[1];
                    String valor = message.split(ESPACIO)[2];
                    if(validarNumeroCuenta(numeroCuenta5)){
                        if(validarExistenciaCuenta(numeroCuenta5, cuentas)){
                            if(isNumber(valor)){
                                double cantidad = Float.parseFloat(valor);
                                if(cantidad > 0){
                                    CuentaAhorros cuentaAhorros = cuentas.get(numeroCuenta5);
                                    cuentaAhorros.depositar(cantidad);
                                    cuentaAhorros.getTransacciones().add(new Transaccion("DEPOSITAR"));
                                    mensajeAlCliente = "Transaccion realizada exitosamente, saldo actual: "+cuentaAhorros.getSaldoCuenta();
                                } else {
                                    mensajeAlCliente ="ERR:El valor debe ser superior a cero";
                                }
                            } else {
                                mensajeAlCliente ="ERR:La cantidad que ingresa no es un numero";
                            }
                        }else {
                            mensajeAlCliente = "ERR:Cuenta inexistente";
                        }
                    } else {
                        mensajeAlCliente = "ERR:Informacion insuficiente o inconsistente";
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    mensajeAlCliente="ERR:Informaccion ingresada de manera erronea";
                }


                break;
            case "RETIRAR":
                try {
                    String numeroCuenta6 = message.split(ESPACIO)[1];
                    String valor = message.split(ESPACIO)[2];
                    if (validarNumeroCuenta(numeroCuenta6)) {
                        if (validarExistenciaCuenta(numeroCuenta6, cuentas)) {

                            if (isNumber(valor)) {
                                double cantidad = Float.parseFloat(valor);
                                if (cantidad > 0) {
                                    CuentaAhorros cuentaAhorros = cuentas.get(numeroCuenta6);
                                    if (cuentaAhorros.getSaldoCuenta() >= cantidad) {
                                        cuentaAhorros.retirar(cantidad);
                                        cuentaAhorros.getTransacciones().add(new Transaccion("RETIRAR"));
                                        mensajeAlCliente = "Transaccion realizada exitosamente, saldo actual: " + cuentaAhorros.getSaldoCuenta();
                                    } else {
                                        mensajeAlCliente = "ERR:Fondos insuficientes";
                                    }
                                } else {
                                    mensajeAlCliente = "ERR:El valor debe ser superior a cero";
                                }
                            } else {
                                mensajeAlCliente = "ERR:La cantidad que ingresa no es un numero";
                            }
                        } else {
                            mensajeAlCliente = "ERR:Cuenta inexistente";
                        }
                    } else {
                        mensajeAlCliente = "ERR:Informacion insuficiente o inconsistente";
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    mensajeAlCliente="ERR:Informacion ingresada de manera erronea";
                }
                break;
            case "TRASLADAR":
                String numeroCuenta7 = message.split(ESPACIO)[1];
                String valor = message.split(ESPACIO)[2];
                System.out.println("entro");
                double saldoAMover=0.0;
                double saldoBolsillo=0.0;
                if (validarNumeroCuenta(numeroCuenta7)) {
                    if (validarExistenciaCuenta(numeroCuenta7, cuentas)) {
                        if (isNumber(valor)) {
                            double cantidad = Float.parseFloat(valor);
                            if (cantidad > 0) {
                                CuentaAhorros cuentaAhorros = cuentas.get(numeroCuenta7);
                                if (cuentaAhorros.getSaldoCuenta() >= cantidad) {
                                    if(cuentaAhorros.isDisponible()) {
                                        saldoAMover = cuentaAhorros.getSaldoCuenta() - cantidad;
                                        cuentaAhorros.setSaldoCuenta(saldoAMover);
                                        saldoBolsillo = cuentaAhorros.getCuentaBolsillo().getSaldoBolsillo();
                                        cuentaAhorros.getCuentaBolsillo().setSaldoBolsillo(saldoBolsillo + cantidad);
                                        cuentaAhorros.getTransacciones().add(new Transaccion("TRASLADAR"));
                                        mensajeAlCliente="Transaccion exitosa, el saldo actual de su cuenta es: "+cuentaAhorros.getSaldoCuenta()+"y el saldo de su bolsillo es: "+cuentaAhorros.getCuentaBolsillo().getSaldoBolsillo();

                                    }else{
                                             mensajeAlCliente="ERR:El bolsillo no existe";
                                    }
                                } else {
                                    mensajeAlCliente = "ERR:Fondos insuficientes";
                                }
                            } else {
                                mensajeAlCliente = "ERR:El valor debe ser superior a cero";
                            }
                        } else {
                            mensajeAlCliente = "ERR:La cantidad que ingresa no es un numero";
                        }
                    } else {
                        mensajeAlCliente = "ERR:Cuenta inexistente";
                    }
                } else {
                    mensajeAlCliente = "ERR:Informacion insuficiente o inconsistente";
                }


                break;

            case "CONSULTAR":
                String numeroCuenta8 = message.split(ESPACIO)[1];
                if(validarNumeroCuenta(numeroCuenta8)){
                    if(validarExistenciaCuenta(numeroCuenta8, cuentas)){
                        CuentaAhorros cuentaAhorros = cuentas.get(numeroCuenta8);
                        cuentaAhorros.getTransacciones().add(new Transaccion("CONSULTAR-CUENTA"));
                        mensajeAlCliente = "Saldo de la Cuenta de Ahorros: "+ cuentaAhorros.getSaldoCuenta();
                    }else {
                        mensajeAlCliente = "ERR:Cuenta inexistente";
                    }
                } else {
                    String numCuenta8=numeroCuenta8.substring(0, numeroCuenta8.length() - 1);
                    if(validarNumeroBolsillo(numeroCuenta8, numCuenta8)){
                        String cuenta=numeroCuenta8.substring(0, numeroCuenta8.length()-1);
                        if(validarExistenciaCuenta(cuenta,cuentas)){
                            CuentaAhorros cuentaAhorros= cuentas.get(cuenta);
                            if(cuentaAhorros.isDisponible()) {
                                CuentaBolsillo bolsillo= cuentaAhorros.getCuentaBolsillo();
                                cuentaAhorros.getTransacciones().add(new Transaccion("CONSULTAR-BOLSILLO"));
                                mensajeAlCliente = "Saldo del Bolsillo: " + bolsillo.getSaldoBolsillo();
                            }else{
                                mensajeAlCliente="ERR:El bolsillo no ha sido creado";
                            }
                        } else {
                            mensajeAlCliente = "ERR:Cuenta del bolsillo inexistente";
                        }
                    }else{
                        mensajeAlCliente = "ERR:Informacion insuficiente o inconsistente";
                    }
                }

                break;

            case "CONSULTAR_NUMERO_CUENTAS":

                mensajeAlCliente = mostrarNumeroCuentas(cuentas);
                break;

            default:
                mensajeAlCliente = "ERR:Ocurrio un error";

                break;


        }
        enviarAlServidor(mensajeAlCliente);


    }


    private static boolean validarNumeroBolsillo(String numeroBolsillo, String numCuenta) {
        System.out.println(numeroBolsillo.charAt((numeroBolsillo.length()-1)));
        if(!numCuenta.matches("^\\d*$") || numeroBolsillo.charAt((numeroBolsillo.length()-1))!='b'){
            return false;
        } else {
            return true;
        }
    }

    public static boolean validarNumeroCuenta(String numeroCuenta)
    {
        if(numeroCuenta.length()>0 && numeroCuenta.matches("^\\d*$"))
        {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validarExistenciaCuenta(String numeroCuenta, HashMap<String, CuentaAhorros> cuentas) {
        CuentaAhorros cuentaAhorros = cuentas.get(numeroCuenta);
        return cuentaAhorros != null;
    }

    private static boolean isNumber(String valor) {
        float cantidad;
        boolean esNumero =true;
        try {
            cantidad = Float.parseFloat(valor);
        } catch (NumberFormatException e){
            esNumero = false;
        }
        return esNumero;
    }



    public static String mostrarNumeroCuentas(HashMap<String, CuentaAhorros> cuentas) {
        return cuentas.toString();
    }


    public static void enviarAlServidor(String texto) {
        toNetwork.println(texto);
    }


    public static void createStreams(Socket socket) throws Exception {
        toNetwork = new PrintWriter(socket.getOutputStream(), true);
        fromNetwork = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
}
