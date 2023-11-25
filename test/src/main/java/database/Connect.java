package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Connect {
	protected final String DRIVER = "com.mysql.cj.jdbc.Driver";
	protected final String USERNAME = "root";
	protected final String PASSWORD = "274gus285wl@";
	protected final String URL = "jdbc:mysql://localhost:3306/java2test";
	protected Connection con=null;
	protected PreparedStatement ps=null;
	protected String st=null;
	final String semicolon =";";
	
	Connect(){
		try {
			Class.forName(DRIVER);
			this.con=DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Connect db connected");
		} catch(ClassNotFoundException e) {
			System.out.println("Connect class not found error");
		} catch(SQLException e) {
			System.out.println("Connect sql error");
		} catch(Exception e) {
			System.out.println("Connect error");
		}
	}
}