package mvc.model;

public class Veicolo{

    private int id;
    private int altezza;
    private int numeroAssi;
    //per semplicità assumiamo che il carrello sia dichiarato nell'imattricolazione
    private boolean carrello;
    private int numeroAssiCarrello;
    private String targa;
    //inquinamento acustico e atmosferico per future normative
    private int co2;
    private int decibel;

    private String modello;
    private String marca;
    private int anno;
    private int peso;

    public Veicolo(int id, int altezza, int numeroAssi, boolean carrello, int numeroAssiCarrello, String targa, int co2, int decibel){
        this.id = id;
        this.altezza = altezza;
        this.numeroAssi = numeroAssi;
        this.carrello = carrello;
        this.numeroAssiCarrello = numeroAssiCarrello;
        this.targa = targa;
        this.co2 = co2;
    }

    public Veicolo(int id, int altezza, int numeroAssi, boolean carrello, int numeroAssiCarrello, String targa, int co2, int decibel, String modello, String marca, int anno, int peso){
        this.id = id;
        this.altezza = altezza;
        this.numeroAssi = numeroAssi;
        this.carrello = carrello;
        this.numeroAssiCarrello = numeroAssiCarrello;
        this.targa = targa;
        this.co2 = co2;
        this.decibel = decibel;
        this.modello = modello;
        this.marca = marca;
        this.anno = anno;
        this.peso = peso;
    }

    //implementazione dei set solo per il carrello perchè sono gli unici elementi che possono cambiare del veicolo.
    public void setCarrello(boolean carrello){
        this.carrello = carrello;
    }
    public void setNumeroAssiCarrello(int numeroAssiCarrello){
        this.numeroAssiCarrello = numeroAssiCarrello;
    }

    public int getId(){
        return this.id;
    }
    public int getAltezza(){
        return this.altezza;
    }
    public int getNumeroAssi(){
        return this.numeroAssi;
    }
    public boolean getCarrello(){
        return this.carrello;
    }
    public int getNumeroAssiCarrello(){
        return this.numeroAssiCarrello;
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