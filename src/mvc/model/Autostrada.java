package mvc.model;

import java.util.HashMap;

public class Autostrada{

    /**
     * attributi
     */
    private int id;
    private String nome;
    private int tipo;
    private HashMap<Integer,Casello> caselli = new HashMap<Integer,Casello>();      //Lista dei caselli
    private HashMap<Integer,String> tariffe = new HashMap<Integer,String>();        //Lista di tariffe


}