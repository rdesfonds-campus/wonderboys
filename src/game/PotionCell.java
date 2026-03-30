package game;

public class PotionCell extends Cell {

    private static final int SOIN = 3;

    public PotionCell(Menu menu) {
        super(menu);
    }

    @Override
    public void interact(characters.Character hero) {
        int anciensPV = hero.getLifeLevel();
        hero.setLifeLevel(anciensPV + SOIN);
        menu.showMessage("Tu trouves une potion et récupères " + SOIN + " PV !");
        menu.showMessage(hero.getName() + " PV : " + anciensPV + " → " + hero.getLifeLevel());
    }

    @Override
    public String toString() {
        return "Case potion";
    }
}