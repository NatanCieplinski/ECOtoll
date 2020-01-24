package dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import mvc.model.Biglietto;

public interface BigliettoDaoI extends DaoI<Biglietto>{
	// TODO: Inserire le firme delle query necessarie
	public List<Biglietto> getAll() throws SQLException;
}
