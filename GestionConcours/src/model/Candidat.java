package model;

public class Candidat {

    private int id;
    private String nom;
    private String email;
    private String ville;
    private String niveauEtude;

    public Candidat() {
    }

    public Candidat(String nom, String email, String ville, String niveauEtude) {
        this.nom = nom;
        this.email = email;
        this.ville = ville;
        this.niveauEtude = niveauEtude;
    }

    public Candidat(int id, String nom, String email, String ville, String niveauEtude) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.ville = ville;
        this.niveauEtude = niveauEtude;
    }

    // Getters & Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getNiveauEtude() { return niveauEtude; }
    public void setNiveauEtude(String niveauEtude) { this.niveauEtude = niveauEtude; }

 
   @Override
public String toString() {
    return nom;
}
}
