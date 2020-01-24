package mvc.controller;

import mvc.model.Casello;
import mvc.model.Veicolo;
import mvc.model.VeicoloA;
import mvc.model.VeicoloB;
import test.Main;
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
    
    public static float calcoloTariffa(Veicolo veicolo, Casello caselloUscita) {
    	HashMap<String, Float> tariffe = Main.listaAutostrade.get(caselloUscita.getIdAutostradaDiAppartenenza()).getTariffe();
    	return tariffe.get(veicolo.getType());
    }

    public static float maggiorazioni(){
        //Spazio per l'implementazione delle normative future
        return 1;
    }

    public static float arrotondamentoPrezzo(float prezzo) {
    	prezzo = (float)(Math.round(prezzo*10))/10;

    	return prezzo;
    }

    public static float getIVA(){
        return IVA;
    }
    
}
