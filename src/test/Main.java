package test;
	
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import dao.implementation.AutostradaDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mvc.controller.GestoreAutostradale;
import mvc.model.Autostrada;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane mainLayout;
	private static AutostradaDao autostradaDao;
	public static HashMap<Integer, Autostrada> listaAutostrade;
	
	public void start(Stage primaryStage) {

		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/mvc/view/Grafica.fxml"));
			//primaryStage.initStyle(StageStyle.TRANSPARENT);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/mvc/view/application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/mvc/view/Home.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		
		/*try{

			autostradaDao = new AutostradaDao();
			
			List<Autostrada> lista = autostradaDao.getAll();
			for (Autostrada i: lista){
				listaAutostrade.put(i.getId(), i);
			}
					
		} catch (Exception e){
			System.out.println(e.getMessage());
		}*/
		
		// launch app view
		launch(args);
		
		//GestoreAutostradale g = new GestoreAutostradale();
		//g.ingresso(8, "AA000AA");
		
	}
}

/*
package test;
	
import java.util.HashMap;

import javafx.application.Application;
import javafx.stage.Stage;
import mvc.model.Autostrada;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	public static HashMap<Integer, Autostrada> listaAutostrade;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("/mvc/view/Home.fxml"));
			Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
*/
