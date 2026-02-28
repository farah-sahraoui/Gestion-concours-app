package dao;

import model.Utilisateur;
import util.DBConnexion;
import util.PasswordUtil;

import java.sql.*;

public class UtilisateurDao {

    private Connection conn;

    public UtilisateurDao() {
        conn = DBConnexion.getConnection();
    }

    public Utilisateur login(String email, String password) {

        String hashedPassword = PasswordUtil.hashPassword(password);

        String sql = "SELECT * FROM utilisateur WHERE email=? AND password=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, hashedPassword);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getInt("candidatId")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void insert(Utilisateur u) {

        String sql = "INSERT INTO utilisateur(email, password, role, candidatId) VALUES(?,?,?,?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getUsername());

            String hashedPassword = PasswordUtil.hashPassword(u.getPassword());
            ps.setString(2, hashedPassword);

            ps.setString(3, u.getRole());
            ps.setInt(4, u.getCandidatId());

            ps.executeUpdate();

            System.out.println("Utilisateur ajouté avec succès !");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
        
