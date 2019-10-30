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
		final String query = "INSERT INTO biglietto(targa, ingresso) VALUES( ?,? );";
		
		this.openDB();
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setString(1, biglietto.getTarga());
		stmt.setInt(2, biglietto.getIdCaselloIngresso());
		stmt.execute();
		this.closeDB(stmt, null);
	}

	@Override
	public Optional<Biglietto> read(Object id) throws DBException, SQLException{ 
		final String query = "SELECT * FROM biglietto WHERE id=?;";
		
		this.openDB();
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setLong(1, (long)id);
		
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
			rs.getInt("ingresso"),
			rs.getString("targa")
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
