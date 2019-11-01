package mvc.controller;

import mvc.model.Veicolo;
import mvc.model.VeicoloA;
import mvc.model.VeicoloB;
import mvc.model.Veicolo3;
import mvc.model.Veicolo4;
import mvc.model.Veicolo5;

import dao.implementation.VeicoloDao;

import java.util.HashMap;

/*
 * La classe normativa serve per raggruppare tutto l'insieme di normative attuali e future da implementare
 * */
public class Normativa{

    private static final float IVA = 1.22f;
    private VeicoloDao veicoloDao;

    public Normativa(){
        this.veicoloDao = new VeicoloDao();
    }
    
    public Veicolo creaVeicolo(String targa, boolean carrello, int numeroAssiCarrello){
        int assi;
        Veicolo veicolo;
        
        try{
            veicolo = veicoloDao.read(targa).get();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

        if(carrello){
            assi = veicolo.getNumeroAssi() + numeroAssiCarrello;
        } else {
            assi = veicolo.getNumeroAssi();
        }            

        if (veicolo.getAltezza() < 130 && assi == 2){
            return new VeicoloA(veicolo.getId(), veicolo.getAltezza(), assi, targa, veicolo.getCo2(), veicolo.getDecibel(), veicolo.getEuro(), veicolo.getModello(), veicolo.getMarca(), veicolo.getAnno(), veicolo.getPeso());
        }
        if (veicolo.getAltezza() > 130 && assi == 2){
            return new VeicoloB(veicolo.getId(), veicolo.getAltezza(), assi, targa, veicolo.getCo2(), veicolo.getDecibel(), veicolo.getEuro(), veicolo.getModello(), veicolo.getMarca(), veicolo.getAnno(), veicolo.getPeso());
        }
        if (assi == 3){
            return new Veicolo3(veicolo.getId(), veicolo.getAltezza(), assi, targa, veicolo.getCo2(), veicolo.getDecibel(), veicolo.getEuro(), veicolo.getModello(), veicolo.getMarca(), veicolo.getAnno(), veicolo.getPeso());
        }
        if (assi == 4){
            return new Veicolo4(veicolo.getId(), veicolo.getAltezza(), assi, targa, veicolo.getCo2(), veicolo.getDecibel(), veicolo.getEuro(), veicolo.getModello(), veicolo.getMarca(), veicolo.getAnno(), veicolo.getPeso());
        }
        return new Veicolo5(veicolo.getId(), veicolo.getAltezza(), assi, targa, veicolo.getCo2(), veicolo.getDecibel(), veicolo.getEuro(), veicolo.getModello(), veicolo.getMarca(), veicolo.getAnno(), veicolo.getPeso());
        
    }


    public static float calcoloTariffa(Veicolo veicolo, int idCaselloUscita) {
    	
    // 	HashMap<String, Float> tariffe; //DB: Da ottenere dall'autostrada che è ottenuta dal casello

    // 	if (veicolo instanceof VeicoloA) return tariffe.get("A");
    // 	if (veicolo instanceof VeicoloB) return tariffe.get("B");
    // 	if (veicolo instanceof Veicolo3) return tariffe.get("3");
    // 	if (veicolo instanceof Veicolo4) return tariffe.get("4");
    // 	return tariffe.get("5");
        return (float)0;
    }

    public static float maggiorazioni(){
        //Spazio per l'implementazione delle normative future
        //TODO implementare una somma di tutti i decibel e i co2 in un autostrada
        return 1;
    }

    public static float arrotondamentoPrezzo(float prezzo) {
    	prezzo = (float)(Math.round(prezzo*10))/10;
    	return prezzo;
    }

    public float getIVA(){
        return this.IVA;
    }
    
    //L'aggiunta delle tariffe, di IVA e di maggiorazioni avviene all'interno di normativa
    //TODO metodo che modifica le tariffe dell'autostrada
    //TODO metodo che aggiunge le tariffe dell'autostrada
}
