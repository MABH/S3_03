package conexio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connexio {
	private String bbdd = "floristeria";
	private String user = "root";
	private String psw = "root";
	private String url ="jdbc:mysql://localhost:3306/"+bbdd;
	private Connection con = null;
	
	public Connection getConnexio() {
		try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = (Connection) DriverManager.getConnection(this.url, this.user, this.psw);
		}catch (SQLException e) {
			System.err.println(e);
		}catch (ClassNotFoundException ex) {
				Logger.getLogger(Connexio.class.getName()).log(Level.SEVERE, null, ex);
		}
		return con;		
	}
}
