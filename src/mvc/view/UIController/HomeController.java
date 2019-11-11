package mvc.view.UIController;

import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ResourceBundle;

import com.sun.xml.internal.bind.v2.schemagen.episode.Bindings;

import dao.exceptions.DBException;
import dao.implementation.AutostradaDao;
import dao.implementation.CaselloDao;
import dao.implementation.VeicoloDao;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mvc.controller.GestoreAutostradale;
import mvc.controller.Pedaggio;
import mvc.model.Autostrada;
import mvc.model.Casello;
import mvc.model.Veicolo;


import javafx.scene.control.ToggleGroup;




public class HomeController implements Initializable {
	
	// vsriabili di cacolo 
	private Integer autostradaSelezionata = null;	// id dell'autostrada selezionata
	private Integer caselloSelezionato = null;		// id del casello selezionato
	private Integer veicoloSelezionato = null;
	private Integer io = null;						// id ingresso-uscita
//	private Integer sn = null;
	private Integer ud = null;
	private boolean sn;
	
	Veicolo v;
	String targa;
	

	

	    @FXML
	    private AnchorPane anchorPane;

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
	    private TextField TextFieldPrezzoBiglietto;

	    @FXML
	    private Separator Separetor;

	    @FXML
	    private TextField TectFieldTarga;

	    @FXML
	    private Label LabelTarga;

	    @FXML
	    private Button BtnInvio;
	    

	    @FXML
	    private Label labelPr;

    
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
		
	}
	
	// metodo che setta i caselli da mostrare in base all'autostrada selezionata
	public void setMenuItemsCaselli() {
		
		if (autostradaSelezionata != null) MenuButtonSettoreCasello.setDisable(false);
		MenuButtonSettoreCasello.getItems().clear();
		
		CaselloDao il = new CaselloDao();
		AutostradaDao daoAuto = new AutostradaDao();
	    LinkedList<Casello> caselloList = null;
		
	    try {
	    	caselloList = (LinkedList<Casello>)il.getAllFromAutostrada(daoAuto.read(autostradaSelezionata).get());
		} catch (DBException | SQLException e) {
			System.out.println("Errore caricamento casello ( getAllFromAutostrada() ) dal databese");
			e.printStackTrace();
		}
	    
	    for(Casello c: caselloList) {
	    	
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
		
		targa = TectFieldTarga.getText();
		System.out.println(targa);
		
		
			if( io == 0   ){ 
				
				System.out.println("Biglietto selezionato su uscita"); 
				
			}else{
				
				if( io == null || ud == null || targa==null ) {    //stampa anche se non si seleziona il carrello
					System.out.println("compila tutti i campi");
				}else {
				
					GestoreAutostradale ga = new GestoreAutostradale();
					ga.ingresso( targa ,caselloSelezionato , sn , ud);
				}
				
			}
			
    }
	/*
	@FXML
	void Invia(MouseEvent event) {
		
		targa = TectFieldTarga.getText();
		System.out.println(targa);
		
	}
	*/
	
    @FXML
    void clickIngresso(MouseEvent event) {
    	io = 1;
    	
    	
    	RdDue.setDisable(false);
    	RdUno.setDisable(false);
    	RdSi.setDisable(false);
    	RdNo.setDisable(false);
    	

    	ToggleGroup radioGroup2 = new ToggleGroup ();
    	RdIngresso.setToggleGroup (radioGroup2);
    	RdUscita.setToggleGroup (radioGroup2);
    }

    @FXML
    void Pagamento(MouseEvent event) {

    }
    

    @FXML
    void clickSi(MouseEvent event) {
    	
    	sn = true;
    	
		ToggleGroup radioGroup3 = new ToggleGroup ();
		  RdSi.setToggleGroup (radioGroup3);
		   RdNo.setToggleGroup (radioGroup3);
    }

    
    
    @FXML
    void clickDue(MouseEvent event) {
   // 	Button b = new button("")

	//	if (autostradaSelezionata != null) MenuButtonSettoreCasello.setDisable(false);
	//	MenuButtonSettoreCasello.getItems().clear();
    	
    	
   // 	private void isDelivery(ActionEvent event){
    //	    txtAddress.setDisable(!rdDelivery.isArmed());
    //	    txtEmail.setDisable(!rdDelivery.isArmed());
    	
  	//  rdDelivery.selectedProperty().bind(Bindings.not(txtAddress.disabledProperty()));
    //	rdDelivery.selectedProperty().bind(Bindings.not(txtEmail.disabledProperty()));
    	
/*        if (io == 1) {
        	RdDue.setDisable(true);
        	
        	//clickDue.selectedProperty().bind(Bindings.not(RdSi.disabledProperty()));
        //	RdSi.setDisable(RdIngresso.isArmed());
        
        }else {
        	RdDue.setDisable(false);       	
         } 	
        	*/
       	
        	
    	ud = 0 ;
    	
    	ToggleGroup radioGroup1 = new ToggleGroup ();
    	RdUno.setToggleGroup (radioGroup1);
    	RdDue.setToggleGroup (radioGroup1);
    	
    	
    }
    	


    @FXML
    void clickNo(MouseEvent event) {

   // 	RdNo.selectedProperty().bind(Bindings.not(RdIngresso.disabledProperty()));
    	
    	sn = false ;
    	
    }

    @FXML
    void clickUno(MouseEvent event) {
    	
    	ud = 1;

    }

    @FXML
    void clickUscita(MouseEvent event) {
    	

        	RdDue.setDisable(true);
        	RdUno.setDisable(true);
        	RdSi.setDisable(true);
        	RdNo.setDisable(true);
        	
        	//clickDue.selectedProperty().bind(Bindings.not(RdSi.disabledProperty()));
        //	RdSi.setDisable(RdIngresso.isArmed());
        
      //  }else {
      //  	RdDue.setDisable(false);
        	
      //  } 	
    	

    	io = 0;
    	
    }

    @FXML
    void paga(MouseEvent event) {
    
    	float prezzo = 0;
    	
    	GestoreAutostradale au = new GestoreAutostradale();
		prezzo = au.calcoloPrezzo(targa , caselloSelezionato);
	
    
		labelPr.setText(prezzo + " €");
		//pannelloBase.setDisable(true);
		//pannelloPedaggio.setVisible(true);

		
		
    	//labelPr.setText(au);

    }

  
    
    

}
