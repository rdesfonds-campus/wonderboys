package fr.campus.wonderboys.game;

import fr.campus.wonderboys.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;import fr.campus.wonderboys.db.HeroDAO;


public class Main {

    public static void main(String[] args) {

        // 1) Test de connexion à la base
        System.out.println("Début du test de connexion...");

        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Connexion réussie à la base wonderboys !");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base :");
            e.printStackTrace();
        }
        HeroDAO heroDAO = new HeroDAO();
        heroDAO.getHeroes();

        // 2) Lancement normal du jeu
        Menu menu = new Menu();
        Game game = new Game(menu);
        game.start();
    }
}
