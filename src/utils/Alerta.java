package utils;

import java.awt.Component;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



public class Alerta {
	
	public static final String ERROR = "Images/images/error50px.png";
	public static final String ADVERTENCIA = "Images/images/advertencia50px.png";
	public static final String DESICION = "Images/images/decision50px.png";
	public static final String EXITO = "Images/images/check48px.png";
	public static final String INFORMACION = "Images/images/informacion50px.png";
	
	
	public static void mensaje(Component c, String texto, String titulo, String img ) {
				
		JOptionPane.showMessageDialog(
				c, 
				texto, 
				titulo, 
				JOptionPane.PLAIN_MESSAGE, 
				new ImageIcon(img)
		);
	}
	
	public static int confirmar(Component c, String texto, String titulo, String img) {
		
		return JOptionPane.showConfirmDialog(
				c, 
				texto, 
				titulo, 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.PLAIN_MESSAGE, 
				new ImageIcon(img)
		);
		
	}
	

}
