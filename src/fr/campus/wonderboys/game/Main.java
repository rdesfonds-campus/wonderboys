package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.enemies.*;
import fr.campus.wonderboys.equipment.*;
import fr.campus.wonderboys.characters.Warrior;
import fr.campus.wonderboys.pieges.*;

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
/*
        System.out.println("\n=== TEST FRAGMENT ===");
        Warrior hero2 = new Warrior("Test", 10, 3, null, null);
        FragmentPuissance frag = new FragmentPuissance();
        hero2.setOffensiveEquipment(frag); // Équipe manuellement pour test
        System.out.println("Équipement: " + hero2.getOffensiveEquipment());
        System.out.println("Dégâts boost: " + hero2.getOffensiveEquipment().getAttackLevel());
*/
        System.out.println("\n=== TEST SUPERSOINS ===");
        Warrior hero3 = new Warrior("Thor", 5, 3, null, null); // PV bas pour tester
        SuperSoin superSoin = new SuperSoin();
        superSoin.use(hero3);
        System.out.println("PV après Super Soin: " + hero3.getLifeLevel());

        System.out.println("\n=== TEST AMULETTE ===");
        AmuletteProtection amu = new AmuletteProtection();
        System.out.println(amu);
        amu.use(hero); // ou hero4 si tu préfères

        // Dans Main.java après Amulette
        System.out.println("\n=== TEST BAZOOKA ===");
        Bazooka baz = new Bazooka();
        baz.testUse(); // Appel la méthode test
        System.out.println(baz);

        System.out.println("\n=== TEST SORT ULTIMA ===");
        SortUltima ultima = new SortUltima();
        ultima.testUse();
        System.out.println(ultima);

        System.out.println("\n=== TEST LAME VORPALE ===");
        LameVorpale lame = new LameVorpale();
        lame.testUse();
        System.out.println(lame);

        System.out.println("\n=== TEST ÉTOILE MARIO ===");
        EtoileMario etoile = new EtoileMario();
        etoile.use(null); // Test sans hero
        System.out.println(etoile);

        System.out.println("\n=== TEST PIÈGE TROU ===");
        Warrior hero5 = new Warrior("Test", 10, 3, null, null);
        Oubliette trou = new Oubliette();
        trou.interact(hero5);
        System.out.println(trou);

        System.out.println("\n=== TEST PIÈGE MINE ===");
        Mine mine = new Mine();
        mine.interact(hero5);
        System.out.println(mine);

        System.out.println("\n=== TEST PIÈGE POISON ===");
        NuagePoison poison = new NuagePoison();
        poison.interact(hero5);
        System.out.println(poison);

        System.out.println("\n=== TEST PIÈGE ÉBOULEMENT ===");
        Eboulement eboule = new Eboulement();
        eboule.interact(hero5);
        System.out.println(eboule);

        System.out.println("\n=== TEST PIÈGE FLAMMES ===");
        Flammes flammes = new Flammes();
        flammes.interact(hero5);
        System.out.println(flammes);


        // Lancement du jeu
        Menu menu = new Menu();
        Game game = new Game(menu);
        game.start();
    }
}
