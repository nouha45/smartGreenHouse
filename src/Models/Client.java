package Models;

public class Client extends Personne{
    
  protected String adresse;

    public Client(int id,String cin , String nom, String prenom, String login, String password, String phoneNumber,String email,String adresse) {
        super(id,cin,nom, prenom, login, password,phoneNumber,email);
        this.adresse=adresse;

    }
    



    public Client (String login,String mdp) {
        super(login,mdp);
    }

   
}
