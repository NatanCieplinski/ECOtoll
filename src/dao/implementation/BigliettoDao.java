package dao.implementation;

import java.util.Optional;
import java.util.LinkedList;
import java.util.List;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.database.DBManager;
import dao.interfaces.BigliettoDaoI;
import mvc.model.Biglietto;

public class BigliettoDao extends DBManager implements BigliettoDaoI {
	private Connection db;
	
	public BigliettoDao() { this.db = this.getDbInstance(); }
	
	public List<Biglietto> getAll() throws SQLException{
		final String query = "SELECT * FROM biglietto;";

		PreparedStatement stmt = this.db.prepareStatement(query);
		List<Biglietto> biglietti = this.makeList(stmt.executeQuery());
		stmt.close();
		
		return biglietti;
	}
	
	/*
	 * CRUD: Implementare in caso di necessita
	 * */
	@Override
	public void create(String[] params) throws SQLException{
		final String query = "INSERT INTO biglietto(targa, ingresso, carrello, n_assi_carrello) VALUES( ?,?,?,? );";

		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setString(1, params[0]);
		stmt.setInt(2, Integer.parseInt(params[1]));
		stmt.setBoolean(3, Boolean.parseBoolean(params[2]));
		stmt.setInt(4, Integer.parseInt(params[3]));
		
		stmt.execute(); stmt.close();
	}

	@Override
	public Optional<Biglietto> read(Object targa) throws SQLException{ 
		final String query = "SELECT * FROM biglietto WHERE targa=?;";
		
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setString(1, (String)targa);
		
		ResultSet rs = stmt.executeQuery();
		rs.next();
		Biglietto biglietto = this.makeObj(rs);

		stmt.close(); rs.close();	
		
		return Optional.ofNullable(biglietto); 
	} 

	@Override
	public void update(Biglietto biglietto, String[] params){}

	@Override
	public void delete(Biglietto biglietto) throws SQLException{
		final String query = "DELETE FROM biglietto WHERE targa=?;";
		
		
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setString(1, biglietto.getTarga());
		stmt.execute(); stmt.close();
		
	}
	
	public Biglietto makeObj(ResultSet rs) throws SQLException{
		return new Biglietto(
			rs.getString("targa"),
			rs.getInt("ingresso"),
			rs.getBoolean("carrello"),
			rs.getInt("n_assi_carrello")
		);
	}
	
	public List<Biglietto> makeList(ResultSet rs) throws SQLException{
		List<Biglietto> biglietti = new LinkedList<Biglietto>();
		while ( rs.next() ) {
			biglietti.add(this.makeObj(rs));
		}
		return biglietti;
	}
}
