package dao.implementation;

import java.util.Optional;

import dao.database.DBManager;
import dao.interfaces.AutostradaDaoI;
import mvc.model.Autostrada;

public class AutostradaDao extends DBManager implements AutostradaDaoI {
	
	// TODO: Implementare le query descritte nell'interfaccia AutostradaDaoI
	
	
	/*
	 * CRUD: Implementare in caso di necessità
	 * */
	@Override
	public void create(Autostrada Autostrada) {}

	@Override
	public Optional<Autostrada> read(long id){ return Optional.ofNullable(null); } 

	@Override
	public void update(Autostrada Autostrada, String[] params){}

	@Override
	public void delete(Autostrada Autostrada) {}
}
