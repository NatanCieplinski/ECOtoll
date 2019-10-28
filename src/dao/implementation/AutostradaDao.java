package dao.implementation;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.mysql.jdbc.ResultSet;

import dao.database.DBManager;
import dao.interfaces.AutostradaDaoI;
import mvc.model.Autostrada;

public class AutostradaDao extends DBManager implements AutostradaDaoI {
	
	// TODO: Implementare le query descritte nell'interfaccia AutostradaDaoI
	
	
	/*
	 * CRUD: Implementare in caso di necessità
	 * */
	@Override
	public void create(Autostrada autostrada) {}

	@Override
	public Optional<Autostrada> read(long id){ return Optional.ofNullable(null); } 

	@Override
	public void update(Autostrada autostrada, String[] params){}

	@Override
	public void delete(Autostrada autostrada) {}
	
	@Override
	public Autostrada makeObj(ResultSet rs) throws SQLException{
		return new Autostrada(
			rs.getInt("id"),
			rs.getString("nome"),
			rs.getInt("tipo")
		);
	}
	
	@Override
	public List<Autostrada> makeList(ResultSet rs) throws SQLException{
		List<Autostrada> autostrade = new LinkedList<Autostrada>();
		while ( rs.next() ) {
			autostrade.add(this.makeObj(rs));
		}
		return autostrade;
	}
}
