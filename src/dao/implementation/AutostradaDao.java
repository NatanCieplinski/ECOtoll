package dao.implementation;

import java.util.Optional;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

import dao.database.DBManager;
import dao.interfaces.AutostradaDaoI;
import mvc.model.Autostrada;
import mvc.model.Casello;

public class AutostradaDao extends DBManager implements AutostradaDaoI {
	private Connection db;
	
	public AutostradaDao() { this.db = this.getDbInstance(); }
	
	public List<Autostrada> getAll() throws SQLException{ 
		final String query = "SELECT * FROM autostrada;";
		
		PreparedStatement stmt = this.db.prepareStatement(query);
		
		ResultSet rs = stmt.executeQuery();
		List<Autostrada> autostrade = this.makeList(rs);
		stmt.close(); rs.close();
		
		return autostrade; 
	} 
	
	public HashMap<Integer,Casello> getCaselli(Autostrada autostrada) throws SQLException{
		HashMap<Integer,Casello> caselli = new HashMap<Integer,Casello>();
		
		CaselloDao caselloDao = new CaselloDao();
		for (Casello i : caselloDao.getAllFromAutostrada(autostrada))
			caselli.put(i.getId(),i);
		
		return caselli;
	}
	
	public HashMap<String ,Float> getTariffe(Autostrada autostrada) throws SQLException{
		final String query = "SELECT * FROM tariffa WHERE autostrada=?;";
		
		HashMap<String ,Float> tariffe = new HashMap<String ,Float>();

		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setInt(1, autostrada.getId());
		
		ResultSet rs = stmt.executeQuery();
		rs.next();
		tariffe.put("A", rs.getFloat("tariffaA"));
		tariffe.put("B", rs.getFloat("tariffaB"));
		tariffe.put("3", rs.getFloat("tariffa3"));
		tariffe.put("4", rs.getFloat("tariffa4"));
		tariffe.put("5", rs.getFloat("tariffa5"));
		stmt.close(); rs.close();
		
		return tariffe;
	}
	
	/*
	 * CRUD: Implementare in caso di necessita
	 * */
	@Override
	public void create(String[] params) {}

	@Override
	public Optional<Autostrada> read(Object id) throws SQLException{ 
		final String query = "SELECT * FROM autostrada WHERE id=?;";
	
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setInt(1, (int)id);
		
		ResultSet rs = stmt.executeQuery();
		rs.next();
		Autostrada autostrada = this.makeObj(rs);
		stmt.close(); rs.close();
		
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
