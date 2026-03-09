package fr.campus.wonderboys.game;

import fr.campus.wonderboys.db.DatabaseConnection;
import fr.campus.wonderboys.db.BoardCellData;
import fr.campus.wonderboys.db.BoardCellDAO;
import java.util.List;

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
        /*HeroDAO heroDAO = new HeroDAO();
        fr.campus.wonderboys.characters.Character hero = heroDAO.getHeroById(1);
        System.out.println(hero);*/
        BoardCellDAO boardDao = new BoardCellDAO();
        List<BoardCellData> cells = boardDao.loadBoardCells();
        System.out.println(cells);


        // 2) Lancement normal du jeu
        Menu menu = new Menu();
        Game game = new Game(menu);
        game.start();
    }
}
