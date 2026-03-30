package game;

public class EmptyCell extends Cell {

    public EmptyCell(Menu menu) {
        super(menu);
    }

    @Override
    public void interact(characters.Character hero) {
        menu.showMessage("Case vide. Tu continues ta route.");
    }

    @Override
    public String toString() {
        return "Case vide";
    }
}