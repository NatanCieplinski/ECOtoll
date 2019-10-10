package mvc.model;

public class Casello{
    
    private int id;
    private String nome;
    private int idAutostradaDiAppartenenza;
    private int kilometro;
    
    public Casello(int id, String nome, int idAutostradaDiAppartenenza, int kilometro){
        this.id = id;
        this.nome = nome;
        this.idAutostradaDiAppartenenza = idAutostradaDiAppartenenza;
        this.kilometro = kilometro;
    }


}