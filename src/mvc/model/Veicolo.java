package mvc.model;

import java.util.HashMap;

import test.Main;

public class Veicolo{

    private int altezza;
    private int numeroAssi;
    private String targa;
    private int co2;
    private int decibel;
    private int euro; // 1-6
    private String modello;
    private String marca;
    private int anno;
    private int peso;

    public Veicolo(int altezza, int numeroAssi, String targa, int co2, int decibel, int euro, String modello, String marca, int anno, int peso){
        this.altezza = altezza;
        this.numeroAssi = numeroAssi;
        this.targa = targa;
        this.co2 = co2;
        this.decibel = decibel;
        this.euro = euro;
        this.modello = modello;
        this.marca = marca;
        this.anno = anno;
        this.peso = peso;
    }

    public String getClasse(){ 
        if (this.altezza < 130 && this.numeroAssi <= 2)
            return "A";
        
        if (this.altezza > 130 && this.numeroAssi == 2)
            return "B";
        
        if (this.numeroAssi == 3)
        	return "3";
        
        if (this.numeroAssi == 4)
        	return "4";

        return "5";
    }
    
    public float getTariffa(Casello caselloUscita) {
    	HashMap<String, Float> tariffe = Main.listaAutostrade.get(caselloUscita.getIdAutostradaDiAppartenenza()).getTariffe();
    	return tariffe.get(this.getClasse());
	}
    
    public int getAltezza(){
        return this.altezza;
    }
    public int getNumeroAssi(){
        return this.numeroAssi;
    }
    public String getTarga(){
        return this.targa;
    }
    public int getCo2(){
        return this.co2;
    }
    public int getDecibel(){
        return this.decibel;
    }
    public int getEuro(){
        return this.euro;
    }
    public String getModello(){
        return this.modello;
    }
    public String getMarca(){
        return this.marca;
    }
    public int getAnno(){
        return this.anno;
    }
    public int getPeso(){
        return this.peso;
    }
    
    public void setNumeroAssi(int assi) {
    	this.numeroAssi = assi;
    }
}