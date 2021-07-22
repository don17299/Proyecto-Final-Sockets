package model;

public class CuentaBolsillo {

    private Double saldoBolsillo;
    private String nombreCuenta;

    public CuentaBolsillo() {
        this.saldoBolsillo = 0.0;
    }

    public double getSaldoBolsillo() {
        return saldoBolsillo;
    }

    public void setSaldoBolsillo(double saldoBolsillo) {
        this.saldoBolsillo = saldoBolsillo;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    @Override
    public String toString() {
        return "CuentaBolsillo{" +
                ", saldoBolsillo=" + saldoBolsillo +
                ", nombreCuenta='" + nombreCuenta + '\'' +
                '}';
    }
}
