package dao.implementation;

import java.util.Optional;
import java.util.LinkedList;
import java.util.List;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.database.DBManager;
import dao.interfaces.BigliettoDaoI;
import dao.exceptions.DBException;
import mvc.model.Biglietto;
import mvc.model.Casello;

public class BigliettoDao extends DBManager implements BigliettoDaoI {
	
	// TODO: Implementare le query descritte nell'interfaccia AutostradaDaoI
	
	
	/*
	 * CRUD: Implementare in caso di necessita
	 * */
	@Override
	public void create(Biglietto biglietto) throws DBException, SQLException{
		final String query = "INSERT INTO biglietto(targa, ingresso, carrello, n_assi_carrello) VALUES( ?,?,?,? );";

		this.openDB();
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setString(1, biglietto.getTarga());
		stmt.setInt(2, biglietto.getIdCaselloIngresso());
		stmt.setBoolean(3, biglietto.getCarrello());
		stmt.setInt(4, biglietto.getNumeroAssiCarrello());
		
		stmt.execute();
		
		this.closeDB(stmt, null);
	}

	@Override
	public Optional<Biglietto> read(Object targa) throws DBException, SQLException{ 
		final String query = "SELECT * FROM biglietto WHERE targa=?;";
		
		this.openDB();
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setString(1, (String)targa);
		
		ResultSet rs = stmt.executeQuery();
		rs.next();
		Biglietto biglietto = this.makeObj(rs);
		
		this.closeDB(stmt, null);
		
		return Optional.ofNullable(biglietto); 
	} 

	@Override
	public void update(Biglietto biglietto, String[] params){}

	@Override
	public void delete(Biglietto biglietto) {}
	
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
