package mantenimiento;

// import java.util.List;

// import model.Equipo;

public class TestEquipoDAO {

	public static void main(String[] args) {

		// Equipo equipo = new Equipo();
		// EquipoDAO equipoDAO = new EquipoDAO();

		// ----------- registrar equipo
		/*
		 * equipo.setCodigoequipo("E001"); equipo.setNombreequipo("Cable HDMI");
		 * equipo.setPrecioequipo(12.50); equipo.setStockequipo(20);
		 * 
		 * equipoDAO.guardarEquipo(equipo);
		 */

		// ---------- actualizar equipo
		/*
		 * equipo.setIdequipo(1); equipo.setCodigoequipo("E001");
		 * equipo.setNombreequipo("Cable VGA"); equipo.setPrecioequipo(20.50);
		 * equipo.setStockequipo(50);
		 * 
		 * equipoDAO.actualizarEquipo(equipo);
		 */

		// ----------- eliminar equipo
		/*
		 * equipo.setIdequipo(2);
		 * 
		 * equipoDAO.eliminarEquipo(equipo);
		 */

		// ------------ listar equipos
		/*
		 * List<Equipo> lstEquipos = equipoDAO.listarEquipos();
		 * 
		 * for(Equipo e: lstEquipos) { System.out.println(e.getCodigoequipo() + " " +
		 * e.getNombreequipo() + " " + e.getPrecioequipo()); }
		 */

		// ---------- buscar equipo
		/*
		 * equipo.setIdequipo(3);
		 * 
		 * Equipo equipobuscado = equipoDAO.buscarEquipoPorId(equipo);
		 * 
		 * System.out.println(equipobuscado.getCodigoequipo());
		 * System.out.println(equipobuscado.getNombreequipo());
		 * System.out.println(equipobuscado.getPrecioequipo());
		 */

		// ---------- buscar equipo por codigo equipo
		/*equipo.setCodigoequipo("E003");

		Equipo equipobuscado = equipoDAO.buscarEquipoPorCodigoEquipo(equipo);

		System.out.println(equipobuscado.getCodigoequipo());
		System.out.println(equipobuscado.getNombreequipo());
		System.out.println(equipobuscado.getPrecioequipo());*/

	}

}
