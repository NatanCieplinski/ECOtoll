package mvc.controller;

import dao.implementation.VeicoloDao;
import mvc.model.Veicolo;

public class VeicoloFactory {
	public static Veicolo getVeicolo(String targa, boolean carrello, int numeroAssiCarrello){
        Veicolo veicolo;
        VeicoloDao veicoloDao = new VeicoloDao();
        
        try{
            veicolo = veicoloDao.read(targa).get();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
        veicolo.setNumeroAssi(veicolo.getNumeroAssi() + (carrello ? numeroAssiCarrello : 0) );
        
        return veicolo;
    }
}
