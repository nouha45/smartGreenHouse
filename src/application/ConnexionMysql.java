package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionMysql {
	public Connection cn= null;
	public static Connection connexionDB() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartgreenhouse","root","");
			System.out.println("connxion ok");
			return cn;
		} catch ( SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Connexion echouee");
			e.printStackTrace();
			return null;
		}
		
	}

}
