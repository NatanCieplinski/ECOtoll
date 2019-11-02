package mvc.controller;

import mvc.model.Veicolo;
import mvc.model.Casello;

public class Pedaggio{

    public static float calcoloPedaggio(Veicolo veicolo, Casello caselloIngresso, Casello caselloUscita){
        
        int km = Math.abs(caselloIngresso.getChilometro() - caselloUscita.getChilometro());

        return Normativa.arrotondamentoPrezzo(Normativa.calcoloTariffa(veicolo, caselloUscita) * km * Normativa.getIVA() * Normativa.maggiorazioni());
        
    }
}