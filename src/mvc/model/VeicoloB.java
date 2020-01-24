package mvc.model;

public class VeicoloB extends Veicolo{

    public VeicoloB(int altezza, int numeroAssi, String targa, int co2, int decibel, int euro, String modello, String marca, int anno, int peso){
        super(altezza, numeroAssi, targa, co2, decibel, euro, modello, marca, anno, peso);
    }
    
    @Override
    public String getType(){ return "B"; }
    
}