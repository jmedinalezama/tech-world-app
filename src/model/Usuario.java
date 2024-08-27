package model;

import java.util.Objects;

public class Usuario {

	private int idusuario;
	private String nombreusuario;
	private String apellidousuario;
	private String dniusuario;
	private String direccionusuario;
	private String correousuario;
	private String userusuario;
	private String passwordusuario;
	private int idcargo;
	
	public Usuario() {}

	public Usuario(int idusuario, String nombreusuario, String apellidousuario, String dniusuario,
			String direccionusuario, String correousuario, String userusuario, String passwordusuario, int idcargo) {
		this.idusuario = idusuario;
		this.nombreusuario = nombreusuario;
		this.apellidousuario = apellidousuario;
		this.dniusuario = dniusuario;
		this.direccionusuario = direccionusuario;
		this.correousuario = correousuario;
		this.userusuario = userusuario;
		this.passwordusuario = passwordusuario;
		this.idcargo = idcargo;
	}
	

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombreusuario() {
		return nombreusuario;
	}

	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}

	public String getApellidousuario() {
		return apellidousuario;
	}

	public void setApellidousuario(String apellidousuario) {
		this.apellidousuario = apellidousuario;
	}

	public String getDniusuario() {
		return dniusuario;
	}

	public void setDniusuario(String dniusuario) {
		this.dniusuario = dniusuario;
	}

	public String getDireccionusuario() {
		return direccionusuario;
	}

	public void setDireccionusuario(String direccionusuario) {
		this.direccionusuario = direccionusuario;
	}

	public String getCorreousuario() {
		return correousuario;
	}

	public void setCorreousuario(String correousuario) {
		this.correousuario = correousuario;
	}

	public String getUserusuario() {
		return userusuario;
	}

	public void setUserusuario(String userusuario) {
		this.userusuario = userusuario;
	}

	public String getPasswordusuario() {
		return passwordusuario;
	}

	public void setPasswordusuario(String passwordusuario) {
		this.passwordusuario = passwordusuario;
	}

	public int getIdcargo() {
		return idcargo;
	}

	public void setIdcargo(int idcargo) {
		this.idcargo = idcargo;
	}
	
	
	@Override
	public String toString() {
		return nombreusuario + " " + apellidousuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idusuario, nombreusuario);
	}

	@Override
	public boolean equals(Object objeto) {
		if(objeto == this) {
			return true;
		}
		if(objeto == null) {
			return false;
		}
	
		if (!(objeto instanceof Usuario)) {
			return false;
		}
		
		Usuario usuario = (Usuario) objeto;
		return (idusuario == usuario.idusuario && Objects.equals(nombreusuario, usuario.nombreusuario));
	}
	
	
	
	
	
}
