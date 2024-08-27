package mantenimiento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import interfaces.IVenta;
import model.Cliente;
import model.DetalleVenta;
import model.Usuario;
import model.Venta;
import utils.Conexion;

public class VentaDAO implements IVenta{

	@Override
	public int registrarVenta(Venta objVenta, List<DetalleVenta> lstDetalleVenta) {
		
		Connection cn = null;

		CallableStatement csventa = null;
		
		CallableStatement csdetalleventa = null;
		
		int resultado = -1;
		
		try {
			
			cn = new Conexion().conectar();
			
			//---- desactivar el autocommit
			cn.setAutoCommit(false);
			
			//--------- registrar venta
			
			String queryVenta = "{call sp_InsertarVenta(?,?,?,?,?,?,?,?)}";
			
			csventa = cn.prepareCall(queryVenta);
			
			csventa.setString(1, objVenta.getIdVenta());
			csventa.setInt(2, objVenta.getIdcliente());
			csventa.setInt(3, objVenta.getIdusuario());
			csventa.setDate(4, new java.sql.Date(objVenta.getFecha().getTime()));
			csventa.setTime(5, new java.sql.Time(objVenta.getHora().getTime()));
			csventa.setDouble(6, objVenta.getSubtotal());
			csventa.setDouble(7, objVenta.getIgv());
			csventa.setDouble(8, objVenta.getTotal());
			
			resultado = csventa.executeUpdate();
			
			//---------- registrar detalle
			String querydetalleventa = "{call sp_InsertarDetalleVenta(?,?,?,?)}";
			
			for(DetalleVenta dv: lstDetalleVenta) {
				
				csdetalleventa = cn.prepareCall(querydetalleventa);
				
				csdetalleventa.setString(1, dv.getIdventa());
				csdetalleventa.setInt(2, dv.getIdequipo());
				csdetalleventa.setInt(3, dv.getCantidad());
				csdetalleventa.setDouble(4, dv.getPrecio());
				
				resultado = csdetalleventa.executeUpdate();
				
			}
			
			//---- confirmar transaccion
			cn.commit();
	
		} catch (SQLException ex) {
			try {
				cn.rollback();
				System.out.println("¡Error! al registrar venta " + ex.toString());
				resultado = -1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			
			try {
				if(csventa != null) {
					csventa.close();
				}
				if(csdetalleventa != null) {
					csdetalleventa.close();
				}
				if(cn != null) {
					cn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//------------ si retorna 1 -> registro correcto; -1 -> ocurrió un error
		return resultado;
	}
	
	@Override
	public List<Venta> listarVentas() {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_ListarVentas()}";
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		List<Venta> lstVentas = new ArrayList<>();
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			rs = cs.executeQuery();
			
		
			while(rs.next()) {
					
				Venta venta = new Venta();
				
				venta.setIdVenta(rs.getString(1));
				venta.setIdcliente(rs.getInt(2));
				venta.setIdusuario(rs.getInt(3));
				venta.setFecha(rs.getDate(4));
				venta.setHora(rs.getDate(5));
				venta.setSubtotal(rs.getDouble(6));
				venta.setIgv(rs.getDouble(7));
				venta.setTotal(rs.getDouble(8));
					
				lstVentas.add(venta);
					
			}
		

		} catch (SQLException e) {
			
			e.printStackTrace();
		
		} 
		
		return lstVentas;
	}

	
	@Override
	public String generarNroVenta() {
		
		List<Venta> lstVentas = listarVentas();
		
		
		if(lstVentas.size() > 0) {

			Venta venta = lstVentas.get(0);
			int nroventa = Integer.parseInt(
					venta.getIdVenta().substring(5, venta.getIdVenta().length()));
			
			for(int i = 1; i < lstVentas.size(); i++) {
				
				Venta ventasiguiente = lstVentas.get(i);
				
				int nroventasiguiente = Integer.parseInt(
						ventasiguiente.getIdVenta().substring(5, ventasiguiente.getIdVenta().length()));
				
				
				if(nroventa < nroventasiguiente) {
					nroventa = nroventasiguiente;
				} 
				
			}

			nroventa++;
			
			return "VENTA" + nroventa;
			
		} else {
			return "VENTA1";
		}
	}

	@Override
	public List<Venta> listarVentasPorUsuarioClienteFechas(Usuario objUsuario, Cliente objCliente, Date fechainicio,
			Date fechafin) {
		
		Connection cn = null;
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		List<Venta> lstVentas = new ArrayList<>();
		
		String query = "{call sp_ListarVentasPorUsuarioClienteFechas(?,?,?,?)}";
		
		try {
			
			cn = new Conexion().conectar();
			
			cs = cn.prepareCall(query);
			
			cs.setInt(1, objCliente.getIdcliente());
			cs.setInt(2, objUsuario.getIdusuario());
			cs.setDate(3, new java.sql.Date(fechainicio.getTime()));
			cs.setDate(4, new java.sql.Date(fechafin.getTime()));
			
			
			rs = cs.executeQuery();
			
			while(rs.next()) {
				
				Venta venta = new Venta();
				
				venta.setIdVenta(rs.getString(1));
				venta.setIdcliente(rs.getInt(2));
				venta.setIdusuario(rs.getInt(3));
				venta.setFecha(rs.getDate(4));
				venta.setHora(rs.getTime(5));
				venta.setSubtotal(rs.getDouble(6));
				venta.setIgv(rs.getDouble(7));
				venta.setTotal(rs.getDouble(8));
				
				lstVentas.add(venta);
				
			}
			
			return lstVentas;
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
			
			try {
				if(cs != null) cs.close();
				if(rs != null) rs.close();
				if(cn != null) cn.close();
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
		}
		return null;
	}



}
