package vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import mantenimiento.EquipoDAO;
import model.Equipo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import utils.Alerta;
import utils.Conexion;
import utils.Pintar;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
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

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Cursor;
import java.awt.Dimension;

public class FrmEquipo extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	
	//variables globales
	private int xMouse, yMouse;
	private DefaultTableModel modelotabla;
	private static int idequipo;
	
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private JTextField txtStock;
	private JScrollPane scpEquipos;
	private JTable tbEquipos;
	private JButton btnLimpiar;
	private JPanel panelBarraTitulo;
	private JLabel lblSalir;
	private JLabel lblIconFrame;
	private JLabel lblMinimizar;
	private JLabel lblEquipos;
	private JButton btnGuardar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JSeparator sptxtCodigo;
	private JSeparator sptxtNombre;
	private JSeparator sptxtPrecio;
	private JSeparator sptxtStock;
	private JButton btnReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEquipo frame = new FrmEquipo();
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
	public FrmEquipo() {
		getContentPane().setBackground(Pintar.PRIMARIO.brighter());
		setBorder(new LineBorder(Color.WHITE, 4));
		setFrameIcon(new ImageIcon(FrmEquipo.class.getResource("/images/equipos_electronicos24px.png")));
		setTitle("Equipos");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 634, 404);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		getContentPane().setLayout(null);
		
		panelBarraTitulo = new JPanel();
		panelBarraTitulo.addMouseListener(new EventoDeMouse());
		panelBarraTitulo.addMouseMotionListener(new EventoDeMouseMovimiento());
		panelBarraTitulo.setLayout(null);
		panelBarraTitulo.setBackground(Pintar.PRIMARIO.brighter());
		panelBarraTitulo.setBounds(0, 0, 626, 30);
		getContentPane().add(panelBarraTitulo);
		
		lblSalir = new JLabel("X");
		lblSalir.addMouseListener(new EventoDeMouse());
		lblSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSalir.setOpaque(true);
		lblSalir.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalir.setForeground(Color.WHITE);
		lblSalir.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSalir.setBackground(new Color(17, 94, 171));
		lblSalir.setBounds(596, 0, 30, 30);
		panelBarraTitulo.add(lblSalir);
		
		lblIconFrame = new JLabel("");
		lblIconFrame.setBounds(0, 0, 30, 30);
		establecerImagenLabel(lblIconFrame, "Images/images/equipos_electronicos24px.png");
		panelBarraTitulo.add(lblIconFrame);
		
		lblMinimizar = new JLabel("-");
		lblMinimizar.addMouseListener(new EventoDeMouse());
		lblMinimizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMinimizar.setOpaque(true);
		lblMinimizar.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimizar.setForeground(Color.WHITE);
		lblMinimizar.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblMinimizar.setBackground(new Color(17, 94, 171));
		lblMinimizar.setBounds(568, 0, 30, 30);
		panelBarraTitulo.add(lblMinimizar);
		
		lblEquipos = new JLabel("Equipos");
		lblEquipos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEquipos.setForeground(Color.WHITE);
		lblEquipos.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblEquipos.setBounds(59, 0, 499, 30);
		panelBarraTitulo.add(lblEquipos);
		
		txtCodigo = new JTextField("Ingrese código");
		txtCodigo.setEditable(false);
		txtCodigo.addFocusListener(new EventoDeFoco());
		txtCodigo.setOpaque(false);
		txtCodigo.setForeground(Pintar.BLANCO);
		txtCodigo.setCaretColor(Color.WHITE);
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtCodigo.setBorder(null);
		txtCodigo.setBounds(40, 50, 230, 30);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField("Ingrese nombre");
		txtNombre.addFocusListener(new EventoDeFoco());
		txtNombre.setOpaque(false);
		txtNombre.setForeground(Pintar.GRIS.brighter());
		txtNombre.setCaretColor(Color.WHITE);
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtNombre.setBorder(null);
		txtNombre.setColumns(10);
		txtNombre.setBounds(350, 50, 230, 30);
		getContentPane().add(txtNombre);
		
		txtPrecio = new JTextField("Ingrese precio");
		txtPrecio.addFocusListener(new EventoDeFoco());
		txtPrecio.setOpaque(false);
		txtPrecio.setForeground(Pintar.GRIS.brighter());
		txtPrecio.setCaretColor(Color.WHITE);
		txtPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecio.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtPrecio.setBorder(null);
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(40, 100, 230, 30);
		getContentPane().add(txtPrecio);
		
		txtStock = new JTextField("Ingrese stock");
		txtStock.addFocusListener(new EventoDeFoco());
		txtStock.setOpaque(false);
		txtStock.setForeground(Pintar.GRIS.brighter());
		txtStock.setCaretColor(Color.WHITE);
		txtStock.setHorizontalAlignment(SwingConstants.CENTER);
		txtStock.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtStock.setBorder(null);
		txtStock.setColumns(10);
		txtStock.setBounds(350, 100, 230, 30);
		getContentPane().add(txtStock);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new EventoDeAccion());
		btnLimpiar.addMouseListener(new EventoDeMouse());
		btnLimpiar.setIcon(new ImageIcon(FrmEquipo.class.getResource("/images/limpiar24px.png")));
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpiar.setBorder(null);
		btnLimpiar.setBackground(Pintar.PRIMARIO.darker());
		btnLimpiar.setBounds(504, 170, 100, 30);
		getContentPane().add(btnLimpiar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new EventoDeAccion());
		btnEliminar.addMouseListener(new EventoDeMouse());
		btnEliminar.setIcon(new ImageIcon(FrmEquipo.class.getResource("/images/eliminar24px.png")));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(Pintar.PRIMARIO.darker());
		btnEliminar.setBounds(394, 170, 100, 30);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new EventoDeAccion());
		btnActualizar.addMouseListener(new EventoDeMouse());
		btnActualizar.setIcon(new ImageIcon(FrmEquipo.class.getResource("/images/actualizar24px.png")));
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnActualizar.setBorder(null);
		btnActualizar.setBackground(Pintar.PRIMARIO.darker());
		btnActualizar.setBounds(271, 170, 113, 30);
		getContentPane().add(btnActualizar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new EventoDeAccion());
		btnGuardar.addMouseListener(new EventoDeMouse());
		btnGuardar.setIcon(new ImageIcon(FrmEquipo.class.getResource("/images/guardar24px.png")));
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setBorder(null);
		btnGuardar.setBackground(Pintar.PRIMARIO.darker());
		btnGuardar.setBounds(161, 170, 100, 30);
		getContentPane().add(btnGuardar);
		
		sptxtCodigo = new JSeparator();
		sptxtCodigo.setBackground(Color.WHITE);
		sptxtCodigo.setForeground(Color.WHITE);
		sptxtCodigo.setBounds(40, 80, 230, 5);
		getContentPane().add(sptxtCodigo);
		
		sptxtPrecio = new JSeparator();
		sptxtPrecio.setForeground(Color.WHITE);
		sptxtPrecio.setBackground(Color.WHITE);
		sptxtPrecio.setBounds(40, 130, 230, 5);
		getContentPane().add(sptxtPrecio);
		
		sptxtNombre = new JSeparator();
		sptxtNombre.setForeground(Color.WHITE);
		sptxtNombre.setBackground(Color.WHITE);
		sptxtNombre.setBounds(350, 80, 230, 5);
		getContentPane().add(sptxtNombre);
		
		sptxtStock = new JSeparator();
		sptxtStock.setForeground(Color.WHITE);
		sptxtStock.setBackground(Color.WHITE);
		sptxtStock.setBounds(350, 130, 230, 5);
		getContentPane().add(sptxtStock);
		
		btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new EventoDeAccion());
		btnReporte.addMouseListener(new EventoDeMouse());
		btnReporte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReporte.setIcon(new ImageIcon(FrmEquipo.class.getResource("/images/reporte_equipos24px.png")));
		btnReporte.setForeground(Color.WHITE);
		btnReporte.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnReporte.setBorder(null);
		btnReporte.setBackground(new Color(8, 46, 84));
		btnReporte.setBounds(24, 170, 100, 30);
		getContentPane().add(btnReporte);
		
		scpEquipos = new JScrollPane();
		scpEquipos.setBorder(new LineBorder(Color.WHITE, 2));
		scpEquipos.setBounds(24, 206, 580, 156);
		getContentPane().add(scpEquipos);
		
		tbEquipos = new JTable();
		tbEquipos.addMouseListener(new EventoDeMouse());
		tbEquipos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tbEquipos.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		tbEquipos.getTableHeader().setBackground(Pintar.PRIMARIO);
		tbEquipos.getTableHeader().setForeground(Pintar.BLANCO);
		tbEquipos.getTableHeader().setPreferredSize(new Dimension(scpEquipos.getWidth(), 30));
		tbEquipos.setRowHeight(25);
		tbEquipos.setFont(new Font("SansSerif", Font.BOLD, 11));
		tbEquipos.setGridColor(Pintar.PRIMARIO.brighter());
		tbEquipos.setShowGrid(true);
		tbEquipos.setShowVerticalLines(false);
		tbEquipos.setShowHorizontalLines(true);
		tbEquipos.setDefaultRenderer(Object.class, new CellRenderTable());
		tbEquipos.setSelectionBackground(Pintar.PRIMARIO.brighter());
		tbEquipos.setSelectionForeground(Pintar.BLANCO);
		scpEquipos.setViewportView(tbEquipos);
		
		modelotabla = new DefaultTableModel();
		
		modelotabla.setColumnIdentifiers(new Object[] {
			"ID",
			"Código",
			"Nombre",
			"Precio",
			"Stock"
		});
		
		tbEquipos.setModel(modelotabla);
		
		ajustarAnchoColumnasTbEquipos();
		listarEquipos();
		generarCodigoEquipo();

	}
	
	//--------------- clases DAO
	EquipoDAO equipoDAO = new EquipoDAO();
	
	
	//--------------- metodos de CRUD
	
	private void actionPerformedGuardar() {
		
		if(validarFormulario()) {
			
			Equipo equipo = new Equipo();
			
			equipo.setCodigoequipo(txtCodigo.getText().trim());
			equipo.setNombreequipo(txtNombre.getText().trim());
			equipo.setPrecioequipo(Double.parseDouble(txtPrecio.getText().trim()));
			equipo.setStockequipo(Integer.parseInt(txtStock.getText().trim()));
			
			equipoDAO.guardarEquipo(equipo);
			
			listarEquipos();
			
			limpiarFormulario();
			
		}
		
	}
	
	private void actionPerformedActualizar() {
		
		if(idequipo != 0) {
			
			if(validarFormulario()) {
				
				int opcion = Alerta.confirmar(FrmEquipo.this, "¿Desea actualizar el registro", 
						"Aviso de actualización", Alerta.DESICION);
				
				if(opcion == 0) {
					
					Equipo equipo = new Equipo();
					
					equipo.setIdequipo(idequipo);
					equipo.setCodigoequipo(txtCodigo.getText().trim());
					equipo.setNombreequipo(txtNombre.getText().trim());
					equipo.setPrecioequipo(Double.parseDouble(txtPrecio.getText().trim()));
					equipo.setStockequipo(Integer.parseInt(txtStock.getText().trim()));
					
					equipoDAO.actualizarEquipo(equipo);
					
					listarEquipos();
					
					limpiarFormulario();
					
				} else if(opcion == 1) {
					limpiarFormulario();
				}
				
			}
			
		} else {
			Alerta.mensaje(FrmEquipo.this, "Debe seleccionar un registro de la tabla",
					"Aviso de selección de registro", Alerta.ADVERTENCIA);
		}
		
	}
	
	private void actionPerformedEliminar() {
		
		if(idequipo != 0) {
			
			int opcion = Alerta.confirmar(FrmEquipo.this, "¿Desea eliminar el registro", 
					"Aviso de eliminación", Alerta.DESICION);
			
			if(opcion == 0) {
				
				Equipo equipo = new Equipo();
				
				equipo.setIdequipo(idequipo);
				
				equipoDAO.eliminarEquipo(equipo);
				
				listarEquipos();
				
				limpiarFormulario();
				
			} else if(opcion == 1) {
				limpiarFormulario();
			}
			
		} else {
			Alerta.mensaje(FrmEquipo.this, "Debe seleccionar un registro de la tabla",
					"Aviso de selección de registro", Alerta.ADVERTENCIA);
		}
		
	}
	
	private void actionPerformedBtnReporteEquipos() {

		try {
			
			Conexion conexion = new Conexion();

			String urlreporte = "src/reportes/reporte_equipos.jasper";
			
			JasperPrint jp=JasperFillManager.fillReport(urlreporte,null,conexion.conectar());

			JasperViewer view = new JasperViewer(jp, false);
			
			view.setTitle("REPORTE DE EQUIPOS");
			view.setExtendedState(JFrame.MAXIMIZED_BOTH);
			view.setVisible(true);
			
			conexion.conectar().close();
		} catch (JRException | SQLException ex) {
			
			ex.printStackTrace();
		}
		
	}
	
	
	//--------------- metodos de usuario
	
	private void obtenerDatosTabla() {
		btnGuardar.setEnabled(false);
		txtCodigo.setForeground(Pintar.BLANCO);
		txtNombre.setForeground(Pintar.BLANCO);
		txtPrecio.setForeground(Pintar.BLANCO);
		txtStock.setForeground(Pintar.BLANCO);
		
		int filaseleccionada = tbEquipos.getSelectedRow();
		
		if(filaseleccionada > -1) {
			
			idequipo = (int) tbEquipos.getValueAt(filaseleccionada, 0);
			
			Equipo equipo = buscarEquipo(idequipo);
			
			if(equipo != null) {
				
				txtCodigo.setText(equipo.getCodigoequipo());
				txtNombre.setText(equipo.getNombreequipo());
				txtPrecio.setText(String.valueOf(equipo.getPrecioequipo()));
				txtStock.setText(String.valueOf(equipo.getStockequipo()));
				
			} else {
				Alerta.mensaje(FrmEquipo.this, "No se encontró el equipo", "¡Error! Equipo", Alerta.ERROR);
			}

		}
	}
	
	private void listarEquipos() {
		
		modelotabla.setRowCount(0);
		
		List<Equipo> lstEquipos = equipoDAO.listarEquipos();
		
		for(Equipo equipo: lstEquipos) {
			
			modelotabla.addRow(new Object[] {
					equipo.getIdequipo(),
					equipo.getCodigoequipo(),
					equipo.getNombreequipo(),
					equipo.getPrecioequipo(),
					equipo.getStockequipo()
			});
			
		}
		
	}
	
	
	private Equipo buscarEquipo(int codigo) {
		Equipo equipo = new Equipo();
		
		equipo.setIdequipo(codigo);
		
		return equipoDAO.buscarEquipoPorId(equipo);
	}
	
	private void generarCodigoEquipo() {
		
		String codigoequipo = equipoDAO.generarCodigoEquipo();
		
		txtCodigo.setText(codigoequipo);
		
	}
	
	private void limpiarFormulario() {
		
		
		txtNombre.setForeground(Pintar.GRIS.brighter());
		txtNombre.setText("Ingrese nombre");
		
		txtPrecio.setForeground(Pintar.GRIS.brighter());
		txtPrecio.setText("Ingrese precio");
		
		txtStock.setForeground(Pintar.GRIS.brighter());
		txtStock.setText("Ingrese stock");
		
		btnGuardar.setEnabled(true);
		tbEquipos.clearSelection();
		
		idequipo = 0;
		
		generarCodigoEquipo();
	}
	
	private boolean validarFormulario() {
		
		if(txtCodigo.getText().trim().equals("") || txtCodigo.getText().trim().equalsIgnoreCase("Ingrese código")) {
			Alerta.mensaje(FrmEquipo.this, "Debe ingresar código", "Aviso de código",
					Alerta.ADVERTENCIA);
			return false;
		}
		if(txtNombre.getText().trim().equals("") || txtNombre.getText().trim().equalsIgnoreCase("Ingrese nombre")) {
			Alerta.mensaje(FrmEquipo.this, "Debe ingresar nombre", "Aviso de nombre",
					Alerta.ADVERTENCIA);
			return false;
		}
		if(txtPrecio.getText().trim().equals("") || txtPrecio.getText().trim().equalsIgnoreCase("Ingrese precio")) {
			Alerta.mensaje(FrmEquipo.this, "Debe ingresar precio", "Aviso de precio",
					Alerta.ADVERTENCIA);
			return false;
		}
		if(txtStock.getText().trim().equals("") || txtStock.getText().trim().equalsIgnoreCase("Ingrese stock")) {
			Alerta.mensaje(FrmEquipo.this, "Debe ingresar stock", "Aviso de stock",
					Alerta.ADVERTENCIA);
			return false;
		}
		
		return true;
		
	}
	
	private void ajustarAnchoColumnasTbEquipos() {
		TableColumnModel modelocolumna = tbEquipos.getColumnModel();
		
		modelocolumna.getColumn(0).setPreferredWidth(anchoColumnaTbEquipos(10)); // id
		modelocolumna.getColumn(1).setPreferredWidth(anchoColumnaTbEquipos(20)); // codigo
		modelocolumna.getColumn(2).setPreferredWidth(anchoColumnaTbEquipos(40)); // nombre
		modelocolumna.getColumn(3).setPreferredWidth(anchoColumnaTbEquipos(15)); // precio
		modelocolumna.getColumn(4).setPreferredWidth(anchoColumnaTbEquipos(15)); // stock
		
	}
	
	private int anchoColumnaTbEquipos(int porcentaje) {
		return porcentaje * scpEquipos.getWidth() / 100;
	}
	
	private void establecerImagenLabel(JLabel label, String rutaimg) {
		
		ImageIcon icono = new ImageIcon(rutaimg);
		Icon imgicono = new ImageIcon(icono.getImage().getScaledInstance(label.getWidth(), label.getHeight(), 
				Image.SCALE_DEFAULT));
		
		label.setIcon(imgicono);
		
		this.repaint();
	}
	
	//--------------- metodos de la barra de titulo
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
	
	
	//--------------- metodos de los campos de texto
	private void focusGainedTextField(JSeparator separator, JTextField txt, String texto) {
		separator.setBackground(Pintar.VERDE.brighter());
		separator.setForeground(Pintar.VERDE.brighter());
		
		if(txt.getText().trim().equalsIgnoreCase(texto)) {
			txt.setText("");
			txt.setForeground(Pintar.BLANCO);
		}
	}
	
	private void focusLostTextField(JSeparator separator, JTextField txt, String texto) {
		separator.setBackground(Pintar.BLANCO);
		separator.setForeground(Pintar.BLANCO);
		
		if(txt.getText().trim().length() == 0) {
			txt.setForeground(Pintar.GRIS.brighter());
			txt.setText(texto);
		}
	}
	
	
	//--------------- metodos de los botones
	private void enteredBoton(JButton boton) {
		boton.setBackground(Pintar.PRIMARIO);
	}
	private void exitedBoton(JButton boton) {
		boton.setBackground(Pintar.PRIMARIO.darker());
	}
	
	//--------------- clase interna para la gestion de eventos de accion ActionEvent
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
				actionPerformedBtnReporteEquipos();
			}

		}
	}
	
	//--------------- clase interna para la gestion de eventos de mouse MouseEvent
	private class EventoDeMouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == lblSalir) {
				clickedLblSalir();
			}
			if(e.getSource() == lblMinimizar) {
				clickedLblMinimizar();
			}
			if(e.getSource() == tbEquipos) {
				obtenerDatosTabla();
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource() == panelBarraTitulo) {
				pressedBarraTitulo(e);
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(e.getSource() == panelBarraTitulo) {
				releasedBarraTitulo();
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() == lblMinimizar) {
				enteredLblMinimizar();
			}
			if(e.getSource() == lblSalir) {
				enteredLblSalir();
			}
			if(e.getSource() == btnGuardar) {
				enteredBoton(btnGuardar);
			}
			if(e.getSource() == btnActualizar) {
				enteredBoton(btnActualizar);
			}
			if(e.getSource() == btnEliminar) {
				enteredBoton(btnEliminar);
			}
			if(e.getSource() == btnLimpiar) {
				enteredBoton(btnLimpiar);
			}
			if(e.getSource() == btnReporte) {
				enteredBoton(btnReporte);
			}
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == lblMinimizar) {
				exitedLblMinimizar();
			}
			if(e.getSource() == lblSalir) {
				exitedLblSalir();
			}
			if(e.getSource() == btnGuardar) {
				exitedBoton(btnGuardar);
			}
			if(e.getSource() == btnActualizar) {
				exitedBoton(btnActualizar);
			}
			if(e.getSource() == btnEliminar) {
				exitedBoton(btnEliminar);
			}
			if(e.getSource() == btnLimpiar) {
				exitedBoton(btnLimpiar);
			}
			if(e.getSource() == btnReporte) {
				exitedBoton(btnReporte);
			}
			
		}
		
		
	}
	
	//--------------- clase interna para la gestion de eventos de movimiento de mouse MouseEvent
	private class EventoDeMouseMovimiento implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			if(e.getSource() == panelBarraTitulo) {
				draggedBarraTitulo(e);
			}
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {}
		
	}
	
	//--------------- clase interna para la gestion de eventos de foco de los elementos FocusEvent
	private class EventoDeFoco implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			if(e.getSource() == txtCodigo) {
				focusGainedTextField(sptxtCodigo, txtCodigo, "Ingrese código");
			}
			if(e.getSource() == txtNombre) {
				focusGainedTextField(sptxtNombre, txtNombre, "Ingrese nombre");
			}
			if(e.getSource() == txtPrecio) {
				focusGainedTextField(sptxtPrecio, txtPrecio, "Ingrese precio");
			}
			if(e.getSource() == txtStock) {
				focusGainedTextField(sptxtStock, txtStock, "Ingrese stock");
			}
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			if(e.getSource() == txtCodigo) {
				focusLostTextField(sptxtCodigo, txtCodigo, "Ingrese código");
			}
			if(e.getSource() == txtNombre) {
				focusLostTextField(sptxtNombre, txtNombre, "Ingrese nombre");
			}
			if(e.getSource() == txtPrecio) {
				focusLostTextField(sptxtPrecio, txtPrecio, "Ingrese precio");
			}
			if(e.getSource() == txtStock) {
				focusLostTextField(sptxtStock, txtStock, "Ingrese stock");
			}
			
		}
		
	}
	
	//--------------- clases interna para modificar el render de la tabla
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
