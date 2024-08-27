package mantenimiento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.IDetalleVenta;
import model.DetalleVenta;
import utils.Conexion;

public class DetalleVentaDAO implements IDetalleVenta{

	@Override
	public List<DetalleVenta> buscarDetalleVentaPorIdVenta(DetalleVenta objDetalleVenta) {
		
		Connection cn = null;
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		List<DetalleVenta> lstDetalleVenta = new ArrayList<>();
		
		String query = "{call sp_BuscarDetalleVentaPorIdVenta(?)}";
		
		try {
			
			cn = new Conexion().conectar();
			
			cs = cn.prepareCall(query);
			
			cs.setString(1, objDetalleVenta.getIdventa());
			
			rs = cs.executeQuery();
			
			while(rs.next()) {
				
				DetalleVenta detalleventa = new DetalleVenta();
				
				detalleventa.setIddetalleventa(rs.getInt(1));
				detalleventa.setIdventa(rs.getString(2));
				detalleventa.setIdequipo(rs.getInt(3));
				detalleventa.setCantidad(rs.getInt(4));
				detalleventa.setPrecio(rs.getDouble(5));
				
				lstDetalleVenta.add(detalleventa);
				
			}
			
			return lstDetalleVenta;
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
			
			try {
				if(cs != null) cs.close();
				if(rs != null) rs.close();
				if(cn != null) cn.close();
			
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
			
		}

		return null;
	}

	

	
	
}
