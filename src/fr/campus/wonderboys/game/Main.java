package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.enemies.*;
import fr.campus.wonderboys.db.DatabaseConnection;
import fr.campus.wonderboys.db.BoardCellData;
import fr.campus.wonderboys.db.BoardCellDAO;
import fr.campus.wonderboys.db.HeroDAO;
import fr.campus.wonderboys.game.EmptyCell;
import fr.campus.wonderboys.game.EnemyCell;
import fr.campus.wonderboys.characters.Warrior;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        // ... ton code existant (connexion BDD, BoardCellDAO, etc.) ...

        // Test rapide Goblin + interact (temporaire)
        Warrior hero = new Warrior("TestHero", 10, 5, null, null);

        System.out.println("\n=== TEST CELLS ===");
        EmptyCell empty = new EmptyCell();
        empty.interact(hero);
        System.out.println("\n=== TEST NOUVEAUX MONSTRES ===");
        Serpent serp = new Serpent();
        System.out.println(serp);

        Champignon champi = new Champignon();
        System.out.println(champi);

        Goblin gob = new Goblin();
        System.out.println(gob);

        EnemyCell enemyCell = new EnemyCell();
        enemyCell.interact(hero);

        Chevalier chev = new Chevalier();
        System.out.println(chev);

        System.out.println("\n=== TEST DÉGÂTS VARIABLES ===");
        Serpent serpent = new Serpent();
        for (int i = 0; i < 5; i++) {
            System.out.println("Serpent attaque: " + serpent.calculateDamage());
        }

        Chevalier cheval = new Chevalier();
        for (int i = 0; i < 5; i++) {
            System.out.println("Chevalier attaque: " + cheval.calculateDamage());
        }
        Dragon dragon = new Dragon();
        System.out.println(dragon);
        System.out.println("Dragon attaque: " + dragon.calculateDamage());


        // Lancement du jeu
        Menu menu = new Menu();
        Game game = new Game(menu);
        game.start();
    }
}
