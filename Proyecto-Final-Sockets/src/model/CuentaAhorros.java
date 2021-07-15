package model;

import java.util.ArrayList;

public class CuentaAhorros {

    private String nombreUsuario;

    private Double saldoCuenta;

    private static ArrayList<Transaccion> transacciones;

    public CuentaAhorros(String nombreUsuario) {
        super();
        this.nombreUsuario = nombreUsuario;
        this.saldoCuenta = 0.0;
        this.transacciones= new ArrayList<>();
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



    @Override
    public String toString() {
        return "CuentaAhorros [" + "nombreUsuario=" + nombreUsuario + ", saldoCuenta="
                + saldoCuenta + ", saldoBolsillo="+"]";
    }
}
