package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.enemies.d4.Goblin;

/**
 * Classe principale du jeu Wonderboys.
 * Cette classe contient la méthode main qui lance tous les tests unitaires
 * pour vérifier le bon fonctionnement des cellules, monstres, équipements,
 * pièges et du jeu complet.
 *
 * Tous les tests sont commentés sauf les premiers pour éviter une exécution
 * trop longue. Décommentez un test à la fois pour le faire fonctionner.
 *
 * @author Romain (apprenant Java niveau débutant)
 * @version 1.0 - Mars 2026
 */
public class Main {

    /**
     * Méthode principale qui exécute les tests et lance le jeu.
     *
     * @param args arguments de la ligne de commande (non utilisés ici)
     */
    public static void main(String[] args) {
        // TODO: Ajouter ici la connexion BDD et BoardCellDAO si nécessaire
        // Exemple: BoardCellDAO dao = new BoardCellDAO();
        // dao.loadBoard();

        /*// === TEST CELLS (TOUJOURS ACTIF - PREMIER TEST) ===
        System.out.println("\n=== TEST CELLS ===");
        Warrior hero = new Warrior("TestHero", 10, 5, null, null);
        EmptyCell empty = new EmptyCell();
        empty.interact(hero);
        /* FIN DU PREMIER TEST - Dis-moi quand tu as vu le résultat ! */

        // === TEST NOUVEAUX MONSTRES (COMMENTÉ) ===
        /*
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
        */

        // === TEST DÉGÂTS VARIABLES (COMMENTÉ) ===
        /*
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
        */

        // === TEST FRAGMENT (COMMENTÉ) ===
        /*
        System.out.println("\n=== TEST FRAGMENT ===");
        Warrior hero2 = new Warrior("Test", 10, 3, null, null);
        FragmentPuissance frag = new FragmentPuissance();
        hero2.setOffensiveEquipment(frag);
        System.out.println("Équipement: " + hero2.getOffensiveEquipment());
        System.out.println("Dégâts boost: " + hero2.getOffensiveEquipment().getAttackLevel());
        */

        // === TEST SUPERSOINS (COMMENTÉ) ===
        /*
        System.out.println("\n=== TEST SUPERSOINS ===");
        Warrior hero3 = new Warrior("Thor", 5, 3, null, null);
        SuperSoin superSoin = new SuperSoin();
        superSoin.use(hero3);
        System.out.println("PV après Super Soin: " + hero3.getLifeLevel());
        */

        // === TEST AMULETTE (COMMENTÉ) ===
        /*
        System.out.println("\n=== TEST AMULETTE ===");
        AmuletteProtection amu = new AmuletteProtection();
        System.out.println(amu);
        amu.use(hero);
        */

        // === TEST BAZOOKA (COMMENTÉ) ===
        /*
        System.out.println("\n=== TEST BAZOOKA ===");
        Bazooka baz = new Bazooka();
        baz.testUse();
        System.out.println(baz);
        */

        // === TEST SORT ULTIMA (COMMENTÉ) ===
        /*
        System.out.println("\n=== TEST SORT ULTIMA ===");
        SortUltima ultima = new SortUltima();
        ultima.testUse();
        System.out.println(ultima);
        */

        // === TEST LAME VORPALE (COMMENTÉ) ===
        /*
        System.out.println("\n=== TEST LAME VORPALE ===");
        LameVorpale lame = new LameVorpale();
        lame.testUse();
        System.out.println(lame);
        */

        // === TEST ÉTOILE MARIO (COMMENTÉ) ===
        /*
        System.out.println("\n=== TEST ÉTOILE MARIO ===");
        EtoileMario etoile = new EtoileMario();
        etoile.use(null);
        System.out.println(etoile);
        */

        // === TESTS PIÈGES (COMMENTÉS) ===
        /*
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
        */
        /*Goblin g = new Goblin();  // ✅ Marche !
        System.out.println(g);  // toString
        g.subirDegats(9);  // Meurt + message score*/


        // === LANCEMENT DU JEU COMPLET (COMMENTÉ POUR NE PAS LE FAIRE TOURNER) ===

        // Lancement du jeu - À décommenter seulement après tous les tests
        Menu menu = new Menu();
        Game game = new Game(menu);
        game.start();


        // Fin de main - Tous les tests sont prêts, un seul actif !
    }
    
}
