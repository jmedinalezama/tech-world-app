package model;

import java.util.Objects;

public class Cliente {
	
	private int idcliente;
	private String nombrecliente;
	private String apellidocliente;
	private String dnicliente;
	private String direccioncliente;
	private String telefonocliente;
	private String correocliente;
	
	public Cliente() {}

	public Cliente(int idcliente, String nombrecliente, String apellidocliente, String dnicliente,
			String direccioncliente, String telefonocliente, String correocliente) {
		this.idcliente = idcliente;
		this.nombrecliente = nombrecliente;
		this.apellidocliente = apellidocliente;
		this.dnicliente = dnicliente;
		this.direccioncliente = direccioncliente;
		this.telefonocliente = telefonocliente;
		this.correocliente = correocliente;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getNombrecliente() {
		return nombrecliente;
	}

	public void setNombrecliente(String nombrecliente) {
		this.nombrecliente = nombrecliente;
	}

	public String getApellidocliente() {
		return apellidocliente;
	}

	public void setApellidocliente(String apellidocliente) {
		this.apellidocliente = apellidocliente;
	}

	public String getDnicliente() {
		return dnicliente;
	}

	public void setDnicliente(String dnicliente) {
		this.dnicliente = dnicliente;
	}

	public String getDireccioncliente() {
		return direccioncliente;
	}

	public void setDireccioncliente(String direccioncliente) {
		this.direccioncliente = direccioncliente;
	}

	public String getTelefonocliente() {
		return telefonocliente;
	}

	public void setTelefonocliente(String telefonocliente) {
		this.telefonocliente = telefonocliente;
	}

	public String getCorreocliente() {
		return correocliente;
	}

	public void setCorreocliente(String correocliente) {
		this.correocliente = correocliente;
	}
	
	@Override
	public String toString() {
		return nombrecliente + " " + apellidocliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idcliente, nombrecliente);
	}

	@Override
	public boolean equals(Object objeto) {
		if(objeto == this) {
			return true;
		}
		if(objeto == null) {
			return false;
		}
	
		if (!(objeto instanceof Cliente)) {
			return false;
		}
		
		Cliente cliente = (Cliente) objeto;
		return (idcliente == cliente.idcliente && Objects.equals(nombrecliente, cliente.nombrecliente));
	}
	
	
	
	
	
	

}
