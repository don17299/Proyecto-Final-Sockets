package model;

public class CuentaBolsillo {

    private Boolean isDisponible;
    private Double saldoBolsillo;
    private String nombreCuenta;

    public CuentaBolsillo() {
        this.isDisponible = false;
    }

    public Boolean getDisponible() {
        return isDisponible;
    }

    public void setDisponible(Boolean disponible) {
        isDisponible = disponible;
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
}
