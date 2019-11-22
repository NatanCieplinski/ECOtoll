package mvc.controller;

import mvc.model.Veicolo;
import mvc.model.Casello;

public class Pedaggio{

    public static float calcoloPedaggio(Veicolo veicolo, Casello caselloIngresso, Casello caselloUscita){
        
        int km = Math.abs(caselloIngresso.getChilometro() - caselloUscita.getChilometro());
        System.out.println(km);
        float f1 = Normativa.calcoloTariffa(veicolo, caselloUscita);
        System.out.println(f1);
        float f2 = km;
        System.out.println(f2);
        float f3 = Normativa.getIVA();
        System.out.println(f3);
        float f4 = Normativa.maggiorazioni();
        System.out.println(f4);
        return Normativa.arrotondamentoPrezzo(f1 * f2 * f3 * f4);
        
    }
}