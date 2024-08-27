package model;

import java.util.Objects;

public class Equipo {
	
	private int idequipo;
	private String codigoequipo;
	private String nombreequipo;
	private double precioequipo;
	private int stockequipo;
	
	
	public Equipo() {}
	
	public Equipo(int idequipo, String codigoequipo, String nombreequipo, double precioequipo, int stockequipo) {
		this.idequipo = idequipo;
		this.codigoequipo = codigoequipo;
		this.nombreequipo = nombreequipo;
		this.precioequipo = precioequipo;
		this.stockequipo = stockequipo;
	}

	public int getIdequipo() {
		return idequipo;
	}

	public void setIdequipo(int idequipo) {
		this.idequipo = idequipo;
	}

	public String getCodigoequipo() {
		return codigoequipo;
	}

	public void setCodigoequipo(String codigoequipo) {
		this.codigoequipo = codigoequipo;
	}

	public String getNombreequipo() {
		return nombreequipo;
	}

	public void setNombreequipo(String nombreequipo) {
		this.nombreequipo = nombreequipo;
	}

	public double getPrecioequipo() {
		return precioequipo;
	}

	public void setPrecioequipo(double precioequipo) {
		this.precioequipo = precioequipo;
	}

	public int getStockequipo() {
		return stockequipo;
	}

	public void setStockequipo(int stockequipo) {
		this.stockequipo = stockequipo;
	}
	
	@Override
	public String toString() {
		return codigoequipo + " " + nombreequipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idequipo, nombreequipo);
	}

	@Override
	public boolean equals(Object objeto) {
		if(objeto == this) {
			return true;
		}
		if(objeto == null) {
			return false;
		}
	
		if (!(objeto instanceof Equipo)) {
			return false;
		}
		
		Equipo equipo = (Equipo) objeto;
		return (idequipo == equipo.idequipo && Objects.equals(nombreequipo, equipo.nombreequipo));
	}
	
	

}
