package fr.campus.wonderboys.cells;

import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.EtoileMario;
import fr.campus.wonderboys.game.Cell;

public class EtoileMarioCell extends Cell {

    private final EtoileMario etoile = new EtoileMario();

    @Override
    public void interact(Character perso) {
        System.out.println("Étoile Mario trouvée !");
        etoile.use(perso);
    }

    @Override
    public String toString() {
        return "Trésor: " + etoile.toString();
    }
}
