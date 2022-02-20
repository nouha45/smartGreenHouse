package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.ConnexionMysql;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.StockCapteur;

public class CrudCapteurController implements Initializable{

	Connection cnx;
	public PreparedStatement st;
	public ResultSet result;
	  @FXML
	    private TableColumn<StockCapteur, String> clt_desc;

	    @FXML
	    private TableColumn<StockCapteur, Integer> clt_id;

	    @FXML
	    private TableColumn<StockCapteur, String> clt_prix;

	    @FXML
	    private TableColumn<StockCapteur, String> clt_type;

	    @FXML
	    private TextArea desCapteur;

	    @FXML
	    private TextField prixCapteur;

	    @FXML
	    private TableView<StockCapteur> table_capteur;

	    @FXML
	    private TextField typeCapteur;

	    @FXML
	    private TextField type_cap;

	    @FXML
	    void addCapteur(MouseEvent event) {

		    	String type=typeCapteur.getText();
		    	String prix=prixCapteur.getText();
		    	String desc=desCapteur.getText();
		    	
		    	
		    	String sql="INSERT INTO stockcapteur (type_capteur,prix_capteur,description)  VALUES(?,?,?)";
		    	if(!type.equals("")&& !prix.equals("")&& !desc.equals("")) {
		    	try {
					st=cnx.prepareStatement(sql);
					st.setString(1, type);
					st.setString(2, prix);
					st.setString(3, desc);
					st.execute();
					
					typeCapteur.setText("");
					prixCapteur.setText("");
					desCapteur.setText("");
					
					Alert alert = new Alert(AlertType.CONFIRMATION,"Capteur ajouté avec succés",javafx.scene.control.ButtonType.OK);
					alert.showAndWait();
					showCapteur();
					
				} catch (SQLException e ) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
						}
					}else {
		    		Alert alert = new Alert(AlertType.WARNING,"Veuillez remplir tout les champs!!",javafx.scene.control.ButtonType.OK);
					alert.showAndWait();
		    	}
		    	

	    }

	    @FXML
	    void deleteCapteur(MouseEvent event) {

	    	String sql="DELETE stockcapteur FROM stockcapteur where  stockcapteur.type_capteur='"+type_cap.getText()+"'";
	    	try {
				st=cnx.prepareStatement(sql);
				st.executeUpdate();
				Alert alert = new Alert(AlertType.CONFIRMATION,"Capteur supprimé avec succés",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
				showCapteur();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void editCapteur(MouseEvent event) {

	    	String type=typeCapteur.getText();
	    	String prix=prixCapteur.getText();
	    	String desc=desCapteur.getText();
	    	
	    	
	    	String sql="UPDATE stockcapteur SET type_capteur=?,prix_capteur=?,description=? WHERE type_capteur='"+type_cap.getText()+"'";
	    	if(!type.equals("")&& !prix.equals("")&& !desc.equals("")) {
	    	try {
				st=cnx.prepareStatement(sql);
				st.setString(1, type);
				st.setString(2, prix);
				st.setString(3, desc);
				st.execute();
				
				typeCapteur.setText("");
				prixCapteur.setText("");
				desCapteur.setText("");
				
				Alert alert = new Alert(AlertType.CONFIRMATION,"Capteur modifié avec succés",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
				showCapteur();
				
			} catch (SQLException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
					}
				}else {
	    		Alert alert = new Alert(AlertType.WARNING,"Veuillez remplir tout les champs!!",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
	    	}
	    	
	    }

	    @FXML
	    void rechercherCapteur(ActionEvent event) {
  
	    	String sql = "SELECT type_capteur,prix_capteur,description FROM stockcapteur where type_capteur='"+type_cap.getText()+"'";
	    	int m=0;
	    	try {
				st=cnx.prepareStatement(sql);
				result=st.executeQuery();
				if(result.next()) {
					typeCapteur.setText(result.getString("type_capteur"));
					prixCapteur.setText(result.getString("prix_capteur"));
					desCapteur.setText(result.getString("description"));
					m=1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           if(m==0) {
        	   Alert alert= new Alert(AlertType.ERROR,"Aucun capteur trouvé de type"+" "+type_cap.getText()+"",javafx.scene.control.ButtonType.OK);
        	   alert.showAndWait();
           }
	    }
	    public ObservableList<StockCapteur> data = FXCollections.observableArrayList();
	    public void showCapteur() {
	    	table_capteur.getItems().clear();
	    	String sql = "SELECT * FROM stockcapteur";
	    	try {
				st=cnx.prepareStatement(sql);
				result=st.executeQuery();
				while(result.next()) {
					data.add(new StockCapteur(result.getInt("id_cap"),result.getString("type_capteur"),result.getString("prix_capteur"),result.getString("description")));
					clt_type.setCellValueFactory(new PropertyValueFactory<StockCapteur, String>("type"));
					clt_id.setCellValueFactory(new PropertyValueFactory<StockCapteur, Integer>("id_capteur"));
					clt_prix.setCellValueFactory(new PropertyValueFactory<StockCapteur, String>("prix"));
					clt_desc.setCellValueFactory(new PropertyValueFactory<StockCapteur, String>("desc"));
					table_capteur.setItems(data);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	
	    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		cnx=ConnexionMysql.connexionDB();
    	showCapteur();
	}

	

}
