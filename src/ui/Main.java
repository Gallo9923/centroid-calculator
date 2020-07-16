package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends javafx.application.Application{
	
	public static void main(String[] args) {
		
		launch(args);
		
	}

	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
		fxmlLoader.setController(new Controller());
		BorderPane bp = fxmlLoader.load();
		
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.setTitle("Plant Layout Centroid Calculator");
		stage.show();
		
	}
	
	
	
}
