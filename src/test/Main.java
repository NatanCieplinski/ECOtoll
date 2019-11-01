package test;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mvc.controller.GestoreAutostradale;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main {//extends Application {
	
	private Stage primaryStage;
	private BorderPane mainLayout;
	
	public void start(Stage primaryStage) {

		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/mvc/view/Home.fxml"));
			primaryStage.initStyle(StageStyle.TRANSPARENT);
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
		//launch(args);
		GestoreAutostradale g = new GestoreAutostradale();
		g.ingresso(8, "AA000AA");
		g.uscita(5, 3);
	}
}
