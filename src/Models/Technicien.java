package Models;

import java.util.LinkedList;

public class Technicien extends Personne{
    
    protected LinkedList<Capteur>[] Capteur_a_Installer;
    protected double Salaire;

    public Technicien(int id, String cin,String nom, String prenom, String login, String password, String phoneNumber,String email,  LinkedList<Capteur>[] capteur_a_Installer) {
        super(id,cin,nom, prenom, login, password, phoneNumber, email);
        
        Capteur_a_Installer = capteur_a_Installer;

    }



    public Technicien(String login, String mdp) {
        super(login, mdp);

    }


    public LinkedList<Capteur>[] getCapteur_a_Installer() {
        return Capteur_a_Installer;
    }

    public void setCapteur_a_Installer(LinkedList<Capteur>[] capteur_a_Installer) {
        Capteur_a_Installer = capteur_a_Installer;
    }



	public double getSalaire() {
		return Salaire;
	}



	public void setSalaire(double salaire) {
		Salaire = salaire;
	}
    


}
