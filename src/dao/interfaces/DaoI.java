package dao.interfaces;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;
import java.util.Optional;

import dao.exceptions.DBException;

// Interfaccia del DAO che fornisce le firme CRUD e le firme per creazione di strutture del modello
public interface DaoI<T> {
	// CRUD
    void 		create(T t) 				 throws DBException, SQLException;
    Optional<T> read(long id)				 throws DBException, SQLException;  
    void		update(T t, String[] params) throws DBException, SQLException;
    void 		delete(T t)					 throws DBException, SQLException;
    
    // Strutture del modello
    T 			makeObj(ResultSet rs)		 throws SQLException;
    List<T> 	makeList(ResultSet rs)		 throws SQLException;
}