package game;

import characters.Warrior;
import characters.Wizard;
import equipment.OffensiveEquipment;
import equipment.DefensiveEquipment;

public class Game {

    private Menu menu;
    private characters.Character currentCharacter;
    private int position;

    public Game(Menu menu) {
        this.menu = menu;
        this.position = 1;
    }

    public void start() {
        menu.showMessage("Bienvenue dans Wonderboys !");
        menu.showMessage("=========================");

        int choix = 0;
        while (choix != 3) {
            menu.showMessage("1 - Créer un personnage");
            menu.showMessage("2 - Jouer");
            menu.showMessage("3 - Quitter");
            choix = menu.askInt("Ton choix :");

            switch (choix) {
                case 1:
                    creerPersonnage();
                    break;
                case 2:
                    if (currentCharacter == null) {
                        menu.showMessage("Crée d'abord un personnage !");
                    } else {
                        jouer();
                    }
                    break;
                case 3:
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

    private void jouer() {
        Board plateau = new Board();
        Dice de = new Dice(6);
        position = 1;

        menu.showMessage("\n--- Début de la partie ! ---");
        menu.showMessage(currentCharacter.getName() + " entre dans le donjon.");

        while (position < plateau.getTotalCases()) {
            menu.askString("Appuie sur Entrée pour lancer le dé...");

            int lancer = de.roll();
            int nouvellePosition = position + lancer;

            menu.showMessage("Tu lances le dé : " + lancer);

            if (nouvellePosition >= plateau.getTotalCases()) {
                menu.showMessage("Tu arrives à la case 64 - Salle du trésor !");
                menu.showMessage("VICTOIRE ! Bien joué " + currentCharacter.getName() + " !");
                break;
            }

            position = nouvellePosition;
            menu.showMessage("Tu avances à la case " + position + " / " + plateau.getTotalCases());

            Cell caseActuelle = plateau.getCase(position);
            menu.showMessage("Tu es sur : " + caseActuelle);
            caseActuelle.interact(currentCharacter);
        }
    }
}