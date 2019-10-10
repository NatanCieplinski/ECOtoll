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

    public void setId(int id){
        this.id = id;
    }
    public void setAltezza(int altezza){
        this.altezza = altezza;
    }
    public void setNumeroAssi(int numeroAssi){
        this.numeroAssi = numeroAssi;
    }
    public void setCarrello(boolean carrello){
        this.carrello = carrello;
    }
    public void setNumeroAssiCarrello(int numeroAssiCarrello){
        this.numeroAssiCarrello = numeroAssiCarrello;
    }
    public void setTarga(String targa){
        this.targa = targa;
    }
    public void setModello(String modello){
        this.modello = modello;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    public void setAnno(int anno){
        this.anno = anno;
    }
    public void setPeso(int peso){
        this.peso = peso;
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