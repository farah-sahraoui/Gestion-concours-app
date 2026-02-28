package model;

import java.util.Date;

public class Concours {

    private int id;
    private String titre;
    private Date date;
    private String filiere;
    private int capacite;
    private double seuil;

    // Constructeur vide
    public Concours() {}

    // Constructeur avec id
    public Concours(int id, String titre, Date date, String filiere, int capacite, double seuil) {
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.filiere = filiere;
        this.capacite = capacite;
        this.seuil = seuil;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getFiliere() { return filiere; }
    public void setFiliere(String filiere) { this.filiere = filiere; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public double getSeuil() { return seuil; }
    public void setSeuil(double seuil) { this.seuil = seuil; }
@Override
public String toString() {
    return titre;
}
}