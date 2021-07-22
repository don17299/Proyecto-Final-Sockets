package model;

import java.util.ArrayList;

public class CuentaAhorros {

    private ArrayList<Transaccion> transacciones;
    private String nombreUsuario;
    private Double saldoCuenta;
    private CuentaBolsillo cuentaBolsillo;

    public CuentaAhorros(String nombreUsuario) {
        super();
        this.nombreUsuario = nombreUsuario;
        this.saldoCuenta = 0.0;
        this.transacciones = new ArrayList<>();
    }

    public CuentaAhorros() {
    }

    public Boolean isDisponible(){
        return cuentaBolsillo!=null;
    }

    public CuentaBolsillo getCuentaBolsillo() {
        return cuentaBolsillo;
    }

    public void setCuentaBolsillo(CuentaBolsillo cuentaBolsillo) {
        this.cuentaBolsillo = cuentaBolsillo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Double getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setSaldoCuenta(Double saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }

    public void depositar(double cantidad) {
        this.saldoCuenta += cantidad;
    }

    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    @Override
    public String toString() {
        return "CuentaAhorros{" +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", saldoCuenta=" + saldoCuenta +
                ", cuentaBolsillo=" +((cuentaBolsillo!=null)?cuentaBolsillo.toString():"no existe")+
                "transacciones=" + transacciones +
                '}';
    }

    public void retirar(double cantidad) {
        this.saldoCuenta -= cantidad;
    }




}
