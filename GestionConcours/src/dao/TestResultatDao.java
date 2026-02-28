package dao;

import java.sql.SQLException;
import model.Resultat;

public class TestResultatDao {

    public static void main(String[] args) throws SQLException {

        ResultatDao dao = new ResultatDao();

      
       // ================= AJOUT NOTES =================
     
       dao.insert(new Resultat(1, 1, 15, ""));
        dao.insert(new Resultat(2, 1, 12, ""));
   

        // ================= GENERER DECISIONS =================
        dao.genererDecisions(1, 10, 1);

        // ================= AFFICHER CLASSEMENT =================
        System.out.println("=== Classement complet ===");
        dao.listeClassementComplet(1);

        // ================= FILTRER PAR DECISION =================
        System.out.println("\n=== Admis ===");
        dao.filtrerParDecision("Admis");

        // ================= FILTRER PAR VILLE =================
        System.out.println("\n=== Ville Marrakech ===");
        dao.filtrerParVille("Marrakech");
    }
}
