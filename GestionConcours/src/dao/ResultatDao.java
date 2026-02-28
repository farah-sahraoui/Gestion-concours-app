package dao;

import model.Resultat;
import util.DBConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class ResultatDao {

    private Connection conn;

    public ResultatDao() {
        conn = DBConnexion.getConnection();
    }

    // ================= INSERT =================
    public void insert(Resultat r) throws SQLException {
      // Remplace 'candidat_id' et 'concours_id' par 'candidatId' et 'concoursId'
        String sql = "INSERT INTO resultat(candidatId, concoursId, note, decision) VALUES(?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, r.getCandidatId());
            ps.setInt(2, r.getConcoursId());
            ps.setDouble(3, r.getNote());
            ps.setString(4, r.getDecision());

            ps.executeUpdate();
            System.out.println("Résultat ajouté !");
        }
       catch (SQLException e) {
    if (e.getMessage().contains("Duplicate")) {
        System.out.println("Ce candidat est déjà inscrit à ce concours !");
    } else {
        System.out.println("Erreur INSERT resultat : " + e.getMessage());
    }
       }
        
    }
    

   

    // ================= GENERER DECISIONS =================
    public void genererDecisions(int concoursId, double seuil, int capacite) {
    // 1. Correction du nom de la colonne : concoursId au lieu de concours_id
    String select = "SELECT id, note FROM resultat WHERE concoursId=? ORDER BY note DESC";
    String update = "UPDATE resultat SET decision=? WHERE id=?";

    try (PreparedStatement psSelect = conn.prepareStatement(select);
         PreparedStatement psUpdate = conn.prepareStatement(update)) {

        psSelect.setInt(1, concoursId);
        ResultSet rs = psSelect.executeQuery();

        int compteur = 0;
        while (rs.next()) {
            int id = rs.getInt("id");
            double note = rs.getDouble("note");
            String decision;

            if (note < seuil) {
                decision = "Refusé"; // Harmonisé avec votre Combo ("Refusé" au lieu de "Rejeté")
            } else {
                compteur++;
                if (compteur <= capacite) {
                    decision = "Admis";
                } else {
                    decision = "En attente"; // Harmonisé avec votre Combo ("En attente" au lieu de "ListeAttente")
                }
            }

            psUpdate.setString(1, decision);
            psUpdate.setInt(2, id);
            psUpdate.executeUpdate();
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erreur SQL : " + e.getMessage());
    }
}

    // ================= LISTER AVEC NOM (CLASSEMENT COMPLET) =================
  public void listeClassementComplet(int concoursId) {

    String sql =
            "SELECT c.nom, r.note, r.decision " +
            "FROM resultat r " +
            "JOIN candidat c ON r.candidat_id = c.id " +
            "WHERE r.concours_id = ? " +
            "ORDER BY r.note DESC";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, concoursId);
        ResultSet rs = ps.executeQuery();

        int classement = 1;

        while (rs.next()) {
            System.out.println(
                    classement + " - " +
                    rs.getString("nom") +
                    " | Note: " +
                    rs.getDouble("note") +
                    " | Décision: " +
                    rs.getString("decision")
            );
            classement++;
        }

    } catch (SQLException e) {
        System.out.println("Erreur classement complet : " + e.getMessage());
    }
}
       

    // ================= FILTRER PAR DECISION =================
    public void filtrerParDecision(String decision) {

    String sql =
        "SELECT c.nom, r.note, r.decision " +
        "FROM resultat r " +
        "JOIN candidat c ON r.candidat_id = c.id " +
        "WHERE r.decision = ?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, decision);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(
                rs.getString("nom") +
                " - Note: " +
                rs.getDouble("note")
            );
        }

    } catch (SQLException e) {
        System.out.println("Erreur filtre décision : " + e.getMessage());
    }
}
    // ================= FILTRER PAR VILLE =================
 public void filtrerParVille(String ville) {

    String sql =
        "SELECT c.nom, c.ville, r.note " +
        "FROM resultat r " +
        "JOIN candidat c ON r.candidat_id = c.id " +
        "WHERE c.ville = ?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, ville);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(
                rs.getString("nom") +
                " - " +
                rs.getString("ville") +
                " - Note: " +
                rs.getDouble("note")
            );
        }

    } catch (SQLException e) {
        System.out.println("Erreur filtre ville : " + e.getMessage());
    }
}

    public void delete(int id) {
      String sql = "DELETE FROM resultat WHERE id=?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ps.executeUpdate();

        System.out.println("Résultat supprimé !");

    } catch (SQLException e) {
        System.out.println("Erreur DELETE resultat : " + e.getMessage());
    }
    }

    public void updateDecision(Resultat r) {
           String sql = "UPDATE resultat SET decision=? WHERE id=?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, r.getDecision());
        ps.setInt(2, r.getId());

        ps.executeUpdate();

        System.out.println("Décision mise à jour !");

    } catch (SQLException e) {
        System.out.println("Erreur UPDATE decision : " + e.getMessage());
    }
    }

    public Resultat findById(int id) {
            String sql = "SELECT * FROM resultat WHERE id=?";
    Resultat r = null;

    try (PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            r = new Resultat(
                    rs.getInt("id"),
                    rs.getInt("candidat_id"),
                    rs.getInt("concours_id"),
                    rs.getDouble("note"),
                    rs.getString("decision")
            );
        }

    } catch (SQLException e) {
        System.out.println("Erreur FIND BY ID resultat : " + e.getMessage());
    }

    return r;
    }
    public List<Object[]> findByConcours(int concoursId) {

    String sql =
        "SELECT r.id, c.nom, co.titre, r.note, r.decision " +
        "FROM resultat r " +
        "JOIN candidat c ON r.candidatId = c.id " +
        "JOIN concours co ON r.concoursId = co.id " +
        "WHERE r.concoursId = ? " +
        "ORDER BY r.note DESC";

    List<Object[]> liste = new ArrayList<>();

    try (PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, concoursId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            liste.add(new Object[]{
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("titre"),
                rs.getDouble("note"),
                rs.getString("decision")
            });
        }

    } catch (SQLException e) {
        System.out.println("Erreur classement : " + e.getMessage());
    }

    return liste;
}

    public List<Resultat> findByCandidat(int idCandidat) {
    List<Resultat> liste = new java.util.ArrayList<>();
    String sql = "SELECT * FROM resultat WHERE candidatId = ?";
    
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idCandidat);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Resultat r = new Resultat(
                rs.getInt("id"),
                rs.getInt("candidatId"),
                rs.getInt("concoursId"),
                rs.getDouble("note"),
                rs.getString("decision")
            );
            liste.add(r);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return liste;
}

   public List<Resultat> findAll() {
        List<Resultat> liste = new ArrayList<>();
        String sql = "SELECT * FROM resultat";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                liste.add(new Resultat(
                    rs.getInt("id"),
                    rs.getInt("candidatId"),
                    rs.getInt("concoursId"),
                    rs.getDouble("note"),
                    rs.getString("decision")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
public List<Object[]> findFilteredWithNames(String recherche, String decision) {
    List<Object[]> liste = new ArrayList<>();
    // On sélectionne exactement 5 colonnes pour correspondre au tableau de l'interface
    String sql = "SELECT r.id, c.nom, co.titre, r.note, r.decision " +
                 "FROM resultat r " +
                 "JOIN candidat c ON r.candidatId = c.id " +
                 "JOIN concours co ON r.concoursId = co.id " +
                 "WHERE (c.nom LIKE ? OR c.ville LIKE ?)";

    // Si on filtre par décision (Admis, Refusé, etc.)
    if (decision != null && !decision.equals("Tous")) {
        sql += " AND r.decision = ?";
    }

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, "%" + recherche + "%");
        ps.setString(2, "%" + recherche + "%");
        if (decision != null && !decision.equals("Tous")) {
            ps.setString(3, decision);
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            // On crée une ligne d'objet avec les 5 colonnes bien alignées
            liste.add(new Object[]{
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("titre"),
                rs.getDouble("note"),
                rs.getString("decision")
            });
        }
    } catch (SQLException e) {
        System.out.println("Erreur SQL dans findFilteredWithNames: " + e.getMessage());
    }
    return liste;
}
   public List<Object[]> findFilteredByCandidat(int candId, String decision) {
    List<Object[]> liste = new ArrayList<>();
    // Cette requête récupère les noms au lieu des chiffres que l'on voit sur ton image d709de
    String sql = "SELECT r.id, c.nom, co.titre, r.note, r.decision " +
                 "FROM resultat r " +
                 "JOIN candidat c ON r.candidatId = c.id " +
                 "JOIN concours co ON r.concoursId = co.id " +
                 "WHERE (c.nom LIKE ? OR c.ville LIKE ?)";

    if (!decision.equals("Tous")) {
        sql += " AND r.decision = ?";
    }

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, candId);
        if (!decision.equals("Tous")) ps.setString(2, decision);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            liste.add(new Object[]{
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("titre"),
                rs.getDouble("note"),
                rs.getString("decision")
            });
        }
    } catch (SQLException e) { e.printStackTrace(); }
    return liste;
}
   // Dans ResultatDao.java



public Map<String, Integer> obtenirStatsDecisions() {
    Map<String, Integer> stats = new HashMap<>();
    // Utilise exactement le nom de la table 'resultat' (minuscule comme sur ton image)
    String sql = "SELECT decision, COUNT(*) as total FROM resultat GROUP BY decision";

    try (Connection conn = util.DBConnexion.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            String decision = rs.getString("decision");
            int total = rs.getInt("total");
            System.out.println("DEBUG: " + decision + " -> " + total); // Vérifie la console NetBeans !
            stats.put(decision, total);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return stats;
}
}