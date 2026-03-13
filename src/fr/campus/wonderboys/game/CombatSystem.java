package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.characters.enemies.Enemy;

import java.util.Random;

public class CombatSystem {

    private final Menu menu;
    private final Random rand = new Random();

    public CombatSystem(Menu menu) {
        this.menu = menu;
    }

    public void fight(Character hero, Enemy monster) {

        menu.showMessage("Un " + monster.getName() + " apparaît !");

        int choix;

        do {

            menu.showMessage("1 Attaquer");
            menu.showMessage("2 Fuir");
            menu.showMessage("3 Stats");
            menu.showMessage("4 Quitter");

            choix = menu.askInt("Choix : ");

            if (choix == 3) {
                menu.showMessage(hero.toString());
            }

            else if (choix == 1) {

                Dice d20 = new Dice(20);
                int jet = d20.roll();

                if (jet == 1) {
                    menu.showMessage("Échec critique !");
                } else {

                    int seuil = hero.getThac0() - monster.getCa();

                    if (jet >= seuil || jet == 20) {

                        int degats =
                                rand.nextInt(1, 11)
                                        + hero.getAttackLevel() / 2;

                        if (jet == 20) {
                            degats *= 2;
                            menu.showMessage("CRITIQUE !");
                        }

                        monster.subirDegats(degats);

                        menu.showMessage("Tu infliges " + degats);

                        if (monster.estMort()) {

                            hero.setScore(
                                    hero.getScore()
                                            + monster.getScoreValue()
                            );

                            menu.showMessage(monster.getName() + " vaincu !");
                            break;
                        }

                    } else {
                        menu.showMessage("Tu rates.");
                    }
                }

                int degatsMonstre = monster.calculateDamage();

                hero.setLifeLevel(
                        hero.getLifeLevel() - degatsMonstre
                );

                menu.showMessage(monster.getName()
                        + " inflige "
                        + degatsMonstre
                        + " dégâts");

                if (hero.getLifeLevel() <= 0) {
                    menu.showMessage("Game Over");
                    System.exit(0);
                }
            }

            else if (choix == 2) {

                if (rand.nextDouble() < 0.5) {
                    menu.showMessage("Fuite réussie");
                    break;
                }

                menu.showMessage("Fuite ratée");

                int degats = monster.calculateDamage();

                hero.setLifeLevel(
                        hero.getLifeLevel() - degats
                );

                menu.showMessage("Tu subis " + degats);

                if (hero.getLifeLevel() <= 0) {
                    menu.showMessage("Mort en fuyant");
                    System.exit(0);
                }
            }

        } while (choix != 4);
    }
}