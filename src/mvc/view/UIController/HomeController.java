package mvc.view.UIController;

import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.xml.internal.bind.v2.schemagen.episode.Bindings;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mvc.controller.GestoreAutostradale;
import mvc.controller.Pedaggio;
import mvc.model.Autostrada;
import mvc.model.Biglietto;
import mvc.model.Casello;
import mvc.model.Veicolo;

import javafx.scene.control.ToggleGroup;

public class HomeController implements Initializable {

	// VARIABILI DI CALCOLO
	
	private Integer autostradaSelezionata = null; // id dell'autostrada selezionata
	private Integer caselloSelezionato = null; // id del casello selezionato
	private Integer veicoloSelezionato = null;
	private String targaSelezionata = "";
	private Integer io = null; // id ingresso-uscita
	private Integer ud = null;
	private boolean sn;

	static ObservableList<String> items = FXCollections.observableArrayList();
	static LinkedList<String> targheIngresso = new LinkedList<String>();
	
	Veicolo v;
	String targa;
	
	int idCaselloModifica;

	// private String ricerca = HomeController.text;

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
					setMenuItemsCaselli();
				}
	    	});

	    	MenuButtonSettoreAutostrada.getItems().add(prov);
	    	 	
	    }
	    
	    // listener 
	    TFcerca.textProperty().addListener(new ChangeListener<String>() {
	    	@Override
	    	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	    		//System.out.println(" Text Changed to " + newValue + ")\n");
	    		ObservableList<String> prov = FXCollections.observableArrayList();;
	    		String input = TFcerca.getText();
	    		System.out.println(input);
	    		input = "^" + input + "[\\w]*\\s*[\\d]*\\s*[\\w]*";
	    		System.out.println(input);
	    		Pattern pattern = Pattern.compile(input);
	    		for (String s : targheIngresso) {
	    			System.out.println("sto valutando " +  s);
	    			Matcher m = pattern.matcher(s);
	    			Tab.getItems().clear();
	    			while(m.find()) {
	    				prov.add(m.group());
	    			}
	    		}
	    		Tab.setItems(prov);
	    	}
	    });
		
	}

	// metodo che setta i caselli da mostrare in base all'autostrada selezionata
	public void setMenuItemsCaselli() {

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
					caselloSelezionato = c.getId();
					System.out.println(caselloSelezionato);
				}
			});

			MenuButtonSettoreCasello.getItems().add(prov);

		}

	}

	@FXML
	public void EmettiBIglietto(MouseEvent event) {

		targa = TFcerca.getText();
		System.out.println(targa);

		if (io == 0) {

			System.out.println("Biglietto selezionato su uscita");

		} else {

			if (io == null || ud == null || targa == null) { // stampa anche se non si seleziona il carrello
				System.out.println("compila tutti i campi");
			} else {

				GestoreAutostradale ga = new GestoreAutostradale();
				ga.ingresso(targa, caselloSelezionato, sn, ud);
			}

		}

	}

	@FXML
	void clickIngresso(MouseEvent event) throws DBException, SQLException {
		io = 1;

		//TODO da rimuovere
//		for (String s: targheList) {
//			Tab.getColumns().add(new TableColumn(s));
//		}

//		// inizializzazione della lista che poi andrà riempita dal dao
//		targheIngresso.add("AA 001 AA");
//		targheIngresso.add("AA 002 AA");
//		targheIngresso.add("AA 003 AA");
//		targheIngresso.add("AA 004 AA");
//		targheIngresso.add("AA 005 AA");
//		targheIngresso.add("AB 001 AA");
//		targheIngresso.add("AB 002 AA");
//		targheIngresso.add("AB 003 AA");
//		targheIngresso.add("AB 004 AA");
//		targheIngresso.add("AB 005 AA");
//		
//		for (String t: targheIngresso) {
//			items.add(t);
//		}
//		
//		Tab.setItems(items);
//		
		// fino qui
		
		
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
		
		LinkedList<String> targhe = new LinkedList<String>();
    	for (Veicolo ve : listaAuto) {
    		targhe.add(ve.getTarga());
    	}
    	for(String targa: targhe) {
    		Tab.getItems().add(new String(targa));
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
		LinkedList<String> targhe = new LinkedList<String>();
    	for (Biglietto bi : listaBiglietti) {
    		targhe.add(bi.getTarga());
    	}
    	for(String targa: targhe) {
    		Tab.getItems().add(new String(targa));
    	}

	}

	@FXML
	void paga(MouseEvent event) {

		float prezzo = 0;

		GestoreAutostradale au = new GestoreAutostradale();
		prezzo = au.calcoloPrezzo(targa, caselloSelezionato);

		labelPr.setText(prezzo + " €");
		// pannelloBase.setDisable(true);
		// pannelloPedaggio.setVisible(true);

		// labelPr.setText(au);

	}

	@FXML
	void TFcerca(ActionEvent event) {

//		
//		  VeicoloDao ve = new VeicoloDao(); LinkedList<Veicolo> veicoloList = null;
//		  
//		 try {
//		  
//		  veicoloList = (LinkedList<Veicolo>)ve.read(); } catch (DBException |
//		  SQLException e) { System.out.println("Errore caricamento veicolo");
//		  e.printStackTrace(); }
//		  
//		  for(Veicolo v: veicoloList) {
//		  
//		  MenuItem hola = new MenuItem(v.getTarga());
//		  
//		  
//		  hola.setOnAction(new EventHandler<ActionEvent>() {
//		  
//		  @Override public void handle(ActionEvent event) { targaSelezionata =
//		  v.getTarga(); setTab(); } });
//		  
//		  Tab.getColumns().add(hola);
//		  
//		  
//		  }
//		  
//		  }
		  
		  
		 

	}
	
	 	@FXML
	    void ClickAggiungi(MouseEvent event) {

	    }

	    @FXML
	    void ClickModifica(MouseEvent event) {
	    	
	    	

			pannelloBase.setDisable(true);
			pannelloModificaCasello.setVisible(true);


//	    	idCaselloModifica = 1;
////	    	sceltaCaselloModifica.setText(caselloModifica.getText());
////			
//			Casello casello = dbFactory.getDaoCasello().getCaselloById( idCaselloModifica );
//			labelNomeCaselloModifica.setText( casello.getNome() );
//			labelChilometroCaselloModifica.setText( new Float( casello.getChilometro() ).toString() );
//			labelAutostradaIdCaselloModifica.setText( new Integer( casello.getIdAutostradaAppartenenza() ).toString() );
//			
	    	
	    	
	    }

	    @FXML
	    void ClickRimuovi(MouseEvent event) {

	    }

}
