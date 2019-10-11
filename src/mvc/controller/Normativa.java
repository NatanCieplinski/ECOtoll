package mvc.controller;

import mvc.model.Veicolo;
import mvc.model.VeicoloA;
import mvc.model.VeicoloB;
import mvc.model.Veicolo3;
import mvc.model.Veicolo4;
import mvc.model.Veicolo5;

import java.util.HashMap;
//la classe normativa serve per raggruppare tutto l'insieme di normative attuali e future da implementare
public class Normativa{

    private static float IVA = 0.22f;

    private static String classificaVeicolo(Veicolo veicolo){
        //TODO implementazione classificazione veicolo
        return "prova";
    }

    //il veicolo verrà creato dalla classe normativa, ricevendo la targa dal gestore autostradale

    // public static float calcoloTariffa(Veicolo veicolo, int id) {
    	
    // 	HashMap<String, Float> tariffe; //Da ottenere dall'autostrada

    // 	if (veicolo instanceof VeicoloA) return tariffe.get("A");
    // 	if (veicolo instanceof VeicoloB) return tariffe.get("B");
    // 	if (veicolo instanceof Veicolo3) return tariffe.get("3");
    // 	if (veicolo instanceof Veicolo4) return tariffe.get("4");
    // 	return tariffe.get("5");
    // }

    public static double arrotondamentoPrezzo(double prezzo) {
    	prezzo = (double)(Math.round(prezzo*10))/10;
    	return prezzo;
    }
    
    //L'aggiunta delle tariffe, di IVA e di maggiorazioni avviene all'interno di normativa
    //TODO metodo che modifica le tariffe dell'autostrada
    //TODO metodo che aggiunge le tariffe dell'autostrada
}