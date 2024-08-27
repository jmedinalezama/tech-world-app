package interfaces;

import java.util.List;

import model.Cliente;

public interface ICliente {

	public void guardarCliente(Cliente objCliente);
	public void actualizarCliente(Cliente objCliente);
	public void eliminarCliente(Cliente objCliente);
	public List<Cliente> listarClientes();
	public Cliente buscarClientePorId(Cliente objCliente);
	
	public List<Cliente> listarTodosClientes();
	
}
