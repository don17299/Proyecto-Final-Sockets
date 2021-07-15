package model;

import java.util.ArrayList;

public class CuentaAhorros {
	
	private String numCuenta;
	
	private String nombreUsuario;
	
	private Double saldoCuenta;
	
	private static ArrayList<Transaccion> transacciones;

	public CuentaAhorros(String numCuenta, String nombreUsuario) {
		super();
		this.numCuenta = numCuenta;
		this.nombreUsuario = nombreUsuario;
		this.saldoCuenta = 0.0;
		this.transacciones= new ArrayList<>();
	}
	

	public String getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
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
		return "CuentaAhorros [numCuenta=" + numCuenta + ", nombreUsuario=" + nombreUsuario + ", saldoCuenta="
				+ saldoCuenta + ", saldoBolsillo="+"]";
	}
	
	
	
	
	

}
