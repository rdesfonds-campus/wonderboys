package fr.campus.wonderboys.game;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.characters.enemies.Enemy;

import java.util.Random;

public class CombatSystem {

    private final Menu menu;
    private final Random rand;
    private final Dice d20;

    public CombatSystem(Menu menu) {
        this.menu = menu;
        this.rand = new Random();
        this.d20 = new Dice(20);
    }

    public void fight(Character hero, Enemy monster) {

        menu.showMessage("⚔️ Un " + monster.getName() + " apparaît !");

        boolean combatActif = true;

        while (combatActif) {

            menu.showMessage("1 - Attaquer");
            menu.showMessage("2 - Fuir");
            menu.showMessage("3 - Stats");
            menu.showMessage("4 - Quitter combat");

            int choix = menu.askInt("Choix : ");

            switch (choix) {

                case 1:
                    attaquer(hero, monster);
                    break;

                case 2:
                    if (tenterFuite(hero, monster)) {
                        combatActif = false;
                    }
                    break;

                case 3:
                    menu.showMessage(hero.toString());
                    break;

                case 4:
                    menu.showMessage("Tu abandonnes le combat.");
                    combatActif = false;
                    break;

                default:
                    menu.showMessage("Choix invalide.");
            }

            if (monster.estMort()) {

                hero.setScore(
                        hero.getScore() + monster.getScoreValue()
                );

                menu.showMessage("🏆 " + monster.getName() + " vaincu !");
                combatActif = false;
            }

            if (hero.getLifeLevel() <= 0) {
                menu.showMessage("💀 Tu es mort.");
                combatActif = false;
            }
        }
    }

    private void attaquer(Character hero, Enemy monster) {

        int jet = d20.roll();

        if (jet == 1) {
            menu.showMessage("❌ Échec critique !");
            attaqueMonstre(hero, monster);
            return;
        }

        int seuil = hero.getThac0() - monster.getCa();

        if (jet >= seuil || jet == 20) {

            int degats =
                    rand.nextInt(10) + 1
                            + hero.getAttackLevel() / 2;

            if (jet == 20) {
                degats *= 2;
                menu.showMessage("🔥 CRITIQUE !");
            }

            monster.subirDegats(degats);

            menu.showMessage("Tu infliges " + degats + " dégâts.");

        } else {
            menu.showMessage("Tu rates ton attaque.");
        }

        if (!monster.estMort()) {
            attaqueMonstre(hero, monster);
        }
    }

    private void attaqueMonstre(Character hero, Enemy monster) {

        int degatsMonstre = monster.calculateDamage();

        hero.setLifeLevel(
                hero.getLifeLevel() - degatsMonstre
        );

        menu.showMessage(
                monster.getName()
                        + " inflige "
                        + degatsMonstre
                        + " dégâts"
        );
    }

    private boolean tenterFuite(Character hero, Enemy monster) {

        if (rand.nextDouble() < 0.5) {
            menu.showMessage("🏃 Fuite réussie !");
            return true;
        }

        menu.showMessage("❌ Fuite ratée !");

        int degats = monster.calculateDamage();

        hero.setLifeLevel(
                hero.getLifeLevel() - degats
        );

        menu.showMessage("Tu subis " + degats + " dégâts.");

        return hero.getLifeLevel() <= 0;
    }
}