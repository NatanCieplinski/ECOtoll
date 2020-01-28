package mvc.controller.normative;

public class NormativaFactory {
	public static Normativa getNormativa(int anno){
		switch(anno) {
			case 2021: 
				return new Normativa2021();
			case 2026: 
				return new Normativa2026();
			default: 
				return new Normativa2019();
		}
	}
}
