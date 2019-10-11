package mvc.model;

//il biglietto è stato implementato come oggetto sia perchè è una copia virtuale del biglietto fisico, per verificare che non ci siano manomissioni, sia perchè agisce da biglietto virtuale per un'eventuale implementazione telepass

public class Biglietto{

    private int idCaselloIngresso;
    private String targa;

    public Biglietto(int idCaselloIngresso, String targa){
        this.idCaselloIngresso = idCaselloIngresso;
        this.targa = targa;
    }

     //i metodi set non sono implementati perchè il biglietto una volta creato non viene mai modificato 
    public int getIdCaselloIngress(){
        return idCaselloIngresso;
    }
    public String getTarga(){
        return this.targa;
    }
}