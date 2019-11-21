package mvc.model;

public class Casello{
    
    private int id;
    private String nome;
    private int idAutostradaDiAppartenenza;
    private int chilometro;
    
    public Casello(int id, String nome, int idAutostradaDiAppartenenza, int chilometro){
    	
        this.id = id;
        this.nome = nome;
        this.idAutostradaDiAppartenenza = idAutostradaDiAppartenenza;
        this.chilometro = chilometro;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setIdAutostradaDiAppartenenza(int idAutostradaDiAppartenenza){
        this.idAutostradaDiAppartenenza = idAutostradaDiAppartenenza;
    }
    public void setChilometro(int chilometro){
        this.chilometro = chilometro;
    }

    public int getId(){
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
    public int getIdAutostradaDiAppartenenza(){
        return this.idAutostradaDiAppartenenza;
    }
    public int getChilometro(){
        return this.chilometro;
    }
}