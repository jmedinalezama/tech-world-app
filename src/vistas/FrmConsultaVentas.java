package vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import mantenimiento.ClienteDAO;
import mantenimiento.DetalleVentaDAO;
import mantenimiento.EquipoDAO;
import mantenimiento.UsuarioDAO;
import mantenimiento.VentaDAO;
import model.Cliente;
import model.DetalleVenta;
import model.Equipo;
import model.Usuario;
import model.Venta;
import utils.Pintar;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;

public class FrmConsultaVentas extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	// variables globales
	private int xMouse, yMouse;
	Thread hilo;
	private DefaultTableModel modelotabladetalle;
	private DefaultTableModel modelotablaventa;
	
	
	private JPanel panelBarraTitulo;
	private JLabel lblSalir;
	private JLabel lblIconFrame;
	private JLabel lblMinimizar;
	private JLabel lblVentas;
	private JComboBox<Usuario> cmbUsuario;
	private JLabel lblUsuario;
	private JLabel lblCliente;
	private JPanel panelDetalleVenta;
	private JScrollPane scpDetalleVenta;
	private JLabel lblTituloDetalleVenta;
	private JTable tbDetalleVenta;
	private JButton btnConsultar;
	private JTable tbVentas;
	private JComboBox<Cliente> cmbCliente;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private JDateChooser dcFechaInicio;
	private JDateChooser dcFechaFin;
	private JScrollPane scpVentas;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaVentas frame = new FrmConsultaVentas();
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
	public FrmConsultaVentas() {
		setFrameIcon(new ImageIcon(FrmConsultaVentas.class.getResource("/images/punto_venta24px.png")));
		getContentPane().setBackground(Pintar.PRIMARIO.brighter());
		setBorder(new LineBorder(Color.WHITE, 4));
		setIconifiable(true);
		setClosable(true);
		setTitle("Ventas realizadas");
		setBounds(100, 100, 900, 600);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		getContentPane().setLayout(null);

		panelBarraTitulo = new JPanel();
		panelBarraTitulo.addMouseListener(new EventoDeMouse());
		panelBarraTitulo.addMouseMotionListener(new EventoDeMouseMovimiento());
		panelBarraTitulo.setLayout(null);
		panelBarraTitulo.setBackground(new Color(17, 94, 171));
		panelBarraTitulo.setBounds(0, 0, 892, 30);
		getContentPane().add(panelBarraTitulo);

		lblSalir = new JLabel("X");
		lblSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSalir.addMouseListener(new EventoDeMouse());
		lblSalir.setOpaque(true);
		lblSalir.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalir.setForeground(Color.WHITE);
		lblSalir.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSalir.setBackground(new Color(17, 94, 171));
		lblSalir.setBounds(862, 0, 30, 30);
		panelBarraTitulo.add(lblSalir);

		lblIconFrame = new JLabel("");
		lblIconFrame.setBounds(0, 0, 30, 30);
		establecerImagenLabel(lblIconFrame, "Images/images/punto_venta24px.png");
		panelBarraTitulo.add(lblIconFrame);

		lblMinimizar = new JLabel("-");
		lblMinimizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMinimizar.addMouseListener(new EventoDeMouse());
		lblMinimizar.setOpaque(true);
		lblMinimizar.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimizar.setForeground(Color.WHITE);
		lblMinimizar.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblMinimizar.setBackground(new Color(17, 94, 171));
		lblMinimizar.setBounds(833, 0, 30, 30);
		panelBarraTitulo.add(lblMinimizar);

		lblVentas = new JLabel("Lista de ventas realizadas");
		lblVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lblVentas.setForeground(Color.WHITE);
		lblVentas.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblVentas.setBounds(59, 0, 764, 30);
		panelBarraTitulo.add(lblVentas);

		

		cmbUsuario = new JComboBox<>();
		cmbUsuario.setBounds(151, 41, 245, 30);
		cmbUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cmbUsuario.setBackground(Pintar.BLANCO);
		cmbUsuario.setRenderer(new CellRenderCombo("Images/images/personas24px.png"));
		getContentPane().add(cmbUsuario);
		
		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(40, 44, 114, 20);
		getContentPane().add(lblUsuario);
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);

		lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(489, 44, 114, 20);
		getContentPane().add(lblCliente);
		lblCliente.setForeground(Color.WHITE);
		lblCliente.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblCliente.setHorizontalAlignment(SwingConstants.LEFT);

		cmbCliente = new JComboBox<>();
		cmbCliente.setBackground(Color.WHITE);
		cmbCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cmbCliente.setBounds(603, 41, 245, 30);
		cmbCliente.setRenderer(new CellRenderCombo("Images/images/personas24px.png"));
		getContentPane().add(cmbCliente);

		lblFechaInicio = new JLabel("Fecha inicio:");
		lblFechaInicio.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaInicio.setForeground(Color.WHITE);
		lblFechaInicio.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblFechaInicio.setBounds(40, 96, 114, 20);
		getContentPane().add(lblFechaInicio);

		lblFechaFin = new JLabel("Fecha fin:");
		lblFechaFin.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaFin.setForeground(Color.WHITE);
		lblFechaFin.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblFechaFin.setBounds(489, 96, 104, 20);
		getContentPane().add(lblFechaFin);

		dcFechaInicio = new JDateChooser();
		dcFechaInicio.getCalendarButton().setForeground(Color.BLACK);
		dcFechaInicio.getCalendarButton().setFont(new Font("SansSerif", Font.BOLD, 12));
		dcFechaInicio.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dcFechaInicio.getCalendarButton().setBackground(Color.WHITE);
		dcFechaInicio.getCalendarButton().setBorder(null);
		dcFechaInicio.setForeground(Color.BLACK);
		dcFechaInicio.setFont(new Font("SansSerif", Font.BOLD, 12));
		dcFechaInicio.setBackground(Color.LIGHT_GRAY);
		dcFechaInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dcFechaInicio.setBorder(null);
		dcFechaInicio.setBounds(151, 96, 245, 30);
		getContentPane().add(dcFechaInicio);

		dcFechaFin = new JDateChooser();
		dcFechaFin.getCalendarButton().setBackground(Color.WHITE);
		dcFechaFin.getCalendarButton().setBorder(null);
		dcFechaFin.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dcFechaFin.getCalendarButton().setFont(new Font("SansSerif", Font.BOLD, 12));
		dcFechaFin.setFont(new Font("SansSerif", Font.BOLD, 12));
		dcFechaFin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dcFechaFin.setBorder(null);
		dcFechaFin.setBounds(603, 96, 245, 30);
		getContentPane().add(dcFechaFin);

		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(40, 151, 146, 30);
		getContentPane().add(btnConsultar);
		btnConsultar.addActionListener(new EventoDeAccion());
		btnConsultar.addMouseListener(new EventoDeMouse());
		btnConsultar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConsultar.setIcon(new ImageIcon(FrmConsultaVentas.class.getResource("/images/agregar_a_carrito24px.png")));
		btnConsultar.setForeground(Color.WHITE);
		btnConsultar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnConsultar.setBorder(null);
		btnConsultar.setBackground(new Color(8, 46, 84));

		scpVentas = new JScrollPane();
		scpVentas.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		scpVentas.setBounds(40, 187, 808, 153);
		getContentPane().add(scpVentas);

		tbVentas = new JTable();
		tbVentas.addMouseListener(new EventoDeMouse());
		tbVentas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tbVentas.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		tbVentas.getTableHeader().setBackground(Pintar.PRIMARIO);
		tbVentas.getTableHeader().setForeground(Pintar.BLANCO);
		tbVentas.getTableHeader().setPreferredSize(new Dimension(scpVentas.getWidth(), 30));
		tbVentas.setRowHeight(25);
		tbVentas.setFont(new Font("SansSerif", Font.BOLD, 11));
		tbVentas.setGridColor(Pintar.PRIMARIO.brighter());
		tbVentas.setShowGrid(true);
		tbVentas.setShowVerticalLines(false);
		tbVentas.setShowHorizontalLines(true);
		tbVentas.setDefaultRenderer(Object.class, new CellRenderTable());
		tbVentas.setSelectionBackground(Pintar.PRIMARIO.brighter());
		tbVentas.setSelectionForeground(Pintar.BLANCO);
		scpVentas.setViewportView(tbVentas);
		
		modelotablaventa = new DefaultTableModel();
		
		modelotablaventa.setColumnIdentifiers(new Object[] {
				"ID",
				"Fecha y hora",
				"Total (S/.)",
				"Cliente",
				"Usuario"
		});
		
		tbVentas.setModel(modelotablaventa);
		

		panelDetalleVenta = new JPanel();
		panelDetalleVenta.setOpaque(false);
		panelDetalleVenta.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		panelDetalleVenta.setBackground(Color.RED);
		panelDetalleVenta.setBounds(40, 351, 808, 207);
		getContentPane().add(panelDetalleVenta);
		panelDetalleVenta.setLayout(null);

		lblTituloDetalleVenta = new JLabel("Detalle de la venta");
		lblTituloDetalleVenta.setBorder(new LineBorder(Color.WHITE));
		lblTituloDetalleVenta.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTituloDetalleVenta.setForeground(Color.WHITE);
		lblTituloDetalleVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloDetalleVenta.setBounds(0, 0, 808, 20);
		panelDetalleVenta.add(lblTituloDetalleVenta);

		scpDetalleVenta = new JScrollPane();
		scpDetalleVenta.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		scpDetalleVenta.setBounds(21, 31, 764, 165);
		panelDetalleVenta.add(scpDetalleVenta);

		tbDetalleVenta = new JTable();
		tbDetalleVenta.addMouseListener(new EventoDeMouse());
		tbDetalleVenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tbDetalleVenta.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		tbDetalleVenta.getTableHeader().setBackground(Pintar.PRIMARIO);
		tbDetalleVenta.getTableHeader().setForeground(Pintar.BLANCO);
		tbDetalleVenta.getTableHeader().setPreferredSize(new Dimension(scpDetalleVenta.getWidth(), 30));
		tbDetalleVenta.setRowHeight(25);
		tbDetalleVenta.setFont(new Font("SansSerif", Font.BOLD, 11));
		tbDetalleVenta.setGridColor(Pintar.PRIMARIO.brighter());
		tbDetalleVenta.setShowGrid(true);
		tbDetalleVenta.setShowVerticalLines(false);
		tbDetalleVenta.setShowHorizontalLines(true);
		tbDetalleVenta.setDefaultRenderer(Object.class, new CellRenderTable());
		tbDetalleVenta.setSelectionBackground(Pintar.PRIMARIO.brighter());
		tbDetalleVenta.setSelectionForeground(Pintar.BLANCO);
		scpDetalleVenta.setViewportView(tbDetalleVenta);
	
		
		modelotabladetalle = new DefaultTableModel();

		modelotabladetalle.setColumnIdentifiers(new Object[] { 
						"ID", 
						"ID Venta",
						"Cód. Equipo",
						"Equipo", 
						"Precio(S/.)", 
						"Cantidad", 
						"Subtotal(S/.)" });
		
		tbDetalleVenta.setModel(modelotabladetalle);

		cargarUsuarios();
		cargarClientes();
		
		ajustarAnchoColumnasTbVenta();
		ajustarAnchoColumnasTbDetalle();
		
		iniciarCargaConsulta();

	}

	// --------- clases DAO
	VentaDAO ventaDAO = new VentaDAO();
	ClienteDAO clienteDAO = new ClienteDAO();
	EquipoDAO equipoDAO = new EquipoDAO();
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	DetalleVentaDAO datalleventaDAO = new DetalleVentaDAO();
	
	List<Venta> lstVentas = new ArrayList<>();
	List<DetalleVenta> lstDetalleVenta = new ArrayList<>();
	
	//------------------- metodo del boton
	private void actionPerformedBtnConsultar() {
		
		Usuario usuario = (Usuario) cmbUsuario.getSelectedItem();
		
		Cliente cliente = (Cliente) cmbCliente.getSelectedItem();
		
		Date fechainicio = dcFechaInicio.getDate();
		
		Date fechafin = dcFechaFin.getDate();
		
		if(fechainicio == null) {
			
			String fecha = "2000-01-01";
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			
			try {
				fechainicio = sdf.parse(fecha);
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		}
		
		if(fechafin == null) {
			
			fechafin = new Date(new Date().getTime());
			
		}
		
		cargarConsulta(usuario, cliente, fechainicio, fechafin);
		
		lstDetalleVenta.clear();
		
		listarDetalleVenta(lstDetalleVenta);
		
	}

	//------------------- metodos de usuario
	
	public void cargarUsuarios() {
		
		cmbUsuario.removeAllItems();
		
		List<Usuario> lstUsuarios = usuarioDAO.listarTodosUsuarios();
		
		for(Usuario usuario: lstUsuarios) {
			cmbUsuario.addItem(usuario);
		}
		
	}
	
	public void cargarClientes() {
		
		cmbCliente.removeAllItems();
		
		List<Cliente> lstClientes = clienteDAO.listarTodosClientes();
		
		for(Cliente cliente: lstClientes) {
			cmbCliente.addItem(cliente);
		}	
	}
	
	public Usuario buscarUsuarioPorId(int codigo) {
		
		Usuario usuario = new Usuario();
		
		usuario.setIdusuario(codigo);
		
		return usuarioDAO.buscarUsuarioPorId(usuario);
	}
	
	public Cliente buscarClientePorId(int codigo) {
		
		Cliente cliente = new Cliente();
		
		cliente.setIdcliente(codigo);
		
		return clienteDAO.buscarClientePorId(cliente);
	}
	
	public Equipo buscarEquipoPorId(int codigo) {
		
		Equipo equipo = new Equipo();
		
		equipo.setIdequipo(codigo);
		
		return equipoDAO.buscarEquipoPorId(equipo);
	}
	
	public List<DetalleVenta> buscarDetalleVentaPorIdVenta(String idventa) {
		
		DetalleVenta detalleventa = new DetalleVenta();
		
		detalleventa.setIdventa(idventa);
		
		return datalleventaDAO.buscarDetalleVentaPorIdVenta(detalleventa);
		
	}
	
	public void iniciarCargaConsulta() {
		
		Usuario usuario = new Usuario();
		usuario.setIdusuario(0);
		
		Cliente cliente = new Cliente();
		cliente.setIdcliente(0);
		
		String fecha = "2000-01-01";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

		Date fechainicio = null;
		Date fechafin = null;
		
		try {
			fechainicio = sdf.parse(fecha);
			fechafin = new Date(new Date().getTime());
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		cargarConsulta(usuario, cliente, fechainicio, fechafin);
		
	}
	
	
	public void cargarConsulta(Usuario objUsuario, Cliente objCliente, Date fechainicio, Date fechafin) {
		
		modelotablaventa.setRowCount(0);
		
		lstVentas = ventaDAO.listarVentasPorUsuarioClienteFechas(objUsuario, objCliente, fechainicio, fechafin);
		
		for(Venta venta: lstVentas) {
			
			Cliente cliente = buscarClientePorId(venta.getIdcliente());
			Usuario usuario = buscarUsuarioPorId(venta.getIdusuario());
			
			modelotablaventa.addRow(new Object[] {
					venta.getIdVenta(),
					venta.getFecha() + "   " + venta.getHora(),
					venta.getTotal(),
					cliente.getNombrecliente() + " " + cliente.getApellidocliente(),
					usuario.getNombreusuario() + " " + usuario.getApellidousuario()
			});
		}
	}
	
	private void mostrarDetalleVenta() {
		
		int filaseleccionada = tbVentas.getSelectedRow();
		
		if(filaseleccionada > -1) {
			
			String idventa = tbVentas.getValueAt(filaseleccionada, 0).toString();
			
			lstDetalleVenta = buscarDetalleVentaPorIdVenta(idventa);
			
			listarDetalleVenta(lstDetalleVenta);
			
		}
		
	}
	
	private void listarDetalleVenta(List<DetalleVenta> lstDetalleVenta) {
		
		modelotabladetalle.setRowCount(0);
		
		for(DetalleVenta dv: lstDetalleVenta) {
			
			Equipo equipo = buscarEquipoPorId(dv.getIdequipo());
			
			modelotabladetalle.addRow(new Object[] {
					dv.getIddetalleventa(),
					dv.getIdventa(),
					equipo.getCodigoequipo(),
					equipo.getNombreequipo(),
					dv.getPrecio(),
					dv.getCantidad(),
					dv.subtotalPorEquipo()
			});
		}
	
	}
	
	
	//------------------- metodos adicionales 
	private void ajustarAnchoColumnasTbVenta() {
		TableColumnModel modelocolumna = tbVentas.getColumnModel();
		
		modelocolumna.getColumn(0).setPreferredWidth(anchoColumnaTbVenta(13)); //id
		modelocolumna.getColumn(1).setPreferredWidth(anchoColumnaTbVenta(22)); //fecha y hora
		modelocolumna.getColumn(2).setPreferredWidth(anchoColumnaTbVenta(15)); //total
		modelocolumna.getColumn(3).setPreferredWidth(anchoColumnaTbVenta(25)); //cliente
		modelocolumna.getColumn(4).setPreferredWidth(anchoColumnaTbVenta(25)); //usuario
	}
	
	private int anchoColumnaTbVenta(int porcentaje) {
		return porcentaje * scpVentas.getWidth() / 100;
	}
	
	private void ajustarAnchoColumnasTbDetalle() {
		TableColumnModel modelocolumna = tbDetalleVenta.getColumnModel();

		modelocolumna.getColumn(0).setPreferredWidth(anchoColumnaTbDetalle(7)); // id
		modelocolumna.getColumn(1).setPreferredWidth(anchoColumnaTbDetalle(15)); // id venta
		modelocolumna.getColumn(2).setPreferredWidth(anchoColumnaTbDetalle(15)); // codigo equipo
		modelocolumna.getColumn(3).setPreferredWidth(anchoColumnaTbDetalle(25)); // equipo
		modelocolumna.getColumn(4).setPreferredWidth(anchoColumnaTbDetalle(15)); // precio
		modelocolumna.getColumn(5).setPreferredWidth(anchoColumnaTbDetalle(8)); // cantidad
		modelocolumna.getColumn(6).setPreferredWidth(anchoColumnaTbDetalle(15)); // subtotal
	} 

	private int anchoColumnaTbDetalle(int porcentaje) {
		return porcentaje * scpDetalleVenta.getWidth() / 100;
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


	// ------------------------------ metodos de los botones
	private void enteredBoton(JButton boton) {
		boton.setBackground(Pintar.PRIMARIO);
	}

	private void exitedBoton(JButton boton) {
		boton.setBackground(Pintar.PRIMARIO.darker());
	}

	// ------------------------------ clase interna para la gestion de eventos de
	// Accion ActionEvent
	private class EventoDeAccion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnConsultar) {
				actionPerformedBtnConsultar();
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
			if(e.getSource() == tbVentas) {
				mostrarDetalleVenta();
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
			if (e.getSource() == btnConsultar) {
				enteredBoton(btnConsultar);
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
			if (e.getSource() == btnConsultar) {
				exitedBoton(btnConsultar);
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

	// -------------------------- clase interna para modificar el render del
	// comboBox
	private class CellRenderCombo implements ListCellRenderer<Object> {

		private String rutaimagen;

		public CellRenderCombo(String rutaimagen) {
			this.rutaimagen = rutaimagen;
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {

			JLabel label = new JLabel();

			label.setText((String) value.toString());
			label.setOpaque(true);

			Icon icono = new ImageIcon(rutaimagen);
			label.setIcon(icono);

			if (isSelected) {
				label.setForeground(Pintar.BLANCO);
				label.setBackground(Pintar.PRIMARIO);
			} else {
				label.setForeground(Pintar.BLANCO);
				label.setBackground(Pintar.PRIMARIO.brighter().brighter());
			}

			return label;
		}

	}

	// -------------------------- clase interna para modificar el render de la tabla
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
