package modelreportes;

public class CincoEquiposMasVendidos {

	private String nombreequipo;
	private int nroventasequipo;
	private int nroequiposvendidos;
	private double montoadquirido;
	
	public CincoEquiposMasVendidos() {}
	
	public CincoEquiposMasVendidos(String nombreequipo, int nroventasequipo, int nroequiposvendidos,
			double montoadquirido) {
	
		this.nombreequipo = nombreequipo;
		this.nroventasequipo = nroventasequipo;
		this.nroequiposvendidos = nroequiposvendidos;
		this.montoadquirido = montoadquirido;
	}

	public String getNombreequipo() {
		return nombreequipo;
	}

	public void setNombreequipo(String nombreequipo) {
		this.nombreequipo = nombreequipo;
	}

	public int getNroventasequipo() {
		return nroventasequipo;
	}

	public void setNroventasequipo(int nroventasequipo) {
		this.nroventasequipo = nroventasequipo;
	}

	public int getNroequiposvendidos() {
		return nroequiposvendidos;
	}

	public void setNroequiposvendidos(int nroequiposvendidos) {
		this.nroequiposvendidos = nroequiposvendidos;
	}

	public double getMontoadquirido() {
		return montoadquirido;
	}

	public void setMontoadquirido(double montoadquirido) {
		this.montoadquirido = montoadquirido;
	}
	
	
	
}
