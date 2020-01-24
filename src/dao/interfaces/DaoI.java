package dao.interfaces;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.List;
import java.util.Optional;

// Interfaccia del DAO che fornisce le firme CRUD e le firme per creazione di strutture del modello
public interface DaoI<T> {
	// CRUD
    void 		create(String[] params) 	 throws SQLException;
    Optional<T> read(Object id)				 throws SQLException;  
    void		update(T t, String[] params) throws SQLException;
    void 		delete(T t)					 throws SQLException;
    
    // Strutture del modello
    T 			makeObj(ResultSet rs)		 throws SQLException;
    List<T> 	makeList(ResultSet rs)		 throws SQLException;
}