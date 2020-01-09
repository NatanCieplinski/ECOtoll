package dao.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.DriverManager;

import dao.exceptions.DBException;
import dao.implementation.AutostradaDao;

public class DBManager {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ecotoll";	
	private static final String DB_USER = "root";	
	private static final String DB_PASS = "";
	public static final String MYSQL = null;
	
	protected Connection db = null;
	
	public void openDB() throws DBException {
		try {	
			Class.forName("com.mysql.jdbc.Driver");
			this.db = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);		
		} catch (Exception e)  {
			throw new DBException(e.getMessage());
		}
	}
	
	public void closeDB(Statement stmt, ResultSet rs) throws DBException {
			
		try  {				
			if (rs 	 != null)  { rs.close(); }
			if (stmt != null)  { stmt.close(); }
			if (this.db	 != null)  { this.db.close(); }
		} catch (SQLException e)  {
			throw new DBException(e.toString());
		}
			
	}

	public static DBManager getDaoFactory(String mysql2) {
		// TODO Auto-generated method stub
		return null;
	}

	public AutostradaDao getDaoCasello() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Object getCaselloDao() {
		// TODO Auto-generated method stub
		return null;
	}
	
}


