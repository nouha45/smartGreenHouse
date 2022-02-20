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
import models.Technicien;

public class CrudTechnicienController implements Initializable {
	Connection cnx;
	public PreparedStatement st;
	public ResultSet result;
	 @FXML
	    private TableColumn<Technicien, String> clt_cin;

	    @FXML
	    private TableColumn<Technicien, String> clt_email;

	    @FXML
	    private TableColumn<Technicien, Integer> clt_id;

	   @FXML
	    private TableColumn<Technicien, String> clt_log; 
	    
	    @FXML
	    private TableColumn<Technicien, String> clt_salaire;


	    @FXML
	    private TableColumn<Technicien, String> clt_nom;

	   @FXML
	    private TableColumn<Technicien, String> clt_pass; 

	    @FXML
	    private TableColumn<Technicien, String> clt_prenom;

	    @FXML
	    private TableColumn<Technicien, String> clt_tel;

	    @FXML
	    private TextField id_rech;

	    @FXML
	    private TableView<Technicien> table_tech;

	    @FXML
	    private TextField txt_cin;

	    @FXML
	    private TextField txt_email;

	   @FXML
	    private TextField txt_log; 

	    @FXML
	    private TextField txt_nom;
	    
	    @FXML
	    private TextField txt_salaire;

	    @FXML
	    private TextField txt_num;

	   @FXML
	    private TextField txt_pass;

	    @FXML
	    private TextField txt_prenom;

	    @FXML
	    void addTech(MouseEvent event) {
	    	String nom=txt_nom.getText();
	    	String prenom=txt_prenom.getText();
	    	String tel=txt_num.getText();
	    	String salaire=txt_salaire.getText();
	    	String cin=txt_cin.getText();
	    	String login=txt_log.getText();
	    	String password=txt_pass.getText();
	    	String email=txt_email.getText();
	    	
	    	String sql="INSERT INTO personne (nom,prenom,CIN,email,phone_number,login,password)  VALUES(?,?,?,?,?,?,?)";
	    	String sql2="INSERT INTO technicien (id_tech,salaire)  VALUES(LAST_INSERT_ID(),?) ";
	    	if(!nom.equals("")&& !tel.equals("")&& !prenom.equals("")&& !cin.equals("")&& !email.equals("")&& !salaire.equals("")&& !login.equals("")&& !password.equals("")) {
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
				st2.setString(1, salaire);
				st2.execute();
				cnx.commit();
				txt_cin.setText("");
				txt_nom.setText("");
				txt_prenom.setText("");
				txt_pass.setText("");
				txt_num.setText("");
				txt_salaire.setText("");
				txt_email.setText("");
				txt_log.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION,"Technicien ajouté avec succés",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
				showTechnicien();
				
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
	    void deleteTech(MouseEvent event) {
	    	String sql="DELETE technicien FROM technicien INNER JOIN personne ON personne.id_p=technicien.id_tech where  personne.CIN='"+id_rech.getText()+"'";
	    	try {
				st=cnx.prepareStatement(sql);
				st.executeUpdate();
				/*txt_cin.setText("");
				txt_nom.setText("");
				txt_prenom.setText("");
				txt_pass.setText("");
				txt_num.setText("");
				txt_salaire.setText("");
				txt_email.setText("");
				txt_log.setText("");*/
				Alert alert = new Alert(AlertType.CONFIRMATION,"Technicien supprimé avec succés",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
				showTechnicien();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void editTech(MouseEvent event) {
	    	String nom=txt_nom.getText();
	    	String prenom=txt_prenom.getText();
	    	String tel=txt_num.getText();
	    	String salaire=txt_salaire.getText();
	    	String cin=txt_cin.getText();
	    	String login=txt_log.getText();
	    	String password=txt_pass.getText();
	    	String email=txt_email.getText();
	    	String sql="UPDATE personne, technicien SET nom=?,prenom=?,CIN=?,email=?,phone_number=?,login=?,password=?,salaire=? WHERE personne.id_p=technicien.id_tech AND  CIN='"+id_rech.getText()+"'";
	    	
	    	if(!nom.equals("")&& !tel.equals("")&& !prenom.equals("")&& !cin.equals("")&& !email.equals("")&& !salaire.equals("")&& !login.equals("")&& !password.equals("")) {
	    	try {
	    		
				st=cnx.prepareStatement(sql);
				
				st.setString(1, nom);
				st.setString(2, prenom);
				st.setString(3, cin);
				st.setString(4, email);
				st.setString(5, tel);
				st.setString(6, login);
				st.setString(7, password);
				st.setString(8, salaire);
				st.executeUpdate();
				
				
				txt_cin.setText("");
				txt_nom.setText("");
				txt_prenom.setText("");
				txt_pass.setText("");
				txt_num.setText("");
				txt_salaire.setText("");
				txt_email.setText("");
				txt_log.setText("");
				Alert alert = new Alert(AlertType.CONFIRMATION,"Technicien modifié avec succés",javafx.scene.control.ButtonType.OK);
				alert.showAndWait();
				showTechnicien();
				
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
	    void rechercherTech() {
	    	String sql = "SELECT nom,prenom,CIN,salaire,phone_number,email,login,password FROM technicien INNER JOIN personne ON technicien.id_tech=personne.id_p WHERE CIN='"+id_rech.getText()+"'";
	    	int m=0;
	    	try {
				st=cnx.prepareStatement(sql);
				result=st.executeQuery();
				System.out.println("1");
				if(result.next()) {
					System.out.println("12");
                    txt_nom.setText(result.getString("nom"));
					txt_prenom.setText(result.getString("prenom"));
					txt_cin.setText(result.getString("CIN"));
					txt_salaire.setText(result.getString("salaire"));
					txt_log.setText(result.getString("login"));
					txt_pass.setText(result.getString("password"));
					txt_num.setText(result.getString("phone_number"));
					txt_email.setText(result.getString("email"));
					m=1;
				}
			} catch (SQLException e) {
				System.out.println("13");

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           if(m==0) {
        	   Alert alert= new Alert(AlertType.ERROR,"Aucun technicien trouvé avec CIN="+id_rech.getText()+"",javafx.scene.control.ButtonType.OK);
        	   alert.showAndWait();
           }

	    }
	    public ObservableList<Technicien> data = FXCollections.observableArrayList();
	    public void showTechnicien() {
	    	table_tech.getItems().clear();
	    	String sql = "SELECT * FROM technicien INNER JOIN personne ON technicien.id_tech = personne.id_p";
	    	try {
				st=cnx.prepareStatement(sql);
				result=st.executeQuery();
				while(result.next()) {
					data.add(new Technicien(result.getInt("id_tech"),result.getString("nom"),result.getString("prenom"),result.getString("login"),result.getString("password"),result.getString("CIN"),result.getString("phone_number"),result.getString("email"),result.getString("salaire")));
					clt_cin.setCellValueFactory(new PropertyValueFactory<Technicien, String>("cin"));
					clt_id.setCellValueFactory(new PropertyValueFactory<Technicien, Integer>("id"));
					clt_nom.setCellValueFactory(new PropertyValueFactory<Technicien, String>("nom"));
					clt_prenom.setCellValueFactory(new PropertyValueFactory<Technicien, String>("prenom"));
					clt_email.setCellValueFactory(new PropertyValueFactory<Technicien, String>("email"));
					clt_salaire.setCellValueFactory(new PropertyValueFactory<Technicien, String>("salaire"));
					// clt_log.setCellValueFactory(new PropertyValueFactory<Technicien, String>("login"));
					//clt_password.setCellValueFactory(new PropertyValueFactory<Technicien, String>("password"));
					clt_tel.setCellValueFactory(new PropertyValueFactory<Technicien, String>("phoneNumber"));
					table_tech.setItems(data);
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
    	showTechnicien();
	}

	

}
