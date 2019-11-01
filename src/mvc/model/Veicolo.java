package mvc.model;

public class Veicolo{

    private int altezza;
    private int numeroAssi;
    //per semplicità assumiamo che il carrello sia dichiarato nell'imattricolazione
    private String targa;
    //inquinamento acustico e atmosferico per future normative
    private int co2;
    private int decibel;
    private int euro; // 1-6
    private String modello;
    private String marca;
    private int anno;
    private int peso;


    public Veicolo(int altezza, int numeroAssi, boolean carrello, int numeroAssiCarrello, String targa, int co2, int decibel, int euro){
        this.altezza = altezza;
        this.numeroAssi = numeroAssi;
        this.targa = targa;
        this.co2 = co2;
        this.euro = euro;
    }

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

    //implementazione dei set solo per il carrello perchè sono gli unici elementi che possono cambiare del veicolo.

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
}