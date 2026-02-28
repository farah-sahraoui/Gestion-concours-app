package ui;

import model.Utilisateur;



/**
 * Point d'entrée principal de l'application
 */
public class MainApp {

    public static void main(String[] args) {
        // On utilise invokeLater pour s'assurer que l'interface Swing 
        // se lance correctement dans le thread dédié (EDT)
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // On lance la toute première fenêtre : la page de connexion
                new LoginFrame().setVisible(true);
            }
        });
    }
}

 