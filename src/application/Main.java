package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage)  {
    	System.out.println(1);
        
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/interfaces/Login.fxml"));
			 Scene scene = new Scene(root);
		        stage.setScene(scene);
		        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(1);
       
        
    }

    public static void main(String[] args) {
    	
        launch();
    }
}