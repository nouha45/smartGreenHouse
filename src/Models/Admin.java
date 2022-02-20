package Models;

public class Admin extends Personne {
    
    public Admin(){
        super();
    }
    public Admin(String login,String password){
        super(login,password);
       
    }
    public Admin(int id,String cin, String nom, String prenom, String login, String password, String phoneNumber,String email ){
        super(id,cin,nom,prenom,login,password,phoneNumber,email);
        
    }

   
}

