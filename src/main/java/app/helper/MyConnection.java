package app.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import app.*;

public class MyConnection {
	private final String DRIVER="com.mysql.jdbc.Driver";
	private final String URL="jdbc:mysql://localhost:3306/registration";
	private final String USERNAME="root";
	private final String PASSWORD="root";
	private Connection con;
	private static MyConnection helper;
	
	public MyConnection() {
		try {
			Class.forName(DRIVER);
			con=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (Exception e) {
			  throw new RuntimeException("uncaught", e);
		}
	}
	public static MyConnection getInstance() {
		if(helper==null) {
			helper=new MyConnection();
		}
		return helper;
	}
	public Connection getConnection() {
		return con;
	}
	
	
	
}


