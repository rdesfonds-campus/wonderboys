package fr.campus.wonderboys.characters.enemies;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.OffensiveEquipment;
import fr.campus.wonderboys.equipment.DefensiveEquipment;
import fr.campus.wonderboys.game.Dice;

/**
 * Classe de base pour tous les ennemis du jeu Wonderboys.
 * Hérite de Character avec type="Enemy".
 */
public abstract class Enemy extends Character {

    // --- Attributs spécifiques aux ennemis ---
    private Dice damageDice;        // Dés pour calculer les dégâts
    private int scoreValue;         // Points de score gagnés quand l'ennemi est vaincu
    private int thac0;
    private int ca;

    /**
     * Constructeur simple (pour Main, dégâts 1d6 par défaut).
     */
    public Enemy(String name, int lifeLevel, int attackLevel,
                 OffensiveEquipment weapon, DefensiveEquipment defense) {
        this(name, lifeLevel, attackLevel, weapon, defense, new Dice(6), 10);
    }

    /**
     * Constructeur complet pour définir tous les paramètres.
     *
     * @param name        nom de l'ennemi
     * @param lifeLevel   points de vie de l'ennemi
     * @param attackLevel niveau d'attaque
     * @param weapon      arme équipée
     * @param defense     défense équipée
     * @param damageDice  dés de dégâts (1d4, 1d6, 1d8...)
     * @param scoreValue  points de score gagnés en le vainquant
     */
    public Enemy(String name, int lifeLevel, int attackLevel,
                 OffensiveEquipment weapon, DefensiveEquipment defense,
                 Dice damageDice, int scoreValue) {
        super("Enemy", name, lifeLevel, attackLevel, weapon, defense);

        this.damageDice = damageDice;
        this.scoreValue = scoreValue;

        // Valeurs par défaut pour le système de combat
        setSkill(8);           // Habileté de base des ennemis
        setArmorModifier(0);   // Protection de base
    }

    /**
     * Appelé quand l'ennemi est vaincu. À surcharger dans les sous-classes.
     */
    public void onDefeated() {
        System.out.println(getName() + " est vaincu !");
    }

    /**
     * Calcule les dégâts de l'ennemi selon ses dés.
     *
     * @return nombre de dégâts (résultat du lancer de dé)
     */
    public int calculateDamage() {
        return damageDice.roll();
    }

    /**
     * Récupère les points de score à gagner en tuant cet ennemi.
     *
     * @return valeur en points de score
     */
    public int getScoreValue() {
        return scoreValue;
    }

    /**
     * Met à jour les points de score de cet ennemi.
     *
     * @param scoreValue nouvelle valeur de score
     */
    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }
    /**
     * Vérifie si l'ennemi est mort (PV <= 0).
     * @return true si mort
     */
    public boolean estMort() {
        return getLifeLevel() <= 0;  // Utilise lifeLevel de Character
    }

    /**
     * Applique dégâts à cet ennemi.
     * @param degats nombre de dégâts à subir
     */
    public void subirDegats(int degats) {
        setLifeLevel(getLifeLevel() - degats);
        if (estMort()) {
            onDefeated();
        }
    }
    public int getThac0() { return thac0; }
    public void setThac0(int thac0) { this.thac0 = thac0; }
    public int getCa() { return ca; }
    public void setCa(int ca) { this.ca = ca; }

}
