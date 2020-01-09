
package mvc.view.UIController;

import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.exceptions.DBException;
import dao.implementation.AutostradaDao;
import dao.implementation.BigliettoDao;
import dao.implementation.CaselloDao;
import dao.implementation.VeicoloDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mvc.controller.GestoreAutostradale;
import mvc.model.Autostrada;
import mvc.model.Biglietto;
import mvc.model.Casello;
import mvc.model.Veicolo;



import javafx.scene.control.ToggleGroup;

public class HomeController implements Initializable {

	// VARIABILI DI CALCOLO
	
	private Autostrada autostradaSelezionata = null; 
	private Casello caselloSelezionato = null; 
	private int tipoCasello = -1; 
	private int numeroAssiCarrello = 0;
	private boolean sn;

	static ObservableList<String> items = FXCollections.observableArrayList();
	static LinkedList<String> targhe = new LinkedList<String>();
	
	Veicolo v;
	String targa;
	
	int idCaselloModifica;
	int idCaselloElimina;
	int idCaselloAggiunta;
	int var;

	/*
	 * Radio Buttons
	 * */
	@FXML
	private RadioButton RDCarrelloNo;
	@FXML
	private RadioButton RDCarrelloSi;
	@FXML
	private RadioButton RDIngresso;
	@FXML
	private RadioButton RDUscita;
	@FXML
	private RadioButton RDCarrelloUno;
	@FXML
	private RadioButton RDCarrelloDue;

	/*
	 * Labels
	 * */
	@FXML
	private Label LPrezzo;

	/*
	 * List Views
	 * */
	@FXML
	private ListView<String> LVTarghe;

	/*
	 * Anchor Panels
	 * */
    @FXML
    private AnchorPane APAggiungiRimuovi;
	@FXML
	private AnchorPane APHome;	
	@FXML
	private AnchorPane APModificaCasello;

	/*
	 * Menu Buttons
	 * */
    @FXML
    private MenuButton MenuButtonSettoreCaselloModifica;
    @FXML
    private MenuButton MenuButtonSettoreCaselloAggRim;
	@FXML
	private MenuButton MenuButtonSettoreCasello;  
    @FXML
    private MenuButton MenuButtonSettoreAutostradaAggiungi;	
    @FXML
    private MenuButton MenuButtonSettoreAutostradaModifica;
    @FXML
    private MenuButton MenuButtonSettoreAutostradaRimuovi;
	@FXML
	private MenuButton MenuButtonSettoreAutostrada;

	/*
	 * Text Fields
	 * */
    @FXML
    private TextField AutostradaAggiunta;
    @FXML
    private TextField CaselloAggiunto;
    @FXML
    private TextField ChilometroAggiunto;   
    @FXML
    private TextField nomeCaselloModifica;
    @FXML
    private TextField chilometroCaselloModifica;
	@FXML
	private TextField TFcerca;

	/*
	 * Buttons
	 * */
    @FXML
    private Button BtnSole;   
    @FXML
    private Button Indietro2;
    @FXML
    private Button BtnAggiungi;   
    @FXML
    private Button Salva;   
    @FXML
    private Button Indietro;   
    @FXML
    private Button BntAggRim;	
	@FXML
	private Button BntModifica;
	@FXML
	private Button BntAggiungi;
	@FXML
	private Button BntRimuovi;
	@FXML
	private Button BntPaga;
	@FXML
	private Button BntEmettiBiglietto;



	// METODI

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		AutostradaDao el = new AutostradaDao();
	    LinkedList<Autostrada> autostradaList = null;
	    
	    try {
	    	autostradaList = (LinkedList<Autostrada>)el.getAll();
		} catch (DBException | SQLException e) {
			System.out.println("Errore caricamento autostrade ( getAll() ) dal database");
			e.printStackTrace();
		}
	    
	    for(Autostrada a: autostradaList) {
	    	MenuItem prov = new MenuItem(a.getNome());
	    	
	    	// evento di click sul MenuItem
	    	prov.setOnAction(new EventHandler<ActionEvent>() {
	    		
				@Override
				public void handle(ActionEvent event) {
					
					autostradaSelezionata = a;
					//System.out.println(autostradaSelezionata.getId());
					setMenuItemsCaselli(0);
					MenuButtonSettoreAutostrada.setText(autostradaSelezionata.getNome());
					
				}
				
	    	});
	    	
	    	MenuButtonSettoreAutostrada.getItems().add(prov);
	    
	    }
	    
	    
	    // Ricerca del testo
	    TFcerca.textProperty().addListener(new ChangeListener<String>() {
	    	
	    	@Override
	    	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	    		
	    		ObservableList<String> prov = FXCollections.observableArrayList();;
	    		String input = TFcerca.getText();
	    		//System.out.println(input);
	    		input = "^" + input + "[\\w]*\\s*[\\d]*\\s*[\\w]*";
	    		//System.out.println(input);
	    		Pattern pattern = Pattern.compile(input);
	    		
	    		for (String s : targhe) {
	    			//System.out.println("sto valutando " +  s);
	    			Matcher m = pattern.matcher(s);
	    			LVTarghe.getItems().clear();
	    			//System.out.println("k");
	    			
	    			while(m.find()) {
	    				//System.out.println("stringa" + m.group());
	    				prov.add(m.group());
	    			}
	    			
	    		}
	    		
	    		LVTarghe.setItems(prov);
	    		
	    	}
	    	
	    });
		
	}

	// Metodo che setta i caselli da mostrare in base all'autostrada selezionata
	public void setMenuItemsCaselli(int var) {
   
		if (autostradaSelezionata != null) {
			MenuButtonSettoreCasello.setDisable(false);
			MenuButtonSettoreCaselloModifica.setDisable(false);
			MenuButtonSettoreCaselloAggRim.setDisable(false);
		}
	
		MenuButtonSettoreCasello.getItems().clear();
		MenuButtonSettoreCaselloModifica.getItems().clear();
		MenuButtonSettoreCaselloAggRim.getItems().clear();
		
		CaselloDao il = new CaselloDao();
		AutostradaDao daoAuto = new AutostradaDao();
		LinkedList<Casello> caselloList = null;

		try {
			caselloList = (LinkedList<Casello>) il.getAllFromAutostrada(daoAuto.read(autostradaSelezionata.getId()).get());
		} catch (DBException | SQLException e) {
			System.out.println("Errore caricamento casello ( getAllFromAutostrada() ) dal databese");
			e.printStackTrace();
		}

		for (Casello c : caselloList) {

			MenuItem prov = new MenuItem(c.getNome());

			// evento che cattura il click su ogni menuItem casello
			prov.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					caselloSelezionato = c;
					//System.out.println(caselloSelezionato.getId());
					// cambio testo nel menu btn
					MenuButtonSettoreCaselloModifica.setText(caselloSelezionato.getNome());
					MenuButtonSettoreCaselloAggRim.setText(caselloSelezionato.getNome());
					MenuButtonSettoreCasello.setText(caselloSelezionato.getNome());

					
					nomeCaselloModifica.setText(caselloSelezionato.getNome());
					chilometroCaselloModifica.setText(Integer.toString(caselloSelezionato.getChilometro()));
					
				}
			});
			
			

			if (var == 0) {
				
				MenuButtonSettoreCasello.getItems().add(prov);
				
			} if (var == 1) {
				
				MenuButtonSettoreCaselloModifica.getItems().add(prov);
				
			} if (var == 2) {
				
				MenuButtonSettoreCaselloAggRim.getItems().add(prov);
				
			}

		}

	}
	
	@FXML
	void Cancella(MouseEvent event) {
		 
		MenuButtonSettoreCasello.setText("Seleziona Casello");
		 	
	}
	 
	@FXML
	public void EmettiBIglietto(MouseEvent event) {

		targa = TFcerca.getText();

		System.out.println(targa);
		System.out.println(this.tipoCasello);
		
		if (this.tipoCasello == 0) {

			System.out.println("Biglietto selezionato su uscita");

		} else {

			if (this.tipoCasello == -1 || targa == null) { 
				
				System.out.println("compila tutti i campi");
				
				
			} else {

				GestoreAutostradale ga = new GestoreAutostradale();
				ga.ingresso(targa, caselloSelezionato.getId(), sn, numeroAssiCarrello);
				
			}

		}
		
	}

	@FXML
	void clickIngresso(MouseEvent event) throws DBException, SQLException {
		this.tipoCasello = 1;
		
		System.out.println("dio cane");
		RDCarrelloDue.setDisable(false);
		RDCarrelloUno.setDisable(false);
		RDCarrelloSi.setDisable(false);
		RDCarrelloNo.setDisable(false);

		ToggleGroup radioGroup2 = new ToggleGroup();
		RDIngresso.setToggleGroup(radioGroup2);
		RDUscita.setToggleGroup(radioGroup2);

		// prendiamo le automobili che potrebbero entrare nell'autostrada
		
		VeicoloDao v = new VeicoloDao();
		List<Veicolo> listaAuto = (List<Veicolo>)v.getAll();
		targhe.clear();
		
    	for (Veicolo ve : listaAuto) {
    		targhe.add(ve.getTarga());
    	}
    	
    	LVTarghe.getItems().clear();
    	
    	for(String targa: targhe) {
    		LVTarghe.getItems().add(targa);
    	}
 
	}

	@FXML
	void clickSi(MouseEvent event) {

		sn = true;

		ToggleGroup radioGroup3 = new ToggleGroup();
		RDCarrelloSi.setToggleGroup(radioGroup3);
		RDCarrelloNo.setToggleGroup(radioGroup3);
		
	}

	@FXML
	void clickDue(MouseEvent event) {

		numeroAssiCarrello = 2;

		ToggleGroup radioGroup1 = new ToggleGroup();
		RDCarrelloUno.setToggleGroup(radioGroup1);
		RDCarrelloDue.setToggleGroup(radioGroup1);

	}

	@FXML
	void clickNo(MouseEvent event) {

		sn = false;

	}

	@FXML
	void clickUno(MouseEvent event) {

		numeroAssiCarrello = 1;

	}

	@FXML
	void clickUscita(MouseEvent event) throws DBException, SQLException {

		RDCarrelloDue.setDisable(true);
		RDCarrelloUno.setDisable(true);
		RDCarrelloSi.setDisable(true);
		RDCarrelloNo.setDisable(true);

		this.tipoCasello = 0;

		// prendiamo le automobili che potrebbero entrare nell'autostrada
		BigliettoDao b = new BigliettoDao();
		List<Biglietto> listaBiglietti = (List<Biglietto>)b.getAll(); // chiamata al metodo getAll();
		targhe.clear();
		
    	for (Biglietto bi : listaBiglietti) {
    		targhe.add(bi.getTarga());
    	}
    	
    	LVTarghe.getItems().clear();
    	
    	for(String targa: targhe) {
    		LVTarghe.getItems().add(targa);
    	}

	}

	@FXML
	void paga(MouseEvent event) {

		float prezzo = 0;

		GestoreAutostradale au = new GestoreAutostradale();
		System.out.println(targa);
		System.out.println(caselloSelezionato.getId());
		
		prezzo = au.calcoloPrezzo(targa, caselloSelezionato.getId());

		LPrezzo.setText(prezzo + " €");
		
	}

	@FXML
	void TFcerca(ActionEvent event) {
		  		 
	}
	
	 	 @FXML
	     void ClickIndietro(MouseEvent event) {
	 		APModificaCasello.setVisible(false);
	    	MenuButtonSettoreCasello.setText("Seleziona Casello");

	     }

	 	@FXML
	    void SelAutMod(MouseEvent event) {
	 		
	 		 MenuButtonSettoreCaselloModifica.setText("Seleziona Casello");		//
	 		 MenuButtonSettoreCaselloModifica.getItems().clear();	

	    }
	 	
	 	@FXML
	    void SelAutMod2(MouseEvent event) {
	 		
	 		MenuButtonSettoreCaselloAggRim.setText("Seleziona Casello");	
	 		
	    }

	    @FXML
	    void ClickModifica(MouseEvent event) throws DBException, SQLException {

			APModificaCasello.setVisible(true);
			
			 MenuButtonSettoreAutostradaModifica.setText("Seleziona Autostrada");
			 MenuButtonSettoreAutostradaModifica.getItems().clear();
			 nomeCaselloModifica.clear();
			 chilometroCaselloModifica.clear();
			 MenuButtonSettoreCaselloModifica.getItems().clear();
			 MenuButtonSettoreCaselloModifica.setText("Seleziona Casello");

			AutostradaDao el = new AutostradaDao();
		    LinkedList<Autostrada> autostradaList = null;
			
		    try {
		    	autostradaList = (LinkedList<Autostrada>)el.getAll();
			} catch (DBException | SQLException e) {
				System.out.println("Errore caricamento autostrade ( getAll() ) dal databese");
				e.printStackTrace();
			}
		    
		    for(Autostrada a: autostradaList) {
		    	MenuItem prov = new MenuItem(a.getNome());
		    	
		    	// evento di click sul MenuItem
		    	prov.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
	
						autostradaSelezionata = a;
						System.out.println(autostradaSelezionata.getId());
				
						setMenuItemsCaselli(1);
						MenuButtonSettoreAutostradaModifica.setText(autostradaSelezionata.getNome());
					}
					
		    	});

		    	MenuButtonSettoreAutostradaModifica.getItems().add(prov);
		    	 	
		    }	
	    	
	    	
	    }
	    
	    
	@FXML
	void SalvaModifiche(MouseEvent event) throws DBException, SQLException {
    
	    CaselloDao dao = new CaselloDao();
		String[] params = new String[3];
		params[1] = nomeCaselloModifica.getText();
		params[2] = chilometroCaselloModifica.getText();
		dao.update(caselloSelezionato, params);
		    
		MenuButtonSettoreAutostradaModifica.setText("Seleziona Autostrada");
		MenuButtonSettoreCaselloModifica.setText("Seleziona Casello");
		nomeCaselloModifica.clear();
		chilometroCaselloModifica.clear();
			
	    }
	    
	    @FXML
	    void ClickRimuovi(MouseEvent event) throws DBException, SQLException {

	    	CaselloDao dao = new CaselloDao();
	    	
	    	dao.delete(caselloSelezionato);
	    	
	    	MenuButtonSettoreAutostradaRimuovi.setText("Seleziona Autostrada");
	    	MenuButtonSettoreCaselloAggRim.setText("Seleziona Casello");

	    }
	    
	    @FXML
	    void ClickAggRim(MouseEvent event) {
	    	
	    	APAggiungiRimuovi.setVisible(true);
	    	
	    	MenuButtonSettoreAutostradaRimuovi.setText("Seleziona Autostrada");
	    	MenuButtonSettoreAutostradaRimuovi.getItems().clear();
	    	MenuButtonSettoreAutostradaAggiungi.setText("Seleziona Autostrada");
	    	MenuButtonSettoreAutostradaAggiungi.getItems().clear(); 
	    	MenuButtonSettoreCaselloAggRim.getItems().clear();
			MenuButtonSettoreCaselloAggRim.setText("Seleziona Casello");

			CaselloAggiunto.clear();
			ChilometroAggiunto.clear();
	    	
	    	var = 2;

			AutostradaDao el = new AutostradaDao();
		    LinkedList<Autostrada> autostradaList = null;
			
		    try {
		    	autostradaList = (LinkedList<Autostrada>)el.getAll();
			} catch (DBException | SQLException e) {
				System.out.println("Errore caricamento autostrade ( getAll() ) dal databese");
				e.printStackTrace();
			}
		    
		    for(Autostrada a: autostradaList) {
		    	MenuItem prov = new MenuItem(a.getNome());
		    	MenuItem prov2 = new MenuItem(a.getNome());
		    	
		    	// evento di click sul MenuItem
		    	prov.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						autostradaSelezionata = a;
						//System.out.println(autostradaSelezionata);
				
					
						setMenuItemsCaselli(2);
						MenuButtonSettoreAutostradaRimuovi.setText(autostradaSelezionata.getNome());
					}
		    	});

		    	prov2.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						autostradaSelezionata = a;
						//System.out.println(autostradaSelezionata);
				
					
						setMenuItemsCaselli(2);
						MenuButtonSettoreAutostradaAggiungi.setText(autostradaSelezionata.getNome());
					}
		    	});
		    	
		    	MenuButtonSettoreAutostradaRimuovi.getItems().add(prov);
		    	MenuButtonSettoreAutostradaAggiungi.getItems().add(prov2);
		    		 	
		    }	
	    	
	    }

	    @FXML
	    void ClickIndietro2(MouseEvent event) {
	    	
	    	APAggiungiRimuovi.setVisible(false);
	    	MenuButtonSettoreCasello.setText("Seleziona Casello");

	    	
	    }

	    
	    @FXML
	    void ClickAggiungi(MouseEvent event) throws DBException, SQLException {
	    	
		    CaselloDao dao = new CaselloDao();
		    String[] params = new String[3];
		    params[0] = Integer.toString(autostradaSelezionata.getId());
			
			//System.out.println(autostradaSelezionata);
	
		    params[1] = CaselloAggiunto.getText();
		    params[2] = ChilometroAggiunto.getText();
		    dao.create(params);
		    
		    CaselloAggiunto.clear();
		    ChilometroAggiunto.clear();
		    MenuButtonSettoreAutostradaAggiungi.setText("Seleziona Autostrada");

	    }
	    
	 
}
