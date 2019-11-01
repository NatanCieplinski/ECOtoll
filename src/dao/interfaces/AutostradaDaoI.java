package dao.interfaces;

import java.sql.SQLException;
import java.util.HashMap;

import dao.exceptions.DBException;
import mvc.model.Autostrada;
import mvc.model.Casello;

public interface AutostradaDaoI extends DaoI<Autostrada>{
	// TODO: Inserire le firme delle query necessarie
	public HashMap<Integer,Casello> getCaselli(Autostrada autostrada) throws DBException, SQLException;
}
