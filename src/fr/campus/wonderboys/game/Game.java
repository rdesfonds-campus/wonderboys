package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.characters.Warrior;
import fr.campus.wonderboys.characters.Wizard;
import fr.campus.wonderboys.equipment.*;
import fr.campus.wonderboys.game.OutOfBoardException;
import java.util.ArrayList;
import java.util.List;




/**
 * Gère une partie complète de Wonderboys avec menu et personnages.
 * Lance le menu principal, crée guerrier/magicien, déplace sur plateau 64 cases.
 *
 * @author Romain D
 * @version 1.0
 */
public class Game {

    // Attribut : permet de discuter avec le joueur (afficher, poser des questions)
    private final Menu menu;

    // Attribut : le personnage actuel du joueur (au début il sera vide)
    private Character currentCharacter;

    // Position actuelle du joueur sur le plateau
    private int playerPosition;

    // Le plateau de jeu (mini version 4 cases)
    private List<Cell> board;

    // Constructeur : sert à créer une partie avec un menu
    /**
     * Crée une nouvelle partie avec menu d'interaction.
     *
     * @param menu objet Menu pour afficher messages et poser questions joueur
     */
    public Game(Menu menu) {
        this.menu = menu;
        this.currentCharacter = null; // pas de personnage au début
        this.playerPosition = 1;      // le joueur commence case 1
   // Mini-plateau de test (4 cases)
        this.board = new ArrayList<>();
        board.add(new EmptyCell());
        board.add(new EnemyCell());
        board.add(new WeaponCell());
        board.add(new PotionCell());
    }


    /**
     * Démarre le jeu avec boucle menu principal.
     * Propose : créer perso, lancer partie, quitter.
     * Appelle createCharacter() ou startGame().
     */
    public void start() {

        int choice = 0;

        while (choice != 2) {
            menu.showMessage("Bienvenue dans Wonderboy !");
            menu.showMessage("1 - Nouveau personnage");
            menu.showMessage("2 - Quitter le jeu");
            menu.showMessage("3 - Démarrer la partie");

            choice = menu.askInt("Que veux-tu faire ?");

            switch (choice) {
                case 1:
                    menu.showMessage("On va créer un nouveau personnage !");
                    createCharacter();
                    break;

                case 2:
                    menu.showMessage("Au revoir !");
                    break;

                case 3:
                    if (currentCharacter == null) {
                        menu.showMessage("Tu dois créer un personnage avant de démarrer !");
                    } else {
                        try {
                            startGame();
                        } catch (OutOfBoardException e) {
                            menu.showMessage("Erreur de plateau : " + e.getMessage());
                        }
                    }

                    break;

                default:
                    menu.showMessage("Choix invalide. Réessaie.");
                    break;
            }


        }
    }


    /**
     * Crée Warrior ou Wizard selon choix joueur (1/2).
     * Demande nom, crée arme/défense, sous-menu infos/modif.
     * Stocke dans currentCharacter.
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

        if (choiceType == 1) {
            type = "Warrior";
            lifeLevel = 10;
            attackLevel = 5;
            menu.showMessage("Tu as choisi : Warrior");
        } else {
            type = "Wizard";
            lifeLevel = 6;
            attackLevel = 8;
            menu.showMessage("Tu as choisi : Wizard");
        }

        String name = menu.askString("Quel est le nom de ton personnage ?");
        menu.showMessage("Ton personnage s'appelle : " + name);

        OffensiveEquipment weapon;
        if (type.equals("fr.campus.wonderboys.characters.Warrior")) {
            weapon = new Weapon(3, "Épée rouillée");  // Weapon a déjà "Weapon" dedans
        } else {
            weapon = new OffensiveEquipment("fr.campus.wonderboys.equipment.Spell", 4, "Boule de feu");
        }

        DefensiveEquipment defense;
        if (type.equals("fr.campus.wonderboys.characters.Warrior")) {
            defense = new Shield(2, "Petit bouclier en bois");
        } else {
            defense = new Potion(3, "Petite potion de soin");
        }


        if (type.equals("fr.campus.wonderboys.characters.Warrior")) {
            currentCharacter = new Warrior(
                    name,
                    lifeLevel,
                    attackLevel,
                    weapon,
                    defense
            );
        } else {
            currentCharacter = new Wizard(
                    name,
                    lifeLevel,
                    attackLevel,
                    weapon,
                    defense
            );
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




    }
    /**
     * Lance partie plateau 64 cases avec dé.
     * Déplace currentCharacter jusqu'à fin ou OutOfBoardException.
     * Propose recommencer ou menu principal.
     * @throws OutOfBoardException si position > 64
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
        int endChoice = 0;

// On demande au joueur ce qu'il veut faire maintenant
        while (endChoice != 1 && endChoice != 2) {
            menu.showMessage("Que veux-tu faire ?");
            menu.showMessage("1 - Recommencer une partie");
            menu.showMessage("2 - Retour au menu principal");
            endChoice = menu.askInt("Ton choix : ");
        }

        if (endChoice == 1) {
            // On relance une nouvelle partie (même personnage)
            startGame();
        } else {
            // On ne fait rien : on retourne au menu principal (car startGame() se termine)
            menu.showMessage("Retour au menu principal...");
        }

    }
    // Getter pour la position du joueur (placé après startGame, mais toujours dans la classe)
    public int getPlayerPosition() {
        return playerPosition;
    }
    /**
     * Affiche le plateau de jeu avec les numéros de cases.
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
     * Lance un dé pipé qui retourne toujours 1.
     * Pour tester facilement le déplacement d'une case.
     *
     * @return toujours 1
     */
    private int rollDice() {
        return 1;
    }
    /**
     * Un tour de jeu sur le mini-plateau de 4 cases.
     * Lance le dé (toujours 1), avance le joueur, affiche la case actuelle.
     */
    public void playTurnMiniBoard() {
        System.out.println("\n--- NOUVEAU TOUR ---");

        // Lancer le dé
        int diceResult = rollDice();

        // Avancer le joueur
        playerPosition += diceResult;

        // Vérifier qu'on ne dépasse pas le plateau
        if (playerPosition > board.size()) {
            System.out.println("🎉 Fin du mini-plateau !");
            return;
        }

        // Afficher la nouvelle position
        System.out.println("Position joueur : " + playerPosition);

        // Afficher la case où il est tombé
        Cell currentCase = board.get(playerPosition - 1); // -1 car ArrayList commence à 0
        System.out.println("Tu es sur : " + currentCase.toString());

        System.out.println("------------------");
    }

}