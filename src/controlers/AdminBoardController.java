package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class AdminBoardController implements Initializable{
	   
	  private Parent fxml;
    
	   @FXML
	    private AnchorPane root;

	    @FXML
	    void capteurs(MouseEvent event) {
	    	 try {
	 			fxml = FXMLLoader.load(getClass().getResource("/interfaces/CrudCapteur.fxml"));
	 			root.getChildren().removeAll();
	 			root.getChildren().setAll(fxml);
	 		} catch (IOException e) {
	 			
	 			e.printStackTrace();
	 		}
	            
	            
	    }

	    @FXML
	    void clients(MouseEvent event) {
           try {
			fxml = FXMLLoader.load(getClass().getResource("/interfaces/CrudClients.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
           
	    }

	    @FXML
	    void dashboard(MouseEvent event) {
	            
	    }

	    @FXML
	    void serres(MouseEvent event) {
	    	 try {
	 			fxml = FXMLLoader.load(getClass().getResource("/interfaces/CrudSerres.fxml"));
	 			root.getChildren().removeAll();
	 			root.getChildren().setAll(fxml);
	 		} catch (IOException e) {
	 			
	 			e.printStackTrace();
	 		}
	            
	    }

	    @FXML
	    void techniciens(MouseEvent event) {
	    	 try {
	 			fxml = FXMLLoader.load(getClass().getResource("/interfaces/CrudTechniciens.fxml"));
	 			root.getChildren().removeAll();
	 			root.getChildren().setAll(fxml);
	 		} catch (IOException e) {
	 			
	 			e.printStackTrace();
	 		}
	            
	    }


		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
		}
	

}
