package mvc.model;

public class Biglietto{

    private int idCaselloIngresso;
    private String targa;

    public Biglietto(int idCaselloIngresso, String targa){
        this.idCaselloIngresso = idCaselloIngresso;
        this.targa = targa;
    }

    public void setIdCaselloIngresso(int idCaselloIngresso){
        this.idCaselloIngresso = idCaselloIngresso;
    }
    public void settarga(String targa){
        this.targa = targa;
    }
    
    public int getIdCaselloIngress(){
        return idCaselloIngresso;
    }
    public String getTarga(){
        return this.targa;
    }
}