package game;

public abstract class Cell {

    protected Menu menu;

    public Cell(Menu menu) {
        this.menu = menu;
    }

    public abstract void interact(characters.Character hero);

    @Override
    public abstract String toString();
}