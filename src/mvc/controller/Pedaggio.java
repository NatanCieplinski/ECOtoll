package mvc.controller;

import mvc.model.Veicolo;

import dao.implementation.*;

public class Pedaggio{

    private static Normativa normativa;

    public Pedaggio(){
        this.normativa = new Normativa();
    }

    public static float calcoloPedaggio(int idCaselloIngresso, Veicolo veicolo, int idCaselloUscita){
        
        int km = 1; //DB: query da implementare (km caselloIngresso - km caselloUscita)

        return normativa.arrotondamentoPrezzo(normativa.calcoloTariffa(veicolo, idCaselloUscita) * km * normativa.getIVA() * normativa.maggiorazioni());
        
    }
}