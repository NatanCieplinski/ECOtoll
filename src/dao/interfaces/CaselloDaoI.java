package dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import mvc.model.Autostrada;
import mvc.model.Casello;

public interface CaselloDaoI extends DaoI<Casello>{
	public List<Casello> getAllFromAutostrada(Autostrada autostrada) throws SQLException;
}
