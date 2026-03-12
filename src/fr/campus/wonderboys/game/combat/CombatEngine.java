package fr.campus.wonderboys.game.combat;

import java.util.Random;
import fr.campus.wonderboys.characters.heros.Character;
import fr.campus.wonderboys.game.Menu;

/**
 * Moteur de combat gérant les règles du D20 et du THAC0.
 */
public class CombatEngine {
    private final Menu menu;

    public CombatEngine(Menu menu) {
        this.menu = menu;
    }

    /**
     * Lance un duel entre le héros et un monstre.
     */
    public void startCombat(Character hero, Random rand) {
        // Utilisons ta logique de BestiaireFactory ici ou une sélection aléatoire
        // Pour l'exemple, on instancie un monstre aléatoire de tes fichiers :
        int type = rand.nextInt(3);

        // On simule l'extraction de stats depuis tes classes
        int monsterHP = (type == 0) ? 5 : (type == 1) ? 12 : 25; // Serpent vs Chevalier vs Dragon
        int monsterCA = (type == 0) ? 12 : (type == 1) ? 15 : 18;
        String monsterName = (type == 0) ? "Serpent" : (type == 1) ? "Squelette" : "Dragon";

        menu.showMessage("\n⚔️ UN " + monsterName.toUpperCase() + " APPARAÎT ! ⚔️");

        boolean combatEnCours = true;

        while (combatEnCours && hero.getLifeLevel() > 0 && monsterHP > 0) {
            menu.showMessage("\n--- TOUR DE COMBAT ---");
            menu.showMessage("Héros : " + hero.getLifeLevel() + " PV | " + monsterName + " : " + monsterHP + " PV");
            menu.showMessage("1 - Attaquer (D20)");
            menu.showMessage("2 - Tenter de fuir");

            int choix = menu.askInt("Action :");

            if (choix == 1) {
                // --- PHASE D'ATTAQUE DU JOUEUR ---
                int d20Roll = rand.nextInt(20) + 1;
                // Formule THAC0 : Pour toucher, D20 >= THAC0 - CA_Cible
                int scoreNecessaire = hero.getThac0() - monsterCA;

                menu.showMessage("🎲 Jet d'attaque : " + d20Roll + " (Cible : " + scoreNecessaire + "+)");

                if (d20Roll >= scoreNecessaire || d20Roll == 20) {
                    int degats = rand.nextInt(6) + 2; // Exemple : 1d6 + 2
                    monsterHP -= degats;
                    menu.showMessage("💥 TOUCHE ! Vous infligez " + degats + " dégâts.");
                } else {
                    menu.showMessage("🛡️ RATE ! Le monstre pare votre coup.");
                }

                // --- PHASE D'ATTAQUE DU MONSTRE (Si toujours en vie) ---
                if (monsterHP > 0) {
                    int monsterRoll = rand.nextInt(20) + 1;
                    // Le monstre essaie de toucher la CA du héros
                    if (monsterRoll + monsterAttackBonus >= hero.getCa()) {
                        int monsterDamages = rand.nextInt(4) + 1;
                        hero.setLifeLevel(hero.getLifeLevel() - monsterDamages);
                        menu.showMessage("⚠️ Le " + monsterName + " vous blesse : -" + monsterDamages + " PV");
                    } else {
                        menu.showMessage("🍃 Le monstre rate son attaque.");
                    }
                }

            } else if (choix == 2) {
                if (rand.nextBoolean()) {
                    menu.showMessage("🏃 Fuite réussie !");
                    combatEnCours = false;
                } else {
                    menu.showMessage("🚫 Fuite échouée ! Le monstre vous bloque.");
                    // Le monstre attaque gratuitement car la fuite a échoué
                }
            }
        }

        if (monsterHP <= 0) {
            menu.showMessage("🏆 Victoire ! Le " + monsterName + " est terrassé.");
            hero.setScore(hero.getScore() + 100);
        }
    }
}