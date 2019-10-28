package dao.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.mysql.jdbc.ResultSet;

// Interfaccia del DAO che fornisce le firme CRUD e le firme per creazione di strutture del modello
public interface DaoI<T> {
	// CRUD
    void 		create(T t);
    Optional<T> read(long id);  
    void		update(T t, String[] params);
    void 		delete(T t);
    
    // Strutture del modello
    T 			makeObj(ResultSet rs) throws SQLException;
    List<T> 	makeList(ResultSet rs) throws SQLException;
}