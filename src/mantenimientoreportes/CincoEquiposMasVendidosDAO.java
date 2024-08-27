package mantenimientoreportes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfacesreportes.ICincoEquipoMasVendidos;
import modelreportes.CincoEquiposMasVendidos;
import utils.Conexion;

public class CincoEquiposMasVendidosDAO implements ICincoEquipoMasVendidos {

	@Override
	public List<CincoEquiposMasVendidos> listarCincoEquiposMasVendidos() {
		
		Connection cn = null;
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		String query = "{call sp_CincoEquiposMasVendidos()}";
		
		List<CincoEquiposMasVendidos> lstCincoEquiposMasVendidos = new ArrayList<>();
		
		try {

			cn = new Conexion().conectar();

			cs = cn.prepareCall(query);
			
			rs = cs.executeQuery();
			
			while(rs.next()) {
				
				CincoEquiposMasVendidos cincoequiposmasvendidos = new CincoEquiposMasVendidos();
				
				cincoequiposmasvendidos.setNombreequipo(rs.getString(1));
				cincoequiposmasvendidos.setNroventasequipo(rs.getInt(2));
				cincoequiposmasvendidos.setNroequiposvendidos(rs.getInt(3));
				cincoequiposmasvendidos.setMontoadquirido(rs.getDouble(4));
				
				lstCincoEquiposMasVendidos.add(cincoequiposmasvendidos);
				
			}
			
			return lstCincoEquiposMasVendidos;
			
		} catch(SQLException ex) {
			
			ex.getMessage();
			
		} finally {
			
			try {
				
				if(cs != null) cs.close();
				
				if(rs != null) rs.close();
				
				if(cn != null) cn.close();
				
			} catch(SQLException ex) {
				ex.getMessage();
			}
	
		}
		
		return null;
	}

}
