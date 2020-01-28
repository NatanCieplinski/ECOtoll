package mvc.controller.normative;

import mvc.model.Veicolo;

public class Normativa2021 extends Normativa2019 implements Normativa{
    @Override
    public float differenziazione(float prezzo, Veicolo veicolo){
    	if(veicolo.getPeso() > 100) {
    		// Veicolo pesante
    		prezzo += 1.5f;
    	}
    	
    	if(veicolo.getCo2() > 100 || veicolo.getDecibel() > 100) {
    		// Veicolo inquinante
    		prezzo *= 1.1;
    	}
    	
    	if(veicolo.getCo2() < 100 && veicolo.getDecibel() < 100) {
    		// Eco sconto
    		prezzo *= 0.9;
    	}
    	
    	return prezzo;
    }

    @Override
    public float round(float prezzo) {
    	return (float)(Math.floor(prezzo*10))/10;
    }
    
    @Override
    public float getIva() {
    	return 1.24f;
    }
}
