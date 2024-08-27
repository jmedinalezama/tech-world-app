package vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import mantenimiento.CargoDAO;
import mantenimiento.ClienteDAO;
import model.Cargo;
import model.Cliente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import utils.Alerta;
import utils.Conexion;
import utils.Pintar;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;


import javax.swing.JSeparator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FrmCliente extends JInternalFrame {


	private static final long serialVersionUID = 1L;
	
	// variables globales
	private int xMouse, yMouse;
	private DefaultTableModel modelotabla;
	private static int idcliente;

	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JTable tbClientes;
	private JScrollPane scpClientes;
	private JButton btnLimpiar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JPanel panelBarraTitulo;
	private JLabel lblSalir;
	private JButton btnGuardar;
	private JLabel lblIconFrame;
	private JLabel lblTitulo;
	private JLabel lblMinimizar;
	private JSeparator sptxtNombres;
	private JSeparator sptxtApellidos;
	private JSeparator sptxtDni;
	private JSeparator sptxtDireccion;
	private JSeparator sptxtTelefono;
	private JSeparator sptxtCorreo;
	private JButton btnReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCliente frame = new FrmCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmCliente() {
		setFrameIcon(new ImageIcon(FrmCliente.class.getResource("/images/personas24px.png")));
		setIconifiable(true);
		getContentPane().setBackground(Pintar.PRIMARIO.brighter());
		setBorder(new LineBorder(new Color(255, 255, 255), 4));
		setForeground(Color.BLACK);
		setTitle("Clientes");
		setClosable(true);
		setBounds(100, 100, 750, 500);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		getContentPane().setLayout(null);

		panelBarraTitulo = new JPanel();
		panelBarraTitulo.addMouseMotionListener(new EventoDeMouseMovimiento());
		panelBarraTitulo.addMouseListener(new EventoDeMouse());
		panelBarraTitulo.setBackground(Pintar.PRIMARIO.brighter());
		panelBarraTitulo.setBounds(0, 0, 742, 30);
		getContentPane().add(panelBarraTitulo);
		panelBarraTitulo.setLayout(null);

		lblSalir = new JLabel("X");
		lblSalir.setOpaque(true);
		lblSalir.setBackground(Pintar.PRIMARIO.brighter());
		lblSalir.addMouseListener(new EventoDeMouse());
		lblSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSalir.setForeground(Color.WHITE);
		lblSalir.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSalir.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalir.setBounds(712, 0, 30, 30);
		panelBarraTitulo.add(lblSalir);

		lblIconFrame = new JLabel("");
		lblIconFrame.setBounds(0, 0, 30, 30);
		establecerImagenLabel(lblIconFrame, "Images/images/personas24px.png");
		panelBarraTitulo.add(lblIconFrame);

		lblMinimizar = new JLabel("-");
		lblMinimizar.setOpaque(true);
		lblMinimizar.setBackground(Pintar.PRIMARIO.brighter());
		lblMinimizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMinimizar.addMouseListener(new EventoDeMouse());
		lblMinimizar.setForeground(Color.WHITE);
		lblMinimizar.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblMinimizar.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimizar.setBounds(682, 0, 30, 30);
		panelBarraTitulo.add(lblMinimizar);

		lblTitulo = new JLabel("Clientes");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblTitulo.setBounds(59, 0, 613, 30);
		panelBarraTitulo.add(lblTitulo);

		txtDni = new JTextField("Ingrese DNI");
		txtDni.addFocusListener(new EventoDeFoco());
		txtDni.setBorder(null);
		txtDni.setCaretColor(Color.WHITE);
		txtDni.setHorizontalAlignment(SwingConstants.CENTER);
		txtDni.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtDni.setForeground(Pintar.GRIS.brighter());
		txtDni.setOpaque(false);
		txtDni.setBounds(70, 160, 250, 30);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);

		txtCorreo = new JTextField("Ingrese correo");
		txtCorreo.addFocusListener(new EventoDeFoco());
		txtCorreo.setOpaque(false);
		txtCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCorreo.setForeground(Pintar.GRIS.brighter());
		txtCorreo.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtCorreo.setCaretColor(Color.WHITE);
		txtCorreo.setBorder(null);
		txtCorreo.setBounds(430, 160, 250, 30);
		getContentPane().add(txtCorreo);
		txtCorreo.setColumns(10);

		txtApellido = new JTextField("Ingrese apellido");
		txtApellido.addFocusListener(new EventoDeFoco());
		txtApellido.setOpaque(false);
		txtApellido.setForeground(Pintar.GRIS.brighter());
		txtApellido.setHorizontalAlignment(SwingConstants.CENTER);
		txtApellido.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtApellido.setCaretColor(Color.WHITE);
		txtApellido.setBorder(null);
		txtApellido.setBounds(70, 110, 250, 30);
		getContentPane().add(txtApellido);
		txtApellido.setColumns(10);

		txtTelefono = new JTextField("Ingrese teléfono");
		txtTelefono.addFocusListener(new EventoDeFoco());
		txtTelefono.setBorder(null);
		txtTelefono.setCaretColor(Color.WHITE);
		txtTelefono.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtTelefono.setForeground(Pintar.GRIS.brighter());
		txtTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		txtTelefono.setOpaque(false);
		txtTelefono.setBounds(430, 110, 250, 30);
		getContentPane().add(txtTelefono);
		txtTelefono.setColumns(10);

		txtNombre = new JTextField("Ingrese nombre");
		txtNombre.addFocusListener(new EventoDeFoco());
		txtNombre.setBorder(null);
		txtNombre.setCaretColor(Color.WHITE);
		txtNombre.setOpaque(false);
		txtNombre.setForeground(Pintar.GRIS.brighter());
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtNombre.setBounds(70, 60, 250, 30);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtDireccion = new JTextField("Ingrese dirección");
		txtDireccion.addFocusListener(new EventoDeFoco());
		txtDireccion.setOpaque(false);
		txtDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		txtDireccion.setForeground(Pintar.GRIS.brighter());
		txtDireccion.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtDireccion.setCaretColor(Color.WHITE);
		txtDireccion.setBorder(null);
		txtDireccion.setBounds(430, 60, 250, 30);
		getContentPane().add(txtDireccion);
		txtDireccion.setColumns(10);

		sptxtNombres = new JSeparator();
		sptxtNombres.setBackground(Pintar.BLANCO);
		sptxtNombres.setForeground(Pintar.BLANCO);
		sptxtNombres.setBounds(71, 90, 250, 5);
		getContentPane().add(sptxtNombres);

		sptxtApellidos = new JSeparator();
		sptxtApellidos.setForeground(Pintar.BLANCO);
		sptxtApellidos.setBackground(Pintar.BLANCO);
		sptxtApellidos.setBounds(71, 140, 250, 5);
		getContentPane().add(sptxtApellidos);

		sptxtDni = new JSeparator();
		sptxtDni.setForeground(Pintar.BLANCO);
		sptxtDni.setBackground(Pintar.BLANCO);
		sptxtDni.setBounds(70, 190, 250, 5);
		getContentPane().add(sptxtDni);

		sptxtDireccion = new JSeparator();
		sptxtDireccion.setForeground(Pintar.BLANCO);
		sptxtDireccion.setBackground(Pintar.BLANCO);
		sptxtDireccion.setBounds(430, 90, 250, 5);
		getContentPane().add(sptxtDireccion);

		sptxtTelefono = new JSeparator();
		sptxtTelefono.setForeground(Pintar.BLANCO);
		sptxtTelefono.setBackground(Pintar.BLANCO);
		sptxtTelefono.setBounds(430, 140, 250, 5);
		getContentPane().add(sptxtTelefono);

		sptxtCorreo = new JSeparator();
		sptxtCorreo.setForeground(Pintar.BLANCO);
		sptxtCorreo.setBackground(Pintar.BLANCO);
		sptxtCorreo.setBounds(430, 190, 250, 5);
		getContentPane().add(sptxtCorreo);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new EventoDeMouse());
		btnGuardar.addActionListener(new EventoDeAccion());
		btnGuardar.setIcon(new ImageIcon(FrmCliente.class.getResource("/images/guardar24px.png")));
		btnGuardar.setBounds(262, 230, 100, 30);
		getContentPane().add(btnGuardar);
		btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnGuardar.setBackground(Pintar.PRIMARIO.darker());
		btnGuardar.setForeground(Pintar.BLANCO);
		btnGuardar.setBorder(null);
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addMouseListener(new EventoDeMouse());
		btnActualizar.addActionListener(new EventoDeAccion());
		btnActualizar.setIcon(new ImageIcon(FrmCliente.class.getResource("/images/actualizar24px.png")));
		btnActualizar.setBounds(372, 230, 124, 30);
		getContentPane().add(btnActualizar);
		btnActualizar.setBackground(Pintar.PRIMARIO.darker());
		btnActualizar.setBorder(null);
		btnActualizar.setForeground(Pintar.BLANCO);
		btnActualizar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new EventoDeMouse());
		btnEliminar.addActionListener(new EventoDeAccion());
		btnEliminar.setIcon(new ImageIcon(FrmCliente.class.getResource("/images/eliminar24px.png")));
		btnEliminar.setBounds(506, 230, 100, 30);
		getContentPane().add(btnEliminar);
		btnEliminar.setBackground(Pintar.PRIMARIO.darker());
		btnEliminar.setForeground(Pintar.BLANCO);
		btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEliminar.setBorder(null);
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new EventoDeMouse());
		btnLimpiar.addActionListener(new EventoDeAccion());
		btnLimpiar.setIcon(new ImageIcon(FrmCliente.class.getResource("/images/limpiar24px.png")));
		btnLimpiar.setBounds(616, 230, 100, 30);
		getContentPane().add(btnLimpiar);
		btnLimpiar.setBorder(null);
		btnLimpiar.setForeground(Pintar.BLANCO);
		btnLimpiar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnLimpiar.setBackground(Pintar.PRIMARIO.darker());
		btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new EventoDeAccion());
		btnReporte.addMouseListener(new EventoDeMouse());
		btnReporte.setIcon(new ImageIcon(FrmCliente.class.getResource("/images/reporte_personas24px.png")));
		btnReporte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReporte.setForeground(Color.WHITE);
		btnReporte.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnReporte.setBorder(null);
		btnReporte.setVisible(false);
		btnReporte.setBackground(new Color(8, 46, 84));
		btnReporte.setBounds(27, 230, 100, 30);
		getContentPane().add(btnReporte);

		scpClientes = new JScrollPane();
		scpClientes.setBorder(new LineBorder(Color.WHITE, 2));
		scpClientes.setBounds(27, 271, 689, 187);
		getContentPane().add(scpClientes);

		tbClientes = new JTable();
		tbClientes.addMouseListener(new EventoDeMouse());
		tbClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tbClientes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		tbClientes.getTableHeader().setBackground(Pintar.PRIMARIO);
		tbClientes.getTableHeader().setForeground(Pintar.BLANCO);
		tbClientes.getTableHeader().setPreferredSize(new Dimension(scpClientes.getWidth(), 30));
		tbClientes.setRowHeight(25);
		tbClientes.setFont(new Font("SansSerif", Font.BOLD, 11));
		tbClientes.setGridColor(Pintar.PRIMARIO.brighter());
		tbClientes.setShowGrid(true);
		tbClientes.setShowVerticalLines(false);
		tbClientes.setShowHorizontalLines(true);
		tbClientes.setDefaultRenderer(Object.class, new CellRenderTable());
		tbClientes.setSelectionBackground(Pintar.PRIMARIO.brighter());
		tbClientes.setSelectionForeground(Pintar.BLANCO);
		scpClientes.setViewportView(tbClientes);
		
		modelotabla = new DefaultTableModel();
		
		modelotabla.setColumnIdentifiers(new Object[] {
				"ID",
				"Nombre y Apellido",
				"DNI",
				"Dirección",
				"Teléfono",
				"Correo"
		});
		
		tbClientes.setModel(modelotabla);
		
		validarSesionDe();
		ajustarAnchoColumnasTbClientes();
		listarClientes();

	}

	//------------ clases DAO
	ClienteDAO clienteDAO = new ClienteDAO();
	CargoDAO cargoDAO = new CargoDAO();
	
	
	//----------- metodos de CRUD
	private void actionPerformedGuardar() {
		
		if(validarFormulario()) {
			
			Cliente cliente = new Cliente();
			
			cliente.setNombrecliente(txtNombre.getText().trim());
			cliente.setApellidocliente(txtApellido.getText().trim());
			cliente.setDnicliente(txtDni.getText().trim());
			cliente.setDireccioncliente(txtDireccion.getText().trim());
			cliente.setTelefonocliente(txtTelefono.getText().trim());
			cliente.setCorreocliente(txtCorreo.getText().trim());
			
			clienteDAO.guardarCliente(cliente);
			
			listarClientes();
			
			limpiarFormulario();
			
		}
		
	}
	
	private void actionPerformedActualizar() {
		
		if(idcliente != 0) {
			
			if(validarFormulario()) {
				
				int opcion = Alerta.confirmar(FrmCliente.this, "¿Desea actualizar el registro?", 
						"Aviso de actualización", Alerta.DESICION);
				
				if(opcion == 0) {
					
					Cliente cliente = new Cliente();
					
					cliente.setIdcliente(idcliente);
					cliente.setNombrecliente(txtNombre.getText().trim());
					cliente.setApellidocliente(txtApellido.getText().trim());
					cliente.setDnicliente(txtDni.getText().trim());
					cliente.setDireccioncliente(txtDireccion.getText().trim());
					cliente.setTelefonocliente(txtTelefono.getText().trim());
					cliente.setCorreocliente(txtCorreo.getText().trim());
					
					clienteDAO.actualizarCliente(cliente);
					
					listarClientes();
					
					limpiarFormulario();
					
				} else if(opcion == 1) {
					limpiarFormulario();
				}
				
			}
			
		} else {
			Alerta.mensaje(FrmCliente.this, "Debe seleccionar un registro de la tabla", 
					"Aviso de selección de registro", Alerta.ADVERTENCIA);
		}
		
	}
	
	private void actionPerformedEliminar() {
		
		if(idcliente != 0) {
			
			int opcion = Alerta.confirmar(FrmCliente.this, "¿Desea eliminar el registro?", 
					"Aviso de eliminación", Alerta.DESICION);
			
			if(opcion == 0) {
				
				Cliente cliente = new Cliente();
				
				cliente.setIdcliente(idcliente);
				
				clienteDAO.eliminarCliente(cliente);
				
				listarClientes();
				
				limpiarFormulario();
				
			} else if(opcion == 1) {
				limpiarFormulario();
			}
			
		} else {
			Alerta.mensaje(FrmCliente.this, "Debe seleccionar un registro de la tabla", 
					"Aviso de selección de registro", Alerta.ADVERTENCIA);
		}
		
	}
	
	private void actionPerformedBtnReporteClientes() {

		try {
			
			Conexion conexion = new Conexion();

			String urlreporte = "src/reportes/reporte_clientes.jasper";
			
			JasperPrint jp=JasperFillManager.fillReport(urlreporte,null,conexion.conectar());

			JasperViewer view = new JasperViewer(jp, false);
			
			view.setTitle("REPORTE DE CLIENTES");
			view.setExtendedState(JFrame.MAXIMIZED_BOTH);
			view.setVisible(true);
			
			conexion.conectar().close();
		} catch (JRException | SQLException ex) {
			
			ex.printStackTrace();
		}
		
	}
	
	
	// ----------------------- metodos de usuario

	private void validarSesionDe() {
		
		try {
			
			int idcargo = FrmLogueo.idcargo;
			
			Cargo cargo = buscarCargo(idcargo);
			
			String tipocargo = cargo.getTipocargo();
			
			if (tipocargo.equalsIgnoreCase("Administrador")) {
				btnReporte.setVisible(true);

			} else if (tipocargo.equalsIgnoreCase("Vendedor")) {
				btnReporte.setVisible(false);
			}
	
		}catch(Exception e) {
			e.getMessage();
		}
		
	}
	
	private void obtenerDatosTabla() {
		
		btnGuardar.setEnabled(false);
		txtNombre.setForeground(Pintar.BLANCO);
		txtApellido.setForeground(Pintar.BLANCO);
		txtDni.setForeground(Pintar.BLANCO);
		txtDireccion.setForeground(Pintar.BLANCO);
		txtTelefono.setForeground(Pintar.BLANCO);
		txtCorreo.setForeground(Pintar.BLANCO);
		
		int filaseleccionada = tbClientes.getSelectedRow();
		
		if(filaseleccionada > -1) {
			
			idcliente = (int) tbClientes.getValueAt(filaseleccionada, 0);
			
			Cliente cliente = buscarCliente(idcliente);
			
			if(cliente != null) {
				
				txtNombre.setText(cliente.getNombrecliente());
				txtApellido.setText(cliente.getApellidocliente());
				txtDni.setText(cliente.getDnicliente());
				txtDireccion.setText(cliente.getDireccioncliente());
				txtTelefono.setText(cliente.getTelefonocliente());
				txtCorreo.setText(cliente.getCorreocliente());
				
			} else {
				Alerta.mensaje(FrmCliente.this, "No se encontró el cliente", "¡Error! Cliente", Alerta.ERROR);
			}
			
		} 
		
	}
	
	private void listarClientes() {
		
		modelotabla.setRowCount(0);
		
		List<Cliente> lstClientes = clienteDAO.listarClientes();
		
		for(Cliente cliente : lstClientes) {
			
			modelotabla.addRow(new Object[] {
					cliente.getIdcliente(),
					cliente.getNombrecliente() + " " + cliente.getApellidocliente(),
					cliente.getDnicliente(),
					cliente.getDireccioncliente(),
					cliente.getTelefonocliente(),
					cliente.getCorreocliente()
			});
			
		}
		
	}
	
	private Cliente buscarCliente(int codigo) {
		Cliente cliente = new Cliente();
		
		cliente.setIdcliente(codigo);
		
		return clienteDAO.buscarClientePorId(cliente);
		
	}
	
	private Cargo buscarCargo(int codigo) {
		Cargo cargo = new Cargo();
		
		cargo.setIdcargo(codigo);
		
		return cargoDAO.buscarCargoPorId(cargo);
	}
	
	private boolean validarFormulario() {
		
		if(txtNombre.getText().trim().equals("") || txtNombre.getText().trim().equalsIgnoreCase("Ingrese nombre")) {
			Alerta.mensaje(FrmCliente.this, "Debe ingresar nombre", "Aviso de nombre", Alerta.ADVERTENCIA);
			return false;
		}
		
		if(txtApellido.getText().trim().equals("") || txtApellido.getText().trim().equalsIgnoreCase("Ingrese apellido")) {
			Alerta.mensaje(FrmCliente.this, "Debe ingresar apellido", "Aviso de apellido", Alerta.ADVERTENCIA);
			return false;
		}
		
		if(txtDni.getText().trim().equals("") || txtDni.getText().trim().equalsIgnoreCase("Ingrese DNI")) {
			Alerta.mensaje(FrmCliente.this, "Debe ingresar DNI", "Aviso de DNI", Alerta.ADVERTENCIA);
			return false;
		}
		
		if(txtDireccion.getText().trim().equals("") || txtDireccion.getText().trim().equalsIgnoreCase("Ingrese dirección")) {
			Alerta.mensaje(FrmCliente.this, "Debe ingresar dirección", "Aviso de dirección", Alerta.ADVERTENCIA);
			return false;
		}
		
		if(txtTelefono.getText().trim().equals("") || txtTelefono.getText().trim().equalsIgnoreCase("Ingrese teléfono")) {
			Alerta.mensaje(FrmCliente.this, "Debe ingresar teléfono", "Aviso de teléfono", Alerta.ADVERTENCIA);
			return false;
		}
		
		if(txtCorreo.getText().trim().equals("") || txtCorreo.getText().trim().equalsIgnoreCase("Ingrese correo")) {
			Alerta.mensaje(FrmCliente.this, "Debe ingresar correo", "Aviso de correo", Alerta.ADVERTENCIA);
			return false;
		}
		return true;
		
	}
	
	private void limpiarFormulario() {
		txtNombre.setForeground(Pintar.GRIS.brighter());
		txtNombre.setText("Ingrese nombre");
		
		txtApellido.setForeground(Pintar.GRIS.brighter());
		txtApellido.setText("Ingrese apellido");
		
		txtDni.setForeground(Pintar.GRIS.brighter());
		txtDni.setText("Ingrese DNI");
		
		txtDireccion.setForeground(Pintar.GRIS.brighter());
		txtDireccion.setText("Ingrese dirección");
		
		txtTelefono.setForeground(Pintar.GRIS.brighter());
		txtTelefono.setText("Ingrese teléfono");
		
		txtCorreo.setForeground(Pintar.GRIS.brighter());
		txtCorreo.setText("Ingrese correo");
		
		btnGuardar.setEnabled(true);
		tbClientes.clearSelection();
		
		idcliente = 0;
	}
	
	private void ajustarAnchoColumnasTbClientes() {
		TableColumnModel modelocolumna = tbClientes.getColumnModel();
		
		modelocolumna.getColumn(0).setPreferredWidth(anchoColumnaTbClientes(5)); //id
		modelocolumna.getColumn(1).setPreferredWidth(anchoColumnaTbClientes(25)); //nombre y apellido
		modelocolumna.getColumn(2).setPreferredWidth(anchoColumnaTbClientes(10)); //dni
		modelocolumna.getColumn(3).setPreferredWidth(anchoColumnaTbClientes(25)); //direccion
		modelocolumna.getColumn(4).setPreferredWidth(anchoColumnaTbClientes(15)); //telefono
		modelocolumna.getColumn(5).setPreferredWidth(anchoColumnaTbClientes(20)); //correo
		
	}
	
	private int anchoColumnaTbClientes(int porcentaje) {
		return porcentaje * scpClientes.getWidth() / 100;
	}
	
	private void establecerImagenLabel(JLabel label, String rutaimg) {

		ImageIcon icono = new ImageIcon(rutaimg);

		Icon imgicono = new ImageIcon(
				icono.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));

		label.setIcon(imgicono);

		this.repaint();
	}

	// ----------------------- metodos de la barra de titulo
	private void clickedLblSalir() {
		this.dispose();
	}

	private void clickedLblMinimizar() {
		try {
			lblMinimizar.setBackground(Pintar.PRIMARIO.brighter());
			setIcon(true);
		} catch (PropertyVetoException e) {

			e.printStackTrace();
		}
	}

	private void pressedBarraTitulo(MouseEvent e) {
		xMouse = e.getX();
		yMouse = e.getY();

		panelBarraTitulo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelBarraTitulo.setBackground(Pintar.PRIMARIO);
		lblMinimizar.setBackground(Pintar.PRIMARIO);
		lblSalir.setBackground(Pintar.PRIMARIO);

	}

	private void draggedBarraTitulo(MouseEvent e) {
		int x = e.getXOnScreen() - 7;
		int y = e.getYOnScreen() - 61;

		this.setLocation(x - xMouse, y - yMouse);
	}

	private void releasedBarraTitulo() {
		panelBarraTitulo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		panelBarraTitulo.setBackground(Pintar.PRIMARIO.brighter());
		lblMinimizar.setBackground(Pintar.PRIMARIO.brighter());
		lblSalir.setBackground(Pintar.PRIMARIO.brighter());
	}

	private void enteredLblMinimizar() {
		lblMinimizar.setBackground(Pintar.PRIMARIO);
	}

	private void exitedLblMinimizar() {
		lblMinimizar.setBackground(Pintar.PRIMARIO.brighter());
	}

	private void enteredLblSalir() {
		lblSalir.setBackground(Pintar.PRIMARIO);
	}

	private void exitedLblSalir() {
		lblSalir.setBackground(Pintar.PRIMARIO.brighter());
	}

	// ------------------------------- metodos de los campos de texto
	private void focusGainedTextField(JSeparator separator, JTextField txt, String texto) {
		separator.setBackground(Pintar.VERDE.brighter());
		separator.setForeground(Pintar.VERDE.brighter());

		if (txt.getText().trim().equalsIgnoreCase(texto)) {
			txt.setText("");
			txt.setForeground(Pintar.BLANCO);
		}

	}

	private void focusLostTextField(JSeparator separator, JTextField txt, String texto) {
		separator.setBackground(Pintar.BLANCO);
		separator.setForeground(Pintar.BLANCO);

		if (txt.getText().trim().length() == 0) {
			txt.setText(texto);
			txt.setForeground(Pintar.GRIS.brighter());
		}
	}

	// ------------------------------ metodos de los botones
	private void enteredButton(JButton boton) {
		boton.setBackground(Pintar.PRIMARIO);
	}

	private void exitedButton(JButton boton) {
		boton.setBackground(Pintar.PRIMARIO.darker());
	}

	
	//-------------------------------- clase interna para la gestion de evento de Accion ActionEvent
	private class EventoDeAccion implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnLimpiar) {
				limpiarFormulario();
			}
			if (e.getSource() == btnGuardar) {
				actionPerformedGuardar();
			}
			if (e.getSource() == btnActualizar) {
				actionPerformedActualizar();
			}
			if (e.getSource() == btnEliminar) {
				actionPerformedEliminar();
			}
			if(e.getSource() == btnReporte) {
				actionPerformedBtnReporteClientes();
			}

		}
		
	}
	
	
	// ------------------------------- clase interna para la gestion de eventos de
	// Mouse MouseEven
	private class EventoDeMouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == lblSalir) {
				clickedLblSalir();
			}
			if (e.getSource() == lblMinimizar) {
				clickedLblMinimizar();
			}
			if(e.getSource() == tbClientes) {
				obtenerDatosTabla();
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == panelBarraTitulo) {
				pressedBarraTitulo(e);
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getSource() == panelBarraTitulo) {
				releasedBarraTitulo();
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == lblMinimizar) {
				enteredLblMinimizar();
			}
			if (e.getSource() == lblSalir) {
				enteredLblSalir();
			}
			if (e.getSource() == btnGuardar) {
				enteredButton(btnGuardar);
			}
			if (e.getSource() == btnActualizar) {
				enteredButton(btnActualizar);
			}
			if (e.getSource() == btnEliminar) {
				enteredButton(btnEliminar);
			}
			if (e.getSource() == btnLimpiar) {
				enteredButton(btnLimpiar);
			}
			if(e.getSource() == btnReporte) {
				enteredButton(btnReporte);
			}

		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getSource() == lblMinimizar) {
				exitedLblMinimizar();
			}
			if (e.getSource() == lblSalir) {
				exitedLblSalir();
			}
			if (e.getSource() == btnGuardar) {
				exitedButton(btnGuardar);
			}
			if (e.getSource() == btnActualizar) {
				exitedButton(btnActualizar);
			}
			if (e.getSource() == btnEliminar) {
				exitedButton(btnEliminar);
			}
			if (e.getSource() == btnLimpiar) {
				exitedButton(btnLimpiar);
			}
			if(e.getSource() == btnReporte) {
				exitedButton(btnReporte);
			}
		}

	}

	// ------------------------------- clase interna para la gestion de eventos de
	// movimiento del Mouse MouseEvent
	private class EventoDeMouseMovimiento implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			if (e.getSource() == panelBarraTitulo) {
				draggedBarraTitulo(e);
			}

		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}

	}

	// -------------------------------- clase interna para la gestion de eventos de
	// foco de los elementos FocusEvent
	private class EventoDeFoco implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			if (e.getSource() == txtNombre) {
				focusGainedTextField(sptxtNombres, txtNombre, "Ingrese nombre");
			}
			if (e.getSource() == txtApellido) {
				focusGainedTextField(sptxtApellidos, txtApellido, "Ingrese apellido");
			}
			if (e.getSource() == txtDni) {
				focusGainedTextField(sptxtDni, txtDni, "Ingrese DNI");
			}
			if (e.getSource() == txtDireccion) {
				focusGainedTextField(sptxtDireccion, txtDireccion, "Ingrese dirección");
			}
			if (e.getSource() == txtTelefono) {
				focusGainedTextField(sptxtTelefono, txtTelefono, "Ingrese teléfono");
			}
			if (e.getSource() == txtCorreo) {
				focusGainedTextField(sptxtCorreo, txtCorreo, "Ingrese correo");
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (e.getSource() == txtNombre) {
				focusLostTextField(sptxtNombres, txtNombre, "Ingrese nombre");
			}
			if (e.getSource() == txtApellido) {
				focusLostTextField(sptxtApellidos, txtApellido, "Ingrese apellido");
			}
			if (e.getSource() == txtDni) {
				focusLostTextField(sptxtDni, txtDni, "Ingrese DNI");
			}
			if (e.getSource() == txtDireccion) {
				focusLostTextField(sptxtDireccion, txtDireccion, "Ingrese dirección");
			}
			if (e.getSource() == txtTelefono) {
				focusLostTextField(sptxtTelefono, txtTelefono, "Ingrese teléfono");
			}
			if (e.getSource() == txtCorreo) {
				focusLostTextField(sptxtCorreo, txtCorreo, "Ingrese correo");
			}
		}

	}

	
	//-------------------------- clase interna para modificar el render de la tabla
	private class CellRenderTable extends DefaultTableCellRenderer {
			
		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
			setBackground(Pintar.BLANCO);
			setForeground(Pintar.NEGRO);
			setHorizontalAlignment(SwingConstants.CENTER);
			
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
			
	}
}
