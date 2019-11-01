package mvc.controller;

import mvc.model.Veicolo;
import mvc.model.Casello;

public class Pedaggio{

    private static Normativa normativa;

    public Pedaggio(){
        this.normativa = new Normativa();
    }

    public static float calcoloPedaggio(Veicolo veicolo, Casello caselloIngresso, Casello caselloUscita){
        
        int km = Math.abs(caselloIngresso.getChilometro() - caselloUscita.getChilometro());

        return normativa.arrotondamentoPrezzo(normativa.calcoloTariffa(veicolo, caselloUscita) * km * normativa.getIVA() * normativa.maggiorazioni());
        
    }
}