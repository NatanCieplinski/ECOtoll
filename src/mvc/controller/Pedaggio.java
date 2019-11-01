package mvc.controller;

import mvc.model.Veicolo;
import mvc.model.Casello;

import dao.implementation.*;

public class Pedaggio{

    private static Normativa normativa;

    public Pedaggio(){
        this.normativa = new Normativa();
    }

    public static float calcoloPedaggio(Casello caselloIngresso, Veicolo veicolo, Casello caselloUscita){
        
        int km = 1; //DB: query da implementare (km caselloIngresso - km caselloUscita)

        return normativa.arrotondamentoPrezzo(normativa.calcoloTariffa(veicolo, caselloUscita) * km * normativa.getIVA() * normativa.maggiorazioni());
        
    }
}