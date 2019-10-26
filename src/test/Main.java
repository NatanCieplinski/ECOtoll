package test;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane mainLayout;
	
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = rimaryXStage;
		this.primaryStage.setTitle("Employee App");
		showMainView();
		
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("/mvc/view/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void showMainView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/Home.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(MainLayout);
		primaryStage.setScene(scene);
		primaryStage
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
