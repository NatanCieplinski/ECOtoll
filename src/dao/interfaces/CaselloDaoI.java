package dao.interfaces;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import dao.exceptions.DBException;
import mvc.model.Autostrada;
import mvc.model.Casello;

public interface CaselloDaoI extends DaoI<Casello>{
	// TODO: Inserire le firme delle query necessarie
	public List<Casello> getAllFromAutostrada(Autostrada autostrada) throws DBException, SQLException;
}
