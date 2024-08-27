package interfaces;

import java.util.Date;
import java.util.List;

import model.Cliente;
import model.DetalleVenta;
import model.Usuario;
import model.Venta;

public interface IVenta {

	public int registrarVenta(Venta objVenta, List<DetalleVenta> lstDetalleVenta);
	
	public List<Venta> listarVentas();
	
	public String generarNroVenta();
	
	public List<Venta> listarVentasPorUsuarioClienteFechas(Usuario objUsuario, Cliente objCliente, Date fechainicio, Date fechafin);
	
}
