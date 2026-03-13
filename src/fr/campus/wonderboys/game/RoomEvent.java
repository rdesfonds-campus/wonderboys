package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.Character;

import java.util.Random;

public class RoomEvent {

    private final Menu menu;
    private final Random rand = new Random();

    public RoomEvent(Menu menu) {
        this.menu = menu;
    }

    public void trigger(Character hero) {

        menu.showMessage("Vous entrez dans une salle.");

        int choix;

        do {

            menu.showMessage("1 Fouiller");
            menu.showMessage("2 Quitter");

            choix = menu.askInt("Choix : ");

        } while (choix != 1 && choix != 2);

        if (choix == 1) {

            double proba = rand.nextDouble();

            if (proba < 0.3) {

                menu.showMessage("Rien trouvé.");

            } else if (proba < 0.7) {

                menu.showMessage("Petit trésor !");
                hero.setScore(hero.getScore() + 10);

            } else if (proba < 0.9) {

                menu.showMessage("Éboulement ! -5 PV");
                hero.setLifeLevel(hero.getLifeLevel() - 5);

            } else {

                menu.showMessage("Super trésor ! PV restaurés");
                hero.setLifeLevel(hero.getMaxLifeLevel());
            }
        }
    }
}