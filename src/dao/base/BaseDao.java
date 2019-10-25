package dao.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import dao.exceptions.DatabaseException;

public interface BaseDao {
	
	public abstract Connection getConnection() throws DatabaseException;
	public abstract void closeConnection( Connection conn, Statement stmt, ResultSet rs ) throws DatabaseException;

}
