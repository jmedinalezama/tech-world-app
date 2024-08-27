package interfaces;

import java.util.List;

import model.DetalleVenta;
import model.Equipo;

public interface IEquipo {
	
	public void guardarEquipo(Equipo objEquipo);
	public void actualizarEquipo(Equipo objEquipo);
	public void eliminarEquipo(Equipo objEquipo);
	public List<Equipo> listarEquipos();
	public Equipo buscarEquipoPorId(Equipo objEquipo);
	public Equipo buscarEquipoPorCodigoEquipo(Equipo objEquipo);
	public void actualizarStockEquipo(List<DetalleVenta> lstDetalleVenta);
	public String generarCodigoEquipo();

}
