package mvc.controller;

import mvc.model.Veicolo;
import mvc.controller.normative.Normativa;
import mvc.model.Casello;

public class Pedaggio{
    public static float calcoloPedaggio(Veicolo veicolo, Casello caselloIngresso, Casello caselloUscita, Normativa normativa){
        int km = Math.abs(caselloIngresso.getChilometro() - caselloUscita.getChilometro());
        
        float pedaggio = veicolo.getTariffa(caselloUscita) * km * normativa.getIva();
        
        return normativa.round(normativa.differenziazione(pedaggio, veicolo));
    }
}