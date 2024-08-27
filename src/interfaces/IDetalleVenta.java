package interfaces;

import java.util.List;

import model.DetalleVenta;

public interface IDetalleVenta {

	public List<DetalleVenta> buscarDetalleVentaPorIdVenta(DetalleVenta objDetalleVenta);
	
}
