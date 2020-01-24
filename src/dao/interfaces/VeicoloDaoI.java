package dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import mvc.model.Veicolo;

public interface VeicoloDaoI extends DaoI<Veicolo>{
	public List<Veicolo> getAll() throws SQLException;
}
