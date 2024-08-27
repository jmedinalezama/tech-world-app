package interfaces;

import java.util.List;

import model.Usuario;

public interface IUsuario {

	public void guardarUsuario(Usuario objUsuario);
	
	public void actualizarUsuario(Usuario objUsuario);
	
	public void eliminarUsuario(Usuario objUsuario);
	
	public List<Usuario> listarUsuarios();
	
	public Usuario buscarUsuarioPorId(Usuario objUsuario);
	
	public Usuario buscarUsuarioPorUserAndPassword(Usuario objUsuario);
	
	public Usuario buscarUsuarioPorIdCargo(Usuario objUsuario);
	
	public List<Usuario> listarTodosUsuarios();
	
}
