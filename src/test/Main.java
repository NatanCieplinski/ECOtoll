package test;
	
import java.util.HashMap;
import java.util.LinkedList;

import dao.implementation.AutostradaDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import mvc.model.Autostrada;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	//private Stage primaryStage;
	//private BorderPane mainLayout;
	private static AutostradaDao autostradaDao;
	public static HashMap<Integer, Autostrada> listaAutostrade = new HashMap<Integer, Autostrada>();
	
	public void start(Stage primaryStage) {

		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/mvc/view/Grafica.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/mvc/view/application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	private void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/mvc/view/Home.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}*/
	
	public static void main(String[] args) {
		
		try{

			autostradaDao = new AutostradaDao();
			
			LinkedList<Autostrada> lista = (LinkedList<Autostrada>) autostradaDao.getAll();
			for (Autostrada i: lista){
				listaAutostrade.put(i.getId(), i);
			}
					
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		// launch app view
		launch(args);
	}
}