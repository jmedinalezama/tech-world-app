package mantenimiento;

// import java.util.List;

// import model.Usuario;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		
		// Usuario usuario = new Usuario();
		// UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		//-------------registrar usuario
		/*usuario.setNombreusuario("Paola");
		usuario.setApellidousuario("Mendez");
		usuario.setDniusuario("12325201");
		usuario.setDireccionusuario("Trujillo");
		usuario.setCorreousuario("paola@gmail.com");
		usuario.setUserusuario("paola");
		usuario.setPasswordusuario("123");
		usuario.setIdcargo(2);
		
		usuarioDAO.guardarUsuario(usuario);*/
		
		
		//-------------actualizar usuario
		/*usuario.setIdusuario(1);
		usuario.setNombreusuario("Maria");
		usuario.setApellidousuario("Lopez");
		usuario.setDniusuario("12325201");
		usuario.setDireccionusuario("Trujillo");
		usuario.setCorreousuario("lopez@gmail.com");
		usuario.setUserusuario("maria");
		usuario.setPasswordusuario("12345");
		usuario.setIdcargo(1);
		
		usuarioDAO.actualizarUsuario(usuario);*/
		
		
		//-----------eliminar usuario
		/*usuario.setIdusuario(2);
		
		usuarioDAO.eliminarUsuario(usuario);*/
		
		
		//--------------listar usuarios
		/*List<Usuario> lstUsuarios = usuarioDAO.listarUsuarios();
		
		for(Usuario u: lstUsuarios) {
			System.out.println(u.getIdusuario() + " " + u.getNombreusuario() + " " + u.getApellidousuario()
			+ " " + u.getCorreousuario());
		}*/
		
		//------------ buscar usuario por id
		/*usuario.setIdusuario(3);
		
		Usuario usuariobuscado = usuarioDAO.buscarUsuarioPorId(usuario);
		
		System.out.println(usuariobuscado.getIdusuario() + " " + usuariobuscado.getNombreusuario() + 
				" "  + usuariobuscado.getApellidousuario() + " " + usuariobuscado.getCorreousuario());*/
		
		
		//------------ buscar usuario por userusuario y passwordusuario
		/*usuario.setUserusuario("maria");
		usuario.setPasswordusuario("12345");
		
		Usuario usuariobuscado = usuarioDAO.buscarUsuarioPorUserAndPassword(usuario);
		
		System.out.println(usuariobuscado.getIdusuario() + " " + usuariobuscado.getNombreusuario() + 
				" "  + usuariobuscado.getApellidousuario() + " " + usuariobuscado.getCorreousuario());*/
		
		

	}

}
