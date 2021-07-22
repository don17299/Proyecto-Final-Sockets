package model;

import java.util.Date;

public class Transaccion {
    private Date FechaTransaccion;

    private String codigoTransaccion;

    public Transaccion(String codigoTransaccion) {
        super();
        FechaTransaccion = new Date();
        this.codigoTransaccion = codigoTransaccion;
    }

    public Date getFechaTransaccion() {
        return FechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        FechaTransaccion = fechaTransaccion;
    }

    public String getCodigoTransaccion() {
        return codigoTransaccion;
    }

    public void setCodigoTransaccion(String codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "FechaTransaccion=" + FechaTransaccion +
                ", codigoTransaccion='" + codigoTransaccion + '\'' +
                '}';
    }
}
