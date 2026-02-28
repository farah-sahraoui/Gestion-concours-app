package util;

import java.sql.Connection;

public class TestConnexion {
    public static void main(String[] args) {

        Connection conn = DBConnexion.getConnection();

        if (conn != null) {
            System.out.println("Connexion réussie !");
        } else {
            System.out.println("Erreur connexion !");
        }
    }
}