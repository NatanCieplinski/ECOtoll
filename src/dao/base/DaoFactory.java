package dao.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.base.MySqlDaoFactory;
import dao.interfaces.BaseAutostradaDao;
import dao.interfaces.BaseCaselloDao;
import dao.interfaces.BaseBigliettoDao;
import dao.interfaces.BaseVeicoloDao;

public abstract class DaoFactory {
	public static final int MYSQL = 1;
	// public static final int ACCESS = 2;
	
	/*
   	 *	Requisite "Abstract Base Class with Virtual Methods"
     	 *	There will be a method for each DAO that can be
     	 * 	created. The concrete factories will have to implement these methods.
	 */
	public abstract BaseCaselloDao getCaselloDao();
	public abstract BaseAutostradaDao getAutostradaDao();
	public abstract BaseVeicoloDao getVeicoloDao();
	public abstract BaseBigliettoDao getBigliettoDao();
	
public static DaoFactory getDaoFactory( int DbmsType )  {
		
		switch ( DbmsType )  {
			case MYSQL:
				return new MySqlDaoFactory();
			// other case
				
			//case ACCESS:
				//return new AccessDaoFactory();
				
			default:
				return null;
		}
	
	}
}
