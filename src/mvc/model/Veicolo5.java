package mvc.model;

public class Veicolo5 extends Veicolo{

    public Veicolo5(int altezza, int numeroAssi, String targa, int co2, int decibel, int euro, String modello, String marca, int anno, int peso){
        super(altezza, numeroAssi, targa, co2, euro, decibel, modello, marca, anno, peso);
    }
    
    @Override
    public String getType(){ return "5"; }    
}