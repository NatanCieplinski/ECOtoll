package dao.implementation;

import java.util.Optional;
import java.util.LinkedList;
import java.util.List;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.database.DBManager;
import dao.interfaces.VeicoloDaoI;
import dao.exceptions.DBException;
import mvc.model.Veicolo;

public class VeicoloDao extends DBManager implements VeicoloDaoI {
	
	// TODO: Implementare le query descritte nell'interfaccia AutostradaDaoI
	
	
	/*
	 * CRUD: Implementare in caso di necessità
	 * */
	@Override
	public void create(Veicolo veicolo) {}

	@Override
	public Optional<Veicolo> read(long id){ return Optional.ofNullable(null); } 

	@Override
	public void update(Veicolo veicolo, String[] params){}

	@Override
	public void delete(Veicolo veicolo) {}
	
	@Override
	public Veicolo makeObj(ResultSet rs) throws SQLException{
		return new Veicolo(
			rs.getInt("id"),
			rs.getInt("altezza"),
			rs.getInt("n_assi"),
			rs.getBoolean("carrello"),
			rs.getInt("n_assi_carrello"),
			rs.getString("targa"),
			rs.getInt("emissioni_Co2"), 		// TODO: DA MODIFICARE IN co2
			rs.getInt("emissioni_decibel") 		// TODO: DA MODIFICARE IN decibel
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
