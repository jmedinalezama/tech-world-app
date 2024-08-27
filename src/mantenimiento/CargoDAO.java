package mantenimiento;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.ICargo;
import model.Cargo;
import utils.Conexion;

public class CargoDAO implements ICargo{

	@Override
	public void guardarCargo(Cargo objCargo) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_InsertarCargo(?)}";
		
		CallableStatement cs = null;
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setString(1, objCargo.getTipocargo());
			
			int x = cs.executeUpdate();
			
			if(x > 0) {
				System.out.println("Cargo registrado correctamente");
			} else {
				System.out.println("No se registró el cargo");
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void actualizarCargo(Cargo objCargo) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_ActualizarCargo(?,?)}";
		
		CallableStatement cs = null;
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setInt(1, objCargo.getIdcargo());
			cs.setString(2, objCargo.getTipocargo());
			
			int x = cs.executeUpdate();
			
			if(x > 0) {
				System.out.println("Cargo actualizado correctamente");
			} else {
				System.out.println("No se puedo actualizar el cargo");
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void eliminarCargo(Cargo objCargo) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_EliminarCargo(?)}";
		
		CallableStatement cs = null;
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setInt(1, objCargo.getIdcargo());
			
			int x = cs.executeUpdate();
			
			if(x > 0) {
				System.out.println("Cargo eliminado correctamente");
			} else {
				System.out.println("No se puedo eliminar el cargo");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Cargo> listarCargos() {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_ListarCargos()}";
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		List<Cargo> lstCargos = new ArrayList<>();
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			rs = cs.executeQuery();
			
			while(rs.next()) {
				
				Cargo cargo = new Cargo();
				
				cargo.setIdcargo(rs.getInt(1));
				cargo.setTipocargo(rs.getString(2));
				
				lstCargos.add(cargo);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lstCargos;
	}

	@Override
	public Cargo buscarCargoPorId(Cargo objCargo) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_BuscarCargoPorId(?)}";
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		Cargo cargo = new Cargo();
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setInt(1, objCargo.getIdcargo());
			
			rs = cs.executeQuery();
			
			if(rs.next()) {
				
				cargo.setIdcargo(rs.getInt(1));
				cargo.setTipocargo(rs.getString(2));
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cargo;
	}
}
