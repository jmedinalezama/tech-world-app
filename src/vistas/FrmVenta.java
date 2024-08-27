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
import mantenimiento.EquipoDAO;
import mantenimiento.VentaDAO;
import model.Cliente;
import model.DetalleVenta;
import model.Equipo;
import model.Venta;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import utils.Alerta;
import utils.Conexion;
import utils.Pintar;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class FrmVenta extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	// variables globales
	private int xMouse, yMouse;
	Thread hilo;
	private DefaultTableModel modelotabladetalle;

	private JPanel panelInfoCliente;
	private JTextField txtDni;
	private JTextField txtTelefono;
	private JPanel paneInfoVenta;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	private JPanel panelBarraTitulo;
	private JLabel lblSalir;
	private JLabel lblIconFrame;
	private JLabel lblMinimizar;
	private JLabel lblVentas;
	private JLabel lblNroVenta;
	private JLabel lblTituloInfoCliente;
	private JComboBox<Cliente> cmbBuscarCliente;
	private JLabel lblTituloInfoVenta;
	private JLabel lbFechaVenta;
	private JLabel lblHoraVenta;
	private JPanel panelDetalleVenta;
	private JComboBox<Equipo> cmbBuscarEquipo;
	private JScrollPane scpDetalleVenta;
	private JLabel lblTituloDetalleVenta;
	private JLabel lblPrecio;
	private JSeparator sptxtPrecio;
	private JLabel lblCantidad;
	private JSeparator sptxtCantidad;
	private JTable tbDetalleVenta;
	private JButton btnAgregar;
	private JButton btnQuitar;
	private JButton btnActualizar;
	private JButton btnLimpiar;
	private JLabel lblSubtotalDeVenta;
	private JLabel lblIgv;
	private JLabel lblTotalDeLaVenta;
	private JLabel lblStock;
	private JTextField txtSubtotal;
	private JTextField txtIgv;
	private JTextField txtTotal;
	private JTextField txtStock;
	private JButton btnRegistrarYFacturar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmVenta frame = new FrmVenta();
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
	public FrmVenta() {
		setFrameIcon(new ImageIcon(FrmVenta.class.getResource("/images/punto_venta24px.png")));
		getContentPane().setBackground(Pintar.PRIMARIO.brighter());
		setBorder(new LineBorder(Color.WHITE, 4));
		setIconifiable(true);
		setClosable(true);
		setTitle("Realizar Venta");
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

		lblVentas = new JLabel("Ventas");
		lblVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lblVentas.setForeground(Color.WHITE);
		lblVentas.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblVentas.setBounds(59, 0, 764, 30);
		panelBarraTitulo.add(lblVentas);

		panelInfoCliente = new JPanel();
		panelInfoCliente.setOpaque(false);
		panelInfoCliente.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		panelInfoCliente.setBackground(Color.RED);
		panelInfoCliente.setBounds(10, 44, 483, 115);
		getContentPane().add(panelInfoCliente);
		panelInfoCliente.setLayout(null);

		lblTituloInfoCliente = new JLabel("Datos del cliente");
		lblTituloInfoCliente.setForeground(Color.WHITE);
		lblTituloInfoCliente.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTituloInfoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloInfoCliente.setBorder(new LineBorder(Color.WHITE));
		lblTituloInfoCliente.setBounds(0, 0, 483, 20);
		panelInfoCliente.add(lblTituloInfoCliente);

		cmbBuscarCliente = new JComboBox<>();
		cmbBuscarCliente.addActionListener(new EventoDeAccion());
		cmbBuscarCliente.setBounds(10, 30, 245, 30);
		cmbBuscarCliente.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cmbBuscarCliente.setBackground(Pintar.BLANCO);
		cmbBuscarCliente.setRenderer(new CellRenderCombo("Images/images/personas24px.png"));
		panelInfoCliente.add(cmbBuscarCliente);

		txtDni = new JTextField("DNI");
		txtDni.setEditable(false);
		txtDni.setBorder(null);
		txtDni.setOpaque(false);
		txtDni.setForeground(Pintar.GRIS.brighter());
		txtDni.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtDni.setHorizontalAlignment(SwingConstants.CENTER);
		txtDni.setBounds(265, 30, 210, 30);
		panelInfoCliente.add(txtDni);
		txtDni.setColumns(10);

		txtTelefono = new JTextField("Teléfono");
		txtTelefono.setEditable(false);
		txtTelefono.setBorder(null);
		txtTelefono.setOpaque(false);
		txtTelefono.setForeground(Pintar.GRIS.brighter());
		txtTelefono.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(265, 75, 210, 30);
		panelInfoCliente.add(txtTelefono);

		paneInfoVenta = new JPanel();
		paneInfoVenta.setOpaque(false);
		paneInfoVenta.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		paneInfoVenta.setBackground(Color.RED);
		paneInfoVenta.setBounds(582, 44, 300, 115);
		getContentPane().add(paneInfoVenta);
		paneInfoVenta.setLayout(null);

		lblTituloInfoVenta = new JLabel("Datos de Venta");
		lblTituloInfoVenta.setForeground(Color.WHITE);
		lblTituloInfoVenta.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTituloInfoVenta.setBorder(new LineBorder(Color.WHITE));
		lblTituloInfoVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloInfoVenta.setBounds(0, 0, 300, 20);
		paneInfoVenta.add(lblTituloInfoVenta);

		lbFechaVenta = new JLabel("Fecha");
		lbFechaVenta.setForeground(Color.WHITE);
		lbFechaVenta.setFont(new Font("SansSerif", Font.BOLD, 14));
		lbFechaVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lbFechaVenta.setBounds(0, 53, 300, 20);
		paneInfoVenta.add(lbFechaVenta);

		lblHoraVenta = new JLabel("Hora");
		lblHoraVenta.setForeground(Color.WHITE);
		lblHoraVenta.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblHoraVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraVenta.setBounds(0, 84, 300, 20);
		paneInfoVenta.add(lblHoraVenta);

		lblNroVenta = new JLabel("New label");
		lblNroVenta.setForeground(Color.WHITE);
		lblNroVenta.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNroVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblNroVenta.setBounds(0, 27, 300, 20);
		paneInfoVenta.add(lblNroVenta);

		panelDetalleVenta = new JPanel();
		panelDetalleVenta.setOpaque(false);
		panelDetalleVenta.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		panelDetalleVenta.setBackground(Color.RED);
		panelDetalleVenta.setBounds(10, 181, 872, 269);
		getContentPane().add(panelDetalleVenta);
		panelDetalleVenta.setLayout(null);

		cmbBuscarEquipo = new JComboBox<>();
		cmbBuscarEquipo.addActionListener(new EventoDeAccion());
		cmbBuscarEquipo.setBounds(10, 30, 245, 30);
		cmbBuscarEquipo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cmbBuscarEquipo.setBackground(Pintar.BLANCO);
		cmbBuscarEquipo.setRenderer(new CellRenderCombo("Images/images/equipos_electronicos24px.png"));
		panelDetalleVenta.add(cmbBuscarEquipo);

		txtPrecio = new JTextField("Precio");
		txtPrecio.setCaretColor(Color.WHITE);
		txtPrecio.addFocusListener(new EventoDeFoco());
		txtPrecio.setOpaque(false);
		txtPrecio.setBorder(null);
		txtPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecio.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtPrecio.setForeground(Pintar.GRIS.brighter());
		txtPrecio.setBounds(497, 30, 110, 30);
		panelDetalleVenta.add(txtPrecio);
		txtPrecio.setColumns(10);

		txtCantidad = new JTextField("Cantidad");
		txtCantidad.setCaretColor(Color.WHITE);
		txtCantidad.addFocusListener(new EventoDeFoco());
		txtCantidad.setOpaque(false);
		txtCantidad.setBorder(null);
		txtCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtCantidad.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtCantidad.setForeground(Pintar.GRIS.brighter());
		txtCantidad.setBounds(733, 30, 110, 30);
		panelDetalleVenta.add(txtCantidad);
		txtCantidad.setColumns(10);

		lblTituloDetalleVenta = new JLabel("Detalle de la venta");
		lblTituloDetalleVenta.setBorder(new LineBorder(Color.WHITE));
		lblTituloDetalleVenta.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTituloDetalleVenta.setForeground(Color.WHITE);
		lblTituloDetalleVenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloDetalleVenta.setBounds(0, 0, 872, 20);
		panelDetalleVenta.add(lblTituloDetalleVenta);

		lblPrecio = new JLabel("Precio (S/.):");
		lblPrecio.setForeground(Color.WHITE);
		lblPrecio.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPrecio.setBounds(418, 33, 79, 22);
		panelDetalleVenta.add(lblPrecio);

		sptxtPrecio = new JSeparator();
		sptxtPrecio.setBounds(497, 60, 110, 5);
		panelDetalleVenta.add(sptxtPrecio);

		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setForeground(Color.WHITE);
		lblCantidad.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblCantidad.setBounds(657, 33, 79, 22);
		panelDetalleVenta.add(lblCantidad);

		sptxtCantidad = new JSeparator();
		sptxtCantidad.setBounds(733, 60, 110, 5);
		panelDetalleVenta.add(sptxtCantidad);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new EventoDeAccion());
		btnAgregar.addMouseListener(new EventoDeMouse());
		btnAgregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAgregar.setIcon(new ImageIcon(FrmVenta.class.getResource("/images/agregar_a_carrito24px.png")));
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnAgregar.setBorder(null);
		btnAgregar.setBackground(new Color(8, 46, 84));
		btnAgregar.setBounds(716, 75, 146, 30);
		panelDetalleVenta.add(btnAgregar);

		btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new EventoDeAccion());
		btnQuitar.addMouseListener(new EventoDeMouse());
		btnQuitar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuitar.setIcon(new ImageIcon(FrmVenta.class.getResource("/images/quitar_de_carrito24px.png")));
		btnQuitar.setForeground(Color.WHITE);
		btnQuitar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnQuitar.setBorder(null);
		btnQuitar.setBackground(new Color(8, 46, 84));
		btnQuitar.setBounds(716, 157, 146, 30);
		panelDetalleVenta.add(btnQuitar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new EventoDeAccion());
		btnActualizar.addMouseListener(new EventoDeMouse());
		btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnActualizar.setIcon(new ImageIcon(FrmVenta.class.getResource("/images/actualizar24px.png")));
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnActualizar.setBorder(null);
		btnActualizar.setBackground(new Color(8, 46, 84));
		btnActualizar.setBounds(716, 116, 146, 30);
		panelDetalleVenta.add(btnActualizar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new EventoDeAccion());
		btnLimpiar.addMouseListener(new EventoDeMouse());
		btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpiar.setIcon(new ImageIcon(FrmVenta.class.getResource("/images/limpiar24px.png")));
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnLimpiar.setBorder(null);
		btnLimpiar.setBackground(new Color(8, 46, 84));
		btnLimpiar.setBounds(716, 198, 146, 30);
		panelDetalleVenta.add(btnLimpiar);

		scpDetalleVenta = new JScrollPane();
		scpDetalleVenta.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		scpDetalleVenta.setBounds(10, 75, 685, 183);
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

		modelotabladetalle
				.setColumnIdentifiers(new Object[] { "Nro.", "ID equipo", "Equipo", "Precio(S/.)", "Cantidad", "Subtotal(S/.)" });

		tbDetalleVenta.setModel(modelotabladetalle);
		
		lblStock = new JLabel("Stock:");
		lblStock.setForeground(Color.WHITE);
		lblStock.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblStock.setBounds(273, 33, 51, 22);
		panelDetalleVenta.add(lblStock);
		
		txtStock = new JTextField("Stock");
		txtStock.setEditable(false);
		txtStock.setOpaque(false);
		txtStock.setHorizontalAlignment(SwingConstants.CENTER);
		txtStock.setForeground(new Color(198, 198, 198));
		txtStock.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtStock.setColumns(10);
		txtStock.setCaretColor(Color.WHITE);
		txtStock.setBorder(null);
		txtStock.setBounds(316, 29, 71, 30);
		panelDetalleVenta.add(txtStock);

		lblSubtotalDeVenta = new JLabel("Subtotal de venta(S/.):");
		lblSubtotalDeVenta.setForeground(Color.WHITE);
		lblSubtotalDeVenta.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblSubtotalDeVenta.setBounds(10, 467, 123, 22);
		getContentPane().add(lblSubtotalDeVenta);

		txtSubtotal = new JTextField("Subtotal");
		txtSubtotal.setOpaque(false);
		txtSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubtotal.setForeground(new Color(198, 198, 198));
		txtSubtotal.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtSubtotal.setEditable(false);
		txtSubtotal.setColumns(10);
		txtSubtotal.setBorder(null);
		txtSubtotal.setBounds(120, 463, 136, 30);
		getContentPane().add(txtSubtotal);

		lblIgv = new JLabel("IGV (18%):");
		lblIgv.setForeground(Color.WHITE);
		lblIgv.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblIgv.setBounds(350, 467, 76, 22);
		getContentPane().add(lblIgv);

		txtIgv = new JTextField("IGV");
		txtIgv.setOpaque(false);
		txtIgv.setHorizontalAlignment(SwingConstants.CENTER);
		txtIgv.setForeground(new Color(198, 198, 198));
		txtIgv.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtIgv.setEditable(false);
		txtIgv.setColumns(10);
		txtIgv.setBorder(null);
		txtIgv.setBounds(401, 463, 136, 30);
		getContentPane().add(txtIgv);

		lblTotalDeLaVenta = new JLabel("Total de la venta(S/.):");
		lblTotalDeLaVenta.setForeground(Color.WHITE);
		lblTotalDeLaVenta.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTotalDeLaVenta.setBounds(632, 467, 123, 22);
		getContentPane().add(lblTotalDeLaVenta);

		txtTotal = new JTextField("Total");
		txtTotal.setOpaque(false);
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setForeground(new Color(198, 198, 198));
		txtTotal.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBorder(null);
		txtTotal.setBounds(746, 463, 136, 30);
		getContentPane().add(txtTotal);
		
		btnRegistrarYFacturar = new JButton("Registrar y Facturar Venta");
		btnRegistrarYFacturar.addActionListener(new EventoDeAccion());
		btnRegistrarYFacturar.addMouseListener(new EventoDeMouse());
		btnRegistrarYFacturar.setIcon(new ImageIcon(FrmVenta.class.getResource("/images/guardar24px.png")));
		btnRegistrarYFacturar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistrarYFacturar.setForeground(Color.WHITE);
		btnRegistrarYFacturar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnRegistrarYFacturar.setBorder(null);
		btnRegistrarYFacturar.setBackground(new Color(8, 46, 84));
		btnRegistrarYFacturar.setBounds(560, 528, 322, 30);
		getContentPane().add(btnRegistrarYFacturar);

		generarNroVenta();
		cargarClientes();
		cargarEquipos();
		ajustarAnchoColumnasTbUsuarios();

		hilo = new ClaseHoraFecha();
		hilo.start();

	}

	// --------- clases DAO
	VentaDAO ventaDAO = new VentaDAO();
	ClienteDAO clienteDAO = new ClienteDAO();
	EquipoDAO equipoDAO = new EquipoDAO();
	List<DetalleVenta> lstDetalleVenta = new ArrayList<>();
	
	
	
	//--------------------- metodos de CRUD de venta
	private void actionPerformedBtnRegistrarYFacturarVenta() {
		
		if(lstDetalleVenta.size() > 0) {
			
			Venta venta = new Venta();
			
			Cliente cliente = (Cliente) cmbBuscarCliente.getSelectedItem();
			
			venta.setIdVenta(lblNroVenta.getText());
			venta.setIdcliente(cliente.getIdcliente());
			venta.setIdusuario(FrmLogueo.idusuario);
			venta.setFecha(new Date(new Date().getTime()));
			venta.setHora(new Date(new Date().getTime()));
			venta.setSubtotal(Double.parseDouble(txtSubtotal.getText()));
			venta.setIgv(Double.parseDouble(txtIgv.getText()));
			venta.setTotal(Double.parseDouble(txtTotal.getText()));
			
			int resultado = ventaDAO.registrarVenta(venta, lstDetalleVenta);
			
			if(resultado != -1) {
				
				equipoDAO.actualizarStockEquipo(lstDetalleVenta);
				
				Alerta.mensaje(
						FrmVenta.this, 
						"Se registró la venta correctamente", 
						"¡Éxito! Venta realizada", 
						Alerta.EXITO);
				
				mostrarReporteVenta();
				
				limpiarFormularioParaNuevaVenta();
				
			} else {
				Alerta.mensaje(
						FrmVenta.this, 
						"¡Error! No se puedo realizar la venta", 
						"¡Error! venta fallida", 
						Alerta.ERROR);
			}

		} else {
			Alerta.mensaje(
					FrmVenta.this, 
					"La tabla de detalle está vacía\nDebe agregar al menos un equipo", 
					"¡Aviso! tabla detalle vacía", 
					Alerta.ADVERTENCIA);
		}
	
	}
	
	private void mostrarReporteVenta() {
		
		try {
			
			Conexion conexion = new Conexion();
			
			String urlreporteventa = "src/reportes/factura_venta.jasper";
			
			HashMap<String, Object> parametros = new HashMap<>();
			parametros.put("nrofactura", lblNroVenta.getText().trim());
			
			JasperPrint jp = JasperFillManager.fillReport(urlreporteventa, parametros, conexion.conectar());
			
			JasperViewer view = new JasperViewer(jp, false);
			
			view.setTitle("FACTURA DE VENTA");
			view.setExtendedState(JFrame.MAXIMIZED_BOTH);
			view.setVisible(true);
			
			conexion.conectar().close();
			
		} catch(SQLException | JRException e) {
			e.printStackTrace();
		}
		
	}
	
	
	// ------------------------ metodo para elementos del panel de detalle de venta
	private void actionPerformedBtnAgregar() {

		if (validarFormularioDetalleVenta()) {
			
			DetalleVenta detalleventa = new DetalleVenta();

			Equipo equipo = (Equipo) cmbBuscarEquipo.getSelectedItem();

			detalleventa.setIdventa(lblNroVenta.getText());
			detalleventa.setIdequipo(equipo.getIdequipo());
			detalleventa.setCantidad(Integer.parseInt(txtCantidad.getText().trim()));
			detalleventa.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));

			if (!validarExistenciaEquipo(detalleventa)) {

				lstDetalleVenta.add(detalleventa);

				listarDetalleVenta();

				limpiarFormularioDetalleVenta();
			} else {
				Alerta.mensaje(
						FrmVenta.this, 
						"El equipo ya fue agregado a la lista\nSeleccione el registro para modificarlo", 
						"¡Información! Equipo agregado", 
						Alerta.INFORMACION);
			}
		}
	}

	private void actionPerformedBtnQuitar() {

		if(lstDetalleVenta.size() > 0) {
			int filaseleccionada = tbDetalleVenta.getSelectedRow();

			if (filaseleccionada > -1) {
				lstDetalleVenta.remove(filaseleccionada);

				listarDetalleVenta();
				
				limpiarFormularioDetalleVenta();

			} else {
				Alerta.mensaje(
						FrmVenta.this, 
						"Debe seleccionar un registro de la tabla", 
						"¡Aviso! selección de registro", 
						Alerta.ADVERTENCIA);
			}
		} else {
			Alerta.mensaje(
					FrmVenta.this, 
					"La lista está vacía\nNo hay datos para quitar", 
					"¡Información! lista vacía", 
					Alerta.INFORMACION);
		}
	}
	
	private void actionPerformedBtnActualizar() {
		
		if(lstDetalleVenta.size() > 0) {
			
			int filaseleccionada = tbDetalleVenta.getSelectedRow();
			
			if(filaseleccionada > -1) {
				
				if (validarFormularioDetalleVenta()) {
					
					DetalleVenta detalleventa = lstDetalleVenta.get(filaseleccionada);
					
					detalleventa.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
					detalleventa.setCantidad(Integer.parseInt(txtCantidad.getText().trim()));
					
					listarDetalleVenta();
					
					limpiarFormularioDetalleVenta();
					
				}
				
			} else {
				Alerta.mensaje(
						FrmVenta.this, 
						"Debe seleccionar un registro de la tabla", 
						"¡Aviso! selección de registro", 
						Alerta.ADVERTENCIA);
			}

		} else {
			Alerta.mensaje(
					FrmVenta.this, 
					"La lista está vacía\nNo hay datos para actualizar", 
					"¡Información! lista vacía", 
					Alerta.INFORMACION);
		}
		
	}
	
	private void actionPerformedBtnLimpiar() {
		limpiarFormularioDetalleVenta();
	}

	private void listarDetalleVenta() {

		modelotabladetalle.setRowCount(0);
		int nrofiladetalleventa = 1;

		if (lstDetalleVenta.size() > 0) {

			for (DetalleVenta dv : lstDetalleVenta) {

				Equipo equipobuscado = buscarEquipoPorId(dv.getIdequipo());

				modelotabladetalle.addRow(new Object[] { nrofiladetalleventa++, equipobuscado.getCodigoequipo(),
						equipobuscado.getNombreequipo(), dv.getPrecio(), dv.getCantidad(), dv.subtotalPorEquipo() });

			}

			Venta venta = new Venta();

			double subtotaldeventa = venta.calcularSubtotalDeVenta(lstDetalleVenta);
			double igv = venta.calcularIgvDeVenta(lstDetalleVenta);
			double total = venta.calcularTotalDeVenta(lstDetalleVenta);

			txtSubtotal.setForeground(Pintar.BLANCO);
			txtIgv.setForeground(Pintar.BLANCO);
			txtTotal.setForeground(Pintar.BLANCO);

			txtSubtotal.setText(String.valueOf(subtotaldeventa));
			txtIgv.setText(String.valueOf(igv));
			txtTotal.setText(String.valueOf(total));

		} else {

			txtSubtotal.setForeground(Pintar.GRIS.brighter());
			txtIgv.setForeground(Pintar.GRIS.brighter());
			txtTotal.setForeground(Pintar.GRIS.brighter());

			txtSubtotal.setText("Subtotal");
			txtIgv.setText("IGV");
			txtTotal.setText("Total");
		}

	}

	private void obtenerDatosTabla() {
		
		btnAgregar.setEnabled(false);
		
		int filaseleccionada = tbDetalleVenta.getSelectedRow();
		
		if(filaseleccionada > -1) {
			
			txtCantidad.setForeground(Pintar.BLANCO);
			
			String codigoequipo = (String) tbDetalleVenta.getValueAt(filaseleccionada, 1);
			
			Equipo equipo = buscarEquipoPorCodigoEquipo(codigoequipo);
			
			cmbBuscarEquipo.setSelectedItem(equipo);
			txtPrecio.setText(tbDetalleVenta.getValueAt(filaseleccionada, 3).toString());
			txtCantidad.setText(tbDetalleVenta.getValueAt(filaseleccionada, 4).toString());
			
		}
		
	}
	
	private boolean validarExistenciaEquipo(DetalleVenta detalleventa) {
		for (DetalleVenta dv : lstDetalleVenta) {
			if (dv.getIdequipo() == detalleventa.getIdequipo()) {
				return true;
			}
		}
		return false;
	}

	private boolean validarFormularioDetalleVenta() {

		if (txtPrecio.getText().trim().equals("") || txtPrecio.getText().trim().equalsIgnoreCase("Precio")) {
			Alerta.mensaje(
					FrmVenta.this, 
					"Debes ingresar precio de equipo", 
					"¡Aviso! precio", 
					Alerta.ADVERTENCIA);
			return false;
		}
		if (txtCantidad.getText().trim().equals("") || txtCantidad.getText().trim().equalsIgnoreCase("Cantidad")) {
			Alerta.mensaje(
					FrmVenta.this, 
					"Debes ingresar la cantidad a adquirir", 
					"¡Aviso! cantidad", 
					Alerta.ADVERTENCIA);
			return false;
		}
		
		if(Integer.parseInt(txtCantidad.getText().trim())<= 0) {
			Alerta.mensaje(
					FrmVenta.this, 
					"La cantidad debe ser mayor a cero", 
					"¡Aviso! cantidad", 
					Alerta.ADVERTENCIA);
			return false;
		}
		if(Integer.parseInt(txtCantidad.getText().trim()) > Integer.parseInt(txtStock.getText().trim())) {
			Alerta.mensaje(
					FrmVenta.this, 
					"No hay stock suficiente.\nLa cantidad deber ser menor o igual al stock", 
					"¡Aviso! Stock", 
					Alerta.ADVERTENCIA);
			return false;
		}
		
		return true;
	}

	private void limpiarFormularioDetalleVenta() {
		txtCantidad.setForeground(Pintar.GRIS.brighter());
		txtCantidad.setText("Cantidad");
		cmbBuscarEquipo.setSelectedIndex(0);
		tbDetalleVenta.clearSelection();
		btnAgregar.setEnabled(true);
	}

	private void ajustarAnchoColumnasTbUsuarios() {
		TableColumnModel modelocolumna = tbDetalleVenta.getColumnModel();
		
		modelocolumna.getColumn(0).setPreferredWidth(anchoColumnaTbUsuarios(10)); //Nro
		modelocolumna.getColumn(1).setPreferredWidth(anchoColumnaTbUsuarios(15)); //Id equipo
		modelocolumna.getColumn(2).setPreferredWidth(anchoColumnaTbUsuarios(30)); //equipo
		modelocolumna.getColumn(3).setPreferredWidth(anchoColumnaTbUsuarios(15)); //precio
		modelocolumna.getColumn(4).setPreferredWidth(anchoColumnaTbUsuarios(10)); //cantidad
		modelocolumna.getColumn(5).setPreferredWidth(anchoColumnaTbUsuarios(20)); //subtotal
		
	}
	
	private int anchoColumnaTbUsuarios(int porcentaje) {
		return porcentaje * scpDetalleVenta.getWidth() / 100;
	}
	
	// ------------------------- metodos de los ComboBox
	private void actionPerformedCmbBuscarCliente() {
		
		if(cmbBuscarCliente.getSelectedItem() != null) {
			txtDni.setForeground(Pintar.BLANCO);
			txtTelefono.setForeground(Pintar.BLANCO);

			Cliente cliente = (Cliente) cmbBuscarCliente.getSelectedItem();

			txtDni.setText("DNI: " + cliente.getDnicliente());
			txtTelefono.setText("Teléfono: " + cliente.getTelefonocliente());
		}
	}

	private void actionPerformedCmbBuscarEquipo() {
		
		if(cmbBuscarEquipo.getSelectedItem() != null) {
			txtPrecio.setForeground(Pintar.BLANCO);
			txtStock.setForeground(Pintar.BLANCO);

			Equipo equipo = (Equipo) cmbBuscarEquipo.getSelectedItem();

			txtPrecio.setText(String.valueOf(equipo.getPrecioequipo()));
			txtStock.setText(String.valueOf(equipo.getStockequipo()));
		}
	}

	// ----------------------- metodos de usuario
	
	private void limpiarFormularioParaNuevaVenta() {
		
		cmbBuscarCliente.setSelectedIndex(0);

		cargarEquipos();
		
		generarNroVenta();
		
		lstDetalleVenta.clear();
		
		listarDetalleVenta();
		
	}
	
	private Equipo buscarEquipoPorId(int codigo) {
		Equipo equipo = new Equipo();

		equipo.setIdequipo(codigo);

		return equipoDAO.buscarEquipoPorId(equipo);

	}

	private Equipo buscarEquipoPorCodigoEquipo(String codigoequipo) {
		Equipo equipo = new Equipo();
		
		equipo.setCodigoequipo(codigoequipo);
		
		return equipoDAO.buscarEquipoPorCodigoEquipo(equipo);
	}
	
	private void cargarClientes() {

		cmbBuscarCliente.removeAllItems();

		List<Cliente> lstClientes = clienteDAO.listarClientes();

		for (Cliente cliente : lstClientes) {
			cmbBuscarCliente.addItem(cliente);
		}

	}

	private void cargarEquipos() {
		cmbBuscarEquipo.removeAllItems();

		List<Equipo> lstEquipos = equipoDAO.listarEquipos();

		for (Equipo equipo : lstEquipos) {
			cmbBuscarEquipo.addItem(equipo);
		}

	}

	private void generarNroVenta() {

		String nroventa = ventaDAO.generarNroVenta();

		lblNroVenta.setText(nroventa);

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

	// ------------------------------ clase interna para la gestion de eventos de
	// Accion ActionEvent
	private class EventoDeAccion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == cmbBuscarCliente) {
				actionPerformedCmbBuscarCliente();
			}
			if (e.getSource() == cmbBuscarEquipo) {
				actionPerformedCmbBuscarEquipo();
			}
			if (e.getSource() == btnAgregar) {
				actionPerformedBtnAgregar();
			}
			if (e.getSource() == btnQuitar) {
				actionPerformedBtnQuitar();
			}
			if(e.getSource() == btnActualizar) {
				actionPerformedBtnActualizar();
			}
			if(e.getSource() == btnLimpiar) {
				actionPerformedBtnLimpiar();
			}
			if(e.getSource() == btnRegistrarYFacturar) {
				actionPerformedBtnRegistrarYFacturarVenta();
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
			if(e.getSource() == tbDetalleVenta) {
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
			if (e.getSource() == btnAgregar) {
				enteredBoton(btnAgregar);
			}
			if (e.getSource() == btnQuitar) {
				enteredBoton(btnQuitar);
			}
			if(e.getSource() == btnActualizar) {
				enteredBoton(btnActualizar);
			}
			if(e.getSource() == btnLimpiar) {
				enteredBoton(btnLimpiar);
			}
			if(e.getSource() == btnRegistrarYFacturar) {
				enteredBoton(btnRegistrarYFacturar);
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
			if (e.getSource() == btnAgregar) {
				exitedBoton(btnAgregar);
			}
			if (e.getSource() == btnQuitar) {
				exitedBoton(btnQuitar);
			}
			if(e.getSource() == btnActualizar) {
				exitedBoton(btnActualizar);
			}
			if(e.getSource() == btnLimpiar) {
				exitedBoton(btnLimpiar);
			}
			if(e.getSource() == btnRegistrarYFacturar) {
				exitedBoton(btnRegistrarYFacturar);
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
			if (e.getSource() == txtPrecio) {
				focusGainedTextField(sptxtPrecio, txtPrecio, "Precio");
			}
			if (e.getSource() == txtCantidad) {
				focusGainedTextField(sptxtCantidad, txtCantidad, "Cantidad");
			}

		}

		@Override
		public void focusLost(FocusEvent e) {
			if (e.getSource() == txtPrecio) {
				focusLostTextField(sptxtPrecio, txtPrecio, "Precio");
			}
			if (e.getSource() == txtCantidad) {
				focusLostTextField(sptxtCantidad, txtCantidad, "Cantidad");
			}
		}

	}

	// --------------------clase interna para la hora y fecha
	private class ClaseHoraFecha extends Thread {

		@Override
		public void run() {

			while (hilo != null) {

				try {

					Calendar calendario = new GregorianCalendar();

					String anio = String.valueOf(calendario.get(Calendar.YEAR));

					String mes = String.valueOf(calendario.get(Calendar.MONTH) + 1);
					if (Integer.parseInt(mes) < 10) {
						mes = "0" + mes;
					}

					String dia = String.valueOf(calendario.get(Calendar.DAY_OF_MONTH));
					if (Integer.parseInt(dia) < 10) {
						dia = "0" + dia;
					}

					String hora = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY));
					if (Integer.parseInt(hora) < 10) {
						hora = "0" + hora;
					}

					String minuto = String.valueOf(calendario.get(Calendar.MINUTE));
					if (Integer.parseInt(minuto) < 10) {
						minuto = "0" + minuto;
					}

					lbFechaVenta.setText("Fecha: " + dia + " / " + mes + " / " + anio);
					lblHoraVenta.setText("Hora: " + hora + ":" + minuto);

					Thread.sleep(1000);

				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}

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
