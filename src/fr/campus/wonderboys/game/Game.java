package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.heros.Character;
import fr.campus.wonderboys.characters.heros.HeroManager;
import fr.campus.wonderboys.db.HeroDAO;
import fr.campus.wonderboys.game.board.Board;
import fr.campus.wonderboys.game.combat.CombatEngine;
import fr.campus.wonderboys.game.cells.EnemyCell;
import fr.campus.wonderboys.game.cells.Cell;
import java.util.Random;

/**
 * Orchestrateur principal du jeu Wonderboys.
 * Gère le menu principal et la boucle de déplacement sur le plateau.
 */
public class Game {

    private final Menu menu;
    private final HeroManager heroManager;
    private final Board board;
    private final Dice dice;
    private final CombatEngine combat;
    private final Random rand;

    private Character currentCharacter;

    /**
     * Constructeur : Initialise les moteurs de jeu et le plateau.
     * @param menu L'instance de Menu pour les entrées/sorties.
     */
    public Game(Menu menu) {
        this.menu = menu;
        this.heroManager = new HeroManager(menu);
        this.board = new Board(); // Génère le plateau de 64 cases
        this.dice = new Dice();
        this.combat = new CombatEngine(menu);
        this.rand = new Random();
        this.currentCharacter = null;
    }

    /**
     * Lance le menu principal du jeu.
     */
    public void start() {
        menu.showMessage("=========================");
        menu.showMessage("BIENVENUE DANS WONDERBOYS");
        menu.showMessage("=========================");

        int choice = 0;
        while (choice != 5) {
            displayMainMenu();
            choice = menu.askInt("\nVotre choix :");
            handleMenuChoice(choice);
        }
    }

    private void displayMainMenu() {
        menu.showMessage("\n--- MENU PRINCIPAL ---");
        menu.showMessage("1 - Créer un nouveau héros");
        menu.showMessage("2 - Choisir un héros existant (BDD)");
        menu.showMessage("3 - Modifier les infos d'un héros");
        menu.showMessage("4 - DÉMARRER L'AVENTURE");
        menu.showMessage("5 - Quitter le jeu");
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> currentCharacter = heroManager.createCharacter();
            case 2 -> currentCharacter = heroManager.chooseExistingHero();
            case 3 -> heroManager.editExistingHero();
            case 4 -> launchGameLoop();
            case 5 -> menu.showMessage("À bientôt dans le donjon !");
            default -> menu.showMessage("Choix invalide.");
        }
    }

    /**
     * Boucle de jeu sur le plateau de 64 cases.
     */
    private void launchGameLoop() {
        if (currentCharacter == null) {
            menu.showMessage("Erreur : Aucun héros sélectionné !");
            return;
        }

        // Reset de la position pour le début de partie
        currentCharacter.setBoardPosition(0);
        menu.showMessage("\nL'aventure commence pour " + currentCharacter.getName() + " !");

        // Boucle tant que le joueur n'a pas atteint ou dépassé la case 63
        while (currentCharacter.getBoardPosition() < 63 && currentCharacter.getLifeLevel() > 0) {
            playTurn();
        }

        finalizeGame();
    }

    /**
     * Gère un tour : lancer de dé, déplacement et interaction.
     */
    private void playTurn() {
        menu.askString("\n[Appuyez sur Entrée pour lancer le dé]");
        int roll = dice.roll();
        int newPos = currentCharacter.getBoardPosition() + roll;

        // --- GESTION DE LA SORTIE / CASE FINALE ---
        if (newPos >= 63) {
            currentCharacter.setBoardPosition(63);
            menu.showMessage("🎲 Dé : " + roll + " -> Vous atteignez la case finale (63) !");

            // Interaction spéciale avec le Boss ou Trésor de fin sur la case 63
            Cell finalCell = board.getCell(63);
            handleInteraction(finalCell);

            // On force la position à 64 pour sortir proprement de la boucle while
            if (currentCharacter.getLifeLevel() > 0) {
                currentCharacter.setBoardPosition(64);
            }
            return;
        }

        // Déplacement normal
        currentCharacter.setBoardPosition(newPos);
        Cell currentCell = board.getCell(newPos);

        menu.showMessage("🎲 Dé : " + roll + " -> Case " + newPos + " : " + currentCell.toString());

        handleInteraction(currentCell);

        // Affichage état
        menu.showMessage("Stats : " + currentCharacter.getLifeLevel() + " PV | Score : " + currentCharacter.getScore());
    }

    /**
     * Détermine si on lance un combat ou une interaction classique.
     */
    private void handleInteraction(Cell cell) {
        if (cell instanceof EnemyCell) {
            // Ici, tu peux passer un nom aléatoire ou un objet Enemy
            String monsterName = "Monstre";
            combat.startCombat(monsterName, currentCharacter, rand);
        } else {
            // Polymorphisme : PiegeCell, PotionCell, FouilleCell gèrent leur propre logique
            cell.interact(currentCharacter);
        }
    }

    /**
     * Fin de partie et sauvegarde en base de données.
     */
    private void finalizeGame() {
        if (currentCharacter.getLifeLevel() <= 0) {
            menu.showMessage("\n💀 GAME OVER... Aragorn est tombé au combat.");
        } else {
            menu.showMessage("\n✨ VICTOIRE ! Vous avez survécu au donjon.");
        }

        // Sauvegarde des PV restants dans la BDD
        HeroDAO heroDAO = new HeroDAO();
        heroDAO.changeLifePointsCharacter(currentCharacter.getId(), currentCharacter.getLifeLevel());
        menu.showMessage("Progression synchronisée avec la base de données.");
    }
}