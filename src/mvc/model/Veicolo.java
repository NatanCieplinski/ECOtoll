package mvc.model;

public class Veicolo{

    private int id;
    private int altezza;
    private int numeroAssi;
    private boolean carrello;
    private int numeroAssiCarrello;
    private String targa;

    private String modello;
    private String marca;
    private int anno;
    private int peso;

    public Veicolo(int id, int altezza, int numeroAssi, boolean carrello, int numeroAssiCarrello, String targa){
        this.id = id;
        this.altezza = altezza;
        this.numeroAssi = numeroAssi;
        this.carrello = carrello;
        this.numeroAssiCarrello = numeroAssiCarrello;
        this.targa = targa;
    }

    public Veicolo(int id, int altezza, int numeroAssi, boolean carrello, int numeroAssiCarrello, String targa, String modello, String marca, int anno, int peso){
        this.id = id;
        this.altezza = altezza;
        this.numeroAssi = numeroAssi;
        this.carrello = carrello;
        this.numeroAssiCarrello = numeroAssiCarrello;
        this.targa = targa;
        this.modello = modello;
        this.marca = marca;
        this.anno = anno;
        this.peso = peso;
    }
}