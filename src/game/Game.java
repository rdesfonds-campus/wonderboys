package game;

import characters.Character;
import characters.Warrior;
import characters.Wizard;
import equipment.OffensiveEquipment;
import equipment.DefensiveEquipment;

public class Game {

        private Menu menu;
        private characters.Character currentCharacter;

        public Game(Menu menu) {
            this.menu = menu;
        }

        public void start() {
            menu.showMessage("Bienvenue dans Wonderboys !");
            menu.showMessage("===========================");

            int choix = 0;
            while (choix != 2) {
                menu.showMessage("1 - Créer un personnage");
                menu.showMessage("2 - Quitter");
                choix = menu.askInt("Ton choix :");

                switch (choix) {
                    case 1:
                        creerPersonnage();
                        break;
                    case 2:
                        menu.showMessage("Au revoir !");
                        break;
                    default:
                        menu.showMessage("Choix invalide.");
                        break;
                }
            }

}

    private void creerPersonnage() {
        menu.showMessage("Quel type de personnage veux-tu ?");
        menu.showMessage("1 - Warrior");
        menu.showMessage("2 - Wizard");

        int type = menu.askInt("Ton choix :");

        String nom = menu.askString("Quel est le nom de ton personnage ?");

        if (type == 1) {
            OffensiveEquipment arme = new OffensiveEquipment("Weapon", 3, "Épée rouillée");
            DefensiveEquipment bouclier = new DefensiveEquipment("Shield", 2, "Petit bouclier");
            currentCharacter = new Warrior(nom, 10, 5, arme, bouclier);
        } else {
            OffensiveEquipment sort = new OffensiveEquipment("Spell", 4, "Boule de feu");
            DefensiveEquipment potion = new DefensiveEquipment("Potion", 3, "Petite potion");
            currentCharacter = new Wizard(nom, 6, 8, sort, potion);
        }

        menu.showMessage("Personnage créé !");
        menu.showMessage(currentCharacter.toString());
    }
}