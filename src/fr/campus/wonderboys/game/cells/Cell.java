package fr.campus.wonderboys.game.cells;
import fr.campus.wonderboys.characters.heros.Character;

public abstract class Cell {
    @Override
    public abstract String toString();

    public abstract void interact(Character hero);  // ← SEUL !

    public void enEntrant(){
        System.out.println("On est rentré");

    }

}
