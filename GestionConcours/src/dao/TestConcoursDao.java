package dao;

import model.Concours;
import java.time.LocalDate;
import java.util.List;

public class TestConcoursDao {

    public static void main(String[] args) {

        ConcoursDao dao = new ConcoursDao();

        // INSERT
        Concours c = new Concours(
        0,
        "Concours Java",
        new java.util.Date(),
        "Informatique",
        100,
        12.0
);
        Concours C = null;
        dao.insert(C);

        // FIND ALL
        List<Concours> liste = dao.findAll();

        for (Concours co : liste) {
            System.out.println(co.getId() + " - " + co.getTitre());
        }
    }
}
