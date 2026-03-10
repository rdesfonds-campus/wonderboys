package fr.campus.wonderboys.game;
import fr.campus.wonderboys.characters.Character;

public abstract class Cell {
    @Override
    public abstract String toString();

    public abstract void interact(Character hero);  // ← SEUL !
}
