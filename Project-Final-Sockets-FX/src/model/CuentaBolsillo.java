package model;

public class CuentaBolsillo {

    private Boolean isDisponible;
    private Double saldoBolsillo;
    private String nombreCuenta;

    public CuentaBolsillo() {
        this.isDisponible = false;
        this.saldoBolsillo = 0.0;
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

    @Override
    public String toString() {
        return "CuentaBolsillo{" +
                "isDisponible=" + isDisponible +
                ", saldoBolsillo=" + saldoBolsillo +
                ", nombreCuenta='" + nombreCuenta + '\'' +
                '}';
    }
}
