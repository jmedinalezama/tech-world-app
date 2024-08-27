package model;

import java.util.Date;
import java.util.List;

public class Venta {

	private String idVenta;
	private int idcliente;
	private int idusuario;
	private Date fecha;
	private Date hora;
	private double subtotal;
	private double igv;
	private double total;
	
	public Venta() {}

	public Venta(String idVenta, int idcliente, int idusuario, Date fecha, Date hora, double subtotal, double igv,
			double total) {
		this.idVenta = idVenta;
		this.idcliente = idcliente;
		this.idusuario = idusuario;
		this.fecha = fecha;
		this.hora = hora;
		this.subtotal = subtotal;
		this.igv = igv;
		this.total = total;
	}

	public String getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(String idVenta) {
		this.idVenta = idVenta;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getIgv() {
		return igv;
	}

	public void setIgv(double igv) {
		this.igv = igv;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	//---- metodos
	public double calcularSubtotalDeVenta(List<DetalleVenta> lstDetalleVenta) {
		int subtotal = 0;
		for(DetalleVenta dv: lstDetalleVenta) {
			subtotal += (dv.getPrecio() * dv.getCantidad());
		}
		return (double)Math.round(subtotal * 100) / 100;
	}
	
	public double calcularIgvDeVenta(List<DetalleVenta> lstDetalleVenta) {
		return (double)Math.round((calcularSubtotalDeVenta(lstDetalleVenta) * 0.18)*100)/100;
	}
	
	public double calcularTotalDeVenta(List<DetalleVenta> lstDetalleVenta) {
		return 
			(double)Math.round((calcularSubtotalDeVenta(lstDetalleVenta) 
					+ calcularIgvDeVenta(lstDetalleVenta)) * 100) / 100;
	}
	
}
