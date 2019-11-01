package dao.implementation;

import java.util.Optional;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.database.DBManager;
import dao.interfaces.AutostradaDaoI;
import dao.exceptions.DBException;
import mvc.model.Autostrada;
import mvc.model.Casello;

public class AutostradaDao extends DBManager implements AutostradaDaoI {
	
	// TODO: Implementare le query descritte nell'interfaccia AutostradaDaoI
	public HashMap<Integer,Casello> getCaselli(Autostrada autostrada) throws DBException, SQLException{
		HashMap<Integer,Casello> caselli = new HashMap<Integer,Casello>();
		
		CaselloDao caselloDao = new CaselloDao();
		for (Casello i : caselloDao.getAllFromAutostrada(autostrada))
			caselli.put(i.getId(),i);
		
		return caselli;
	}
	
	/*
	 * CRUD: Implementare in caso di necessitÓ
	 * */
	@Override
	public void create(Autostrada autostrada) {}

	@Override
	public Optional<Autostrada> read(Object id) throws DBException, SQLException{ 
		final String query = "SELECT * FROM autostrada WHERE id=?;";
	
		this.openDB();
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setInt(1, (int)id);
		
		ResultSet rs = stmt.executeQuery();
		rs.next();
		Autostrada autostrada = this.makeObj(rs);
		
		this.closeDB(stmt, null);
		
		return Optional.ofNullable(autostrada); 
	} 

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
