package mvc.model;

import java.util.HashMap;

import dao.implementation.AutostradaDao;

public class Autostrada{

    private int id;
    private String nome;
    private int tipo;
    private HashMap<Integer,Casello> caselli = new HashMap<Integer,Casello>();      //Lista dei caselli <id,nome>
    private HashMap<String, Float> tariffe = new HashMap<String, Float>();        //Lista di tariffe <costo,tipo_veicolo>

    private AutostradaDao modelDao;
    
    public Autostrada(int id, String nome, int tipo){
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.modelDao = new AutostradaDao();
        
        try {
            this.setCaselli(this.modelDao.getCaselli(this));
            this.setTariffe(this.modelDao.getTariffe(this));
        }catch(Exception e){
        	System.out.println(e.getMessage());
        }
    }

    // SETTERS
    //assenza del set del tipo perche' non puo' cambiare dopo la prima istanza 
    
    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
        
    public void setCaselli(HashMap<Integer,Casello> caselli) {
    	this.caselli = caselli;
    }
    
    public void setTariffe(HashMap<String, Float> tariffe) {
    	this.tariffe = tariffe;
    }

    // GETTERS
    public int getId(){
        return this.id;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public int getTipo(){
        return this.tipo;
    }
    
    public HashMap<Integer,Casello> getCaselli(){
        return this.caselli;
    }
    
    public HashMap<String,Float> getTariffe(){
        return this.tariffe;
    }
    
}