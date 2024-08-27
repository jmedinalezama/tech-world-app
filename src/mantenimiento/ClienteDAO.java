package mantenimiento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.ICliente;
import model.Cliente;
import utils.Conexion;

public class ClienteDAO implements ICliente {

	@Override
	public void guardarCliente(Cliente objCliente) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_InsertarCliente(?,?,?,?,?,?)}";
		
		CallableStatement cs = null;
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setString(1, objCliente.getNombrecliente());
			cs.setString(2, objCliente.getApellidocliente());
			cs.setString(3, objCliente.getDnicliente());
			cs.setString(4, objCliente.getDireccioncliente());
			cs.setString(5, objCliente.getTelefonocliente());
			cs.setString(6, objCliente.getCorreocliente());
			
			int x = cs.executeUpdate();
			
			if(x > 0) {
				System.out.println("Se registró cliente correctamente");
			} else {
				System.out.println("No se registró el cliente");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void actualizarCliente(Cliente objCliente) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_ActualizarCliente(?,?,?,?,?,?,?)}";
		
		CallableStatement cs = null;
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setInt(1, objCliente.getIdcliente());
			cs.setString(2, objCliente.getNombrecliente());
			cs.setString(3, objCliente.getApellidocliente());
			cs.setString(4, objCliente.getDnicliente());
			cs.setString(5, objCliente.getDireccioncliente());
			cs.setString(6, objCliente.getTelefonocliente());
			cs.setString(7, objCliente.getCorreocliente());
			
			int x = cs.executeUpdate();
			
			if(x > 0) {
				System.out.println("Se actualizó cliente correctamente");
			} else {
				System.out.println("No se actualizó el cliente");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void eliminarCliente(Cliente objCliente) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_EliminarCliente(?)}";
		
		CallableStatement cs = null;
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setInt(1, objCliente.getIdcliente());
			
			int x = cs.executeUpdate();
			
			if(x > 0) {
				System.out.println("Se eliminó cliente correctamente");
			} else {
				System.out.println("No se eliminó el cliente");
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Cliente> listarClientes() {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_ListarClientes()}";
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		List<Cliente> lstClientes = new ArrayList<>();
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			rs = cs.executeQuery();
			
			while(rs.next()) {
				
				Cliente cliente = new Cliente();
				
				cliente.setIdcliente(rs.getInt(1));
				cliente.setNombrecliente(rs.getString(2));
				cliente.setApellidocliente(rs.getString(3));
				cliente.setDnicliente(rs.getString(4));
				cliente.setDireccioncliente(rs.getString(5));
				cliente.setTelefonocliente(rs.getString(6));
				cliente.setCorreocliente(rs.getString(7));
				
				lstClientes.add(cliente);
				
			}
	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lstClientes;
	}

	@Override
	public Cliente buscarClientePorId(Cliente objCliente) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_BuscarClientePorId(?)}";
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		Cliente cliente = new Cliente();
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setInt(1, objCliente.getIdcliente());
			
			rs = cs.executeQuery();
			
			if(rs.next()) {
				
				cliente.setIdcliente(rs.getInt(1));
				cliente.setNombrecliente(rs.getString(2));
				cliente.setApellidocliente(rs.getString(3));
				cliente.setDnicliente(rs.getString(4));
				cliente.setDireccioncliente(rs.getString(5));
				cliente.setTelefonocliente(rs.getString(6));
				cliente.setCorreocliente(rs.getString(7));
				
			} else {
				return null;
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cliente;
	}

	@Override
	public List<Cliente> listarTodosClientes() {
		
		Connection cn = null;
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		List<Cliente> lstClientes = new ArrayList<>();
		
		String query = "{call sp_ListarTodosClientes}";
		
		try {
			
			cn = new Conexion().conectar();
			
			cs = cn.prepareCall(query);
			
			rs = cs.executeQuery();
			
			while(rs.next()) {
				
				Cliente cliente = new Cliente();
				
				cliente.setIdcliente(rs.getInt(1));
				cliente.setNombrecliente(rs.getString(2));
				cliente.setApellidocliente("");
				
				lstClientes.add(cliente);
				
			}
			
			return lstClientes;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		
		} finally {
			try {
				if(cs != null) cs.close();
				if(rs != null) rs.close();
				if(cs != null) cs.close();
				
			} catch (SQLException e) {
				e.getMessage();
			}
		}

		return null;
	}
}
