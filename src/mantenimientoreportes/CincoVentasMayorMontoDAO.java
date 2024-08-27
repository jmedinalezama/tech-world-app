package mantenimientoreportes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfacesreportes.ICincoVentasMayorMonto;
import modelreportes.CincoVentasMayorMonto;
import utils.Conexion;

public class CincoVentasMayorMontoDAO implements ICincoVentasMayorMonto{

	@Override
	public List<CincoVentasMayorMonto> listarCincoVentasMayorMonto() {
		
		Connection cn = null;
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		List<CincoVentasMayorMonto> lstCincoVentasMayorMonto = new ArrayList<>();
		
		String query = "{call sp_CincoVentasMayorMonto()}";
		
		try {
			
			cn = new Conexion().conectar();
			
			cs = cn.prepareCall(query);
			
			rs = cs.executeQuery();
			
			while(rs.next()) {
				
				CincoVentasMayorMonto obj = new CincoVentasMayorMonto();
				
				obj.setIdventa(rs.getString(1));
				obj.setCliente(rs.getString(2));
				obj.setUsuario(rs.getString(3));
				obj.setFecha(rs.getDate(4));
				obj.setMonto(rs.getDouble(5));
				
				lstCincoVentasMayorMonto.add(obj);
				
			}
			
			return lstCincoVentasMayorMonto;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			try {
				if(cs != null) cs.close();
				
				if(rs != null) rs.close();
				
				if(cn != null) cn.close();
				
			} catch(SQLException ex) {
				
			}
			
		}
		
		return null;
	}

}
