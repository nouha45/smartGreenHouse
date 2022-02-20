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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Client;



public class CrudClientsController implements Initializable{
	
	Connection cnx;
	public PreparedStatement st;
	public ResultSet result;
	   @FXML
	    private TableColumn<Client, Integer> clm_id;

	   @FXML
	   private TableColumn<Client, String> clt_add;

	    @FXML
	    private TableColumn<Client, String> clt_cin;

	    @FXML
	    private TableColumn<Client, String> clt_log;

	    @FXML
	    private TableColumn<Client, String> clt_mail;

	    @FXML
	    private TableColumn<Client, String> clt_nom;

	    @FXML
	    private TableColumn<Client, String> clt_password;

	    @FXML
	    private TableColumn<Client, String> clt_prenom;

	    public TableColumn<Client, String> getClt_log() {
			return clt_log;
		}

		public void setClt_log(TableColumn<Client, String> clt_log) {
			this.clt_log = clt_log;
		}

		public TableColumn<Client, String> getClt_password() {
			return clt_password;
		}

		public void setClt_password(TableColumn<Client, String> clt_password) {
			this.clt_password = clt_password;
		}

		@FXML
	    private TableColumn<Client, String> clt_tel;

	    @FXML
	    private TextField id_rech;

	    @FXML
	    private TableView<Client> table_view;

	    @FXML
	    private TextField txt_add;

	    @FXML
	    private TextField txt_cin;

	    @FXML
	    private TextField txt_log;

	    @FXML
	    private TextField txt_mail;

	    @FXML
	    private TextField txt_nom;

	    @FXML
	    private TextField txt_password;

	    @FXML
	    private TextField txt_prenom;

	    @FXML
	    private TextField txt_tel;

	    @FXML
	    void addClient(MouseEvent event) {
	    	String nom=txt_nom.getText();
	    	String prenom=txt_prenom.getText();
	    	String tel=txt_tel.getText();
	    	String addresse=txt_add.getText();
	    	String cin=txt_cin.getText();
	    	String login=txt_log.getText();
	    	String password=txt_password.getText();
	    	String email=txt_mail.getText();
	    	
	    	String sql="INSERT INTO personne (nom,prenom,CIN,email,phone_number,login,password)  VALUES(?,?,?,?,?,?,?)";
	    	String sql2="INSERT INTO client (id_client,addresse_client)  VALUES(LAST_INSERT_ID(),?) ";
	    	if(!nom.equals("")&& !tel.equals("")&& !prenom.equals("")&& !cin.equals("")&& !email.equals("")&& !addresse.equals("")&& !login.equals("")&& !password.equals("")) {
	    	try {
	    		cnx.setAutoCommit(false);
				st=cnx.prepareStatement(sql);
				PreparedStatement st2=cnx.prepareStatement(sql2);
				st.setString(1, nom);
				st.setString(2, prenom);
				st.setString(3, cin);
				st.setString(4, email);
				st.setString(5, tel);
				st.setString(6, login);
				st.setString(7, password);
				st.execute();
				st2.setString(1, addresse);
				st2.execute();
				cnx.commit();
				txt_cin.setText("");
				txt_nom.setText("");
				txt_prenom.setText("");
				txt_password.setText("");
				txt_tel.setText("");
				txt_add.setText("");
				txt_mail.setText("");
				txt_log.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION,"Client ajouté avec succés",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
				showClient();
				
			} catch (SQLException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(cnx!=null) {
					try {
						System.err.print("transaction rolled back");
						cnx.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
	    	}else {
	    		Alert alert = new Alert(AlertType.WARNING,"Veuillez remplir tout les champs!!",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
	    	}
	    	

	    }

	    @FXML
	    void deleteClient(MouseEvent event) {
	    	String sql="DELETE client FROM client INNER JOIN personne ON personne.id_p=client.id_client where  personne.CIN='"+id_rech.getText()+"'";
	    	try {
				st=cnx.prepareStatement(sql);
				st.executeUpdate();
				Alert alert = new Alert(AlertType.CONFIRMATION,"Client supprimé avec succés",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
				showClient();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }

	    @FXML
	    void editClient(MouseEvent event) {
	    	String nom=txt_nom.getText();
	    	String prenom=txt_prenom.getText();
	    	String tel=txt_tel.getText();
	    	String addresse=txt_add.getText();
	    	String cin=txt_cin.getText();
	    	String login=txt_log.getText();
	    	String password=txt_password.getText();
	    	String email=txt_mail.getText();
	    	String sql="UPDATE personne, client SET nom=?,prenom=?,CIN=?,email=?,phone_number=?,login=?,password=?,addresse_client=? WHERE personne.id_p=client.id_client AND  CIN='"+id_rech.getText()+"'";
	    	
	    	if(!nom.equals("")&& !tel.equals("")&& !prenom.equals("")&& !cin.equals("")&& !email.equals("")&& !addresse.equals("")&& !login.equals("")&& !password.equals("")) {
	    	try {
	    		
				st=cnx.prepareStatement(sql);
				
				st.setString(1, nom);
				st.setString(2, prenom);
				st.setString(3, cin);
				st.setString(4, email);
				st.setString(5, tel);
				st.setString(6, login);
				st.setString(7, password);
				st.setString(8, addresse);
				st.executeUpdate();
				
				
				txt_cin.setText("");
				txt_nom.setText("");
				txt_prenom.setText("");
				txt_password.setText("");
				txt_tel.setText("");
				txt_add.setText("");
				txt_mail.setText("");
				txt_log.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION,"Client modifié avec succés",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
				showClient();
				
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
	    void rechercherClient() {
	    	String sql = "SELECT nom,prenom,CIN,addresse_client,phone_number,email,login,password FROM client INNER JOIN personne ON client.id_client = personne.id_p where CIN='"+id_rech.getText()+"'";
	    	int m=0;
	    	try {
				st=cnx.prepareStatement(sql);
				result=st.executeQuery();
				if(result.next()) {
					txt_nom.setText(result.getString("nom"));
					txt_prenom.setText(result.getString("prenom"));
					txt_cin.setText(result.getString("CIN"));
					txt_add.setText(result.getString("addresse_client"));
					txt_log.setText(result.getString("login"));
					txt_password.setText(result.getString("password"));
					txt_tel.setText(result.getString("phone_number"));
					txt_mail.setText(result.getString("email"));
					m=1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           if(m==0) {
        	   Alert alert= new Alert(AlertType.ERROR,"Aucun client trouvé avec CIN="+id_rech.getText()+"",javafx.scene.control.ButtonType.OK);
        	   alert.showAndWait();
           }
	    }

	   
	    
	    public ObservableList<Client> data = FXCollections.observableArrayList();
	    public void showClient() {
	    	table_view.getItems().clear();
	    	String sql = "SELECT * FROM client INNER JOIN personne ON client.id_client = personne.id_p";
	    	try {
				st=cnx.prepareStatement(sql);
				result=st.executeQuery();
				while(result.next()) {
					data.add(new Client(result.getInt("id_client"),result.getString("nom"),result.getString("prenom"),result.getString("login"),result.getString("password"),result.getString("CIN"),result.getString("addresse_client"),result.getString("phone_number"),result.getString("email")));
					clt_cin.setCellValueFactory(new PropertyValueFactory<Client, String>("cin"));
					clm_id.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
					clt_nom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
					clt_prenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
					clt_mail.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
					clt_add.setCellValueFactory(new PropertyValueFactory<Client, String>("addresse"));
					// clt_log.setCellValueFactory(new PropertyValueFactory<Client, String>("login"));
					//clt_password.setCellValueFactory(new PropertyValueFactory<Client, String>("password"));
					clt_tel.setCellValueFactory(new PropertyValueFactory<Client, String>("phoneNumber"));
					table_view.setItems(data);
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
	    	showClient();
		
	}

	

}
