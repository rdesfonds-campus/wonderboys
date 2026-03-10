package fr.campus.wonderboys.cells;
import fr.campus.wonderboys.characters.Character;
import fr.campus.wonderboys.equipment.SortUltima;
import fr.campus.wonderboys.game.Cell;

public class SortUltimaCell extends Cell {

    private final SortUltima ultima = new SortUltima();

    @Override
    public void interact(Character perso) {
        System.out.println("Sort Ultima trouvé !");
        ultima.testUse();
    }

    @Override
    public String toString() {
        return "Trésor: " + ultima.toString();
    }
}
