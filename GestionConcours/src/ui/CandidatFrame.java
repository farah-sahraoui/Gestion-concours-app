package ui;

import model.Utilisateur;

public class CandidatFrame extends javax.swing.JFrame {

    private Utilisateur currentUser;

    public CandidatFrame(Utilisateur user) {
        initComponents();
        this.currentUser = user;

        setTitle("Espace Candidat - " + user.getEmail());
        setLocationRelativeTo(null);

        chargerInformationsCandidat();
    }

    CandidatFrame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 400);

        javax.swing.JLabel lbl = new javax.swing.JLabel("Bienvenue Candidat");
        lbl.setFont(new java.awt.Font("Arial", 1, 18));
        lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        add(lbl);
    }

    private void chargerInformationsCandidat() {
        System.out.println("ID Candidat : " + currentUser.getCandidatId());
    }
}
