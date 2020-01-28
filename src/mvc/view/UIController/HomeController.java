
package mvc.view.UIController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import mvc.controller.GestoreAutostradale;
import mvc.controller.normative.Normativa;
import mvc.controller.normative.NormativaFactory;
import mvc.model.Autostrada;
import mvc.model.Biglietto;
import mvc.model.Casello;
import mvc.model.Veicolo;



import javafx.scene.control.ToggleGroup;

public class HomeController implements Initializable {
	
	private int tipoCasello = -1; 
	private int numeroAssiCarrello = 0;
	private boolean hasCarrello;

	private Casello caselloSelezionato = null; 
	private Autostrada autostradaSelezionata = null; 
	private Normativa normativaSelezionata = null;

	private LinkedList<String> targhe = new LinkedList<String>();
	private String targa;
	
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
	private AnchorPane APHome;	
	@FXML
	private AnchorPane APAggiungiRimuovi;
	@FXML
	private AnchorPane APModificaCasello;

	/*
	 * Menu Buttons
	 * */
	@FXML
	private MenuButton MBCaselloModifica;
	@FXML
	private MenuButton MBCaselloAggRim;
	@FXML
	private MenuButton MBCasello;  
	@FXML
	private MenuButton MBAutostradaAggiungi;	
	@FXML
	private MenuButton MBAutostradaModifica;
	@FXML
	private MenuButton MBAutostradaRimuovi;
	@FXML
	private MenuButton MBAutostrada;
	@FXML
    private MenuButton MBNormativa;

	/*
	 * Text Fields
	 * */
	@FXML
	private TextField TFAutostradaAggiunta;
	@FXML
	private TextField TFCaselloAggiunto;
	@FXML
	private TextField TFChilometroAggiunto;   
	@FXML
	private TextField TFNomeCasello;
	@FXML
	private TextField TFChilometroCasello;
	@FXML
	private TextField TFCerca;

	/*
	 * Buttons
	 * */ 
	@FXML
	private Button BTNSalva;   
	@FXML
	private Button BTNAggRim;	
	@FXML
	private Button BTNModifica;
	@FXML
	private Button BTNAggiungi;
	@FXML
	private Button BTNRimuovi;
	@FXML
	private Button BTNPaga;
	@FXML
	private Button BTNEmettiBiglietto;
	@FXML
	private Button BTNIndietroUno;   
	@FXML
	private Button BTNIndietroDue;

	
	/* ***************************
	*          METODI
	******************************/ 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		/*
		* Autostrade dropmenu init
		* */ 
		AutostradaDao autstradaDao = new AutostradaDao();
		LinkedList<Autostrada> autostradaList = null;
		
		try {
			autostradaList = (LinkedList<Autostrada>)autstradaDao.getAll();
		} catch (Exception e) {
			System.out.println("Errore caricamento autostrade ( getAll() ) dal database");
			e.printStackTrace();
		}
		
		for(Autostrada autostrada: autostradaList) {
			MenuItem menuItem = new MenuItem(autostrada.getNome());
			
			// evento di click sul MenuItem
			menuItem.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					autostradaSelezionata = autostrada;
					MBAutostrada.setText(autostradaSelezionata.getNome());
					setMICaselli(0);					
				}
				
			});
			
			MBAutostrada.getItems().add(menuItem);
		}

		/*
		* Listener normative
		* */
		ArrayList<Pair<Integer, String>> normative = new ArrayList<Pair<Integer, String>>();
		normative.add(new Pair<Integer, String>(2019,"Normativa 2019"));
		normative.add(new Pair<Integer, String>(2021,"Normativa 2021"));
		normative.add(new Pair<Integer, String>(2026,"Normativa 2026"));
		Iterator<Pair<Integer, String>> it = normative.iterator();
		
		while (it.hasNext()){
			Pair<Integer, String> curr = it.next();
			MenuItem item = new MenuItem(curr.getValue());

			item.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					normativaSelezionata = NormativaFactory.getNormativa(curr.getKey());
					MBNormativa.setText(item.getText());				
				}
				
			});
			MBNormativa.getItems().add(item);
		}
		
		
		/*
		* Listener ricerca targa
		* */ 
		TFCerca.textProperty().addListener(new ChangeListener<String>() {
			
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				ObservableList<String> prov = FXCollections.observableArrayList();;
				String input = TFCerca.getText();
				input = "^" + input + "[\\w]*\\s*[\\d]*\\s*[\\w]*";
				Pattern pattern = Pattern.compile(input);
				
				for (String s : targhe) {
					Matcher m = pattern.matcher(s);
					LVTarghe.getItems().clear();
					
					while(m.find()) {
						prov.add(m.group());
					}
					
				}
				
				LVTarghe.setItems(prov);
				
			}
			
		});
		
	}

	/*
	 * Caselli dropdown init
	 * */ 
	public void setMICaselli(int var) {
   
		if (this.autostradaSelezionata != null) {
			MBCasello.setDisable(false);
			MBCaselloModifica.setDisable(false);
			MBCaselloAggRim.setDisable(false);
		}
	
		MBCasello.getItems().clear();
		MBCaselloModifica.getItems().clear();
		MBCaselloAggRim.getItems().clear();
		
		CaselloDao il = new CaselloDao();
		AutostradaDao daoAuto = new AutostradaDao();
		LinkedList<Casello> caselloList = null;

		try {
			caselloList = (LinkedList<Casello>) il.getAllFromAutostrada(daoAuto.read(this.autostradaSelezionata.getId()).get());
		} catch (Exception e) {
			System.out.println("Errore caricamento casello ( getAllFromAutostrada() ) dal database");
			e.printStackTrace();
		}

		for (Casello c : caselloList) {

			MenuItem prov = new MenuItem(c.getNome());

			// evento che cattura il click su ogni menuItem casello
			prov.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					caselloSelezionato = c;
					// cambio testo nel menu btn
					MBCaselloModifica.setText(caselloSelezionato.getNome());
					MBCaselloAggRim.setText(caselloSelezionato.getNome());
					MBCasello.setText(caselloSelezionato.getNome());

					
					TFNomeCasello.setText(caselloSelezionato.getNome());
					TFChilometroCasello.setText(Integer.toString(caselloSelezionato.getChilometro()));
					
				}
			});
			
			

			if (var == 0) {
				
				MBCasello.getItems().add(prov);
				
			} if (var == 1) {
				
				MBCaselloModifica.getItems().add(prov);
				
			} if (var == 2) {
				
				MBCaselloAggRim.getItems().add(prov);
				
			}

		}

	}
	
	/*
	 * Resetta la scritta dei caselli quando si clicca 
	 * sulla dropdown dell'autostrada
	 * */ 
	@FXML
	void clickMBAutostrada(MouseEvent event) {
		MBCasello.setText("Seleziona Casello");
	}
	 
	/*
	 * Evento che genera un nuovo biglietto
	 * */ 
	@FXML
	public void clickBTNEmettiBiglietto(MouseEvent event) {
		this.targa = TFCerca.getText();
		
		if (this.tipoCasello == 0) {
			System.out.println("Casello selezionato su uscita");
		} else {

			if (this.tipoCasello == -1 || this.targa == null) { 
				System.out.println("Compila tutti i campi");
			} else {
				GestoreAutostradale gestoreAutostradale = new GestoreAutostradale();
				
				gestoreAutostradale.ingresso(
					this.targa, 
					this.caselloSelezionato.getId(), 
					this.hasCarrello, 
					this.numeroAssiCarrello
				);
			}
		}		
	}

	/*
	 * Click del radio button del tipo casello
	 * */ 
	@FXML
	void clickRDIngresso(MouseEvent event) throws SQLException {
		// Casello di ingresso
		this.tipoCasello = 1;

		RDCarrelloDue.setDisable(false);
		RDCarrelloUno.setDisable(false);
		RDCarrelloSi.setDisable(false);
		RDCarrelloNo.setDisable(false);
		MBNormativa.setDisable(true);

		ToggleGroup radioGroup = new ToggleGroup();
		RDIngresso.setToggleGroup(radioGroup);
		RDUscita.setToggleGroup(radioGroup);

		// Aggiornamento della lista targhe 
		this.targhe.clear();
		LVTarghe.getItems().clear();
		
		VeicoloDao veicoloDao = new VeicoloDao();
		List<Veicolo> veicoli = (List<Veicolo>)veicoloDao.getAll();
		
		for (Veicolo veicolo : veicoli) {
			this.targhe.add(veicolo.getTarga());
		}
		
		for(String targa: this.targhe) {
			LVTarghe.getItems().add(targa);
		}
	}
	
	@FXML
	void clickRDUscita(MouseEvent event) throws SQLException {
		// Casello di uscita
		this.tipoCasello = 0;

		RDCarrelloDue.setDisable(true);
		RDCarrelloUno.setDisable(true);
		RDCarrelloSi.setDisable(true);
		RDCarrelloNo.setDisable(true);
		MBNormativa.setDisable(false);

		// Aggiornamento della lista targhe 
		this.targhe.clear();
		LVTarghe.getItems().clear();

		BigliettoDao bigliettoDao = new BigliettoDao();
		List<Biglietto> biglietti = (List<Biglietto>)bigliettoDao.getAll(); 
		
		for (Biglietto biglietto : biglietti) {
			this.targhe.add(biglietto.getTarga());
		}
		
		for(String targa: this.targhe) {
			LVTarghe.getItems().add(targa);
		}
	}

	/*
	 * Click del radio button del carrello si/no
	 * */ 
	@FXML
	void clickRDCarrelloSi(MouseEvent event) {
		this.hasCarrello = true;

		ToggleGroup radioGroup = new ToggleGroup();
		RDCarrelloSi.setToggleGroup(radioGroup);
		RDCarrelloNo.setToggleGroup(radioGroup);
	}
	@FXML
	void clickRDCarrelloNo(MouseEvent event) {
		this.hasCarrello = false;
		this.numeroAssiCarrello = 0;

		ToggleGroup radioGroup = new ToggleGroup();
		RDCarrelloSi.setToggleGroup(radioGroup);
		RDCarrelloNo.setToggleGroup(radioGroup);
	}

	/*
	 * Click del radio button del numero assi carrello
	 * */ 
	@FXML
	void clickRDCarrelloUno(MouseEvent event) {
		if(this.hasCarrello)
			this.numeroAssiCarrello = 1;
	}
	@FXML
	void clickRDCarrelloDue(MouseEvent event) {
		if(this.hasCarrello)
			this.numeroAssiCarrello = 2;

		ToggleGroup radioGroup = new ToggleGroup();
		RDCarrelloUno.setToggleGroup(radioGroup);
		RDCarrelloDue.setToggleGroup(radioGroup);
	}

	/*
	 * Click di un elemento del dropdown di normativa
	 * */ 
	 @FXML
	 void clickMBNormativa(MouseEvent event) {

	 }

	/*
	 * Click del bottone paga
	 * */ 
	@FXML
	void clickBTNPaga(MouseEvent event) {
		float prezzo = 0;
		this.targa = TFCerca.getText();

		GestoreAutostradale gestoreAutostradale = new GestoreAutostradale();
		
		prezzo = gestoreAutostradale.calcoloPrezzo(
			this.targa, 
			this.caselloSelezionato.getId(),
			this.normativaSelezionata
		);
		
		try {
			BigliettoDao bigliettoDao = new BigliettoDao();
			bigliettoDao.delete(new Biglietto(this.targa, this.caselloSelezionato.getId(), this.hasCarrello, this.numeroAssiCarrello));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		LPrezzo.setText(prezzo + " €");
	}
	
	/*
	 * Click della navigazione
	 * */ 
	@FXML
	void clickBTNIndietroUno(MouseEvent event) {
	 	APModificaCasello.setVisible(false);
		MBCasello.setText("Seleziona Casello");
	}
	@FXML
	void clickBTNIndietroDue(MouseEvent event) {
		APAggiungiRimuovi.setVisible(false);
		MBCasello.setText("Seleziona Casello");
	}
	

	/* *************************
	 * 	PANNELLO MODIFICA CASELLO
	 ***************************/
	
	/*
	 * Click del bottone per il salvataggio delle modifiche al casello
	 * */ 
	@FXML
	void clickBTNSalva(MouseEvent event) throws SQLException {
	
		CaselloDao caselloDao = new CaselloDao();
		String[] params = new String[3];
		params[0] = Integer.toString(this.autostradaSelezionata.getId());
		params[1] = TFNomeCasello.getText();
		params[2] = TFChilometroCasello.getText();
		caselloDao.update(this.caselloSelezionato, params);
			
		MBAutostradaModifica.setText("Seleziona Autostrada");
		MBCaselloModifica.setText("Seleziona Casello");
		TFNomeCasello.clear();
		TFChilometroCasello.clear();
	}

	/*
	 * Click del bottone andare alla schermata modifica casello
	 * */ 
	@FXML
	void clickBTNModifica(MouseEvent event) throws SQLException {
		APModificaCasello.setVisible(true);
		
		TFNomeCasello.clear();
		TFChilometroCasello.clear();
		MBCaselloModifica.getItems().clear();
		MBAutostradaModifica.getItems().clear();
		MBCaselloModifica.setText("Seleziona Casello");
		MBAutostradaModifica.setText("Seleziona Autostrada");
		
		AutostradaDao autostradaDao = new AutostradaDao();
		LinkedList<Autostrada> autostrade = null;
			
		try {
			autostrade = (LinkedList<Autostrada>)autostradaDao.getAll();
		} catch (Exception e) {
			System.out.println("Errore caricamento autostrade ( getAll() ) dal database");
			e.printStackTrace();
		}
			
		for(Autostrada autostrada: autostrade) {
			MenuItem menuItem = new MenuItem(autostrada.getNome());
				
			// evento di click sul MenuItem
			menuItem.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					autostradaSelezionata = autostrada;
					setMICaselli(1);
					MBAutostradaModifica.setText(autostradaSelezionata.getNome());
				}
			});
			
			MBAutostradaModifica.getItems().add(menuItem);
		}	
	}
 	
	/*
	 * Click del dropdown di autostrada nel pannello agg/rim
	 * */ 
	@FXML
	void clickMBAutostradaModifica(MouseEvent event) {
		MBCaselloModifica.setText("Seleziona Casello");	
	}


	/* *************************
	 * 	PANNELLO AGG/RIM CASELLO
	 ***************************/

	/*
	 * Click del bottone per aggiungere un casello
	 * */ 
	@FXML
	void clickBTNAggiungi(MouseEvent event) throws SQLException {
			
		CaselloDao dao = new CaselloDao();
		String[] params = new String[3];
		params[0] = Integer.toString(this.autostradaSelezionata.getId());
		params[1] = TFCaselloAggiunto.getText();
		params[2] = TFChilometroAggiunto.getText();
		dao.create(params);
			
		TFCaselloAggiunto.clear();
		TFChilometroAggiunto.clear();
		MBAutostradaAggiungi.setText("Seleziona Autostrada");
	} 

	/*
	 * Click del bottone per la rimozione di un casello
	 * */ 
	@FXML
	void clickBTNRimuovi(MouseEvent event) throws SQLException {
		CaselloDao dao = new CaselloDao();
			
		dao.delete(this.caselloSelezionato);
		
		MBAutostradaRimuovi.setText("Seleziona Autostrada");
		MBCaselloAggRim.setText("Seleziona Casello");
	}
	 	
	/*
	 * Click del dropdown di autostrada nel pannello agg/rim
	 * */ 
	@FXML
	void clickMBAutostradaRimuovi(MouseEvent event) {
		MBCaselloAggRim.setText("Seleziona Casello");	
	}

	/*
	 * Click del bottone per andare alla schermata agg/rim casello
	 * */ 
	@FXML
	void clickBTNAggRim(MouseEvent event) {
		APAggiungiRimuovi.setVisible(true);
			
		TFCaselloAggiunto.clear();
		TFChilometroAggiunto.clear();
		MBCaselloAggRim.getItems().clear();
		MBAutostradaRimuovi.getItems().clear();
		MBAutostradaAggiungi.getItems().clear(); 
		MBCaselloAggRim.setText("Seleziona Casello");
		MBAutostradaRimuovi.setText("Seleziona Autostrada");
		MBAutostradaAggiungi.setText("Seleziona Autostrada");

		AutostradaDao autostradaDao = new AutostradaDao();
		LinkedList<Autostrada> autostrade = null;
			
		try {
			autostrade = (LinkedList<Autostrada>)autostradaDao.getAll();
		} catch (Exception e) {
			System.out.println("Errore caricamento autostrade ( getAll() ) dal database");
			e.printStackTrace();
		}
			
		for(Autostrada autostrada: autostrade) {
			MenuItem menuItem = new MenuItem(autostrada.getNome());
			MenuItem menuItemDue = new MenuItem(autostrada.getNome());
				
			// evento di click sul MenuItem
			menuItem.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					autostradaSelezionata = autostrada;
							
					setMICaselli(2);
					MBAutostradaRimuovi.setText(autostradaSelezionata.getNome());
				}
			});

			menuItemDue.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					autostradaSelezionata = autostrada;
									
					setMICaselli(2);
					MBAutostradaAggiungi.setText(autostradaSelezionata.getNome());
				}
			});
				
			MBAutostradaRimuovi.getItems().add(menuItem);
			MBAutostradaAggiungi.getItems().add(menuItemDue);					 	
		}				
	}
}
