package dao;

import model.Candidat;
import java.util.List;

public class TestCandidatDao {

    public static void main(String[] args) {

        CandidatDao dao = new CandidatDao();

        // INSERT
     
           Candidat c = new Candidat("Imane", "imane@gmail.com", "Rabat", "Master");
       
        dao.insert(c);
  

        // FIND ALL
        List<Candidat> liste = dao.getAll();

        for (Candidat cand : liste) {
            System.out.println(cand.getId() + " - " + cand.getNom());
        }

        // UPDATE (changer ID selon ce qui existe)
        Candidat update = new Candidat(1, "Sara Updated", "sara@gmail.com", "Marrakech", "Licence");
        dao.update(update);

        // DELETE (exemple id=2)
        // dao.delete(2);
    }
}