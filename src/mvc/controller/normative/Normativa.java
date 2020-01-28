package mvc.controller.normative;

import mvc.model.Veicolo;

public interface Normativa {
    public default float differenziazione(float prezzo, Veicolo veicolo){
        return prezzo;
    }

    public default float round(float prezzo) {
    	return (float)(Math.round(prezzo*10))/10;
    }
    
    public default float getIva() {
    	return 1.22f;
    }
}
