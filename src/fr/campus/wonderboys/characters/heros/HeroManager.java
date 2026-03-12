package fr.campus.wonderboys.characters.heros;

import fr.campus.wonderboys.db.HeroDAO;
import fr.campus.wonderboys.equipment.*;
import fr.campus.wonderboys.game.Menu;

/**
 * Gère la création, la sélection et la modification des héros.
 * Fait le pont entre le Menu (UI) et le HeroDAO (Base de données).
 */
public class HeroManager {

    private final Menu menu;
    private final HeroDAO heroDAO;

    public HeroManager(Menu menu) {
        this.menu = menu;
        this.heroDAO = new HeroDAO();
    }

    /**
     * Crée un nouveau héros et l'enregistre en BDD.
     */
    public Character createCharacter() {
        menu.showMessage("On va créer un nouveau personnage !");
        menu.showMessage("Choisis ton type de personnage :");
        menu.showMessage("1 - Warrior\n2 - Wizard");

        int choiceType = menu.askInt("Tape 1 ou 2 :");
        while (choiceType != 1 && choiceType != 2) {
            choiceType = menu.askInt("Choix invalide. Tape 1 ou 2 :");
        }

        String type = (choiceType == 1) ? "Warrior" : "Wizard";
        int life = (choiceType == 1) ? 10 : 6;
        int attack = (choiceType == 1) ? 10 : 8;

        String name = menu.askString("Quel est le nom de ton personnage ?");

        // Logique simplifiée d'équipement de départ (à adapter selon tes besoins)
        OffensiveEquipment weapon = type.equals("Warrior") ? new Weapon(3, "Épée rouillée") : new OffensiveEquipment("Spell", 4, "Bâton magique");
        DefensiveEquipment defense = type.equals("Warrior") ? new Shield() : new Potion(3, "Petite potion");

        Character hero;
        if (type.equals("Warrior")) {
            hero = new Warrior(name, life, attack, weapon, defense);
        } else {
            hero = new Wizard(name, life, attack, weapon, defense);
        }

        heroDAO.createHero(hero);
        menu.showMessage("Héros créé et sauvegardé avec succès !");
        return hero;
    }

    /**
     * Permet de choisir un héros existant dans la base de données.
     */
    public Character chooseExistingHero() {
        menu.showMessage("Liste des héros disponibles :");
        heroDAO.getHeroes(); // Affiche la liste

        int idToUse = menu.askInt("Quel Id de héros veux-tu utiliser ?");
        Character hero = heroDAO.getHeroById(idToUse);

        if (hero == null) {
            menu.showMessage("Aucun héros trouvé avec cet Id.");
        } else {
            menu.showMessage("Tu joueras maintenant avec : " + hero.getName());
        }
        return hero;
    }

    /**
     * Modifie un héros existant.
     */
    public void editExistingHero() {
        menu.showMessage("Liste des héros en base :");
        heroDAO.getHeroes();

        int idToEdit = menu.askInt("Quel Id de héros veux-tu modifier ?");
        Character heroFromDb = heroDAO.getHeroById(idToEdit);

        if (heroFromDb == null) {
            menu.showMessage("Héros introuvable.");
            return;
        }

        String newName = menu.askString("Nouveau nom pour " + heroFromDb.getName() + " ?");
        heroFromDb.setName(newName);

        // Ici tu peux rajouter ta logique de choix d'armes/boucliers de Game.java

        heroDAO.editHero(heroFromDb);
        menu.showMessage("Modification enregistrée.");
    }
}