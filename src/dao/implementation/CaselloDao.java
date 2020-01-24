package dao.implementation;

import java.util.Optional;
import java.util.LinkedList;
import java.util.List;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.database.DBManager;
import dao.interfaces.CaselloDaoI;
import mvc.model.Autostrada;
import mvc.model.Casello;

public class CaselloDao extends DBManager implements CaselloDaoI {
	private Connection db;
	
	public CaselloDao() { this.db = this.getDbInstance(); }
	
	public List<Casello> getAllFromAutostrada(Autostrada autostrada) throws SQLException{
		final String query = "SELECT * FROM casello WHERE autostrada=?;";
		
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setInt(1, autostrada.getId());
		
		ResultSet rs = stmt.executeQuery();
		List<Casello> caselli = this.makeList(rs);
		
		stmt.close(); rs.close();
		
		return caselli; 
	}
	
	/*
	 * CRUD: Implementare in caso di necessita
	 * */
	@Override
	public void create(String[] params) throws SQLException{
		final String query = "INSERT INTO casello(autostrada, nome, chilometro) VALUES( ?,?,? );";
		
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setInt(1, Integer.parseInt(params[0]));
		stmt.setString(2, params[1]);
		stmt.setFloat(3, Float.parseFloat(params[2]));
		
		stmt.execute(); stmt.close();
	}

	@Override
	public Optional<Casello> read(Object id) throws SQLException{
		final String query = "SELECT * FROM casello WHERE id=?;";
	
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setInt(1, (int)id);
		
		ResultSet rs = stmt.executeQuery();
		rs.next();
		Casello casello = this.makeObj(rs);
		
		stmt.close(); rs.close();
		
		return Optional.ofNullable(casello); 
	}

	@Override
	public void update(Casello casello, String[] params) throws SQLException{
		final String query = "UPDATE casello SET autostrada=?, nome=?, chilometro=? WHERE id=?;";
		
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setInt(1, Integer.parseInt(params[0]));
		stmt.setString(2, params[1]);
		stmt.setFloat(3, Float.parseFloat(params[2]));
		stmt.setInt(4, casello.getId());
		
		stmt.execute(); stmt.close();
	}

	@Override
	public void delete(Casello casello) throws SQLException{
		final String query = "DELETE FROM casello WHERE id=?;";
		
		PreparedStatement stmt = this.db.prepareStatement(query);
		stmt.setInt(1, casello.getId());
		stmt.execute();
		stmt.close();
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
