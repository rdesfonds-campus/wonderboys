package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.characters.Warrior;
import fr.campus.wonderboys.characters.Wizard;
import fr.campus.wonderboys.db.HeroDAO;
import fr.campus.wonderboys.equipment.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Gère une partie complète de Wonderboys avec menu et personnages.
 * Lance le menu principal, crée guerrier/magicien, déplace sur plateau 64 cases.
 */
public class Game {

    // Pour discuter avec le joueur
    private final Menu menu;

    // Personnage actuel du joueur
    private Character currentCharacter;

    // Position actuelle sur le plateau
    private int playerPosition;

    // Mini-plateau de test (4 cases)
    private List<Cell> board;

    public Game(Menu menu) {
        this.menu = menu;
        this.currentCharacter = null;
        this.playerPosition = 1;

        /*// Mini-plateau pour les tests
        this.board = new ArrayList<>();
        board.add(new EmptyCell());
        board.add(new EnemyCell());
        board.add(new WeaponCell());
        board.add(new PotionCell());*/
    }

    /**
     * Démarre le jeu avec boucle menu principal.
     */
    public void start() {

        int choice = 0;

        while (choice != 5) {
            menu.showMessage("Bienvenue dans Wonderboy !");
            menu.showMessage("1 - Nouveau personnage");
            menu.showMessage("2 - Choisir un héros existant");
            menu.showMessage("3 - Modifier un personnage");
            menu.showMessage("4 - Démarrer la partie");
            menu.showMessage("5 - Quitter le jeu");

            choice = menu.askInt("Que veux-tu faire ?");

            switch (choice) {
                case 1:
                    menu.showMessage("On va créer un nouveau personnage !");
                    createCharacter();
                    break;

                case 2:
                    chooseExistingHero();
                    break;

                case 3:
                    editExistingHero();
                    break;

                case 4:
                    if (currentCharacter == null) {
                        menu.showMessage("Tu dois créer ou choisir un personnage avant de démarrer !");
                    } else {
                        try {
                            startGame();
                        } catch (OutOfBoardException e) {
                            menu.showMessage("Erreur de plateau : " + e.getMessage());
                        }
                    }
                    break;

                case 5:
                    menu.showMessage("Au revoir !");
                    break;

                default:
                    menu.showMessage("Choix invalide. Réessaie.");
                    break;
            }
        }
    }

    /**
     * Crée Warrior ou Wizard selon choix joueur.
     * Enregistre le personnage en BDD.
     */
    private void createCharacter() {
        menu.showMessage("Choisis ton type de personnage :");
        menu.showMessage("1 - Warrior");
        menu.showMessage("2 - Wizard");

        int choiceType = menu.askInt("Tape 1 ou 2 :");

        while (choiceType != 1 && choiceType != 2) {
            menu.showMessage("Choix invalide pour le type de personnage.");
            choiceType = menu.askInt("Tape 1 ou 2 :");
        }

        int lifeLevel;
        int attackLevel;
        String type;
        String weaponName = null;
        String defenseName = null;

        if (choiceType == 1) {
            type = "Warrior";
            lifeLevel = 10;
            attackLevel = 10;
            menu.showMessage("Tu as choisi : Warrior");
            // Choix de la nouvelle arme
            menu.showMessage("Choisis une nouvelle arme :");
            menu.showMessage("1 - Épée rouillée");
            menu.showMessage("2 - Massue en bois");
            int weaponChoice = menu.askInt("Ton choix d'arme : ");

            if (weaponChoice == 1) {
                weaponName = "Épée rouillée";
            } else {
                weaponName = "Massue en bois";
            }

            // Choix de la nouvelle défense
            menu.showMessage("Choisis une nouvelle défense :");
            menu.showMessage("1 - Petit bouclier en bois");
            menu.showMessage("2 - Grande potion de soin");
            int defenseChoice = menu.askInt("Ton choix de défense : ");

            if (defenseChoice == 1) {
                defenseName = "Petit bouclier en bois";
            } else {
                defenseName = "Grande potion de soin";
            }
        } else {
            type = "Wizard";
            lifeLevel = 6;
            attackLevel = 8;
            menu.showMessage("Tu as choisi : Wizard");

            // Choix de la nouvelle arme (sort)
            menu.showMessage("Choisis un nouveau sort :");
            menu.showMessage("1 - Bâton magique");
            menu.showMessage("2 - Boule de feu");
            int weaponChoice = menu.askInt("Ton choix de sort : ");

            if (weaponChoice == 1) {
                weaponName = "Bâton magique";
            } else {
                weaponName = "Boule de feu";
            }

            // Choix de la nouvelle défense (équipement magique / potion)
            menu.showMessage("Choisis une nouvelle défense :");
            menu.showMessage("1 - Petite potion de soin");
            menu.showMessage("2 - Grande potion de soin");
            int defenseChoice = menu.askInt("Ton choix de défense : ");

            if (defenseChoice == 1) {
                defenseName = "Petite potion de soin";
            } else {
                defenseName = "Grande potion de soin";
            }
        }

        String name = menu.askString("Quel est le nom de ton personnage ?");
        menu.showMessage("Ton personnage s'appelle : " + name);

        OffensiveEquipment weapon;
        if (type.equals("Warrior")) {
            weapon = new Weapon(3, weaponName);
        } else {
            weapon = new OffensiveEquipment("Spell", 4, weaponName);
        }

        DefensiveEquipment defense;
        if (type.equals("Warrior")) {
            defense = new Shield();
        } else {
            defense = new Potion(3, defenseName);
        }


        if (type.equals("Warrior")) {
            currentCharacter = new Warrior(name, lifeLevel, attackLevel, weapon, defense);
        } else {
            currentCharacter = new Wizard(name, lifeLevel, attackLevel, weapon, defense);
        }

        int subChoice = 0;

        while (subChoice != 3) {
            menu.showMessage("Que veux-tu faire avec ce personnage ?");
            menu.showMessage("1 - Afficher ses infos");
            menu.showMessage("2 - Modifier son nom");
            menu.showMessage("3 - Retour au menu principal");

            subChoice = menu.askInt("Ton choix : ");

            switch (subChoice) {
                case 1:
                    menu.showMessage("Infos du personnage : " + currentCharacter);
                    menu.showMessage("Nom de l'arme : " + currentCharacter.getWeapon().getName());
                    menu.showMessage("Nom de la protection : " + currentCharacter.getDefense().getName());
                    break;

                case 2:
                    String newName = menu.askString("Nouveau nom pour ton personnage : ");
                    currentCharacter.setName(newName);
                    menu.showMessage("Nouveau nom enregistré : " + currentCharacter.getName());
                    break;

                case 3:
                    menu.showMessage("Retour au menu principal...");
                    break;

                default:
                    menu.showMessage("Choix invalide dans le sous-menu.");
                    break;
            }
        }

        // Enregistrer le personnage en base de données
        HeroDAO heroDAO = new HeroDAO();
        heroDAO.createHero(currentCharacter);
    }

    /**
     * Modifie un héros existant en BDD (nom + équipement).
     */
    private void editExistingHero() {
        menu.showMessage("Liste des héros en base :");

        HeroDAO heroDAO = new HeroDAO();
        heroDAO.getHeroes();   // affiche les héros en console

        int idToEdit = menu.askInt("Quel Id de héros veux-tu modifier ?");

        // On récupère le héros pour connaître son type
        Character heroFromDb = heroDAO.getHeroById(idToEdit);
        if (heroFromDb == null) {
            menu.showMessage("Aucun héros trouvé avec cet Id.");
            return;
        }

        // Nouveau nom (commun aux deux types)
        String newName = menu.askString("Quel nouveau nom veux-tu donner à ce héros ?");

        String weaponName;
        String defenseName;

        // Cas 1 : le héros est un Warrior
        if (heroFromDb instanceof Warrior) {
            // Choix de la nouvelle arme
            menu.showMessage("Choisis une nouvelle arme :");
            menu.showMessage("1 - Épée rouillée");
            menu.showMessage("2 - Massue en bois");
            int weaponChoice = menu.askInt("Ton choix d'arme : ");

            if (weaponChoice == 1) {
                weaponName = "Épée rouillée";
            } else {
                weaponName = "Massue en bois";
            }

            // Choix de la nouvelle défense
            menu.showMessage("Choisis une nouvelle défense :");
            menu.showMessage("1 - Petit bouclier en bois");
            menu.showMessage("2 - Grande potion de soin");
            int defenseChoice = menu.askInt("Ton choix de défense : ");

            if (defenseChoice == 1) {
                defenseName = "Petit bouclier en bois";
            } else {
                defenseName = "Grande potion de soin";
            }

            // Création d'un Warrior pour pousser les valeurs en BDD
            Warrior heroToUpdate = new Warrior(
                    newName,
                    heroFromDb.getLifeLevel(),
                    heroFromDb.getAttackLevel(),
                    null,
                    null
            );
            heroToUpdate.setId(idToEdit);
            heroToUpdate.setWeapon(new Weapon(3, weaponName));
            heroToUpdate.setDefense(new Shield());

            heroDAO.editHero(heroToUpdate);

            // Cas 2 : le héros est un Wizard
        } else if (heroFromDb instanceof Wizard) {
            // Choix de la nouvelle arme (sort)
            menu.showMessage("Choisis un nouveau sort :");
            menu.showMessage("1 - Bâton magique");
            menu.showMessage("2 - Boule de feu");
            int weaponChoice = menu.askInt("Ton choix de sort : ");

            if (weaponChoice == 1) {
                weaponName = "Bâton magique";
            } else {
                weaponName = "Boule de feu";
            }

            // Choix de la nouvelle défense (équipement magique / potion)
            menu.showMessage("Choisis une nouvelle défense :");
            menu.showMessage("1 - Petite potion de soin");
            menu.showMessage("2 - Grande potion de soin");
            int defenseChoice = menu.askInt("Ton choix de défense : ");

            if (defenseChoice == 1) {
                defenseName = "Petite potion de soin";
            } else {
                defenseName = "Grande potion de soin";
            }

            // Création d'un Wizard pour pousser les valeurs en BDD
            Wizard heroToUpdate = new Wizard(
                    newName,
                    heroFromDb.getLifeLevel(),
                    heroFromDb.getAttackLevel(),
                    null,
                    null
            );
            heroToUpdate.setId(idToEdit);
            // Ici tu peux utiliser OffensiveEquipment / DefensiveEquipment adaptés aux wizards
            heroToUpdate.setWeapon(new OffensiveEquipment("Spell", 4, weaponName));
            heroToUpdate.setDefense(new Potion(3, defenseName));

            heroDAO.editHero(heroToUpdate);
        }

        menu.showMessage("Modification envoyée en base pour l'Id " + idToEdit +
                " avec le nom : " + newName);
    }



    /**
     * Choisir un héros existant en BDD pour jouer.
     */
    private void chooseExistingHero() {
        HeroDAO heroDAO = new HeroDAO();

        menu.showMessage("Liste des héros disponibles :");
        heroDAO.getHeroes();

        int idToUse = menu.askInt("Quel Id de héros veux-tu utiliser pour jouer ?");

        Character hero = heroDAO.getHeroById(idToUse);

        if (hero == null) {
            menu.showMessage("Aucun héros trouvé avec cet Id.");
        } else {
            currentCharacter = hero;
            menu.showMessage("Tu joueras maintenant avec : " + currentCharacter.getName());
        }
    }

    /**
     * Affiche le mini-plateau de test.
     */
    public void displayBoard() {
        System.out.println("=== MINI-PLATEAU (4 cases) ===");
        for (int i = 0; i < board.size(); i++) {
            System.out.println("Case " + (i + 1) + " : " + board.get(i).toString());
        }
        System.out.println("Position joueur : " + playerPosition);
        System.out.println("=========================");
    }

    /**
     * Lance la vraie partie plateau 64 cases avec dé.
     */
    private void startGame() throws OutOfBoardException {

        // On crée le plateau (64 cases)
        Board board = new Board();

        // On crée un dé à 6 faces (par défaut)
        Dice dice = new Dice();

        // Au démarrage, le joueur est sur la case 1
        playerPosition = 1;

        menu.showMessage("Début de la partie !");
        menu.showMessage("Personnage : " + currentCharacter.getName());
        menu.showMessage("Case " + playerPosition + " / " + board.getTotalSquares());

        menu.showMessage("Aperçu du plateau :");
        for (int i = 0; i < 10; i++) { // 10 premières cases
            menu.showMessage("Case " + (i+1) + " : " + board.getCell(i));
        }

        // Tant que tu n'es pas à la fin, tu rejoues un tour
        while (playerPosition < board.getTotalSquares()) {

            int roll = dice.roll();
            int newPosition = playerPosition + roll;

            if (newPosition > board.getTotalSquares()) {
                throw new OutOfBoardException(
                        "Position " + newPosition + " hors du plateau (max = " + board.getTotalSquares() + ")"
                );
            }

            menu.showMessage("Tu lances le dé : " + roll);
            menu.showMessage("Tu avances : " + playerPosition + " -> " + newPosition);

            playerPosition = newPosition;
        }

        menu.showMessage("Bravo ! Tu es arrivé à la case " + board.getTotalSquares() + " !");
        HeroDAO heroDAO = new HeroDAO();
        heroDAO.changeLifePointsCharacter(
                currentCharacter.getId(),
                currentCharacter.getLifeLevel()
        );


        int endChoice = 0;

        while (endChoice != 1 && endChoice != 2) {
            menu.showMessage("Que veux-tu faire ?");
            menu.showMessage("1 - Recommencer une partie");
            menu.showMessage("2 - Retour au menu principal");
            endChoice = menu.askInt("Ton choix : ");
        }

        if (endChoice == 1) {
            startGame();
        } else {
            menu.showMessage("Retour au menu principal...");
        }
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    // ====== MINI-PLATEAU ======

    private int rollDice() {
        return 1;
    }

    public void playTurnMiniBoard() {
        System.out.println("\n--- NOUVEAU TOUR ---");
        int diceResult = rollDice();
        playerPosition += diceResult;

        if (playerPosition > board.size()) {
            System.out.println("Fin du mini-plateau !");
            return;
        }

        System.out.println("Position joueur : " + playerPosition);
        Cell currentCase = board.get(playerPosition - 1);
        System.out.println("Tu es sur : " + currentCase.toString());
        System.out.println("------------------");
    }
}
