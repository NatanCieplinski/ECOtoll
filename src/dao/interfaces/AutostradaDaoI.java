package dao.interfaces;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import dao.exceptions.DBException;
import mvc.model.Autostrada;
import mvc.model.Casello;

public interface AutostradaDaoI extends DaoI<Autostrada>{
	// TODO: Inserire le firme delle query necessarie
	public List<Autostrada> getAll() throws DBException, SQLException;
	public HashMap<Integer,Casello> getCaselli(Autostrada autostrada) throws DBException, SQLException;
	public HashMap<Integer,String> getTariffe(Autostrada autostrada) throws DBException, SQLException;
}
