package mvc.controller;

import mvc.controller.normative.Normativa;
import mvc.model.Biglietto;
import mvc.model.Veicolo;
import mvc.model.Casello;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;

import dao.implementation.*;

public class GestoreAutostradale{

	private BigliettoDao bigliettoDao;
	private CaselloDao caselloDao;

    public GestoreAutostradale(){
        this.bigliettoDao = new BigliettoDao();
		this.caselloDao = new CaselloDao();
    }

    public void ingresso(String targa, int idCaselloIngresso, boolean carrello, int numeroAssiCarrello){
    	try {
			Biglietto b = new Biglietto(targa, idCaselloIngresso, carrello, numeroAssiCarrello);
			String[] params = {targa, Integer.toString(idCaselloIngresso), Boolean.toString(carrello), Integer.toString(numeroAssiCarrello)};
    		bigliettoDao.create(params);
    		this.writeBiglietto(b);
    		System.out.println("biglietto emesso");
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
    }

    public float calcoloPrezzo(String targa, int idCaselloUscita, Normativa normativa){
    	try {
    		// Prende il biglietto dal DB
    		Biglietto biglietto = bigliettoDao.read(targa).get();
    		System.out.println("Biglietto DB: "+biglietto.getIdCaselloIngresso()+" : "+biglietto.getTarga());
    		
			Biglietto bigliettoTxt = this.readBiglietto();

    		// Confronta i due biglietti
    		if(bigliettoTxt.equals(biglietto)) {   			
        		System.out.println("Biglietto valido");     

				//Creazione le informazioni da passare alla classe pedaggio
				Veicolo veicolo = VeicoloFactory.getVeicolo(biglietto.getTarga(), biglietto.getCarrello(), biglietto.getNumeroAssiCarrello());
				Casello caselloIngresso = caselloDao.read(biglietto.getIdCaselloIngresso()).get();
				Casello caselloUscita = caselloDao.read(idCaselloUscita).get();

				return Pedaggio.calcoloPedaggio(veicolo, caselloIngresso, caselloUscita, normativa);  
						
    		} else {
				throw new Exception("Biglietto manomesso");
			}
        }catch(Exception e) {
			System.out.println(e.getMessage());
			return -1;
		} 	
    }

	private void writeBiglietto(Biglietto b) throws IOException{
		// Scrive il biglietto TXT
        List<String> lines = Arrays.asList(
        		b.getTarga(), 
        		Integer.toString(b.getIdCaselloIngresso()), 
        		Boolean.toString(b.getCarrello()),  
        		Integer.toString(b.getNumeroAssiCarrello())
        );
        
        Path file = Paths.get("biglietto.txt");
        Files.write(file, lines, StandardCharsets.UTF_8);
	}
	
	private Biglietto readBiglietto() throws IOException{
		// Legge il e crea il biglietto TXT
    	Path path = Paths.get("biglietto.txt");
    	BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);

    	String targa = reader.readLine();
    	int idCasello = Integer.parseInt(reader.readLine());
    	boolean carrello = Boolean.parseBoolean(reader.readLine());
    	int nAssiCarrello = Integer.parseInt(reader.readLine());

    	return new Biglietto(targa, idCasello, carrello, nAssiCarrello);	
	}

}