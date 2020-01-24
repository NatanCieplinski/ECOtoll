package dao.interfaces;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import mvc.model.Autostrada;
import mvc.model.Casello;

public interface AutostradaDaoI extends DaoI<Autostrada>{
	public List<Autostrada> getAll() throws SQLException;
	public HashMap<Integer,Casello> getCaselli(Autostrada autostrada) throws SQLException;
	public HashMap<String ,Float> getTariffe(Autostrada autostrada) throws SQLException;
}
