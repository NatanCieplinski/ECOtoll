package mvc.view.UIController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import mvc.controller.GestoreAutostradale;
import mvc.controller.Pedaggio;
import mvc.model.Casello;
import mvc.model.Veicolo;
import sun.applet.Main;
import dao.database.DBManager;
import dao.exceptions.DBException;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class HomeController{
	
	private Main main;

	// ATTRIBUTI
	
	//private DBManager dbManager = DBManager.getDBManager(DBManager.MYSQL);
	
	//Videocamera videocamera = new Videocamera();
	Veicolo v;
	String targa;

	int idAutostrada;
	int idAutostradaModificaCasello;
	int idAutostradaAggiuntaCasello;
	int idCaselloIngresso;
	int idCaselloUscita;
	int idCaselloModifica;
	int idCaselloElimina;

	boolean ingresso;
	
	
	// ATTRIBUTI FXML
	
	 	@FXML
	    private BorderPane pannelloPrincipale;

	    @FXML
	    private AnchorPane pannelloAnchor;

	    @FXML
	    private Label labelCarrello;

	    @FXML
	    private MenuButton selettoreAutostrada;

	    @FXML
	    private Label labelTipoDiCasello;

	    @FXML
	    private Label labelAssi;
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	

		@FXML
		private Label Carrello;

		@FXML
		private Label TipoDiCasello;

		@FXML
		private Label Assi;

		@FXML
		private Label SettorePannello;

		@FXML
		private Label Prezzo; 	      

		@FXML
		private MenuButton SettoreAutostrada;

		@FXML
		private MenuButton SettoreCAsello;

		@FXML
		private RadioButton one;

		@FXML
		private RadioButton two;

		@FXML
		private RadioButton Uscita;

		@FXML
		private RadioButton Ingresso;

		@FXML
		private Button EmettiBiglietto;

		@FXML
		private Button Paga;

		@FXML
		private MenuItem Casello1;
		
		@FXML
		private MenuItem Casello2;
		
		@FXML
		private MenuItem Casello3;
		
		@FXML
		private MenuItem Casello4;
		
		@FXML
		private MenuItem Casello5;
		
		@FXML
		private MenuItem Autostrada1;

		@FXML
		private MenuItem Autostrada2;
		
		@FXML
		private MenuItem Autostrada3;
		
		@FXML
		private MenuItem Autostrada4;
		
		@FXML
		private MenuItem Autostrada5;
		
		@FXML
		private MenuItem Autostrada6;
		
		


}
