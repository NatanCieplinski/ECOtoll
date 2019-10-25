package dao.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import dao.implementation.*;
import dao.interfaces.BaseAutostradaDao;
import dao.interfaces.BaseCaselloDao;
import dao.interfaces.BaseIngressoDao;
import dao.interfaces.BaseNormativaDao;
import dao.interfaces.BaseVeicoloDao;


public class MySqlDaoFactory extends DaoFactory {

	private static String URL = "jdbc:mysql://localhost:3306/DBAutostrada";	
	
	public static Connection getConnection() throws DatabaseException {
		
		Connection conn = null;
		
		try {	
			Myconn = DriverManager.getConnection(URL, "root", "jucola98");		
		} catch (SQLException e)  {
			// logger ...
			throw new DatabaseException(e.getMessage());
		}
		
		return Myconn;
		
	}
	
	
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws DatabaseException {
			
		try  {				
			if (rs != null)  {  rs.close(); }
			if (stmt != null)  { stmt.close(); }
			if (conn != null)  { conn.close(); }
		} catch (SQLException e)  {
			//logger.debug(se.getMessage(),  se);
			throw new DatabaseException(e.toString());
		}
			
	}


	@Override
	public BaseCaselloDao getCaselloDao() {
		return new CaselloDaoMySql();
	}

	@Override
	public BaseAutostradaDao getAutostradaDao() {
		return new AutostradaDaoMySql();
	}

	@Override
	public BaseVeicoloDao getVeicoloDao() {
		return new VeicoloDaoMySql();
	}


	@Override
	public BaseBigliettoDao getBigliettoDao() {
		return new BigliettoDaoMySql();
	} 
	
}


