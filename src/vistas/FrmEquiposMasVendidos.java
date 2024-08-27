package vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.LineBorder;

import mantenimientoreportes.CincoEquiposMasVendidosDAO;
import modelreportes.CincoEquiposMasVendidos;
import utils.Pintar;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.util.List;


public class FrmEquiposMasVendidos extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	// variables globales
	private int xMouse, yMouse;
	
	private JPanel panelBarraTitulo;
	private JLabel lblSalir;
	private JLabel lblIconFrame;
	private JLabel lblTitulo;
	private JLabel lblMinimizar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEquiposMasVendidos frame = new FrmEquiposMasVendidos();
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
	public FrmEquiposMasVendidos() {
		setFrameIcon(new ImageIcon(FrmEquiposMasVendidos.class.getResource("/images/reportes24px.png")));
		setIconifiable(true);
		getContentPane().setBackground(Pintar.PRIMARIO.brighter());
		setBorder(new LineBorder(new Color(255, 255, 255), 4));
		setForeground(Color.BLACK);
		setTitle("Productos más vendidos");
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
		establecerImagenLabel(lblIconFrame, "Images/images/reportes24px.png");
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

		lblTitulo = new JLabel("Los cinco productos más vendidos");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblTitulo.setBounds(59, 0, 613, 30);
		panelBarraTitulo.add(lblTitulo);
		

	}
	
	//---------- clases DAO
	CincoEquiposMasVendidosDAO cincoequipomasvendidosDAO = new CincoEquiposMasVendidosDAO();

	//------------ metodos de usuario
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
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (e.getSource() == lblMinimizar) {
				exitedLblMinimizar();
			}
			if (e.getSource() == lblSalir) {
				exitedLblSalir();
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
	

	
	
	
	
	// metodo para pintar en el frame
	public void paint(Graphics g) {
		super.paint(g);
		
		List<CincoEquiposMasVendidos> lstCincoEquiposMasVendidos = cincoequipomasvendidosDAO.listarCincoEquiposMasVendidos();
		
		if(lstCincoEquiposMasVendidos.size() >= 5 ) {
			
			int cantidadmayor = lstCincoEquiposMasVendidos.get(0).getNroequiposvendidos();
			
			String nombre1 = lstCincoEquiposMasVendidos.get(0).getNombreequipo();
			String nombre2 = lstCincoEquiposMasVendidos.get(1).getNombreequipo();
			String nombre3 = lstCincoEquiposMasVendidos.get(2).getNombreequipo();
			String nombre4 = lstCincoEquiposMasVendidos.get(3).getNombreequipo();
			String nombre5 = lstCincoEquiposMasVendidos.get(4).getNombreequipo();
			
			int nroventasequipo1 = lstCincoEquiposMasVendidos.get(0).getNroventasequipo();
			int nroventasequipo2 = lstCincoEquiposMasVendidos.get(1).getNroventasequipo();
			int nroventasequipo3 = lstCincoEquiposMasVendidos.get(2).getNroventasequipo();
			int nroventasequipo4 = lstCincoEquiposMasVendidos.get(3).getNroventasequipo();
			int nroventasequipo5 = lstCincoEquiposMasVendidos.get(4).getNroventasequipo();
			
			int nroequiposvendidos1 = lstCincoEquiposMasVendidos.get(0).getNroequiposvendidos();
			int nroequiposvendidos2 = lstCincoEquiposMasVendidos.get(1).getNroequiposvendidos();
			int nroequiposvendidos3 = lstCincoEquiposMasVendidos.get(2).getNroequiposvendidos();
			int nroequiposvendidos4 = lstCincoEquiposMasVendidos.get(3).getNroequiposvendidos();
			int nroequiposvendidos5 = lstCincoEquiposMasVendidos.get(4).getNroequiposvendidos();
			
			double montoadquirido1 = lstCincoEquiposMasVendidos.get(0).getMontoadquirido();
			double montoadquirido2 = lstCincoEquiposMasVendidos.get(1).getMontoadquirido();
			double montoadquirido3 = lstCincoEquiposMasVendidos.get(2).getMontoadquirido();
			double montoadquirido4 = lstCincoEquiposMasVendidos.get(3).getMontoadquirido();
			double montoadquirido5 = lstCincoEquiposMasVendidos.get(4).getMontoadquirido();
			
			int largobarra1 = nroequiposvendidos1 * 500 / cantidadmayor;
			int largobarra2 = nroequiposvendidos2 * 500 / cantidadmayor;
			int largobarra3 = nroequiposvendidos3 * 500 / cantidadmayor;
			int largobarra4 = nroequiposvendidos4 * 500 / cantidadmayor;
			int largobarra5 = nroequiposvendidos5 * 500 / cantidadmayor;
			
			
			//----- comenzar grafica
			g.setColor(new Color(240,248,0));
			g.fillRect(220, 120, largobarra1, 40);
			g.setFont(new Font("SansSerif", Font.BOLD, 12));
			g.drawString(nombre1, 50, 125);
			g.drawString("Nro equipos vendidos: " + nroequiposvendidos1, 50, 140);
			g.drawString("Nro Ventas: " + nroventasequipo1, 50, 155);
			g.drawString("Monto adquirido (S/.): " + montoadquirido1, 50, 170);
			
			
			g.setColor(new Color(255,0,0));
			g.fillRect(220, 190, largobarra2, 40);
			g.setFont(new Font("SansSerif", Font.BOLD, 12));
			g.drawString(nombre2, 50, 195);
			g.drawString("Nro equipos vendidos: " + nroequiposvendidos2, 50, 210);
			g.drawString("Nro Ventas: " + nroventasequipo2, 50, 225);
			g.drawString("Monto adquirido (S/.): " + montoadquirido2, 50, 240);

			g.setColor(new Color(255,255,255));
			g.fillRect(220, 260, largobarra3, 40);
			g.setFont(new Font("SansSerif", Font.BOLD, 12));
			g.drawString(nombre3, 50, 265);
			g.drawString("Nro equipos vendidos: " + nroequiposvendidos3, 50, 280);
			g.drawString("Nro Ventas: " + nroventasequipo3, 50, 295);
			g.drawString("Monto adquirido (S/.): " + montoadquirido3, 50, 310);
			
			
			g.setColor(new Color(0,255,0));
			g.fillRect(220, 330, largobarra4, 40);
			g.setFont(new Font("SansSerif", Font.BOLD, 12));
			g.drawString(nombre4, 50, 335);
			g.drawString("Nro equipos vendidos: " + nroequiposvendidos4, 50, 350);
			g.drawString("Nro Ventas: " + nroventasequipo4, 50, 365);
			g.drawString("Monto adquirido (S/.): " + montoadquirido4, 50, 380);
			
			g.setColor(new Color(0,0,0));
			g.fillRect(220, 400, largobarra5, 40);
			g.setFont(new Font("SansSerif", Font.BOLD, 12));
			g.drawString(nombre5, 50, 405);
			g.drawString("Nro equipos vendidos: " + nroequiposvendidos5, 50, 420);
			g.drawString("Nro Ventas: " + nroventasequipo5, 50, 435);
			g.drawString("Monto adquirido (S/.): " + montoadquirido5, 50, 450);
			

		} else {
			
			g.setColor(new Color(255, 255, 255));
			g.setFont(new Font("SansSerif", Font.BOLD, 18));
			g.drawString("<<Se debe haber vendido por lo menos cinco productos diferentes>>", 
					70, 230);
			
		}
		
	}
}
