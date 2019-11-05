package mvc.view.UIController;

import java.util.LinkedList;

import dao.implementation.AutostradaDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import mvc.model.Autostrada;

public class HomeController {

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
    
    // creazione automatica menu items
    
    AutostradaDao el = new AutostradaDao();
    
    private LinkedList<MenuItem> itemList = new LinkedList<MenuItem>();
    private LinkedList<Autostrada> autostradeList = (LinkedList<Autostrada>) el.getAll();
    for(Autostrada a: itemList) {
    	
    	MenuItem prov = new MenuItem();
    	prov.setText(a.getNome());
    	
    	itemList.add(prov);
    	
    }
    

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
    private TextField TExtFieldPrezzoBiglietto;

    @FXML
    private Separator Separetor;

}
