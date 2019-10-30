package mvc.controller;

import mvc.model.Biglietto;
import mvc.model.Veicolo;
import mvc.model.VeicoloA;
import mvc.model.VeicoloB;
import mvc.model.Veicolo3;
import mvc.model.Veicolo4;
import mvc.model.Veicolo5;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dao.implementation.BigliettoDao;
import dao.implementation.VeicoloDao;

/*
 * La classe normativa serve per raggruppare tutto l'insieme di normative attuali e future da implementare
 * */
public class Normativa{

    private static final float IVA = 0.22f;
    
    //public Veicolo creaVeicolo(String targa, boolean carrello, int numeroAssiCarrello){
    	//TODO creare classe pedaggio che si occupa delle informazioni 
    //   if (altezza < 130 && assi == 2){
    //         return new VeicoloA(id, altezza, numeroAssi, carrello, numeroAssiCarrello, targa, co2, decibel, modello, marca, anno, peso);
    //     }
    //     if (altezza > 130 && assi == 2){
    //         return new VeicoloB(id, altezza, numeroAssi, carrello, numeroAssiCarrello, targa, co2, decibel, modello, marca, anno, peso);
    //     }
    //     if (assi == 3){
    //         return new Veicolo3(id, altezza, numeroAssi, carrello, numeroAssiCarrello, targa, co2, decibel, modello, marca, anno, peso);
    //     }
    //     if (assi == 4){
    //         return new Veicolo4(id, altezza, numeroAssi, carrello, numeroAssiCarrello, targa, co2, decibel, modello, marca, anno, peso);
    //     }
    //     return new Veicolo5(id, altezza, numeroAssi, carrello, numeroAssiCarrello, targa, co2, decibel, modello, marca, anno, peso);
    // }

    //il veicolo verra  creato dalla classe normativa, ricevendo la targa dal gestore autostradale

    // public static float calcoloTariffa(Veicolo veicolo, int id) {
    	
    // 	HashMap<String, Float> tariffe; //Da ottenere dall'autostrada

    // 	if (veicolo instanceof VeicoloA) return tariffe.get("A");
    // 	if (veicolo instanceof VeicoloB) return tariffe.get("B");
    // 	if (veicolo instanceof Veicolo3) return tariffe.get("3");
    // 	if (veicolo instanceof Veicolo4) return tariffe.get("4");
    // 	return tariffe.get("5");
    //}

    public static double arrotondamentoPrezzo(double prezzo) {
    	prezzo = (double)(Math.round(prezzo*10))/10;
    	return prezzo;
    }
    
    //L'aggiunta delle tariffe, di IVA e di maggiorazioni avviene all'interno di normativa
    //TODO metodo che modifica le tariffe dell'autostrada
    //TODO metodo che aggiunge le tariffe dell'autostrada
}
