package interfaces;

import java.util.List;

import model.Cargo;

public interface ICargo {
	
	public void guardarCargo(Cargo objCargo);
	
	public void actualizarCargo(Cargo objCargo);
	
	public void eliminarCargo(Cargo objCargo);
	
	public List<Cargo> listarCargos();
	
	public Cargo buscarCargoPorId(Cargo objCargo);
	

}
