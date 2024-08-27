package model;

import java.util.Objects;

public class Cargo {

	private int idcargo;
	private String tipocargo;
	
	public Cargo() {}
	
	public Cargo(int idcargo, String tipocargo) {
		this.idcargo = idcargo;
		this.tipocargo = tipocargo;
	}

	
	public int getIdcargo() {
		return idcargo;
	}

	public void setIdcargo(int idcargo) {
		this.idcargo = idcargo;
	}

	public String getTipocargo() {
		return tipocargo;
	}

	public void setTipocargo(String tipocargo) {
		this.tipocargo = tipocargo;
	}

	@Override
	public String toString() {
		return tipocargo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idcargo, tipocargo);
	}

	@Override
	public boolean equals(Object objeto) {
		if(objeto == this) {
			return true;
		}
		if(objeto == null) {
			return false;
		}
	
		if (!(objeto instanceof Cargo)) {
			return false;
		}
		
		Cargo cargo = (Cargo) objeto;
		return (idcargo == cargo.idcargo && Objects.equals(tipocargo, cargo.tipocargo));
	}
	
	
	
	

	

	
	
	
	
	
}
