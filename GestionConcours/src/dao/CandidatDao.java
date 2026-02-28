package dao;

import model.Candidat;
import util.DBConnexion;   // garde UN SEUL fichier de connexion

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatDao {

    private Connection conn;

    public CandidatDao() {
        conn = DBConnexion.getConnection();
    }

    // ================== INSERT ==================
public void insert(Candidat c) {
    String sql = "INSERT INTO candidat(nom,email,ville,niveauEtude) VALUES(?,?,?,?)";

    try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setString(1, c.getNom());
        ps.setString(2, c.getEmail());
        ps.setString(3, c.getVille());
        ps.setString(4, c.getNiveauEtude());

        ps.executeUpdate();

        // 🔥 Récupérer l'ID généré
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            c.setId(rs.getInt(1));
        }

        System.out.println("Candidat ajouté avec succès ! ID = " + c.getId());

    } catch (SQLException e) {
        System.out.println("Erreur INSERT : " + e.getMessage());
    }
}
    // ================== UPDATE ==================
    public void update(Candidat c) {
        String sql = "UPDATE candidat SET nom=?, email=?, ville=?, niveauEtude=? WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNom());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getVille());
            ps.setString(4, c.getNiveauEtude());
            ps.setInt(5, c.getId());

            ps.executeUpdate();
            System.out.println("Candidat modifié avec succès !");

        } catch (SQLException e) {
            System.out.println("Erreur UPDATE : " + e.getMessage());
        }
    }

    // ================== DELETE ==================
    public void delete(int id) {
        String sql = "DELETE FROM candidat WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Candidat supprimé avec succès !");

        } catch (SQLException e) {
            System.out.println("Erreur DELETE : " + e.getMessage());
        }
    }

    // ================== FIND ALL ==================
    public List<Candidat> getAll() {

        List<Candidat> liste = new ArrayList<>();

        try {
            String sql = "SELECT * FROM candidat";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Candidat c = new Candidat(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("ville"),
                        rs.getString("niveauEtude")
                );

                liste.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return liste;
    }

    // ================== FIND BY VILLE ==================
    public List<Candidat> getByVille(String ville) {
        List<Candidat> liste = new ArrayList<>();
        String sql = "SELECT * FROM candidat WHERE ville = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ville);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Candidat c = new Candidat(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("ville"),
                        rs.getString("niveauEtude")
                );
                liste.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erreur FIND BY VILLE : " + e.getMessage());
        }
        return liste;
    }
 
    
}

   
