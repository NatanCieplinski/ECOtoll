package dao.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBManager {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ecotoll";	
	private static final String DB_USER = "root";	
	private static final String DB_PASS = "";
	
	private static Connection db = null;
	
	static {
		try {
			open();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void open() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		if (db == null) { db = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); }	
	}
	
	private static void close() throws SQLException {	
		if (db != null) { db.close(); }
	}
	
	public Connection getDbInstance() {
		return db;
	}
}


