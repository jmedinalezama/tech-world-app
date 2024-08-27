package mantenimientoreportes;

import java.util.List;

import modelreportes.CincoVentasMayorMonto;

public class TestCincoVentasMayorMonto {

	public static void main(String[] args) {
		
		CincoVentasMayorMontoDAO objDAO = new CincoVentasMayorMontoDAO();
		
		List<CincoVentasMayorMonto> lista = objDAO.listarCincoVentasMayorMonto();

		for(CincoVentasMayorMonto x: lista) {
			System.out.println(x.getIdventa() + " " + x.getCliente() + " " + x.getUsuario() + 
					" " + x.getFecha() + " " + x.getMonto());
		}
		
	}

}
