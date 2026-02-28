package dao;

import model.Concours;
import util.DBConnexion;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConcoursDao {

    private Connection conn;

    public ConcoursDao() {
        conn = DBConnexion.getConnection();
    }

    // INSERT
    public void insert(Concours c) {
String sql = "INSERT INTO concours(titre,date,filiere,capacite,seuil) VALUES(?,?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getTitre());
            ps.setDate(2, new java.sql.Date(c.getDate().getTime()));
            ps.setString(3, c.getFiliere());
            ps.setInt(4, c.getCapacite());
            ps.setDouble(5, c.getSeuil());

            ps.executeUpdate();
            System.out.println("Concours ajouté !");

        } catch (SQLException e) {
            System.out.println("Erreur INSERT concours : " + e.getMessage());
        }
    }

    // UPDATE
    public void update(Concours c) {
        String sql = "UPDATE concours SET titre=?, date=?, filiere=?, capacite=? WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getTitre());
    ps.setDate(2, new java.sql.Date(c.getDate().getTime()));
            ps.setString(3, c.getFiliere());
            ps.setInt(4, c.getCapacite());
            ps.setInt(5, c.getId());

            ps.executeUpdate();
            System.out.println("Concours modifié !");

        } catch (SQLException e) {
            System.out.println("Erreur UPDATE concours : " + e.getMessage());
        }
    }

    // DELETE
    public void delete(int id) {
        String sql = "DELETE FROM concours WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Concours supprimé !");

        } catch (SQLException e) {
            System.out.println("Erreur DELETE concours : " + e.getMessage());
        }
    }

    // FIND BY ID
    public Concours findById(int id) {
        String sql = "SELECT * FROM concours WHERE id=?";
        Concours c = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

           if (rs.next()) {
          c = new Concours(
        rs.getInt("id"),
        rs.getString("titre"),
        rs.getDate("date"),
        rs.getString("filiere"),
        rs.getInt("capacite"),
        rs.getDouble("seuil")
    );
}

        } catch (SQLException e) {
            System.out.println("Erreur FIND BY ID concours : " + e.getMessage());
        }

        return c;
    }

    // FIND ALL
    public List<Concours> findAll() {
        String sql = "SELECT * FROM concours";
        List<Concours> liste = new ArrayList<>();

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
             Concours c = new Concours(
        rs.getInt("id"),
        rs.getString("titre"),
        rs.getDate("date"),   // ✅ sans toLocalDate()
        rs.getString("filiere"),
        rs.getInt("capacite"),
        rs.getDouble("seuil")
);
                liste.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Erreur FIND ALL concours : " + e.getMessage());
        }

        return liste;
    }

  public List<Concours> getAll() {

    List<Concours> liste = new ArrayList<>();
    String sql = "SELECT * FROM concours";

    try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {

            Concours c;
            c = new Concours(
                    rs.getInt("id"),
                    rs.getString("titre"),
                     rs.getDate("date"),   
                    rs.getString("filiere"),
                    rs.getInt("capacite"),
                    rs.getDouble("seuil")
            );

            liste.add(c);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return liste;
}

  public List<Concours> getByFiliere(String filiereSelectionnee) {
    List<Concours> liste = new ArrayList<>();
    String sql = "SELECT * FROM concours WHERE filiere = ?";
    
    // On utilise la connexion 'this.conn' déjà initialisée dans le constructeur
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, filiereSelectionnee);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            // Utilisation du constructeur complet pour éviter les erreurs de type
            Concours c = new Concours(
                rs.getInt("id"),
                rs.getString("titre"),
                rs.getDate("date"),    // ✅ On récupère un java.sql.Date
                rs.getString("filiere"),
                rs.getInt("capacite"),
                rs.getDouble("seuil")
            );
            liste.add(c);
        }
    } catch (SQLException e) {
        System.out.println("Erreur GET BY FILIERE : " + e.getMessage());
    }
    return liste;
}
}