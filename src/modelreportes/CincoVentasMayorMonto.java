package modelreportes;

import java.util.Date;

public class CincoVentasMayorMonto {

	private String idventa;
	private String cliente;
	private String usuario;
	private Date fecha;
	private double monto;
	
	public CincoVentasMayorMonto() {}
	
	public CincoVentasMayorMonto(String idventa, String cliente, String usuario, Date fecha, double monto) {
		
		this.idventa = idventa;
		this.cliente = cliente;
		this.usuario = usuario;
		this.fecha = fecha;
		this.monto = monto;
	}

	public String getIdventa() {
		return idventa;
	}

	public void setIdventa(String idventa) {
		this.idventa = idventa;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	
	
	
}
