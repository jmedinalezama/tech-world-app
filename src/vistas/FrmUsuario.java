package vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import mantenimiento.CargoDAO;
import mantenimiento.UsuarioDAO;
import model.Cargo;
import model.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import utils.Alerta;
import utils.Conexion;
import utils.Pintar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class FrmUsuario extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	// variables globales
	private int xMouse, yMouse;
	private DefaultTableModel modelotabla;
	private static int idusuario;

	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtDireccion;
	private JTextField txtUser;
	private JTextField txtCorreo;
	private JTable tbUsuarios;
	private JScrollPane scpUsuarios;
	private JButton btnLimpiar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JTextField txtPassword;
	private JComboBox<Cargo> cmbCargo;
	private JPanel panelBarraTitulo;
	private JLabel lblSalir;
	private JLabel lblIconFrame;
	private JLabel lblMinimizar;
	private JLabel lblUsuarios;
	private JButton btnGuardar;
	private JSeparator sptxtNombre;
	private JSeparator sptxtApellido;
	private JSeparator sptxtDni;
	private JSeparator sptxtDireccion;
	private JSeparator sptxtCorreo;
	private JSeparator sptxtUser;
	private JSeparator sptxtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmUsuario frame = new FrmUsuario();
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
	public FrmUsuario() {
		setFrameIcon(new ImageIcon(FrmUsuario.class.getResource("/images/personas24px.png")));
		getContentPane().setBackground(Pintar.PRIMARIO.brighter());
		setBorder(new LineBorder(Color.WHITE, 4));
		setMaximizable(true);
		setIconifiable(true);
		setForeground(Color.BLACK);
		setTitle("Usuarios");
		setClosable(true);
		setBounds(100, 100, 750, 536);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		getContentPane().setLayout(null);

		panelBarraTitulo = new JPanel();
		panelBarraTitulo.addMouseListener(new EventoDeMouse());
		panelBarraTitulo.addMouseMotionListener(new EventoDeMouseMovimiento());
		panelBarraTitulo.setLayout(null);
		panelBarraTitulo.setBackground(Pintar.PRIMARIO.brighter());
		panelBarraTitulo.setBounds(0, 0, 742, 30);
		getContentPane().add(panelBarraTitulo);

		lblSalir = new JLabel("X");
		lblSalir.addMouseListener(new EventoDeMouse());
		lblSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblSalir.setOpaque(true);
		lblSalir.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalir.setForeground(Color.WHITE);
		lblSalir.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSalir.setBackground(new Color(17, 94, 171));
		lblSalir.setBounds(712, 0, 30, 30);
		panelBarraTitulo.add(lblSalir);

		lblIconFrame = new JLabel("");
		lblIconFrame.setBounds(0, 0, 30, 30);
		establecerImagenLabel(lblIconFrame, "Images/images/personas24px.png");
		panelBarraTitulo.add(lblIconFrame);

		lblMinimizar = new JLabel("-");
		lblMinimizar.addMouseListener(new EventoDeMouse());
		lblMinimizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblMinimizar.setOpaque(true);
		lblMinimizar.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimizar.setForeground(Color.WHITE);
		lblMinimizar.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblMinimizar.setBackground(new Color(17, 94, 171));
		lblMinimizar.setBounds(682, 0, 30, 30);
		panelBarraTitulo.add(lblMinimizar);

		lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setForeground(Color.WHITE);
		lblUsuarios.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblUsuarios.setBounds(59, 0, 613, 30);
		panelBarraTitulo.add(lblUsuarios);

		txtNombre = new JTextField("Ingrese nombre");
		txtNombre.addFocusListener(new EventoDeFoco());
		txtNombre.setOpaque(false);
		txtNombre.setForeground(Pintar.GRIS.brighter());
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtNombre.setCaretColor(Color.WHITE);
		txtNombre.setBorder(null);
		txtNombre.setBounds(70, 50, 250, 30);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField("Ingrese apellido");
		txtApellido.addFocusListener(new EventoDeFoco());
		txtApellido.setOpaque(false);
		txtApellido.setForeground(Pintar.GRIS.brighter());
		txtApellido.setHorizontalAlignment(SwingConstants.CENTER);
		txtApellido.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtApellido.setCaretColor(Color.WHITE);
		txtApellido.setBorder(null);
		txtApellido.setColumns(10);
		txtApellido.setBounds(70, 100, 250, 30);
		getContentPane().add(txtApellido);

		txtDni = new JTextField("Ingrese DNI");
		txtDni.addFocusListener(new EventoDeFoco());
		txtDni.setOpaque(false);
		txtDni.setForeground(Pintar.GRIS.brighter());
		txtDni.setHorizontalAlignment(SwingConstants.CENTER);
		txtDni.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtDni.setCaretColor(Color.WHITE);
		txtDni.setBorder(null);
		txtDni.setColumns(10);
		txtDni.setBounds(70, 150, 250, 30);
		getContentPane().add(txtDni);

		txtDireccion = new JTextField("Ingrese dirección");
		txtDireccion.addFocusListener(new EventoDeFoco());
		txtDireccion.setOpaque(false);
		txtDireccion.setForeground(Pintar.GRIS.brighter());
		txtDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		txtDireccion.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtDireccion.setCaretColor(Color.WHITE);
		txtDireccion.setBorder(null);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(70, 200, 250, 30);
		getContentPane().add(txtDireccion);

		txtUser = new JTextField("Ingrese usuario");
		txtUser.addFocusListener(new EventoDeFoco());
		txtUser.setOpaque(false);
		txtUser.setForeground(Pintar.GRIS.brighter());
		txtUser.setHorizontalAlignment(SwingConstants.CENTER);
		txtUser.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtUser.setCaretColor(Color.WHITE);
		txtUser.setBorder(null);
		txtUser.setColumns(10);
		txtUser.setBounds(430, 100, 250, 30);
		getContentPane().add(txtUser);

		txtCorreo = new JTextField("Ingrese correo");
		txtCorreo.addFocusListener(new EventoDeFoco());
		txtCorreo.setOpaque(false);
		txtCorreo.setForeground(Pintar.GRIS.brighter());
		txtCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCorreo.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtCorreo.setCaretColor(Color.WHITE);
		txtCorreo.setBorder(null);
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(430, 50, 250, 30);
		getContentPane().add(txtCorreo);

		txtPassword = new JTextField("Ingrese contraseña");
		txtPassword.addFocusListener(new EventoDeFoco());
		txtPassword.setOpaque(false);
		txtPassword.setForeground(Pintar.GRIS.brighter());
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtPassword.setCaretColor(Color.WHITE);
		txtPassword.setBorder(null);
		txtPassword.setColumns(10);
		txtPassword.setBounds(430, 150, 250, 30);
		getContentPane().add(txtPassword);

		cmbCargo = new JComboBox<>();
		cmbCargo.setBounds(430, 200, 250, 30);
		cmbCargo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cmbCargo.setBackground(Pintar.BLANCO);
		cmbCargo.setRenderer(new CellRenderCombo());
		getContentPane().add(cmbCargo);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new EventoDeMouse());
		btnLimpiar.addActionListener(new EventoDeAccion());
		btnLimpiar.setIcon(new ImageIcon(FrmUsuario.class.getResource("/images/limpiar24px.png")));
		btnLimpiar.setBackground(Pintar.PRIMARIO.darker());
		btnLimpiar.setBorder(null);
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpiar.setBounds(612, 261, 100, 30);
		getContentPane().add(btnLimpiar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new EventoDeMouse());
		btnEliminar.addActionListener(new EventoDeAccion());
		btnEliminar.setIcon(new ImageIcon(FrmUsuario.class.getResource("/images/eliminar24px.png")));
		btnEliminar.setBackground(Pintar.PRIMARIO.darker());
		btnEliminar.setBorder(null);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setBounds(502, 261, 100, 30);
		getContentPane().add(btnEliminar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new EventoDeMouse());
		btnGuardar.addActionListener(new EventoDeAccion());
		btnGuardar.setIcon(new ImageIcon(FrmUsuario.class.getResource("/images/guardar24px.png")));
		btnGuardar.setBackground(Pintar.PRIMARIO.darker());
		btnGuardar.setBorder(null);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setBounds(269, 261, 100, 30);
		getContentPane().add(btnGuardar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addMouseListener(new EventoDeMouse());
		btnActualizar.addActionListener(new EventoDeAccion());
		btnActualizar.setIcon(new ImageIcon(FrmUsuario.class.getResource("/images/actualizar24px.png")));
		btnActualizar.setBackground(Pintar.PRIMARIO.darker());
		btnActualizar.setBorder(null);
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnActualizar.setBounds(379, 261, 113, 30);
		getContentPane().add(btnActualizar);

		sptxtNombre = new JSeparator();
		sptxtNombre.setBackground(Color.WHITE);
		sptxtNombre.setForeground(Color.WHITE);
		sptxtNombre.setBounds(70, 80, 250, 5);
		getContentPane().add(sptxtNombre);

		sptxtApellido = new JSeparator();
		sptxtApellido.setForeground(Color.WHITE);
		sptxtApellido.setBackground(Color.WHITE);
		sptxtApellido.setBounds(70, 130, 250, 5);
		getContentPane().add(sptxtApellido);

		sptxtDni = new JSeparator();
		sptxtDni.setForeground(Color.WHITE);
		sptxtDni.setBackground(Color.WHITE);
		sptxtDni.setBounds(70, 180, 250, 5);
		getContentPane().add(sptxtDni);

		sptxtDireccion = new JSeparator();
		sptxtDireccion.setForeground(Color.WHITE);
		sptxtDireccion.setBackground(Color.WHITE);
		sptxtDireccion.setBounds(70, 230, 250, 5);
		getContentPane().add(sptxtDireccion);

		sptxtCorreo = new JSeparator();
		sptxtCorreo.setForeground(Color.WHITE);
		sptxtCorreo.setBackground(Color.WHITE);
		sptxtCorreo.setBounds(430, 80, 250, 5);
		getContentPane().add(sptxtCorreo);

		sptxtUser = new JSeparator();
		sptxtUser.setForeground(Color.WHITE);
		sptxtUser.setBackground(Color.WHITE);
		sptxtUser.setBounds(430, 130, 250, 5);
		getContentPane().add(sptxtUser);

		sptxtPassword = new JSeparator();
		sptxtPassword.setForeground(Color.WHITE);
		sptxtPassword.setBackground(Color.WHITE);
		sptxtPassword.setBounds(430, 180, 250, 5);
		getContentPane().add(sptxtPassword);
		
		btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new EventoDeAccion());
		btnReporte.addMouseListener(new EventoDeMouse());
		btnReporte.setIcon(new ImageIcon(FrmUsuario.class.getResource("/images/reporte_personas24px.png")));
		btnReporte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReporte.setForeground(Color.WHITE);
		btnReporte.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnReporte.setBorder(null);
		btnReporte.setBackground(new Color(8, 46, 84));
		btnReporte.setBounds(30, 261, 100, 30);
		getContentPane().add(btnReporte);

		scpUsuarios = new JScrollPane();
		scpUsuarios.setBackground(Pintar.BLANCO);
		scpUsuarios.setBorder(new LineBorder(Color.WHITE, 2));
		scpUsuarios.setBounds(30, 299, 682, 195);
		getContentPane().add(scpUsuarios);

		tbUsuarios = new JTable();
		tbUsuarios.addMouseListener(new EventoDeMouse());
		tbUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tbUsuarios.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		tbUsuarios.getTableHeader().setBackground(Pintar.PRIMARIO);
		tbUsuarios.getTableHeader().setForeground(Pintar.BLANCO);
		tbUsuarios.getTableHeader().setPreferredSize(new Dimension(scpUsuarios.getWidth(), 30));
		tbUsuarios.setRowHeight(25);
		tbUsuarios.setFont(new Font("SansSerif", Font.BOLD, 11));
		tbUsuarios.setGridColor(Pintar.PRIMARIO.brighter());
		tbUsuarios.setShowGrid(true);
		tbUsuarios.setShowVerticalLines(false);
		tbUsuarios.setShowHorizontalLines(true);
		tbUsuarios.setDefaultRenderer(Object.class, new CellRenderTable());
		tbUsuarios.setSelectionBackground(Pintar.PRIMARIO.brighter());
		tbUsuarios.setSelectionForeground(Pintar.BLANCO);
		scpUsuarios.setViewportView(tbUsuarios);

		modelotabla = new DefaultTableModel();

		modelotabla.setColumnIdentifiers(new Object[] { "ID", "Nombre y Apellido", "DNI", "Correo", "User", "Cargo" });

		tbUsuarios.setModel(modelotabla);

		ajustarAnchoColumnasTbUsuarios();
		cargarCargos();
		listarUsuarios();

	}

	// --------- clases DAO
	CargoDAO cargoDAO = new CargoDAO();
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	private JButton btnReporte;

	// ----------- metodos de CRUD
	private void actionPerformedGuardar() {

		if (validarFormulario()) {

			Cargo cargo = (Cargo) cmbCargo.getSelectedItem();

			Usuario usuario = new Usuario();

			usuario.setNombreusuario(txtNombre.getText().trim());
			usuario.setApellidousuario(txtApellido.getText().trim());
			usuario.setDniusuario(txtDni.getText().trim());
			usuario.setDireccionusuario(txtDireccion.getText().trim());
			usuario.setCorreousuario(txtCorreo.getText().trim());
			usuario.setUserusuario(txtUser.getText().trim());
			usuario.setPasswordusuario(txtPassword.getText().trim());
			usuario.setIdcargo(cargo.getIdcargo());

			usuarioDAO.guardarUsuario(usuario);

			listarUsuarios();

			limpiarFormulario();

		}

	}

	private void actionPerformedActualizar() {

		if (idusuario != 0) {

			if (validarFormulario()) {

				int opcion = Alerta.confirmar(FrmUsuario.this, "¿Desea actualizar el registro?",
						"Aviso de actualización", Alerta.DESICION);

				if (opcion == 0) {

					Cargo cargo = (Cargo) cmbCargo.getSelectedItem();

					Usuario usuario = new Usuario();

					usuario.setIdusuario(idusuario);
					usuario.setNombreusuario(txtNombre.getText().trim());
					usuario.setApellidousuario(txtApellido.getText().trim());
					usuario.setDniusuario(txtDni.getText().trim());
					usuario.setDireccionusuario(txtDireccion.getText().trim());
					usuario.setCorreousuario(txtCorreo.getText().trim());
					usuario.setUserusuario(txtUser.getText().trim());
					usuario.setPasswordusuario(txtPassword.getText().trim());
					usuario.setIdcargo(cargo.getIdcargo());

					usuarioDAO.actualizarUsuario(usuario);

					listarUsuarios();

					limpiarFormulario();

				} else if (opcion == 1) {
					limpiarFormulario();
				}

			}

		} else {
			Alerta.mensaje(FrmUsuario.this, "Debe seleccionar un registro de la tabla",
					"Aviso de selección de registro", Alerta.ADVERTENCIA);
		}

	}

	private void actionPerformedEliminar() {

		if (idusuario != 0) {

			int opcion = Alerta.confirmar(FrmUsuario.this, "¿Desea eliminar el registro?", "Aviso de eliminación",
					Alerta.DESICION);

			if (opcion == 0) {

				Usuario usuario = new Usuario();

				usuario.setIdusuario(idusuario);

				usuarioDAO.eliminarUsuario(usuario);

				listarUsuarios();

				limpiarFormulario();

			} else if (opcion == 1) {
				limpiarFormulario();
			}

		} else {
			Alerta.mensaje(FrmUsuario.this, "Debe seleccionar un registro de la tabla",
					"Aviso de selección de registro", Alerta.ADVERTENCIA);
		}

	}
	
	private void actionPerformedBtnReporteUsuarios() {

		try {
			
			Conexion conexion = new Conexion();

			String urlreporte = "src/reportes/reporte_usuarios.jasper";
			
			JasperPrint jp=JasperFillManager.fillReport(urlreporte,null,conexion.conectar());

			JasperViewer view = new JasperViewer(jp, false);
			
			view.setTitle("REPORTE DE USUARIOS");
			view.setExtendedState(JFrame.MAXIMIZED_BOTH);
			view.setVisible(true);
			
			conexion.conectar().close();
		} catch (JRException | SQLException ex) {
			
			ex.printStackTrace();
		}
		
	}
	

	// ----------------------- metodos de usuario
	private void obtenerDatosTabla() {

		btnGuardar.setEnabled(false);
		txtNombre.setForeground(Pintar.BLANCO);
		txtApellido.setForeground(Pintar.BLANCO);
		txtDni.setForeground(Pintar.BLANCO);
		txtDireccion.setForeground(Pintar.BLANCO);
		txtCorreo.setForeground(Pintar.BLANCO);
		txtUser.setForeground(Pintar.BLANCO);
		txtPassword.setForeground(Pintar.BLANCO);

		int filaseleccionada = tbUsuarios.getSelectedRow();

		if (filaseleccionada > -1) {

			idusuario = (int) tbUsuarios.getValueAt(filaseleccionada, 0);

			Usuario usuario = buscarUsuario(idusuario);

			if (usuario != null) {

				Cargo cargo = buscarCargo(usuario.getIdcargo());

				if (cargo != null) {

					txtNombre.setText(usuario.getNombreusuario());
					txtApellido.setText(usuario.getApellidousuario());
					txtDni.setText(usuario.getDniusuario());
					txtDireccion.setText(usuario.getDireccionusuario());
					txtCorreo.setText(usuario.getCorreousuario());
					txtUser.setText(usuario.getUserusuario());
					txtPassword.setText(usuario.getPasswordusuario());

					cmbCargo.setSelectedItem(cargo);

				}

			} else {
				Alerta.mensaje(FrmUsuario.this, "No se encontró el usuario", "¡Error! Usuario",
						Alerta.ERROR);
			}

		}
	}

	private void listarUsuarios() {

		modelotabla.setRowCount(0);

		List<Usuario> lstUsuarios = usuarioDAO.listarUsuarios();

		for (Usuario usuario : lstUsuarios) {

			Cargo cargo = buscarCargo(usuario.getIdcargo());

			if (cargo != null) {

				modelotabla.addRow(new Object[] { usuario.getIdusuario(),
						usuario.getNombreusuario() + " " + usuario.getApellidousuario(), usuario.getDniusuario(),
						usuario.getCorreousuario(), usuario.getUserusuario(), cargo.getTipocargo() });

			} else {
				Alerta.mensaje(FrmUsuario.this, "Ha ocurrido un error al obtener cargo del usuario",
						"¡Error! cargo de usuario", Alerta.ERROR);
			}
		}

	}

	private void cargarCargos() {
		cmbCargo.removeAllItems();

		List<Cargo> lstCargos = cargoDAO.listarCargos();

		for (Cargo cargo : lstCargos) {
			cmbCargo.addItem(cargo);
		}

	}

	private Usuario buscarUsuario(int codigo) {
		Usuario usuario = new Usuario();

		usuario.setIdusuario(codigo);

		return usuarioDAO.buscarUsuarioPorId(usuario);
	}

	private Cargo buscarCargo(int codigo) {
		Cargo cargo = new Cargo();

		cargo.setIdcargo(codigo);

		return cargoDAO.buscarCargoPorId(cargo);
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
		
		txtCorreo.setForeground(Pintar.GRIS.brighter());
		txtCorreo.setText("Ingrese correo");
		
		txtUser.setForeground(Pintar.GRIS.brighter());
		txtUser.setText("Ingrese usuario");
		
		txtPassword.setForeground(Pintar.GRIS.brighter());
		txtPassword.setText("Ingrese contraseña");

		cmbCargo.setSelectedIndex(0);

		btnGuardar.setEnabled(true);
		tbUsuarios.clearSelection();

		idusuario = 0;
	}

	private boolean validarFormulario() {

		if (txtNombre.getText().trim().equals("") || txtNombre.getText().trim().equalsIgnoreCase("Ingrese nombre")) {
			Alerta.mensaje(FrmUsuario.this, "Debe ingresar nombre", "Aviso de nombre",
					Alerta.ADVERTENCIA);
			return false;
		}
		if (txtApellido.getText().trim().equals("")
				|| txtApellido.getText().trim().equalsIgnoreCase("Ingrese apellido")) {
			Alerta.mensaje(FrmUsuario.this, "Debe ingresar apellido", "Aviso de apellido",
					Alerta.ADVERTENCIA);
			return false;
		}
		if (txtDni.getText().trim().equals("") || txtDni.getText().trim().equalsIgnoreCase("Ingrese DNI")) {
			Alerta.mensaje(FrmUsuario.this, "Debe ingresar DNI", "Aviso de DNI", Alerta.ADVERTENCIA);
			return false;
		}
		if (txtDireccion.getText().trim().equals("")
				|| txtDireccion.getText().trim().equalsIgnoreCase("Ingrese dirección")) {
			Alerta.mensaje(FrmUsuario.this, "Debe ingresar dirección", "Aviso de dirección",
					Alerta.ADVERTENCIA);
			return false;
		}
		if (txtCorreo.getText().trim().equals("") || txtCorreo.getText().trim().equalsIgnoreCase("Ingrese correo")) {
			Alerta.mensaje(FrmUsuario.this, "Debe ingresar correo", "Aviso de correo",
					Alerta.ADVERTENCIA);
			return false;
		}
		if (txtUser.getText().trim().equals("") || txtUser.getText().trim().equalsIgnoreCase("Ingrese usuario")) {
			Alerta.mensaje(FrmUsuario.this, "Debe ingresar usuario", "Aviso de usuario",
					Alerta.ADVERTENCIA);
			return false;
		}
		if (txtPassword.getText().trim().equals("")
				|| txtPassword.getText().trim().equalsIgnoreCase("Ingrese contraseña")) {
			Alerta.mensaje(FrmUsuario.this, "Debe ingresar contraseña", "Aviso de contraseña",
					Alerta.ADVERTENCIA);
			return false;
		}

		return true;
	}

	private void ajustarAnchoColumnasTbUsuarios() {
		TableColumnModel modelocolumna = tbUsuarios.getColumnModel();
		
		modelocolumna.getColumn(0).setPreferredWidth(anchoColumnaTbUsuarios(5)); //ID
		modelocolumna.getColumn(1).setPreferredWidth(anchoColumnaTbUsuarios(25)); //Nombre
		modelocolumna.getColumn(2).setPreferredWidth(anchoColumnaTbUsuarios(15)); //DNI
		modelocolumna.getColumn(3).setPreferredWidth(anchoColumnaTbUsuarios(25)); //Correo
		modelocolumna.getColumn(4).setPreferredWidth(anchoColumnaTbUsuarios(13)); //User
		modelocolumna.getColumn(5).setPreferredWidth(anchoColumnaTbUsuarios(17)); //Cargo
		
	}
	
	private int anchoColumnaTbUsuarios(int porcentaje) {
		return porcentaje * scpUsuarios.getWidth() / 100;
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
	private void enteredBoton(JButton boton) {
		boton.setBackground(Pintar.PRIMARIO);
	}

	private void exitedBoton(JButton boton) {
		boton.setBackground(Pintar.PRIMARIO.darker());
	}

	// ------------------------------ clase interna para la gestion de eventos de Accion ActionEvent
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
				actionPerformedBtnReporteUsuarios();
			}

		}

	}

	// ------------------------------- clase interna para la gestion de eventos de Mouse MouseEven
	private class EventoDeMouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == lblSalir) {
				clickedLblSalir();
			}
			if (e.getSource() == lblMinimizar) {
				clickedLblMinimizar();
			}
			if(e.getSource() == tbUsuarios) {
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
				enteredBoton(btnGuardar);
			}
			if (e.getSource() == btnActualizar) {
				enteredBoton(btnActualizar);
			}
			if (e.getSource() == btnEliminar) {
				enteredBoton(btnEliminar);
			}
			if (e.getSource() == btnLimpiar) {
				enteredBoton(btnLimpiar);
			}
			if(e.getSource() == btnReporte) {
				enteredBoton(btnReporte);
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
				exitedBoton(btnGuardar);
			}
			if (e.getSource() == btnActualizar) {
				exitedBoton(btnActualizar);
			}
			if (e.getSource() == btnEliminar) {
				exitedBoton(btnEliminar);
			}
			if (e.getSource() == btnLimpiar) {
				exitedBoton(btnLimpiar);
			}
			if (e.getSource() == btnReporte) {
				exitedBoton(btnReporte);
			}
		}

	}

	// ------------------------------- clase interna para la gestion de eventos de movimiento del Mouse MouseEvent
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

	// -------------------------------- clase interna para la gestion de eventos de foco de los elementos FocusEvent
	private class EventoDeFoco implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			if (e.getSource() == txtNombre) {
				focusGainedTextField(sptxtNombre, txtNombre, "Ingrese nombre");
			}
			if (e.getSource() == txtApellido) {
				focusGainedTextField(sptxtApellido, txtApellido, "Ingrese apellido");
			}
			if (e.getSource() == txtDni) {
				focusGainedTextField(sptxtDni, txtDni, "Ingrese DNI");
			}
			if (e.getSource() == txtDireccion) {
				focusGainedTextField(sptxtDireccion, txtDireccion, "Ingrese dirección");
			}
			if (e.getSource() == txtCorreo) {
				focusGainedTextField(sptxtCorreo, txtCorreo, "Ingrese correo");
			}
			if (e.getSource() == txtUser) {
				focusGainedTextField(sptxtUser, txtUser, "Ingrese usuario");
			}
			if (e.getSource() == txtPassword) {
				focusGainedTextField(sptxtPassword, txtPassword, "Ingrese contraseña");
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (e.getSource() == txtNombre) {
				focusLostTextField(sptxtNombre, txtNombre, "Ingrese nombre");
			}
			if (e.getSource() == txtApellido) {
				focusLostTextField(sptxtApellido, txtApellido, "Ingrese apellido");
			}
			if (e.getSource() == txtDni) {
				focusLostTextField(sptxtDni, txtDni, "Ingrese DNI");
			}
			if (e.getSource() == txtDireccion) {
				focusLostTextField(sptxtDireccion, txtDireccion, "Ingrese dirección");
			}
			if (e.getSource() == txtCorreo) {
				focusLostTextField(sptxtCorreo, txtCorreo, "Ingrese correo");
			}
			if (e.getSource() == txtUser) {
				focusLostTextField(sptxtUser, txtUser, "Ingrese usuario");
			}
			if (e.getSource() == txtPassword) {
				focusLostTextField(sptxtPassword, txtPassword, "Ingrese contraseña");
			}
		}

	}

	//-------------------------- clase interna para modificar el render del comboBox
	private class CellRenderCombo implements ListCellRenderer<Object> {
		
		@Override
		public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			
			JLabel label = new JLabel();
			
			label.setText((String)value.toString());
			label.setOpaque(true);
			
			Icon icono = new ImageIcon("Images/images/cargo24px.png");
			label.setIcon(icono);
			
			if(isSelected) {
				label.setForeground(Pintar.BLANCO);
				label.setBackground(Pintar.PRIMARIO);
			} else {
				label.setForeground(Pintar.BLANCO);
				label.setBackground(Pintar.PRIMARIO.brighter().brighter());
			}
			
			return label;
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
