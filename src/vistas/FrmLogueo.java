package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mantenimiento.UsuarioDAO;
import model.Usuario;
import utils.Alerta;
import utils.Pintar;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.SwingConstants;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FrmLogueo extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//variables globales
	public static int idcargo = 0;
	public static int idusuario = 0;
	
	Thread hilo;
	private int xMouse, yMouse;
	private boolean hiloactivo = false;
	
	
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JPanel panelBarraTitulo;
	private JLabel lblSalir;
	private JLabel lblIconFrame;
	private JPanel panelPrincipal;
	private JLabel lblIniciarSesion;
	private JLabel lblImagenPrincipal;
	private JLabel lblCopy;
	private JSeparator sptxtUsuario;
	private JSeparator sptxtPassword;
	private JCheckBox chkMostrar;
	private JLabel lblCarga;
	private JLabel lblPorcentajeCarga;
	private JButton btnIngresar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogueo frame = new FrmLogueo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public FrmLogueo() {
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmLogueo.class.getResource("/images/apple_white.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelBarraTitulo = new JPanel();
		panelBarraTitulo.addMouseMotionListener(new EventoDeMouseMovimiento());
		panelBarraTitulo.addMouseListener(new EventoDeMouse());
		panelBarraTitulo.setBackground(Pintar.PRIMARIO);
		panelBarraTitulo.setBounds(0, 0, 600, 30);
		contentPane.add(panelBarraTitulo);
		panelBarraTitulo.setLayout(null);
		
		lblSalir = new JLabel("X");
		lblSalir.setForeground(Pintar.BLANCO);
		lblSalir.addMouseListener(new EventoDeMouse());
		lblSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSalir.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSalir.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalir.setBounds(570, 0, 30, 30);
		panelBarraTitulo.add(lblSalir);
		
		lblIconFrame = new JLabel("");
		lblIconFrame.setBounds(0, 2, 26, 26);
		establecerImagenLabel(lblIconFrame, "Images/images/apple_white.png");
		panelBarraTitulo.add(lblIconFrame);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Pintar.PRIMARIO);
		panelPrincipal.setBounds(0, 30, 600, 370);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		txtUsuario = new JTextField("Ingrese usuario");
		txtUsuario.setCaretColor(Pintar.BLANCO);
		txtUsuario.addFocusListener(new EventoDeFoco());
		txtUsuario.setBorder(null);
		txtUsuario.setForeground(Pintar.GRIS);
		txtUsuario.setFont(new Font("SansSerif", Font.BOLD, 16));
		txtUsuario.setOpaque(false);
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setBounds(326, 105, 230, 30);
		panelPrincipal.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtPassword = new JPasswordField("Ingrese contraseña");
		txtPassword.setCaretColor(Pintar.BLANCO);
		txtPassword.addFocusListener(new EventoDeFoco());
		txtPassword.setEchoChar((char)0);
		txtPassword.setBorder(null);
		txtPassword.setFont(new Font("SansSerif", Font.BOLD, 16));
		txtPassword.setForeground(Pintar.GRIS);
		txtPassword.setOpaque(false);
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setBounds(326, 170, 230, 30);
		panelPrincipal.add(txtPassword);
		
		lblIniciarSesion = new JLabel("Iniciar Sesión");
		lblIniciarSesion.setForeground(Color.WHITE);
		lblIniciarSesion.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesion.setBounds(0, 25, 600, 40);
		panelPrincipal.add(lblIniciarSesion);
		
		lblImagenPrincipal = new JLabel("");
		lblImagenPrincipal.setBounds(60, 80, 200, 200);
		establecerImagenLabel(lblImagenPrincipal, "Images/images/tech_shop.png");
		panelPrincipal.add(lblImagenPrincipal);
		
		lblCopy = new JLabel("Todos los derechos reservados © 2022-2023");
		lblCopy.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		lblCopy.setForeground(Pintar.BLANCO);
		lblCopy.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopy.setBounds(0, 345, 600, 20);
		panelPrincipal.add(lblCopy);
		
		sptxtUsuario = new JSeparator();
		sptxtUsuario.setForeground(Pintar.BLANCO);
		sptxtUsuario.setBackground(Pintar.BLANCO);
		sptxtUsuario.setBounds(326, 135, 230, 5);
		panelPrincipal.add(sptxtUsuario);
		
		sptxtPassword = new JSeparator();
		sptxtPassword.setForeground(Pintar.BLANCO);
		sptxtPassword.setBackground(Pintar.BLANCO);
		sptxtPassword.setBounds(326, 200, 230, 5);
		panelPrincipal.add(sptxtPassword);
		
		chkMostrar = new JCheckBox("Mostrar");
		chkMostrar.addActionListener(new EventoDeAccion());
		chkMostrar.setForeground(Pintar.BLANCO);
		chkMostrar.setOpaque(false);
		chkMostrar.setFont(new Font("SansSerif", Font.PLAIN, 11));
		chkMostrar.setBounds(493, 207, 63, 23);
		panelPrincipal.add(chkMostrar);
		
		lblCarga = new JLabel("");
		lblCarga.setBounds(282, 282, 35, 35);
		panelPrincipal.add(lblCarga);
		
		lblPorcentajeCarga = new JLabel("0%");
		lblPorcentajeCarga.setVisible(false);
		lblPorcentajeCarga.setForeground(Color.WHITE);
		lblPorcentajeCarga.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPorcentajeCarga.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorcentajeCarga.setBounds(280, 320, 46, 14);
		panelPrincipal.add(lblPorcentajeCarga);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addMouseListener(new EventoDeMouse());
		btnIngresar.addActionListener(new EventoDeAccion());
		btnIngresar.setForeground(Pintar.BLANCO);
		btnIngresar.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnIngresar.setBackground(Pintar.PRIMARIO.darker());
		btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnIngresar.setBorder(null);
		btnIngresar.setBounds(371, 252, 143, 30);
		panelPrincipal.add(btnIngresar);
		
		
		
	}
	
	//clases DAO
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	
	
	//------------------------------ metodos de usuario
	private void validarLogueo() {
		
		if(validarFormulario()) {
			String usuario = txtUsuario.getText().trim();
			String password = String.valueOf(txtPassword.getPassword()).trim();
			
			Usuario objUsuario = new Usuario();
			
			objUsuario.setUserusuario(usuario);
			objUsuario.setPasswordusuario(password);
			
			Usuario objUsuarioBuscado = usuarioDAO.buscarUsuarioPorUserAndPassword(objUsuario);
			
			if(objUsuarioBuscado.getUserusuario() != null && objUsuarioBuscado.getPasswordusuario() != null) {
				
				idusuario = objUsuarioBuscado.getIdusuario();
				idcargo = objUsuarioBuscado.getIdcargo();
				
				hilo = new ClaseHilo();
				hilo.start();
				
			} else {
								
				Alerta.mensaje(
						FrmLogueo.this, 
						"Credenciales incorrectas", 
						"¡Error! Inicio Sesión", 
						Alerta.ERROR
				);
				
			}

		}
	}
	
	private boolean validarFormulario() {
		
		String usuario = txtUsuario.getText().trim();
		String password = String.valueOf(txtPassword.getPassword()).trim();
		
		if(usuario.equalsIgnoreCase("Ingrese usuario") || usuario.length() == 0) {
			
			Alerta.mensaje(
					FrmLogueo.this, 
					"Debe ingresar un usuario", 
					"Aviso de usuario", 
					Alerta.ADVERTENCIA
			);
			
			return false;
			
		}
		
		if(password.equalsIgnoreCase("Ingrese contraseña") || password.length() == 0) {
			
			Alerta.mensaje(
					FrmLogueo.this, 
					"Debe ingresar una contraseña", 
					"Aviso de contraseña", 
					Alerta.ADVERTENCIA
			);
			return false;
		}
		
		return true;
	}
	
	private void abrirFrmPrincipal() {
		
		Alerta.mensaje(
				FrmLogueo.this, 
				"Bienvenido/a al sistema", 
				"Éxito", 
				Alerta.INFORMACION
		);
		
		FrmPrincipal frmprincipal = new FrmPrincipal();
		
		frmprincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		frmprincipal.setVisible(true);
		
		dispose();
	}
	
	private void establecerImagenLabel(JLabel label, String rutaimg) {
		
		ImageIcon icono = new ImageIcon(rutaimg);
		
		Icon imgicono = new ImageIcon(icono.getImage().getScaledInstance(label.getWidth(), label.getHeight(), 
				Image.SCALE_DEFAULT));
		
		label.setIcon(imgicono);
		
		this.repaint();
	}
	
	
	// ----------------------- metodos de la barra de titulo
	private void clickedLblSalir() {
		//System.exit(JFrame.EXIT_ON_CLOSE);
		this.dispose();
		hiloactivo = false;
	}

	private void pressedBarraTitulo(MouseEvent e) {
		xMouse = e.getX();
		yMouse = e.getY();
		
		panelBarraTitulo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelBarraTitulo.setBackground(Pintar.PRIMARIO.darker());
	}
	
	private void draggedBarraTitulo(MouseEvent e) {
		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		
		this.setLocation(x  - xMouse, y - yMouse);
	}
	
	private void releasedBarraTitulo() {
		panelBarraTitulo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		panelBarraTitulo.setBackground(Pintar.PRIMARIO);
	}
	
	
	//---------------------- metodos del boton
	private void enteredBtnIngresar() {
		btnIngresar.setBackground(Pintar.PRIMARIO.darker().darker());	
	}
	
	private void exitedBtnIngresar() {
		btnIngresar.setBackground(Pintar.PRIMARIO.darker());
	}
	
	//--------------------- metodo del checkBox
	private void actionPerformedChkMostrarPassowrd() {
		
		if(!String.valueOf(txtPassword.getPassword()).trim().equalsIgnoreCase("Ingrese contraseña")) {
			
			if(chkMostrar.isSelected()) {
				txtPassword.setEchoChar((char)0);
			} else {
				txtPassword.setEchoChar('•');
			}
		}	
	}
	
	//---------------------- metodos de los campos de texto
	private void focusGainedTxtUsuario() {
		
		sptxtUsuario.setBackground(Pintar.VERDE.brighter());
		sptxtUsuario.setForeground(Pintar.VERDE.brighter());
		
		if(txtUsuario.getText().equalsIgnoreCase("Ingrese usuario")) {
			txtUsuario.setText("");
			txtUsuario.setForeground(Pintar.BLANCO);
		}
		
		
	}
	
	private void focusLostTxtUsuario() {
		sptxtUsuario.setBackground(Pintar.BLANCO);
		sptxtUsuario.setForeground(Pintar.BLANCO);
		
		if(txtUsuario.getText().trim().length() == 0) {
			txtUsuario.setText("Ingrese usuario");
			txtUsuario.setForeground(Pintar.GRIS);
		}
		
	}
	
	private void focusGainedTxtPassword() {
		
		sptxtPassword.setBackground(Pintar.VERDE.brighter());
		sptxtPassword.setForeground(Pintar.VERDE.brighter());
		
		if(String.valueOf(txtPassword.getPassword()).equalsIgnoreCase("Ingrese contraseña")) {
			
			txtPassword.setText("");
			txtPassword.setForeground(Pintar.BLANCO);
				
			if(chkMostrar.isSelected()) {
				txtPassword.setEchoChar((char)0);
			} else {
				txtPassword.setEchoChar('•');
			}

		}
	
	}
	
	private void focusLostTxtPassword() {
		sptxtPassword.setBackground(Pintar.BLANCO);
		sptxtPassword.setForeground(Pintar.BLANCO);
		
		if(String.valueOf(txtPassword.getPassword()).trim().length() == 0) {
			
			txtPassword.setEchoChar((char)0);
			txtPassword.setForeground(Pintar.GRIS);
			txtPassword.setText("Ingrese contraseña");	
		}
	}
		
	
	//------------------------------ clase interna para la gestion de eventos de Accion ActionEvent
	private class EventoDeAccion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == chkMostrar) {
				actionPerformedChkMostrarPassowrd();
			}
			
			if(e.getSource() == btnIngresar) {
				validarLogueo();
			}
			
		}
		
	}
	
	//------------------------------- clase interna para la gestion de eventos de Mouse MouseEvent
	private class EventoDeMouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == lblSalir) {
				clickedLblSalir();
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
			if(e.getSource() == btnIngresar) {
				enteredBtnIngresar();
			}
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == btnIngresar) {
				exitedBtnIngresar();
			}
			
		}
		
	}
	
	
	//------------------------------- clase interna para la gestion de eventos de movimiento del Mouse MouseEvent
	private class EventoDeMouseMovimiento implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			if(e.getSource() == panelBarraTitulo) {
				draggedBarraTitulo(e);
			}
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
			
		}
		
	}
	
	//-------------------------------- clase interna para la gestion de eventos de foco de los elementos FocusEvent
	private class EventoDeFoco implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			if(e.getSource() == txtUsuario) {
				focusGainedTxtUsuario();
			}
			if(e.getSource() == txtPassword) {
				focusGainedTxtPassword();
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			if(e.getSource() == txtUsuario) {
				focusLostTxtUsuario();
			}
			if(e.getSource() == txtPassword) {
				focusLostTxtPassword();
			}
			
		}
		
	}

	//-------------------------------- clase interna para la gestion de hilo de carga (loading)
	private class ClaseHilo extends Thread {

		@Override
		public void run() {
			
			lblPorcentajeCarga.setVisible(true);
			btnIngresar.setEnabled(false);
			chkMostrar.setEnabled(false);
			txtUsuario.setEditable(false);
			txtPassword.setEditable(false);
	
			hiloactivo = true;
			
			for(int i = 1; i <= 15; i++) {
				
				try {
					Thread.sleep(500);
					
					if(i == 1) {
						establecerImagenLabel(lblCarga, "Images/loading/loading_initial.png");
						lblPorcentajeCarga.setText("0%");
					}
					
					if(i == 2 || i == 5 || i == 8 || i == 11 || i == 14) {
						establecerImagenLabel(lblCarga, "Images/loading/loading_top.png");
						
						if(i == 2) {lblPorcentajeCarga.setText("7%");}
						if(i == 5) {lblPorcentajeCarga.setText("30%");}
						if(i == 8) {lblPorcentajeCarga.setText("55%");}
						if(i == 11) {lblPorcentajeCarga.setText("76%");}
						if(i == 14) {lblPorcentajeCarga.setText("98%");}
						
					}
					
					if(i == 3 || i == 6 || i == 9 || i == 12) {
						establecerImagenLabel(lblCarga, "Images/loading/loading_bottom.png");
						
						if(i == 3) {lblPorcentajeCarga.setText("15%");}
						if(i == 6) {lblPorcentajeCarga.setText("37%");}
						if(i == 9) {lblPorcentajeCarga.setText("63%");}
						if(i == 12) {lblPorcentajeCarga.setText("85%");}
						
					}
					
					if(i == 4 || i == 7 || i == 10 || i == 13) {
						establecerImagenLabel(lblCarga, "Images/loading/loading_left.png");
						
						if(i == 4) {lblPorcentajeCarga.setText("22%");}
						if(i == 7) {lblPorcentajeCarga.setText("48%");}
						if(i == 10) {lblPorcentajeCarga.setText("70%");}
						if(i == 13) {lblPorcentajeCarga.setText("92%");}
						
					}
						
					if(i == 15) {
						establecerImagenLabel(lblCarga, "Images/loading/loading_full.png");
						lblPorcentajeCarga.setText("100%");
						
						abrirFrmPrincipal();
						
					}
					
					if(hiloactivo == false) {
						System.out.println("Se canceló el proceso");
						break;
					}
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}

		}
		
	}
}
