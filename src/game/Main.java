package game;

import characters.Warrior;
import characters.Wizard;
import equipment.OffensiveEquipment;
import equipment.DefensiveEquipment;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Game game = new Game(menu);
        game.start();
    }
}