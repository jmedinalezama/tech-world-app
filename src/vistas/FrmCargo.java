package vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.JTextField;

import utils.Alerta;
import utils.Pintar;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import mantenimiento.CargoDAO;
import mantenimiento.UsuarioDAO;
import model.Cargo;
import model.Usuario;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FrmCargo extends JInternalFrame {

	
	private static final long serialVersionUID = 1L;
	
	// variables globales
	private int xMouse, yMouse;
	private DefaultTableModel modelotabla;
	private static int idcargo;

	private JTextField txtCargo;
	private JScrollPane scpCargos;
	private JTable tbCargos;
	private JButton btnGuardar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnLimpiar;
	private JPanel panelBarraTitulo;
	private JLabel lblSalir;
	private JLabel lblIconFrame;
	private JLabel lblMinimizar;
	private JLabel lblCargos;
	private JSeparator sptxtCargo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmCargo frame = new FrmCargo();
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
	public FrmCargo() {
		setBorder(new LineBorder(Color.WHITE, 4));
		getContentPane().setBackground(Pintar.PRIMARIO.brighter());
		setFrameIcon(new ImageIcon(FrmCargo.class.getResource("/images/cargo24px.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle("Cargos");
		setBounds(100, 100, 600, 287);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		getContentPane().setLayout(null);

		panelBarraTitulo = new JPanel();
		panelBarraTitulo.addMouseListener(new EventoDeMouse());
		panelBarraTitulo.addMouseMotionListener(new EventoDeMouseMovimiento());
		panelBarraTitulo.setLayout(null);
		panelBarraTitulo.setBackground(new Color(17, 94, 171));
		panelBarraTitulo.setBounds(0, 0, 592, 30);
		getContentPane().add(panelBarraTitulo);

		lblSalir = new JLabel("X");
		lblSalir.addMouseListener(new EventoDeMouse());
		lblSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblSalir.setOpaque(true);
		lblSalir.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalir.setForeground(Color.WHITE);
		lblSalir.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSalir.setBackground(new Color(17, 94, 171));
		lblSalir.setBounds(562, 0, 30, 30);
		panelBarraTitulo.add(lblSalir);

		lblIconFrame = new JLabel("");
		lblIconFrame.setBounds(0, 0, 30, 30);
		establecerImagenLabel(lblIconFrame, "Images/images/cargo24px.png");
		panelBarraTitulo.add(lblIconFrame);

		lblMinimizar = new JLabel("-");
		lblMinimizar.addMouseListener(new EventoDeMouse());
		lblMinimizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblMinimizar.setOpaque(true);
		lblMinimizar.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimizar.setForeground(Color.WHITE);
		lblMinimizar.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblMinimizar.setBackground(new Color(17, 94, 171));
		lblMinimizar.setBounds(533, 0, 30, 30);
		panelBarraTitulo.add(lblMinimizar);

		lblCargos = new JLabel("Cargos");
		lblCargos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargos.setForeground(Color.WHITE);
		lblCargos.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblCargos.setBounds(59, 0, 454, 30);
		panelBarraTitulo.add(lblCargos);

		txtCargo = new JTextField("Ingrese cargo");
		txtCargo.addFocusListener(new EventoDeFoco());
		txtCargo.setOpaque(false);
		txtCargo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCargo.setForeground(Pintar.GRIS.brighter());
		txtCargo.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtCargo.setCaretColor(Color.WHITE);
		txtCargo.setBorder(null);
		txtCargo.setBounds(35, 50, 250, 30);
		getContentPane().add(txtCargo);
		txtCargo.setColumns(10);

		sptxtCargo = new JSeparator();
		sptxtCargo.setForeground(Pintar.BLANCO);
		sptxtCargo.setBackground(Pintar.BLANCO);
		sptxtCargo.setBounds(35, 80, 250, 5);
		getContentPane().add(sptxtCargo);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new EventoDeMouse());
		btnGuardar.addActionListener(new EventoDeAccion());
		btnGuardar.setBackground(Pintar.PRIMARIO.darker());
		btnGuardar.setIcon(new ImageIcon(FrmCargo.class.getResource("/images/guardar24px.png")));
		btnGuardar.setBorder(null);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setBounds(324, 48, 100, 30);
		getContentPane().add(btnGuardar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addMouseListener(new EventoDeMouse());
		btnActualizar.addActionListener(new EventoDeAccion());
		btnActualizar.setIcon(new ImageIcon(FrmCargo.class.getResource("/images/actualizar24px.png")));
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnActualizar.setBorder(null);
		btnActualizar.setBackground(Pintar.PRIMARIO.darker());
		btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnActualizar.setBounds(449, 48, 113, 30);
		getContentPane().add(btnActualizar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new EventoDeMouse());
		btnEliminar.addActionListener(new EventoDeAccion());
		btnEliminar.setBackground(Pintar.PRIMARIO.darker());
		btnEliminar.setBorder(null);
		btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setIcon(new ImageIcon(FrmCargo.class.getResource("/images/eliminar24px.png")));
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setBounds(324, 104, 100, 30);
		getContentPane().add(btnEliminar);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new EventoDeMouse());
		btnLimpiar.addActionListener(new EventoDeAccion());
		btnLimpiar.setIcon(new ImageIcon(FrmCargo.class.getResource("/images/limpiar24px.png")));
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnLimpiar.setBorder(null);
		btnLimpiar.setBackground(Pintar.PRIMARIO.darker());
		btnLimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpiar.setBounds(449, 104, 113, 30);
		getContentPane().add(btnLimpiar);

		scpCargos = new JScrollPane();
		scpCargos.setBackground(Pintar.BLANCO);
		scpCargos.setBorder(new LineBorder(Color.WHITE, 2));
		scpCargos.setBounds(35, 104, 250, 141);
		getContentPane().add(scpCargos);

		tbCargos = new JTable();
		tbCargos.addMouseListener(new EventoDeMouse());
		tbCargos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tbCargos.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		tbCargos.getTableHeader().setBackground(Pintar.PRIMARIO);
		tbCargos.getTableHeader().setForeground(Pintar.BLANCO);
		tbCargos.getTableHeader().setPreferredSize(new Dimension(scpCargos.getWidth(), 30));
		tbCargos.setRowHeight(25);
		tbCargos.setFont(new Font("SansSerif", Font.BOLD, 11));
		tbCargos.setGridColor(Pintar.PRIMARIO.brighter());
		tbCargos.setShowGrid(true);
		tbCargos.setShowVerticalLines(false);
		tbCargos.setShowHorizontalLines(true);
		tbCargos.setDefaultRenderer(Object.class, new CellRenderTable());
		tbCargos.setSelectionBackground(Pintar.PRIMARIO.brighter());
		tbCargos.setSelectionForeground(Pintar.BLANCO);

		scpCargos.setViewportView(tbCargos);

		modelotabla = new DefaultTableModel();

		modelotabla.setColumnIdentifiers(new Object[] { "ID", "Tipo" });

		tbCargos.setModel(modelotabla);

		ajustarAnchoColumnasTbCargo();
		listarCargos();

	}

	// ---------- clases DAO
	CargoDAO cargoDAO = new CargoDAO();
	UsuarioDAO usuarioDAO = new UsuarioDAO();

	// ----------- metodos de CRUD
	private void actionPerformedGuardar() {

		if (validarFormulario()) {

			Cargo cargo = new Cargo();

			String tipocargo = txtCargo.getText().trim();

			cargo.setTipocargo(tipocargo);

			cargoDAO.guardarCargo(cargo);

			listarCargos();

			limpiarFormulario();

		}
	}

	private void actionPerformedActualizar() {

		if (idcargo != 0) {

			if (validarFormulario()) {

				int opcion = Alerta.confirmar(FrmCargo.this, "¿Desea actualizar el registro?",
						"Confirmación de actualización", Alerta.DESICION);

				if (opcion == 0) {

					Cargo cargo = new Cargo();

					String tipocargo = txtCargo.getText().trim();

					cargo.setIdcargo(idcargo);
					cargo.setTipocargo(tipocargo);

					cargoDAO.actualizarCargo(cargo);

					listarCargos();

					limpiarFormulario();

				} else if (opcion == 1) {
					limpiarFormulario();
				}

			}

		} else {
			Alerta.mensaje(FrmCargo.this, "Seleccione un registro de la tabla", "Aviso",
					Alerta.ADVERTENCIA);
		}
	}

	private void actionPerformedEliminar() {

		if (idcargo != 0) {

			Usuario usuario = buscarUsuarioPorIdCargo(idcargo);

			if (usuario == null) {

				int opcion = Alerta.confirmar(FrmCargo.this, "¿Desea eliminar el registro?",
						"Confirmación de eliminación", Alerta.DESICION);

				if (opcion == 0) {

					Cargo cargo = new Cargo();

					cargo.setIdcargo(idcargo);

					cargoDAO.eliminarCargo(cargo);

					listarCargos();

					limpiarFormulario();

				} else if (opcion == 1) {
					limpiarFormulario();
				}

			} else {

				Alerta.mensaje(FrmCargo.this,
						"El cargo no puede ser eliminado.\nHay al menos un usuario asignado al cargo",
						"Información sobre el cargo", Alerta.INFORMACION);

			}

		} else {
			Alerta.mensaje(FrmCargo.this, "Seleccione un registro de la tabla", "Aviso",
					Alerta.ADVERTENCIA);
		}
	}

	// ----------------------- metodos de usuario
	private void listarCargos() {
		modelotabla.setRowCount(0);

		List<Cargo> lstCargos = cargoDAO.listarCargos();

		for (Cargo cargo : lstCargos) {

			modelotabla.addRow(new Object[] { cargo.getIdcargo(), cargo.getTipocargo() });
		}
	}

	private void obtenerDatosTabla() {

		btnGuardar.setEnabled(false);
		txtCargo.setForeground(Pintar.BLANCO);

		int filaseleccionada = tbCargos.getSelectedRow();

		if (filaseleccionada > -1) {

			idcargo = (int) tbCargos.getValueAt(filaseleccionada, 0);

			Cargo cargo = buscarCargo(idcargo);

			if (cargo != null) {

				txtCargo.setText(cargo.getTipocargo());

			} else {

				Alerta.mensaje(FrmCargo.this, "El cargo no se encontró", "¡Error! Contacte con TI",
						Alerta.ERROR);
			}
		}
	}

	private Cargo buscarCargo(int codigo) {

		Cargo cargo = new Cargo();

		cargo.setIdcargo(codigo);

		return cargoDAO.buscarCargoPorId(cargo);

	}

	private Usuario buscarUsuarioPorIdCargo(int codigo) {

		Usuario usuario = new Usuario();

		usuario.setIdcargo(codigo);

		return usuarioDAO.buscarUsuarioPorIdCargo(usuario);

	}

	private boolean validarFormulario() {
		if (txtCargo.getText().trim().equals("") || txtCargo.getText().trim().equalsIgnoreCase("Ingrese cargo")) {
			Alerta.mensaje(FrmCargo.this, "Debe ingresar un cargo", "Aviso de cargo",
					Alerta.ADVERTENCIA);
			return false;
		}
		return true;
	}

	private void limpiarFormulario() {
		txtCargo.setForeground(Pintar.GRIS.brighter());
		txtCargo.setText("Ingrese cargo");
		btnGuardar.setEnabled(true);
		tbCargos.clearSelection();

		idcargo = 0;
	}

	private void ajustarAnchoColumnasTbCargo() {
		TableColumnModel modelocolumna = tbCargos.getColumnModel();

		modelocolumna.getColumn(0).setPreferredWidth(anchoColumnaTbCargo(30)); // id cargo
		modelocolumna.getColumn(1).setPreferredWidth(anchoColumnaTbCargo(70)); // tipo cargo
	}

	private int anchoColumnaTbCargo(int porcentaje) {
		return porcentaje * scpCargos.getWidth() / 100;
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
	private void enteredBtnGuardar(JButton boton) {
		boton.setBackground(Pintar.PRIMARIO);
	}

	private void exitedBtnGuardar(JButton boton) {
		boton.setBackground(Pintar.PRIMARIO.darker());
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

	// ------------------------------ clase interna para la gestion de eventos de
	// Accion ActionEvent
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
			if (e.getSource() == tbCargos) {
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
				enteredBtnGuardar(btnGuardar);
			}
			if (e.getSource() == btnActualizar) {
				enteredBtnGuardar(btnActualizar);
			}
			if (e.getSource() == btnEliminar) {
				enteredBtnGuardar(btnEliminar);
			}
			if (e.getSource() == btnLimpiar) {
				enteredBtnGuardar(btnLimpiar);
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
				exitedBtnGuardar(btnGuardar);
			}
			if (e.getSource() == btnActualizar) {
				exitedBtnGuardar(btnActualizar);
			}
			if (e.getSource() == btnEliminar) {
				exitedBtnGuardar(btnEliminar);
			}
			if (e.getSource() == btnLimpiar) {
				exitedBtnGuardar(btnLimpiar);
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
			if (e.getSource() == txtCargo) {
				focusGainedTextField(sptxtCargo, txtCargo, "Ingrese cargo");
			}

		}

		@Override
		public void focusLost(FocusEvent e) {
			if (e.getSource() == txtCargo) {
				focusLostTextField(sptxtCargo, txtCargo, "Ingrese cargo");
			}

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
