package co.kozao.internmanagement.dao;

import co.kozao.internmanagement.model.Intern;
import co.kozao.internmanagement.model.Supervisor;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        InternManagementDao dao = new InternManagementDaoImpl();

        System.out.println("=== Test de connexion et récupération ===");
        try {
            List<Intern> interns = dao.readAll();
            System.out.println("Connexion réussie ! Nombre de stagiaires : " + interns.size());
            for (Intern i : interns) {
                System.out.println("- " + i.getName() + " " + i.getSurname());
            }
        } catch (Exception e) {
            System.err.println("Échec du test ! Vérifiez votre configuration de base de données.");
            e.printStackTrace();
        }
    }
}