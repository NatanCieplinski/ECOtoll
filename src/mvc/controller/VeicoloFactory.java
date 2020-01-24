package mvc.controller;

import dao.implementation.VeicoloDao;
import mvc.model.Veicolo;
import mvc.model.Veicolo3;
import mvc.model.Veicolo4;
import mvc.model.Veicolo5;
import mvc.model.VeicoloA;
import mvc.model.VeicoloB;

public class VeicoloFactory {
	public static Veicolo getVeicolo(String targa, boolean carrello, int numeroAssiCarrello){
        int assi;
        Veicolo veicolo;
        VeicoloDao veicoloDao = new VeicoloDao();
        
        try{
            veicolo = veicoloDao.read(targa).get();
            System.out.println(veicolo.getTarga());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

        if(carrello){
            assi = veicolo.getNumeroAssi() + numeroAssiCarrello;
        } else {
            assi = veicolo.getNumeroAssi();
        }            

        if (veicolo.getAltezza() < 130 && assi == 2){
            return new VeicoloA(veicolo.getAltezza(), assi, targa, veicolo.getCo2(), veicolo.getDecibel(), veicolo.getEuro(), veicolo.getModello(), veicolo.getMarca(), veicolo.getAnno(), veicolo.getPeso());
        }
        if (veicolo.getAltezza() > 130 && assi == 2){
            return new VeicoloB(veicolo.getAltezza(), assi, targa, veicolo.getCo2(), veicolo.getDecibel(), veicolo.getEuro(), veicolo.getModello(), veicolo.getMarca(), veicolo.getAnno(), veicolo.getPeso());
        }
        if (assi == 3){
            return new Veicolo3(veicolo.getAltezza(), assi, targa, veicolo.getCo2(), veicolo.getDecibel(), veicolo.getEuro(), veicolo.getModello(), veicolo.getMarca(), veicolo.getAnno(), veicolo.getPeso());
        }
        if (assi == 4){
            return new Veicolo4(veicolo.getAltezza(), assi, targa, veicolo.getCo2(), veicolo.getDecibel(), veicolo.getEuro(), veicolo.getModello(), veicolo.getMarca(), veicolo.getAnno(), veicolo.getPeso());
        }
        return new Veicolo5(veicolo.getAltezza(), assi, targa, veicolo.getCo2(), veicolo.getDecibel(), veicolo.getEuro(), veicolo.getModello(), veicolo.getMarca(), veicolo.getAnno(), veicolo.getPeso());
        
    }
}
