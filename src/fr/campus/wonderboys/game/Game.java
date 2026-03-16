package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.*;
import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.characters.heros.Warrior;
import fr.campus.wonderboys.characters.heros.Wizard;
import fr.campus.wonderboys.db.HeroDAO;
import fr.campus.wonderboys.game.Cell;

import java.util.List;

public class Game {

    private final Menu menu;
    private Character currentCharacter;

    private HeroDAO heroDAO = new HeroDAO();

    public Game(Menu menu) {
        this.menu = menu;
    }

    public void start() {
        showMainMenu();
    }

    private void showMainMenu() {

        int choice = 0;

        while (choice != 3) {

            menu.showMessage("\n=== WONDERBOYS ===");
            menu.showMessage("1 - Créer un héros");
            menu.showMessage("2 - Charger un héros");
            menu.showMessage("3 - Quitter");

            choice = menu.askInt("Choix : ");

            switch (choice) {

                case 1 -> createCharacter();

                case 2 -> chooseExistingHero();

                case 3 -> menu.showMessage("Au revoir aventurier !");
            }
        }
    }

    public void createCharacter() {

        String name = menu.askString("Nom du héros :");

        int type = menu.askInt("1 - Warrior\n2 - Wizard");

        if (type == 1) {
            currentCharacter = new Warrior(name);
        } else {
            currentCharacter = new Wizard(name);
        }

        HeroDAO dao = new HeroDAO();
        dao.saveCharacter(currentCharacter);

        menu.showMessage("Héros créé !");
    }

    public void chooseExistingHero() {

        List<Character> heroes = heroDAO.getAllHeroes();

        if (heroes.isEmpty()) {
            menu.showMessage("Aucun héros enregistré.");
            return;
        }

        menu.showMessage("\n--- Héros disponibles ---");

        for (int i = 0; i < heroes.size(); i++) {
            menu.showMessage((i + 1) + " - " + heroes.get(i).getName());
        }

        int choice = menu.askInt("Choix : ");

        currentCharacter = heroes.get(choice - 1);

        menu.showMessage("Héros chargé : " + currentCharacter.getName());

        startGame();
    }

    public void startGame() {

        currentCharacter.setBoardPosition(1);

        Dice dice = new Dice();
        Board board = new Board();
        while (true) {

            int roll = dice.roll();

            int newPosition = currentCharacter.getBoardPosition() + roll;

            menu.showMessage("Dé : " + roll);

            if (newPosition >= 64) {

                menu.showMessage("🏆 Salle du trésor !");
                currentCharacter.gainScore(1000);

                menu.showMessage("Score final : " + currentCharacter.getScore());

                heroDAO.changeLifePointsCharacter(
                        currentCharacter.getId(),
                        currentCharacter.getLifeLevel()
                );

                break;
            }

            currentCharacter.setBoardPosition(newPosition);

            menu.showMessage("Position : " + newPosition);

            Cell cell = board.getCell(newPosition - 1);
            cell.interact(currentCharacter);

            menu.askString("Entrée pour continuer...");
        }
    }public Character getCurrentCharacter() {
        return currentCharacter;
    }

    public void editExistingHero() {

        if (currentCharacter == null) {
            menu.showMessage("Aucun héros sélectionné.");
            return;
        }

        menu.showMessage("Modification du héros : " + currentCharacter.getName());

        String newName = menu.askString("Nouveau nom : ");
        currentCharacter.setName(newName);

        menu.showMessage("Nom mis à jour !");
    }
}