package mvc.controller;

import mvc.model.Biglietto;
import mvc.model.Veicolo;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import dao.implementation.*;

public class GestoreAutostradale{

    private BigliettoDao bigliettoDao;
	private Normativa normativa;

    public GestoreAutostradale(){
        this.bigliettoDao = new BigliettoDao();
		this.normativa = new Normativa();
    }

    public void ingresso(int idCasello, String targa){
    	try {
    		bigliettoDao.create(new Biglietto(idCasello, targa));
    		
        	List<String> lines = Arrays.asList(Integer.toString(idCasello), targa);
        	Path file = Paths.get("biglietto.txt");
        	Files.write(file, lines, StandardCharsets.UTF_8);
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
    }

    public void uscita(long idBiglietto, int idCaselloUscita){
    	try {
    		
    		// Prende il biglietto dal DB
    		Biglietto biglietto = bigliettoDao.read(idBiglietto).get();
    		System.out.println("Biglietto DB: "+biglietto.getIdCaselloIngresso()+" : "+biglietto.getTarga());
    		
    		// Legge il e crea il biglietto TXT
    		Path path = Paths.get("biglietto.txt");
    		BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
    		int idCasello = Integer.parseInt(reader.readLine());
    		String targa = reader.readLine();
    		Biglietto bigliettoTxt = new Biglietto(idCasello, targa);
    		System.out.println("Biglietto TXT: "+bigliettoTxt.getIdCaselloIngresso()+" : "+bigliettoTxt.getTarga());
    		
    		// Confronta i due biglietti
    		if(bigliettoTxt.equals(biglietto)) {   			
        		System.out.println("Biglietto valido");       		
    		}else {
        		System.out.println("Biglietto manomesso");
			}

			//Creazione del veicolo e chiamata al metodo pedaggio
			Veicolo veicolo = normativa.creaVeicolo(targa, biglietto.getCarrello(), biglietto.getNumeroAssiCarrello());

        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
    	
        //Veicolo veicolo = Normativa.creaVeicolo(biglietto.getTarga());
        // Pedaggio pedaggio = new Pedaggio();
        //TODO aggiungere metodo pedaggio
    }

}