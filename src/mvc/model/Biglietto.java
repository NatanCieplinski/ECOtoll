package mvc.model;

//il biglietto è stato implementato come oggetto sia perchè è una copia virtuale del biglietto fisico, per verificare che non ci siano manomissioni, sia perchè agisce da biglietto virtuale per un'eventuale implementazione telepass

public class Biglietto{

    private int idCaselloIngresso;
    private String targa;
    private boolean carrello;
    private int numeroAssiCarrello;

    public Biglietto(int idCaselloIngresso, String targa){
        this.idCaselloIngresso = idCaselloIngresso;
        this.targa = targa;
    }

     //i metodi set non sono implementati perchè il biglietto una volta creato non viene mai modificato 
    public int getIdCaselloIngresso(){
        return idCaselloIngresso;
    }
    public String getTarga(){
        return this.targa;
    }
    public boolean carrello(){
        return this.carrello;
    }
    public int numeroAssiCarrello(){
        return this.numeroAssiCarrello;
    }
    
    @Override
    public boolean equals(Object b) {
        if (b instanceof Biglietto) {  
	        return this.getIdCaselloIngresso() == ((Biglietto)b).getIdCaselloIngresso() &&
	        	   this.getTarga().equals(((Biglietto)b).getTarga());
        } 

        return false;
    }
}