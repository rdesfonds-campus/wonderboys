package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.characters.heros.HeroFactory;
import fr.campus.wonderboys.db.HeroDAO;

public class Game {

    private final Menu menu;
    private Character currentCharacter;

    public Game(Menu menu) {
        this.menu = menu;
    }

    public void startGame() {

        Board board = new Board();
        Dice dice = new Dice();

        // Création du héros
        HeroFactory heroFactory = new HeroFactory();

        String name = menu.askString("Nom du héros :");

        menu.showMessage("Choisir classe :");
        menu.showMessage("1 - Guerrier");
        menu.showMessage("2 - Mage");

        int type = menu.askInt("Votre choix :");

        currentCharacter = heroFactory.createHero(type, name);

        currentCharacter.setBoardPosition(1);

        menu.showMessage("Début de l'aventure !");
        menu.showMessage(currentCharacter.getName() + " entre dans le donjon.");

        while (true) {

            int roll = dice.roll();
            String cmd = menu.askString("Entrée pour avancer ou 'status'");

            if(cmd.equalsIgnoreCase("status")){
                menu.showMessage(currentCharacter.toString());
            }
            menu.showMessage("🎲 Dé : " + roll);

            int newPosition = currentCharacter.getBoardPosition() + roll;

            if (newPosition == board.getTotalSquares()) {

                menu.showMessage("🏆 Tu tombes pile sur la salle du trésor !");
                currentCharacter.setScore(currentCharacter.getScore() + 1000);

                break;
            }

            if (newPosition > board.getTotalSquares()) {

                menu.showMessage("Tu dépasses la salle du trésor...");
                menu.showMessage("Le trésor se referme sous tes yeux.");

                break;
            }

            currentCharacter.setBoardPosition(newPosition);

            menu.showMessage("Position : " + newPosition);

            Cell cell = board.getCell(newPosition);

            menu.showMessage(cell.toString());

            cell.interact(currentCharacter);

            if (currentCharacter.getLifeLevel() <= 0) {
                menu.showMessage("💀 Tu es mort.");
                break;

            }

            menu.askString("Appuie sur Entrée pour continuer...");
        }

        HeroDAO dao = new HeroDAO();

        dao.changeLifePointsCharacter(
                currentCharacter.getId(),
                currentCharacter.getLifeLevel()
        );
    }

    public void start() {
        startGame();
    }


}