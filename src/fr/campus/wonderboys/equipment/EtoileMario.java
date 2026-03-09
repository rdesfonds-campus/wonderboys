package fr.campus.wonderboys.equipment;

import fr.campus.wonderboys.characters.Character;

public class EtoileMario extends DefensiveEquipment { // Change ici

    public EtoileMario() {
        super("Étoile Mario", 0, "etoile"); // 0 défense, c'est cosmétique
    }

    @Override
    public void use(Character hero) {
        System.out.println("★ Étoile Mario ! Téléport case 64 (invincible)");
        System.out.println("Victoire immédiate ! Tous ennemis battus.");
    }

    @Override
    public String toString() {
        return "Étoile Mario (téléport fin + invincible)";
    }
}
