package dao.implementation;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.mysql.jdbc.ResultSet;

import dao.database.DBManager;
import dao.interfaces.BigliettoDaoI;
import mvc.model.Biglietto;

public class BigliettoDao extends DBManager implements BigliettoDaoI {
	
	// TODO: Implementare le query descritte nell'interfaccia AutostradaDaoI
	
	
	/*
	 * CRUD: Implementare in caso di necessita
	 * */
	@Override
	public void create(Biglietto biglietto) {}

	@Override
	public Optional<Biglietto> read(long id){ return Optional.ofNullable(null); } 

	@Override
	public void update(Biglietto biglietto, String[] params){}

	@Override
	public void delete(Biglietto biglietto) {}
	
	@Override
	public Biglietto makeObj(ResultSet rs) throws SQLException{
		return new Biglietto(
			rs.getInt("id"),
			rs.getString("targa")
		);
	}
	
	@Override
	public List<Biglietto> makeList(ResultSet rs) throws SQLException{
		List<Biglietto> biglietti = new LinkedList<Biglietto>();
		while ( rs.next() ) {
			biglietti.add(this.makeObj(rs));
		}
		return biglietti;
	}
}
