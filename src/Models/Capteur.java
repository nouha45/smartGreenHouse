package Models;

public class Capteur {
    protected int id_capteur;
    protected String nom;
    protected double prix;

    public Capteur (){

    }
    public Capteur(int id_capteur, String nom, double prix){
        this.id_capteur=id_capteur;
        this.nom=nom;
        this.prix=prix;
    }

    public int getId_capteur() {
        return id_capteur;
    }

    public void setId_capteur(int id_capteur) {
        this.id_capteur = id_capteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
