package model;

public class Resultat {

    private int id;
    private int candidatId;
    private int concoursId;
    // Utilisation du primitif double (minuscule) pour éviter le NullPointerException
    private double note; 
    private String decision;

    // Constructeur vide
    public Resultat() {
        this.note = 0.0; // Valeur par défaut pour éviter le null
    }

    // Constructeur sans ID (pour l'insertion)
    public Resultat(int candidatId, int concoursId, double note, String decision) {
        this.candidatId = candidatId;
        this.concoursId = concoursId;
        this.note = note;
        this.decision = decision;
    }

    // Constructeur avec ID (pour la lecture)
    public Resultat(int id, int candidatId, int concoursId, double note, String decision) {
        this.id = id;
        this.candidatId = candidatId;
        this.concoursId = concoursId;
        this.note = note;
        this.decision = decision;
    }

    // --- Getters & Setters ---

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCandidatId() { return candidatId; }
    public void setCandidatId(int candidatId) { this.candidatId = candidatId; }

    public int getConcoursId() { return concoursId; }
    public void setConcoursId(int concoursId) { this.concoursId = concoursId; }

    public double getNote() { return note; }
    public void setNote(double note) { // Changé Double en double ici aussi
        this.note = note;
    }

    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }

    public Object getCandidat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getConcours() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}