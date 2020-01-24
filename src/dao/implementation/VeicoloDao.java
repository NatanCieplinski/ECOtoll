package dao.implementation;

import java.util.Optional;
import java.util.LinkedList;
import java.util.List;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.database.DBManager;
import dao.interfaces.VeicoloDaoI;
import mvc.model.Veicolo;

public class VeicoloDao extends DBManager implements VeicoloDaoI {
	private Connection db;
	
	public VeicoloDao() { this.db = this.getDbInstance(); }
	
	// TODO: Implementare le query descritte nell'interfaccia VeicoloDaoI
	public List<Veicolo> getAll() throws SQLException{
		final String query = "SELECT * FROM veicolo;";

		PreparedStatement stmt = this.db.prepareStatement(query);
		List<Veicolo> veicoli = this.makeList(stmt.executeQuery());
		stmt.close();
		
		return veicoli;
	}
	
	/*
	 * CRUD: Implementare in caso di necessit�
	 * */
	@Override
	public void create(String[] params) {}

	@Override
	public Optional<Veicolo> read(Object targa) throws SQLException{
		final String query = "SELECT * FROM veicolo WHERE targa=?;";
	
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setString(1, (String)targa);
		
		ResultSet rs = stmt.executeQuery();
		rs.next();
		Veicolo veicolo = this.makeObj(rs);
		
		stmt.close(); rs.close();
		
		return Optional.ofNullable(veicolo); 
	} 

	@Override
	public void update(Veicolo veicolo, String[] params){}

	@Override
	public void delete(Veicolo veicolo) {}
	
	@Override
	public Veicolo makeObj(ResultSet rs) throws SQLException{
		return new Veicolo(
			rs.getInt("altezza"),
			rs.getInt("n_assi"),
			rs.getString("targa"),
			rs.getInt("co2"), 	
			rs.getInt("decibel"), 
			rs.getInt("euro"),
			rs.getString("modello"),
			rs.getString("marca"),
			rs.getInt("anno"),
			rs.getInt("peso")
		);
	}
	
	@Override
	public List<Veicolo> makeList(ResultSet rs) throws SQLException{
		List<Veicolo> veicoli = new LinkedList<Veicolo>();
		while ( rs.next() ) {
			veicoli.add(this.makeObj(rs));
		}
		return veicoli;
	}
}
