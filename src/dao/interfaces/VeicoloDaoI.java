package dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import mvc.model.Veicolo;

public interface VeicoloDaoI extends DaoI<Veicolo>{
	// TODO: Inserire le firme delle query necessarie
	public List<Veicolo> getAll() throws SQLException;
}
