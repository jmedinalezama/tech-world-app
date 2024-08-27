package mantenimiento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.IUsuario;
import model.Usuario;
import utils.Conexion;

public class UsuarioDAO implements IUsuario {

	@Override
	public void guardarUsuario(Usuario objUsuario) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_InsertarUsuario(?,?,?,?,?,?,?,?)}";
		
		CallableStatement cs = null;
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setString(1, objUsuario.getNombreusuario());
			cs.setString(2, objUsuario.getApellidousuario());
			cs.setString(3, objUsuario.getDniusuario());
			cs.setString(4, objUsuario.getDireccionusuario());
			cs.setString(5, objUsuario.getCorreousuario());
			cs.setString(6, objUsuario.getUserusuario());
			cs.setString(7, objUsuario.getPasswordusuario());
			cs.setInt(8, objUsuario.getIdcargo());
			
			int x = cs.executeUpdate();
			
			if(x > 0) {
				System.out.println("Usuario registrado correctamente");
			} else {
				System.out.println("No se puedo registrar el usuario");
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarUsuario(Usuario objUsuario) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_ActualizarUsuario(?,?,?,?,?,?,?,?,?)}";
		
		CallableStatement cs = null;
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setInt(1, objUsuario.getIdusuario());
			cs.setString(2, objUsuario.getNombreusuario());
			cs.setString(3, objUsuario.getApellidousuario());
			cs.setString(4, objUsuario.getDniusuario());
			cs.setString(5, objUsuario.getDireccionusuario());
			cs.setString(6, objUsuario.getCorreousuario());
			cs.setString(7, objUsuario.getUserusuario());
			cs.setString(8, objUsuario.getPasswordusuario());
			cs.setInt(9, objUsuario.getIdcargo());
			
			int x = cs.executeUpdate();
			
			if(x > 0) {
				System.out.println("Usuario actualizado correctamente");
			} else {
				System.out.println("No se pudo actualizar el usuario");
			}
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void eliminarUsuario(Usuario objUsuario) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_EliminarUsuario(?)}";
		
		CallableStatement cs = null;
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setInt(1, objUsuario.getIdusuario());
			
			int x = cs.executeUpdate();
			
			if(x > 0) {
				System.out.println("Usuario eliminado correctamente");
			} else {
				System.out.println("No se pudo eliminar el usuario");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Usuario> listarUsuarios() {
	
		Conexion conexion = new Conexion();
		
		String query = "{call sp_ListarUsuarios()}";
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		List<Usuario> lstUsuarios = new ArrayList<>();
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			rs = cs.executeQuery();
			
			while(rs.next()) {
				
				Usuario usuario = new Usuario();
				
				usuario.setIdusuario(rs.getInt(1));
				usuario.setNombreusuario(rs.getString(2));
				usuario.setApellidousuario(rs.getString(3));
				usuario.setDniusuario(rs.getString(4));
				usuario.setDireccionusuario(rs.getString(5));
				usuario.setCorreousuario(rs.getString(6));
				usuario.setUserusuario(rs.getString(7));
				usuario.setPasswordusuario(rs.getString(8));
				usuario.setIdcargo(rs.getInt(9));
				
				lstUsuarios.add(usuario);
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return lstUsuarios;
	}

	@Override
	public Usuario buscarUsuarioPorId(Usuario objUsuario) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_BuscarUsuarioPorId(?)}";
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		Usuario usuario = new Usuario();
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setInt(1, objUsuario.getIdusuario());
			
			rs = cs.executeQuery();
			
			if(rs.next()) {
				
				usuario.setIdusuario(rs.getInt(1));
				usuario.setNombreusuario(rs.getString(2));
				usuario.setApellidousuario(rs.getString(3));
				usuario.setDniusuario(rs.getString(4));
				usuario.setDireccionusuario(rs.getString(5));
				usuario.setCorreousuario(rs.getString(6));
				usuario.setUserusuario(rs.getString(7));
				usuario.setPasswordusuario(rs.getString(8));
				usuario.setIdcargo(rs.getInt(9));
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return usuario;
	}

	@Override
	public Usuario buscarUsuarioPorUserAndPassword(Usuario objUsuario) {
		
		Conexion conexion = new Conexion();
		
		String query = "{call sp_BuscarUsuarioPorUserAndPassword(?,?)}";
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		Usuario usuario = new Usuario();
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setString(1, objUsuario.getUserusuario());
			cs.setString(2, objUsuario.getPasswordusuario());
			
			rs = cs.executeQuery();
			
			if(rs.next()) {
				
				usuario.setIdusuario(rs.getInt(1));
				usuario.setNombreusuario(rs.getString(2));
				usuario.setApellidousuario(rs.getString(3));
				usuario.setDniusuario(rs.getString(4));
				usuario.setDireccionusuario(rs.getString(5));
				usuario.setCorreousuario(rs.getString(6));
				usuario.setUserusuario(rs.getString(7));
				usuario.setPasswordusuario(rs.getString(8));
				usuario.setIdcargo(rs.getInt(9));
						
			}
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return usuario;
	}

	
	@Override
	public Usuario buscarUsuarioPorIdCargo(Usuario objUsuario) {

		Conexion conexion = new Conexion();
		
		String query = "{call sp_BuscarUsuarioPorIdCargo(?)}";
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		Usuario usuario = new Usuario();
		
		try {
			cs = conexion.conectar().prepareCall(query);
			
			cs.setInt(1, objUsuario.getIdcargo());
			
			rs = cs.executeQuery();
			
			if(rs.next()) {
				
				usuario.setIdusuario(rs.getInt(1));
				usuario.setNombreusuario(rs.getString(2));
				usuario.setApellidousuario(rs.getString(3));
				usuario.setDniusuario(rs.getString(4));
				usuario.setDireccionusuario(rs.getString(5));
				usuario.setCorreousuario(rs.getString(6));
				usuario.setUserusuario(rs.getString(7));
				usuario.setPasswordusuario(rs.getString(8));
				usuario.setIdcargo(rs.getInt(9));
				
			} else {
				return null;
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return usuario;
	}

	@Override
	public List<Usuario> listarTodosUsuarios() {
		
		Connection cn = null;
		
		CallableStatement cs = null;
		
		ResultSet rs = null;
		
		List<Usuario> lstUsuarios = new ArrayList<>();
		
		String query = "{call sp_ListarTodosUsuarios()}";
		
		
		try {
			
			cn = new Conexion().conectar();
			
			cs = cn.prepareCall(query);
			
			rs = cs.executeQuery();
			
			while(rs.next()) {
				
				Usuario usuario = new Usuario();
				
				usuario.setIdusuario(rs.getInt(1));
				usuario.setNombreusuario(rs.getString(2));
				usuario.setApellidousuario("");
				
				lstUsuarios.add(usuario);
				
			}
			
			return lstUsuarios;
				
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		} finally {
			
			try {
				if(cs != null) cs.close();
				if(rs != null) rs.close();
				if(cn != null) cn.close();
			
			}catch(SQLException ex) {
				 ex.getMessage();
			}
		}
		
		return null;
	}

		
	
}
