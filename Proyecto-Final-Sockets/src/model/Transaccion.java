package model;

import java.util.Date;

public class Transaccion {
	
	private Date FechaTransaccion;
	
	private String codigoTransaccion;

	public Transaccion(Date fechaTransaccion, String codigoTransaccion) {
		super();
		FechaTransaccion = fechaTransaccion;
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
	
	

}
