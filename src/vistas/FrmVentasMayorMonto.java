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

import mantenimientoreportes.CincoVentasMayorMontoDAO;
import modelreportes.CincoVentasMayorMonto;
import utils.Pintar;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.MouseMotionListener;
import java.beans.PropertyVetoException;
import java.util.List;


public class FrmVentasMayorMonto extends JInternalFrame {

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
					FrmVentasMayorMonto frame = new FrmVentasMayorMonto();
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
	public FrmVentasMayorMonto() {
		setFrameIcon(new ImageIcon(FrmVentasMayorMonto.class.getResource("/images/reportes24px.png")));
		setIconifiable(true);
		getContentPane().setBackground(Pintar.PRIMARIO.brighter());
		setBorder(new LineBorder(new Color(255, 255, 255), 4));
		setForeground(Color.BLACK);
		setTitle("Ventas con mayor monto");
		setClosable(true);
		setBounds(100, 100, 950, 500);
		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		getContentPane().setLayout(null);

		panelBarraTitulo = new JPanel();
		panelBarraTitulo.addMouseMotionListener(new EventoDeMouseMovimiento());
		panelBarraTitulo.addMouseListener(new EventoDeMouse());
		panelBarraTitulo.setBackground(Pintar.PRIMARIO.brighter());
		panelBarraTitulo.setBounds(0, 0, 942, 30);
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
		lblSalir.setBounds(912, 0, 30, 30);
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
		lblMinimizar.setBounds(882, 0, 30, 30);
		panelBarraTitulo.add(lblMinimizar);

		lblTitulo = new JLabel("Ventas con mayor monto recaudado");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblTitulo.setBounds(59, 0, 809, 30);
		panelBarraTitulo.add(lblTitulo);
		

	}
	
	//---------- clases DAO
	CincoVentasMayorMontoDAO cincoventasmayormontoDAO = new CincoVentasMayorMontoDAO();

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
		
		List<CincoVentasMayorMonto> lstCincoVentasMayorMonto = cincoventasmayormontoDAO.listarCincoVentasMayorMonto();
		
		if(lstCincoVentasMayorMonto.size() >= 5 ) {
			
			int cantidadmayor = (int) lstCincoVentasMayorMonto.get(0).getMonto();
			
			String venta1 = lstCincoVentasMayorMonto.get(0).getIdventa();
			String venta2 = lstCincoVentasMayorMonto.get(1).getIdventa();
			String venta3 = lstCincoVentasMayorMonto.get(2).getIdventa();
			String venta4 = lstCincoVentasMayorMonto.get(3).getIdventa();
			String venta5 = lstCincoVentasMayorMonto.get(4).getIdventa();
			
			String cliente1 = lstCincoVentasMayorMonto.get(0).getCliente();
			String cliente2 = lstCincoVentasMayorMonto.get(1).getCliente();
			String cliente3 = lstCincoVentasMayorMonto.get(2).getCliente();
			String cliente4 = lstCincoVentasMayorMonto.get(3).getCliente();
			String cliente5 = lstCincoVentasMayorMonto.get(4).getCliente();
			
			String usuario1 = lstCincoVentasMayorMonto.get(0).getUsuario();
			String usuario2 = lstCincoVentasMayorMonto.get(1).getUsuario();
			String usuario3 = lstCincoVentasMayorMonto.get(2).getUsuario();
			String usuario4 = lstCincoVentasMayorMonto.get(3).getUsuario();
			String usuario5 = lstCincoVentasMayorMonto.get(4).getUsuario();
			
			String fecha1 = lstCincoVentasMayorMonto.get(0).getFecha().toString();
			String fecha2 = lstCincoVentasMayorMonto.get(1).getFecha().toString();
			String fecha3 = lstCincoVentasMayorMonto.get(2).getFecha().toString();
			String fecha4 = lstCincoVentasMayorMonto.get(3).getFecha().toString();
			String fecha5 = lstCincoVentasMayorMonto.get(4).getFecha().toString();
			
			double monto1 = lstCincoVentasMayorMonto.get(0).getMonto();
			double monto2 = lstCincoVentasMayorMonto.get(1).getMonto();
			double monto3 = lstCincoVentasMayorMonto.get(2).getMonto();
			double monto4 = lstCincoVentasMayorMonto.get(3).getMonto();
			double monto5 = lstCincoVentasMayorMonto.get(4).getMonto();
			
			
			int altobarra1 = (int) (monto1 * 350 / cantidadmayor) * -1;
			int altobarra2 = (int) (monto2 * 350 / cantidadmayor) * -1;
			int altobarra3 = (int) (monto3 * 350 / cantidadmayor) * -1;
			int altobarra4 = (int) (monto4 * 350 / cantidadmayor) * -1;
			int altobarra5 = (int) (monto5 * 350 / cantidadmayor) * -1;
			
			
			//----- comenzar grafica
			
			g.setColor(Pintar.BLANCO);
			g.drawLine(30, 415, 900, 415);
			g.drawLine(30, 415, 30, 415 - (altobarra1 * -1));
			
			g.setColor(new Color(240,248,0));
			g.fillRect(85, 415, 40, altobarra1);
			g.setFont(new Font("SansSerif", Font.BOLD, 12));
			g.drawString(venta1, 50, 435);
			g.drawString("Cliente: " + cliente1, 50, 450);
			g.drawString("Usuario: " + usuario1, 50, 465);
			g.drawString("Fecha: " + fecha1, 50, 480);
			g.drawString("Monto: S/. " + monto1, 50, 415 - (altobarra1 * -1) - 10);
			
			
			g.setColor(new Color(255,0,0));
			g.fillRect(270, 415, 40, altobarra2);
			g.setFont(new Font("SansSerif", Font.BOLD, 12));
			g.drawString(venta2, 235, 435);
			g.drawString("Cliente: " + cliente2, 235, 450);
			g.drawString("Usuario: " + usuario2, 235, 465);
			g.drawString("Fecha: " + fecha2, 235, 480);
			g.drawString("Monto: S/. " + monto2, 235, 415 - (altobarra2 * -1) - 10);

			g.setColor(new Color(255,0,255));
			g.fillRect(455, 415, 40, altobarra3);
			g.setFont(new Font("SansSerif", Font.BOLD, 12));
			g.drawString(venta3, 420, 435);
			g.drawString("Cliente: " + cliente3, 420, 450);
			g.drawString("Usuario: " + usuario3, 420, 465);
			g.drawString("Fecha: " + fecha3, 420, 480);
			g.drawString("Monto: S/. " + monto3, 420, 415 - (altobarra3 * -1) - 10);
			
			
			g.setColor(new Color(0,255,0));
			g.fillRect(640, 415, 40, altobarra4);
			g.setFont(new Font("SansSerif", Font.BOLD, 12));
			g.drawString(venta4, 605, 435);
			g.drawString("Cliente: " + cliente4, 605, 450);
			g.drawString("Usuario: " + usuario4, 605, 465);
			g.drawString("Fecha: " + fecha4, 605, 480);
			g.drawString("Monto: S/. " + monto4, 605, 415 - (altobarra4 * -1) - 10);
			
			g.setColor(new Color(0,0,0));
			g.fillRect(825, 415, 40, altobarra5);
			g.setFont(new Font("SansSerif", Font.BOLD, 12));
			g.drawString(venta5, 790, 435);
			g.drawString("Cliente: " + cliente5, 790, 450);
			g.drawString("Usuario: " + usuario5, 790, 465);
			g.drawString("Fecha: " + fecha5, 790, 480);
			g.drawString("Monto: S/. " + monto5, 790, 415 - (altobarra5 * -1) - 10);
			

		} else {
			
			g.setColor(new Color(255, 255, 255));
			g.setFont(new Font("SansSerif", Font.BOLD, 18));
			g.drawString("<<Se debe haber realizado por lo menos cinco ventas>>", 
					70, 230);
			
		}
		
	}
}
