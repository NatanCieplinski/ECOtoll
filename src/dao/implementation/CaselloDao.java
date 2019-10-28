package dao.implementation;

import java.sql.SQLException;
import java.util.Optional;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.ResultSet;

import dao.database.DBManager;
import dao.interfaces.CaselloDaoI;
import mvc.model.Casello;

public class CaselloDao extends DBManager implements CaselloDaoI {
	
	// TODO: Implementare le query descritte nell'interfaccia AutostradaDaoI
	
	
	/*
	 * CRUD: Implementare in caso di necessità
	 * */
	@Override
	public void create(Casello casello) {
	}

	@Override
	public Optional<Casello> read(long id){ return Optional.ofNullable(null); } 

	@Override
	public void update(Casello casello, String[] params){}

	@Override
	public void delete(Casello casello) {}
	
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
