package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mantenimiento.CargoDAO;
import mantenimiento.UsuarioDAO;
import model.Cargo;
import model.Usuario;
import utils.Alerta;
import utils.Pintar;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Cursor;
import javax.swing.ImageIcon;

public class FrmPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	// variables globales
	Thread hilo;

	private JPanel contentPane;
	private JMenuBar mbMenuBarra;
	private JMenu mSistema;
	private JMenuItem miCambiarCuenta;
	private JMenu mMantenimiento;
	private JMenuItem miClientes;
	private JMenuItem miEquipos;
	private JMenuItem miUsuarios;
	private JMenuItem miCargos;
	private JMenu mVentas;
	private JMenuItem miVenta;
	private JMenu mAcercaDe;
	private JPanel panelFooter;
	private JDesktopPane dpEscritorio;
	private JLabel lblSesionDe;
	private JLabel lblHoraFecha;
	private JMenuItem miSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
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
	public FrmPrincipal() {
		setTitle("Menu Principal");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmPrincipal.class.getResource("/images/apple_white.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 420);

		mbMenuBarra = new JMenuBar();
		mbMenuBarra.setBackground(Pintar.PRIMARIO);
		setJMenuBar(mbMenuBarra);

		mSistema = new JMenu("Sistema");
		mSistema.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/inicio24px.png")));
		mSistema.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mSistema.setForeground(Pintar.BLANCO);
		mSistema.setVisible(false);
		mbMenuBarra.add(mSistema);

		miCambiarCuenta = new JMenuItem("Cambiar Cuenta");
		miCambiarCuenta.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/cambiar_cuenta24px.png")));
		miCambiarCuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		miCambiarCuenta.addActionListener(new EventoDeAccion());
		miCambiarCuenta.setForeground(Pintar.BLANCO);
		miCambiarCuenta.setBackground(Pintar.PRIMARIO);
		miCambiarCuenta.setVisible(false);
		mSistema.add(miCambiarCuenta);

		miSalir = new JMenuItem("Salir");
		miSalir.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/salir24px.png")));
		miSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		miSalir.addActionListener(new EventoDeAccion());
		miSalir.setForeground(Pintar.BLANCO);
		miSalir.setBackground(Pintar.PRIMARIO);
		miSalir.setVisible(false);
		mSistema.add(miSalir);

		mMantenimiento = new JMenu("Mantenimiento");
		mMantenimiento.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/gestion24px.png")));
		mMantenimiento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mMantenimiento.setForeground(Pintar.BLANCO);
		mMantenimiento.setVisible(false);
		mbMenuBarra.add(mMantenimiento);

		miClientes = new JMenuItem("Clientes");
		miClientes.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/personas24px.png")));
		miClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		miClientes.addActionListener(new EventoDeAccion());
		miClientes.setForeground(Pintar.BLANCO);
		miClientes.setBackground(Pintar.PRIMARIO);
		miClientes.setVisible(false);
		mMantenimiento.add(miClientes);

		miEquipos = new JMenuItem("Equipos");
		miEquipos.addActionListener(new EventoDeAccion());
		miEquipos.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/equipos_electronicos24px.png")));
		miEquipos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		miEquipos.setForeground(Pintar.BLANCO);
		miEquipos.setBackground(Pintar.PRIMARIO);
		miEquipos.setVisible(false);
		mMantenimiento.add(miEquipos);

		miUsuarios = new JMenuItem("Usuarios");
		miUsuarios.addActionListener(new EventoDeAccion());
		miUsuarios.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/personas24px.png")));
		miUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		miUsuarios.setForeground(Pintar.BLANCO);
		miUsuarios.setBackground(Pintar.PRIMARIO);
		miUsuarios.setVisible(false);
		mMantenimiento.add(miUsuarios);

		miCargos = new JMenuItem("Cargos");
		miCargos.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/cargo24px.png")));
		miCargos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		miCargos.addActionListener(new EventoDeAccion());
		miCargos.setForeground(Pintar.BLANCO);
		miCargos.setBackground(Pintar.PRIMARIO);
		miCargos.setVisible(false);
		mMantenimiento.add(miCargos);

		mVentas = new JMenu("Ventas");
		mVentas.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/punto_venta24px.png")));
		mVentas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mVentas.setForeground(Pintar.BLANCO);
		mVentas.setVisible(false);
		mbMenuBarra.add(mVentas);

		miVenta = new JMenuItem("Realizar venta");
		miVenta.addActionListener(new EventoDeAccion());
		miVenta.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/punto_venta24px.png")));
		miVenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		miVenta.setForeground(Pintar.BLANCO);
		miVenta.setBackground(Pintar.PRIMARIO);
		miVenta.setVisible(false);
		mVentas.add(miVenta);
		
		miVentasRealizadas = new JMenuItem("Ventas realizadas");
		miVentasRealizadas.addActionListener(new EventoDeAccion());
		miVentasRealizadas.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/punto_venta24px.png")));
		miVentasRealizadas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		miVentasRealizadas.setForeground(Pintar.BLANCO);
		miVentasRealizadas.setBackground(Pintar.PRIMARIO);
		mVentas.add(miVentasRealizadas);

		
		mReportes = new JMenu("Reportes");
		mReportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mReportes.setForeground(Color.WHITE);
		mReportes.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/reportes24px.png")));
		mbMenuBarra.add(mReportes);
		
		miCincoEquiposMasVendidos = new JMenuItem("Cinco equipos más vendidos");
		miCincoEquiposMasVendidos.addActionListener(new EventoDeAccion());
		miCincoEquiposMasVendidos.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/reportes24px.png")));
		miCincoEquiposMasVendidos.setCursor(new Cursor(Cursor.HAND_CURSOR));
		miCincoEquiposMasVendidos.setForeground(Pintar.BLANCO);
		miCincoEquiposMasVendidos.setBackground(Pintar.PRIMARIO);
		mReportes.add(miCincoEquiposMasVendidos);
		
		miCincoVentasMayorMonto = new JMenuItem("Cinco ventas con mayor monto");
		miCincoVentasMayorMonto.addActionListener(new EventoDeAccion());
		miCincoVentasMayorMonto.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/reportes24px.png")));
		miCincoVentasMayorMonto.setCursor(new Cursor(Cursor.HAND_CURSOR));
		miCincoVentasMayorMonto.setForeground(Color.WHITE);
		miCincoVentasMayorMonto.setBackground(Pintar.PRIMARIO);
		mReportes.add(miCincoVentasMayorMonto);
		
		
		mAcercaDe = new JMenu("AcercaDe");
		mAcercaDe.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/programing24px.png")));
		mAcercaDe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mAcercaDe.setForeground(Pintar.BLANCO);
		mAcercaDe.setVisible(false);
		mbMenuBarra.add(mAcercaDe);
		
		miCreadoPor = new JMenuItem("Creado Por");
		miCreadoPor.addActionListener(new EventoDeAccion());
		miCreadoPor.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/images/programing24px.png")));
		miCreadoPor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		miCreadoPor.setForeground(Pintar.BLANCO);
		miCreadoPor.setBackground(Pintar.PRIMARIO);
		miCreadoPor.setVisible(false);
		mAcercaDe.add(miCreadoPor);

		contentPane = new JPanel();
		contentPane.setBackground(Pintar.PRIMARIO);
		contentPane.setBorder(new EmptyBorder(3, 3, 3, 3));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		dpEscritorio = new JDesktopPane();
		dpEscritorio.setBackground(Pintar.PRIMARIO.brighter().brighter());
		contentPane.add(dpEscritorio, BorderLayout.CENTER);

		panelFooter = new JPanel();
		panelFooter.setBackground(Pintar.PRIMARIO);
		contentPane.add(panelFooter, BorderLayout.SOUTH);
		panelFooter.setLayout(new BorderLayout(0, 0));

		lblSesionDe = new JLabel("Sesión de");
		lblSesionDe.setForeground(Pintar.BLANCO);
		lblSesionDe.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelFooter.add(lblSesionDe, BorderLayout.WEST);

		lblHoraFecha = new JLabel("Hora: 00:00:00    Fecha: 01/01/2023");
		lblHoraFecha.setForeground(Pintar.BLANCO);
		lblHoraFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelFooter.add(lblHoraFecha, BorderLayout.EAST);

		validarCargaMenu();
		cargarSesionDe();

		hilo = new ClaseHoraFecha();
		hilo.start();

	}

	//---- clases DAO
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	CargoDAO cargoDAO = new CargoDAO();
	private JMenuItem miCreadoPor;
	private JMenuItem miCincoEquiposMasVendidos;
	private JMenu mReportes;
	private JMenuItem miCincoVentasMayorMonto;
	private JMenuItem miVentasRealizadas;

	//----------- metodo para validar de que usuario es la sesion
	private void cargarSesionDe() {

		int idusuario = FrmLogueo.idusuario;

		Usuario objUsuario = new Usuario();

		objUsuario.setIdusuario(idusuario);

		Usuario objUsuarioBuscado = usuarioDAO.buscarUsuarioPorId(objUsuario);

		String nombreusuario = objUsuarioBuscado.getNombreusuario();
		String apellidousuario = objUsuarioBuscado.getApellidousuario();

		lblSesionDe.setText("Sesión de " + nombreusuario + " " + apellidousuario);

	}

	//---------- metodo para cargar formulario si el usuario es administrador
	private void cargarParaAdministrador() {
		mSistema.setVisible(true);
		miCambiarCuenta.setVisible(true);
		miSalir.setVisible(true);

		mMantenimiento.setVisible(true);
		miClientes.setVisible(true);
		miEquipos.setVisible(true);
		miUsuarios.setVisible(true);
		miCargos.setVisible(true);

		mVentas.setVisible(true);
		miVenta.setVisible(true);

		mAcercaDe.setVisible(true);
		miCreadoPor.setVisible(true);
	}
	
	//-------------metodo para cargar formulario si el usuario es vendedor
	private void cargarParaVendedor() {
		mSistema.setVisible(true);
		miCambiarCuenta.setVisible(true);
		miSalir.setVisible(true);

		mMantenimiento.setVisible(true);
		miClientes.setVisible(true);
		miEquipos.setVisible(false);
		miUsuarios.setVisible(false);
		miCargos.setVisible(false);

		mVentas.setVisible(true);
		miVenta.setVisible(true);

		mAcercaDe.setVisible(true);
		miCreadoPor.setVisible(true);

	}
	
	//------------- metodo para validar que tipo de carga realizará el formulario
	private void validarCargaMenu() {

		try {
			int idcargo = FrmLogueo.idcargo;

			Cargo objCargo = new Cargo();
			
			objCargo.setIdcargo(idcargo);

			Cargo objCargoBuscado = cargoDAO.buscarCargoPorId(objCargo);

			String tipocargo = objCargoBuscado.getTipocargo();

			if (tipocargo.equalsIgnoreCase("Administrador")) {

				cargarParaAdministrador();

			} else if (tipocargo.equalsIgnoreCase("Vendedor")) {

				cargarParaVendedor();

			}
		} catch (Exception ex) {
			System.out.println("Debes iniciar desde la vista logueo");
		}

	}
	
	
	//--------------- metodo para la barra de menus
	private void actionPerformedSalir() {
		dispose();
		System.exit(0);
	}

	private void actionPerformedCambiarCuenta() {
		FrmLogueo frmlogueo = new FrmLogueo();

		frmlogueo.setVisible(true);

		dispose();

	}

	private void actionPerformedMostrarJInternalFrame(JInternalFrame frame) {

		frame.setLocation((dpEscritorio.getWidth() / 2) - (frame.getWidth() / 2),
				(dpEscritorio.getHeight() / 2) - frame.getHeight() / 2);

		dpEscritorio.add(frame);

		frame.setVisible(true);
	}


	// -------------------- clase interna para los eventos de accion ActionEvent
	private class EventoDeAccion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == miSalir) {
				actionPerformedSalir();
			}

			if (e.getSource() == miCambiarCuenta) {
				actionPerformedCambiarCuenta();
			}

			if (e.getSource() == miClientes) {
				FrmCliente frmcliente = new FrmCliente();
				actionPerformedMostrarJInternalFrame(frmcliente);
			}
			
			if(e.getSource() == miEquipos) {
				FrmEquipo frmequipo = new FrmEquipo();
				actionPerformedMostrarJInternalFrame(frmequipo);
			}

			if (e.getSource() == miUsuarios) {
				FrmUsuario frmusuario = new FrmUsuario();
				actionPerformedMostrarJInternalFrame(frmusuario);
			}
			if (e.getSource() == miCargos) {
				FrmCargo frmcargo = new FrmCargo();
				actionPerformedMostrarJInternalFrame(frmcargo);
			}
			if(e.getSource() == miVenta) {
				FrmVenta frmventa = new FrmVenta();
				actionPerformedMostrarJInternalFrame(frmventa);
			}
			if(e.getSource() == miCincoEquiposMasVendidos) {
				FrmEquiposMasVendidos frmequiposmasvendidos = new FrmEquiposMasVendidos();
				actionPerformedMostrarJInternalFrame(frmequiposmasvendidos);
			}
			if(e.getSource() == miCincoVentasMayorMonto) {
				FrmVentasMayorMonto frmventasmayormonto = new FrmVentasMayorMonto();
				actionPerformedMostrarJInternalFrame(frmventasmayormonto);
			}
			if(e.getSource() == miVentasRealizadas) {
				FrmConsultaVentas frmconsultaventas = new FrmConsultaVentas();
				actionPerformedMostrarJInternalFrame(frmconsultaventas);
			}
			
			if(e.getSource() == miCreadoPor) {
				Alerta.mensaje(
						FrmPrincipal.this,
						"Jerson Medina Lezama\n© Todos los derechos reservados", 
						"CREADORES", 
						"Images/images/programing50px.png");
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

					String segundo = String.valueOf(calendario.get(Calendar.SECOND));
					if (Integer.parseInt(segundo) < 10) {
						segundo = "0" + segundo;
					}

					lblHoraFecha.setText("Hora: " + hora + ":" + minuto + ":" + segundo + "     Fecha: " + dia + "/"
							+ mes + "/" + anio);
					
					Thread.sleep(1000);

				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}

		}

	}
}
