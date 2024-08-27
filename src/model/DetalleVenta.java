package model;


public class DetalleVenta {

	private int iddetalleventa;
	private String idventa;
	private int idequipo;
	private int cantidad;
	private double precio;
	
	public DetalleVenta() {}
	
	public DetalleVenta(int iddetalleventa, String idventa, int idequipo, int cantidad, double precio) {
		
		this.iddetalleventa = iddetalleventa;
		this.idventa = idventa;
		this.idequipo = idequipo;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public int getIddetalleventa() {
		return iddetalleventa;
	}

	public void setIddetalleventa(int iddetalleventa) {
		this.iddetalleventa = iddetalleventa;
	}

	public String getIdventa() {
		return idventa;
	}

	public void setIdventa(String idventa) {
		this.idventa = idventa;
	}

	public int getIdequipo() {
		return idequipo;
	}

	public void setIdequipo(int idequipo) {
		this.idequipo = idequipo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	//metodos de la clase
	public double subtotalPorEquipo() {
		return (double)Math.round((precio * cantidad) * 100) / 100;
	}
	
}
