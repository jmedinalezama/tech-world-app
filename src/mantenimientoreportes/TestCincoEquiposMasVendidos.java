package mantenimientoreportes;

import java.util.List;

import modelreportes.CincoEquiposMasVendidos;

public class TestCincoEquiposMasVendidos {

	public static void main(String[] args) {
		
		CincoEquiposMasVendidosDAO objDAO = new CincoEquiposMasVendidosDAO();
		
		List<CincoEquiposMasVendidos> lista = objDAO.listarCincoEquiposMasVendidos();

		for(CincoEquiposMasVendidos x: lista) {
			
			System.out.println(x.getNombreequipo() + " " + x.getNroventasequipo() + " " + x.getNroequiposvendidos()
			+ " " + x.getMontoadquirido());
		}
		
	}

}
