package Models;

public class Personne {
	protected String cin;
	protected int id;
	protected String nom;
    protected String prenom;
    protected String login;
    protected String password;
    protected String phoneNumber;
    protected String email;
    protected Personne(){}

    public Personne(int id,String cin, String nom, String prenom,String login, String password,String phoneNumber,String email){
    	this.id=id;
    	this.cin=cin;
        this.nom=nom;
        this.prenom=prenom;
        this.login=login;
        this.password=password;
        this.phoneNumber=phoneNumber;
        this.email=email;


    }
    public Personne(String login,String password){
        this.login=login;
        this.password=password;
    }
  public  String getNom(){
        return nom;
    }
 public   String getPrenom(){
        return prenom;
    }
  public  String getlogin(){
        return login;
    }
   public String getPaasword(){
        return password;
    }

    public String getEmail(){
        return email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

public void setNom(String nom) {
    this.nom = nom;
}

public void setPrenom(String prenom) {
    this.prenom = prenom;
}

public void setLogin(String login) {
    this.login = login;
}

public void setPassword(String password) {
    this.password = password;
}

public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
}

public void setEmail(String email) {
    this.email = email;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}


}
