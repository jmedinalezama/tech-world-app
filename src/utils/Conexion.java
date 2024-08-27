package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private String url = "jdbc:mysql://localhost/bdequiposelectronicos?useSSL=false";
	private String user = "root";
	private String pass = "root";
	private Connection cn;
	
	
	public Connection conectar() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			cn = DriverManager.getConnection(url, user, pass);
			
			if(cn != null) {
				System.out.println("Conexión correcta con la BD bdEquiposElectronicos conector 8.0.32");
				
			}
		
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cn;
	}
	
	
}
