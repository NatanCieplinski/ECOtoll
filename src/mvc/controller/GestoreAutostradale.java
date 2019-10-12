package mvc.controller;

import mvc.model.Biglietto;

public class GestoreAutostradale{

    public void ingresso(int idCasello, String targa){
        Biglietto biglietto = new Biglietto(idCasello, targa);
        //TODO DB: insert del biglietto
        //TODO generare file txt con il biglietto
    }

    public void uscita(){
        Biglietto biglietto; //TODO DB: query per informazione sul biglietto
        //TODO lettura da file del biglietto
        //TODO controllo dei dati tra il biglietto e i dati sul txt
        //TODO aggiungere metodo pedaggio
    }
}