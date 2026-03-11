package fr.campus.wonderboys.characters.enemies.d4;
import fr.campus.wonderboys.characters.enemies.Enemy;
import fr.campus.wonderboys.game.Dice;
public class Serpent extends Enemy {

    public Serpent() {
        super("Serpent", 4, 4, null, null, new Dice(4), 10);

        // Valeurs spécifiques au Serpent
        setSkill(1);           // S1 selon tes specs
        setArmorModifier(0);   // A0 selon tes specs
    }

    @Override
    public String toString() {
        return "Serpent (PV: " + getLifeLevel()
                + ", dégâts: 1d4, Habileté: " + getSkill()
                + ", Armure: " + getArmorModifier()
                + ", Score: " + getScoreValue() + ")";
    }

    @Override
    public void onDefeated() {
        super.onDefeated();
        System.out.println("Le Serpent rapporte 10 points de score !");
    }
}
