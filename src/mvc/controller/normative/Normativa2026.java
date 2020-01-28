package mvc.controller.normative;

import mvc.model.Veicolo;

public class Normativa2026 extends Normativa2021 implements Normativa {
    @Override
    public float differenziazione(float prezzo, Veicolo veicolo){    	
    	if(veicolo.getEuro() > 4) {
    		// Veicolo inquinante
    		prezzo *= 1.15;
    	}
    	if(veicolo.getCo2() < 100 && veicolo.getDecibel() < 100) {
    		// Eco sconto
    		prezzo *= 0.85;
		}
    	return prezzo;
    }
    
    @Override
    public float getIva() {
    	return 1.21f;
    }
}
