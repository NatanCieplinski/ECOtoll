package dao.implementation;

import java.util.Optional;
import java.util.LinkedList;
import java.util.List;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.database.DBManager;
import dao.interfaces.CaselloDaoI;
import dao.exceptions.DBException;
import mvc.model.Autostrada;
import mvc.model.Casello;
import mvc.model.Veicolo;

public class CaselloDao extends DBManager implements CaselloDaoI {
	
	// TODO: Implementare le query descritte nell'interfaccia AutostradaDaoI
	public List<Casello> getAllFromAutostrada(Autostrada autostrada) throws DBException, SQLException{
		final String query = "SELECT * FROM casello WHERE autostrada=?;";
		
		this.openDB();
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setInt(1, autostrada.getId());
		
		ResultSet rs = stmt.executeQuery();
		List<Casello> caselli = this.makeList(rs);
		
		this.closeDB(stmt, null);
		
		return caselli; 
	}
	
	/*
	 * CRUD: Implementare in caso di necessit�
	 * */
	@Override
	public void create(String[] params) throws DBException, SQLException{
		final String query = "INSERT INTO casello(autostrada, nome, chilometro) VALUES( ?,?,? );";
		
		this.openDB();
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setInt(1, Integer.parseInt(params[0]));
		stmt.setString(2, params[1]);
		stmt.setFloat(3, Float.parseFloat(params[2]));
		stmt.execute();
		this.closeDB(stmt, null);
	}

	@Override
	public Optional<Casello> read(Object id) throws DBException, SQLException{
		final String query = "SELECT * FROM casello WHERE id=?;";
	
		this.openDB();
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setInt(1, (int)id);
		
		ResultSet rs = stmt.executeQuery();
		rs.next();
		Casello casello = this.makeObj(rs);
		
		this.closeDB(stmt, null);
		
		return Optional.ofNullable(casello); 
	}

	@Override
	public void update(Casello casello, String[] params) throws DBException, SQLException{
		final String query = "UPDATE casello SET autostrada=?, nome=?, chilometro=? WHERE id=?;";
		
		this.openDB();
		
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setInt(1, Integer.parseInt(params[0]));
		stmt.setString(2, params[1]);
		stmt.setFloat(3, Float.parseFloat(params[2]));
				
		stmt.setInt(4, casello.getId());
		
	
		stmt.execute();
		
		stmt.execute();
		
		this.closeDB(stmt, null);
	}

	@Override
	public void delete(Casello casello) throws DBException, SQLException{
		final String query = "DELETE FROM casello WHERE id=?;";
		
		this.openDB();
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setInt(1, casello.getId());
		stmt.execute();
		this.closeDB(stmt, null);
	}

	@Override
	public Casello makeObj(ResultSet rs) throws SQLException{
		return new Casello(
			rs.getInt("id"),
			rs.getString("nome"),
			rs.getInt("autostrada"),
			rs.getInt("chilometro")
		);
	}

	@Override
	public List<Casello> makeList(ResultSet rs) throws SQLException{
		List<Casello> caselli = new LinkedList<Casello>();
		while ( rs.next() ) {
			caselli.add(this.makeObj(rs));
		}
		return caselli;
	}


	
}
