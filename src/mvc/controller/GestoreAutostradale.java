package mvc.controller;

import mvc.model.Biglietto;
import mvc.model.Veicolo;
import dao.implementation.*;

public class GestoreAutostradale{

    private BigliettoDao bigliettoDao;

    public GestoreAutostradale(){
        this.bigliettoDao = new BigliettoDao();
    }

    public void ingresso(int idCasello, String targa){
    	try {
    		bigliettoDao.create(new Biglietto(idCasello, targa));
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        //TODO generare file txt con il biglietto
    }

    public void uscita(){
        //Biglietto biglietto = bigliettoDao.read(id);
        //Veicolo veicolo = Normativa.creaVeicolo(biglietto.getTarga());
        //TODO lettura da file del biglietto
        //TODO controllo dei dati tra il biglietto e i dati sul txt
        // Pedaggio pedaggio = new Pedaggio();
        //TODO aggiungere metodo pedaggio
    }

}