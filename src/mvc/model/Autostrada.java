package mvc.model;

import java.util.HashMap;

public class Autostrada{

    private int id;
    private String nome;
    private int tipo;
    private HashMap<Integer,Casello> caselli = new HashMap<Integer,Casello>();      //Lista dei caselli <id,nome>
    private HashMap<Integer,String> tariffe = new HashMap<Integer,String>();        //Lista di tariffe <costo,tipo_veicolo>

    public Autostrada(int id, String nome, int tipo){
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    //assenza del set del tipo perchè non può cambiare dopo la prima istanza 

    // TODO set dei caselli e delle tariffe

    public int getId(){
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
    public int getTipo(){
        return this.tipo;
    }
    // TODO get dei caselli e delle tariffe
}