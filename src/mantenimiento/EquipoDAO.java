package mantenimiento;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.IEquipo;
import model.DetalleVenta;
import model.Equipo;
import utils.Conexion;

public class EquipoDAO implements IEquipo{

	@Override
	public void guardarEquipo(Equipo objEquipo) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_InsertarEquipo(?,?,?,?)}";
		
		CallableStatement cs = null;
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setString(1, objEquipo.getCodigoequipo());
			cs.setString(2, objEquipo.getNombreequipo());
			cs.setDouble(3, objEquipo.getPrecioequipo());
			cs.setInt(4, objEquipo.getStockequipo());
			
			int x = cs.executeUpdate();
			
			if(x > 0) {
				System.out.println("Se registró el equipo correctamente");
			} else {
				System.out.println("No se registró el equipo");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void actualizarEquipo(Equipo objEquipo) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_ActualizarEquipo(?,?,?,?,?)}";
		
		CallableStatement cs = null;
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setInt(1, objEquipo.getIdequipo());
			cs.setString(2, objEquipo.getCodigoequipo());
			cs.setString(3, objEquipo.getNombreequipo());
			cs.setDouble(4, objEquipo.getPrecioequipo());
			cs.setInt(5, objEquipo.getStockequipo());
			
			int x = cs.executeUpdate();
			
			if(x > 0) {
				System.out.println("Se actualizó el equipo correctamente");
			} else {
				System.out.println("No se actualizó el equipo");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void eliminarEquipo(Equipo objEquipo) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_EliminarEquipo(?)}";
		
		CallableStatement cs = null;
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setInt(1, objEquipo.getIdequipo());
			
			int x = cs.executeUpdate();
			
			if(x > 0) {
				System.out.println("Se eliminó el equipo correctamente");
			} else {
				System.out.println("No se eliminó el equipo");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Equipo> listarEquipos() {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_ListarEquipos()}";
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		List<Equipo> lstEquipos = new ArrayList<>();
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			rs = cs.executeQuery();
			
			while(rs.next()) {
				
				Equipo equipo = new Equipo();
				
				equipo.setIdequipo(rs.getInt(1));
				equipo.setCodigoequipo(rs.getString(2));
				equipo.setNombreequipo(rs.getString(3));
				equipo.setPrecioequipo(rs.getDouble(4));
				equipo.setStockequipo(rs.getInt(5));
				
				lstEquipos.add(equipo);
				
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		return lstEquipos;
	}

	@Override
	public Equipo buscarEquipoPorId(Equipo objEquipo) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_BuscarEquipoPorId(?)}";
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		Equipo equipo = new Equipo();
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setInt(1, objEquipo.getIdequipo());
			
			rs = cs.executeQuery();
			
			if(rs.next()) {
				
				equipo.setIdequipo(rs.getInt(1));
				equipo.setCodigoequipo(rs.getString(2));
				equipo.setNombreequipo(rs.getString(3));
				equipo.setPrecioequipo(rs.getDouble(4));
				equipo.setStockequipo(rs.getInt(5));
				
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return equipo;
	}

	@Override
	public Equipo buscarEquipoPorCodigoEquipo(Equipo objEquipo) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_BuscarEquipoPorCodigoEquipo(?)}";
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		Equipo equipo = new Equipo();
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setString(1, objEquipo.getCodigoequipo());
			
			rs = cs.executeQuery();
			
			if(rs.next()) {
				
				equipo.setIdequipo(rs.getInt(1));
				equipo.setCodigoequipo(rs.getString(2));
				equipo.setNombreequipo(rs.getString(3));
				equipo.setPrecioequipo(rs.getDouble(4));
				equipo.setStockequipo(rs.getInt(5));
				
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return equipo;
		
	}

	

	
	@Override
	public void actualizarStockEquipo(List<DetalleVenta> lstDetalleVenta) {

		Conexion conexion = new Conexion();
		
		CallableStatement cs = null;
		
		int resultado = -1;
		
		try {
			
			String query = "{call sp_ActualizarStockEquipo(?,?)}";
			
			for(DetalleVenta dv: lstDetalleVenta) {
				
				cs = conexion.conectar().prepareCall(query);
				
				cs.setInt(1, dv.getIdequipo());
				cs.setInt(2, dv.getCantidad());
				
				resultado = cs.executeUpdate();
				
			}
			
			if(resultado != -1) {
				System.out.println("Se actualizó stock correctamente");
			} else {
				System.out.println("No se actualizó el stock");
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}


	@Override
	public String generarCodigoEquipo() {
		
		List<Equipo> lstEquipos = listarEquipos();
		
		if(lstEquipos.size() > 0) {
			Equipo equipo = lstEquipos.get(lstEquipos.size() - 1);
			
			int numero = Integer.parseInt(equipo.getCodigoequipo().substring(1, equipo.getCodigoequipo().length())) + 1;
			
			return "E" + numero;
			
		} else {
			return "E1";
		}
	}

}
