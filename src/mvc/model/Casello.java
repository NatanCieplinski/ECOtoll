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

    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setIdAutostradaDiAppartenenza(int idAutostradaDiAppartenenza){
        this.idAutostradaDiAppartenenza = idAutostradaDiAppartenenza;
    }
    public void setKilometro(int kilometro){
        this.kilometro = kilometro;
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
    public int getKilometro(){
        return this.kilometro;
    }
}