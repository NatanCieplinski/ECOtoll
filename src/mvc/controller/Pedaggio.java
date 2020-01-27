package mvc.controller;

import mvc.model.Veicolo;
import mvc.model.Casello;

public class Pedaggio{
    public static float calcoloPedaggio(Veicolo veicolo, Casello caselloIngresso, Casello caselloUscita, Normativa normativa){
        int km = Math.abs(caselloIngresso.getChilometro() - caselloUscita.getChilometro());
        
        float pedaggio = normativa.round(
        	veicolo.getTariffa(caselloUscita) * 
        	km * 
        	normativa.getIva()
        );
        
        return normativa.differenziazione(pedaggio, veicolo);
    }
}