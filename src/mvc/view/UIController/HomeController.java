package mvc.view.UIController;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.database.DBManager;
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
import javafx.event.Event;
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
	
	private DBManager dbFactory = DBManager.getDaoFactory(DBManager.MYSQL);
	
	
	
	
	private Integer autostradaSelezionata = null; // id dell'autostrada selezionata
	private Casello caselloSelezionato = null; 
	private Integer veicoloSelezionato = null;
	private String targaSelezionata = "";
	private Integer io = null; // id ingresso-uscita
	private Integer ud = null;
	private boolean sn;
	private Casello CaselloNuovo = null;
	
	static ObservableList<String> items = FXCollections.observableArrayList();
	static LinkedList<String> targhe = new LinkedList<String>();
	
	Veicolo v;
	String targa;
	
	int idCaselloModifica;
	int idCaselloElimina;
	int idCaselloAggiunta;
	int var;


	@FXML
	private AnchorPane pannelloBase;
	
	@FXML
	private AnchorPane pannelloModificaCasello;

	@FXML
	private Label labelSettorepannello;

	@FXML
	private Label labelTipoDiCasello;

	@FXML
	private Label LabelPrezzo;

	@FXML
	private Label labelPannelloDiControllo;

	@FXML
	private MenuButton MenuButtonSettoreAutostrada;

	@FXML
	private MenuButton MenuButtonSettoreCasello;

	@FXML
	private RadioButton RdSi;

	@FXML
	private ToggleGroup group;

	@FXML
	private RadioButton RdNo;

	@FXML
	private RadioButton RdIngresso;

	@FXML
	private RadioButton RdUscita;

	@FXML
	private RadioButton RdUno;

	@FXML
	private RadioButton RdDue;

	@FXML
	private Label LabelCarrello;

	@FXML
	private Label LabelAssi;

	@FXML
	private Button BntPaga;

	@FXML
	private Button BntEmettiBiglietto;

	@FXML
	private Separator Separetor;

	@FXML
	private TextField TFcerca;

	@FXML
	private Label LabelTarga;

	@FXML
	private Label labelPr;

	@FXML
	private ListView<String> Tab;
	
	@FXML
	private Button BntModifica;

	@FXML
	private Button BntAggiungi;

	@FXML
	private Button BntRimuovi;
	
    @FXML
    private MenuButton MenuButtonSettoreAutostradaModifica;
    
    @FXML
    private MenuButton MenuButtonSettoreCaselloModifica;
    
    @FXML
    private TextField nomeCaselloModifica;

    @FXML
    private TextField chilometroCaselloModifica;
	 
    @FXML
    private Button Salva;
    
    @FXML
    private Button Indietro;
    
    @FXML
    private Button BntAggRim;
    
    @FXML
    private AnchorPane PannelloAggRim;
    
    @FXML
    private Button Indietro2;

    @FXML
    private Button BtnAggiungi;

    @FXML
    private MenuButton MenuButtonSettoreCaselloAggRim;
    

    @FXML
    private MenuButton MenuButtonSettoreAutostradaRimuovi;

    @FXML
    private TextField AutostradaAggiunta;

    @FXML
    private TextField CaselloAggiunto;

    @FXML
    private TextField ChilometroAggiunto;

    @FXML
    private Button BtnSole;

    @FXML
    private MenuButton MenuButtonSettoreAutostradaAggiungi;


	// METODI

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
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
					autostradaSelezionata = a.getId();
					System.out.println(autostradaSelezionata);
					setMenuItemsCaselli(0);
//					MenuButtonSettoreAutostrada.setText(caselloSelezionato.getNome());
				}
				
	    	});
	    	
	    	
	    	MenuButtonSettoreAutostrada.getItems().add(prov);
	    //	 MenuButtonSettoreAutostrada.setText(autostradaSelezionata.getNome());
	    }
	    
	    // listener 
	    TFcerca.textProperty().addListener(new ChangeListener<String>() {
	    	@Override
	    	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	    		//System.out.println(" Text Changed to " + newValue + ")\n");
	    		ObservableList<String> prov = FXCollections.observableArrayList();;
	    		String input = TFcerca.getText();
	    		//System.out.println(input);
	    		input = "^" + input + "[\\w]*\\s*[\\d]*\\s*[\\w]*";
	    		//System.out.println(input);
	    		Pattern pattern = Pattern.compile(input);
	    		for (String s : targhe) {
	    			//System.out.println("sto valutando " +  s);
	    			Matcher m = pattern.matcher(s);
	    			Tab.getItems().clear();
	    			//System.out.println("k");
	    			while(m.find()) {
	    				//System.out.println("stringa" + m.group());
	    				prov.add(m.group());
	    			}
	    		}
	    		Tab.setItems(prov);
	    	}
	    });
		
	}

	// metodo che setta i caselli da mostrare in base all'autostrada selezionata
	public void setMenuItemsCaselli(int var) {
   

		if (autostradaSelezionata != null)
			MenuButtonSettoreCasello.setDisable(false);
		MenuButtonSettoreCasello.getItems().clear();

		CaselloDao il = new CaselloDao();
		AutostradaDao daoAuto = new AutostradaDao();
		LinkedList<Casello> caselloList = null;

		try {
			caselloList = (LinkedList<Casello>) il.getAllFromAutostrada(daoAuto.read(autostradaSelezionata).get());
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
					System.out.println(caselloSelezionato.getId());
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
	public void EmettiBIglietto(MouseEvent event) {

		targa = TFcerca.getText();
		System.out.println(targa);

		if (io == 0) {

			System.out.println("Biglietto selezionato su uscita");

		} else {

			if (io == null || ud == null || targa == null) { 
				System.out.println("compila tutti i campi");
			} else {

				GestoreAutostradale ga = new GestoreAutostradale();
				ga.ingresso(targa, caselloSelezionato.getId(), sn, ud);
			}

		}

	}

	@FXML
	void clickIngresso(MouseEvent event) throws DBException, SQLException {
		io = 1;


		
		RdDue.setDisable(false);
		RdUno.setDisable(false);
		RdSi.setDisable(false);
		RdNo.setDisable(false);

		ToggleGroup radioGroup2 = new ToggleGroup();
		RdIngresso.setToggleGroup(radioGroup2);
		RdUscita.setToggleGroup(radioGroup2);

		
		// prendiamo le automobili che potrebbero entrare nell'autostrada
		
		VeicoloDao v = new VeicoloDao();
		
		List<Veicolo> listaAuto = (List<Veicolo>)v.getAll();

		targhe.clear();
		
    	for (Veicolo ve : listaAuto) {
    		targhe.add(ve.getTarga());
    	}
    	Tab.getItems().clear();
    	for(String targa: targhe) {
    		Tab.getItems().add(targa);
    	}
 

	}

	@FXML
	void Pagamento(MouseEvent event) {

	}

	@FXML
	void clickSi(MouseEvent event) {

		sn = true;

		ToggleGroup radioGroup3 = new ToggleGroup();
		RdSi.setToggleGroup(radioGroup3);
		RdNo.setToggleGroup(radioGroup3);
	}

	@FXML
	void clickDue(MouseEvent event) {

		ud = 0;

		ToggleGroup radioGroup1 = new ToggleGroup();
		RdUno.setToggleGroup(radioGroup1);
		RdDue.setToggleGroup(radioGroup1);

	}

	@FXML
	void clickNo(MouseEvent event) {

		sn = false;

	}

	@FXML
	void clickUno(MouseEvent event) {

		ud = 1;

	}

	@FXML
	void clickUscita(MouseEvent event) throws DBException, SQLException {

		RdDue.setDisable(true);
		RdUno.setDisable(true);
		RdSi.setDisable(true);
		RdNo.setDisable(true);

		io = 0;

		// prendiamo le automobili che potrebbero entrare nell'autostrada
		BigliettoDao b = new BigliettoDao();
		List<Biglietto> listaBiglietti = (List<Biglietto>)b.getAll(); // chiamata al metodo getAll();
		targhe.clear();
    	for (Biglietto bi : listaBiglietti) {
    		targhe.add(bi.getTarga());
    	}
    	Tab.getItems().clear();
    	for(String targa: targhe) {
    		Tab.getItems().add(targa);
    	}

	}

	@FXML
	void paga(MouseEvent event) {

		float prezzo = 0;

		this.targa = TFcerca.getText();
		
		GestoreAutostradale au = new GestoreAutostradale();
		prezzo = au.calcoloPrezzo(targa, caselloSelezionato.getId());

		labelPr.setText(prezzo + " €");
		// pannelloBase.setDisable(true);
		// pannelloPedaggio.setVisible(true);

		// labelPr.setText(au);

	}

	@FXML
	void TFcerca(ActionEvent event) {
		  		 

	}
	
	 	
	 	
	 	 @FXML
	     void ClickIndietro(MouseEvent event) {
	 		pannelloModificaCasello.setVisible(false);
	     }


	    @FXML
	    void ClickModifica(MouseEvent event) throws DBException, SQLException {

			pannelloModificaCasello.setVisible(true);
			
			
			
			
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
	
						autostradaSelezionata = a.getId();
						System.out.println(autostradaSelezionata);
				
						setMenuItemsCaselli(1);
					}
		    	});

		    	MenuButtonSettoreAutostradaModifica.getItems().add(prov);
		    	 	
		    }	
	    	
	    	
	    }
	    
	    
	    @FXML
	    void SalvaModifiches(MouseEvent event) throws DBException, SQLException {
    
		    CaselloDao dao = new CaselloDao();
		    String[] params = new String[3];
		//    params[0] = Integer.toString(autostradaSelezionata);
		    params[1] = nomeCaselloModifica.getText();
		    params[2] = chilometroCaselloModifica.getText();
		    dao.update(caselloSelezionato, params);
			
	    }

	    
	    
	    @FXML
	    void ClickRimuovi(MouseEvent event) throws DBException, SQLException {

	    	CaselloDao dao = new CaselloDao();
	    	
	    	dao.delete(caselloSelezionato);
	    	
	    }
	    
	    @FXML
	    void ClickAggRim(MouseEvent event) {
	    	
	    	PannelloAggRim.setVisible(true);
	    	
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
						autostradaSelezionata = a.getId();
						System.out.println(autostradaSelezionata);
				
					
						setMenuItemsCaselli(2);
					}
		    	});

		    	prov2.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						autostradaSelezionata = a.getId();
						System.out.println(autostradaSelezionata);
				
					
						setMenuItemsCaselli(2);
					}
		    	});
		    	
		    	MenuButtonSettoreAutostradaRimuovi.getItems().add(prov);
		    	MenuButtonSettoreAutostradaAggiungi.getItems().add(prov2);
		    	
		    	 	
		    }	
	    	
	    }
	    

	    @FXML
	    void ClickIndietro2(MouseEvent event) {
	    	
	    	PannelloAggRim.setVisible(false);
	    	
	    }
	    @FXML
	    void Sole(MouseEvent event) {

	    	idCaselloAggiunta = 1;
		
	    }
	    
	    @FXML
	    void ClickAggiungi(MouseEvent event) throws DBException, SQLException {
	    	

		    CaselloDao dao = new CaselloDao();
		    String[] params = new String[3];
		    params[0] = Integer.toString(autostradaSelezionata);
			
			System.out.println(autostradaSelezionata);
	
		    params[1] = CaselloAggiunto.getText();
		    params[2] = ChilometroAggiunto.getText();
		    dao.create(params);

	    }
		
		

}
