package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.characters.enemies.BestiaireFactory;
import fr.campus.wonderboys.characters.enemies.Enemy;
import fr.campus.wonderboys.characters.heros.HeroFactory;
import fr.campus.wonderboys.db.HeroDAO;

import java.util.Random;

public class Game {

    private final Menu menu;
    private Character currentCharacter;

    private final Random rand = new Random();

    public Game(Menu menu) {
        this.menu = menu;
    }

    public void start() {

        int choice = 0;

        while (choice != 5) {

            menu.showMessage("\n1 - Créer un personnage");
            menu.showMessage("2 - Choisir un héros existant");
            menu.showMessage("3 - Modifier un personnage");
            menu.showMessage("4 - Démarrer la partie");
            menu.showMessage("5 - Quitter");

            choice = menu.askInt("Choix : ");

            switch (choice) {

                case 1:
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
                        menu.showMessage("Crée ou choisis un personnage !");
                    } else {
                        startGame();
                    }

                    break;

                case 5:
                    menu.showMessage("Au revoir !");
                    break;

                default:
                    menu.showMessage("Choix invalide.");
            }
        }
    }

    private void startGame() {

        Board gameBoard = new Board();
        Dice dice = new Dice();

        CombatSystem combat = new CombatSystem(menu);
        RoomEvent roomEvent = new RoomEvent(menu);

        currentCharacter.setBoardPosition(0);

        while (currentCharacter.getBoardPosition() < gameBoard.getTotalSquares()) {

            int roll = dice.roll();
            int newPosition = currentCharacter.getBoardPosition() + roll;

            if (newPosition >= gameBoard.getTotalSquares()) {
                menu.showMessage("Bravo ! Salle du trésor !");
                break;
            }

            currentCharacter.setBoardPosition(newPosition);

            encounter(newPosition, combat, roomEvent);

            menu.askString("Entrée pour continuer...");
        }
    }

    private void encounter(int position, CombatSystem combat, RoomEvent roomEvent) {

        if (rand.nextDouble() < 0.5) {

            Enemy monster = BestiaireFactory.creerMonstre(position);

            if (monster != null) {
                combat.fight(currentCharacter, monster);
            }

        } else {

            roomEvent.trigger(currentCharacter);

        }
    }

    private void createCharacter() {

        menu.showMessage("1 Warrior");
        menu.showMessage("2 Wizard");

        int choice = menu.askInt("Choix : ");
        String name = menu.askString("Nom : ");

        currentCharacter = HeroFactory.createHero(choice, name);

        HeroDAO heroDAO = new HeroDAO();
        heroDAO.createHero(currentCharacter);
    }

    private void chooseExistingHero() {

        HeroDAO dao = new HeroDAO();

        dao.getHeroes();

        int id = menu.askInt("Id du héros : ");

        currentCharacter = dao.getHeroById(id);

        if (currentCharacter == null) {
            menu.showMessage("Héros introuvable");
        }
    }

    private void editExistingHero() {

        HeroDAO dao = new HeroDAO();

        dao.getHeroes();

        int id = menu.askInt("Id : ");

        Character hero = dao.getHeroById(id);

        if (hero == null) {
            menu.showMessage("Introuvable");
            return;
        }

        String newName = menu.askString("Nouveau nom : ");

        hero.setName(newName);

        dao.editHero(hero);
    }
}