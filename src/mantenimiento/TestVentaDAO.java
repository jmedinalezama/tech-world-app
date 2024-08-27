package mantenimiento;

// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;

// import model.DetalleVenta;
// import model.Venta;

public class TestVentaDAO {

	public static void main(String[] args) {
		// Venta venta = new Venta();
		// VentaDAO ventaDAO = new VentaDAO();
		
		//-------------------- registrar venta y detalle de venta
		/*venta.setIdVenta("VENTA3");
		venta.setIdcliente(4);
		venta.setIdusuario(3);
		venta.setFecha(new Date(new Date().getTime()));
		venta.setHora(new Date(new Date().getTime()));
		venta.setSubtotal(100);
		venta.setIgv(18);
		venta.setTotal(118);
		
		List<DetalleVenta> lstDetalleVenta = new ArrayList<>();
		DetalleVenta dv1 = new DetalleVenta();
		DetalleVenta dv2 = new DetalleVenta();
		
		//objeto 1
		dv1.setIdventa("VENTA3");
		dv1.setIdequipo(8);
		dv1.setPrecio(50);
		dv1.setCantidad(2);
		
		//objeto 2
		dv2.setIdventa("VENTA3");
		dv2.setIdequipo(12);
		dv2.setPrecio(200);
		dv2.setCantidad(1);
		
		//agregar objetos a la lista
		lstDetalleVenta.add(dv1);
		lstDetalleVenta.add(dv2);
		
		int resultado = ventaDAO.registrarVenta(venta, lstDetalleVenta);
		
		if(resultado != -1) {
			System.out.println("Registro correcto");
		} else {
			System.out.println("Ocurrió un error -1");
		}*/
		
		
		//---------- listar ventas
		/*List<Venta> lstVentas = ventaDAO.listarVentas();
		
		for(Venta v: lstVentas) {
			System.out.println(v.getIdVenta());
		}*/
		
		//----------generar nro de venta
		//System.out.println(ventaDAO.generarNroVenta());
		
	}

}
