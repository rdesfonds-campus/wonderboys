package game;

import java.util.ArrayList;


public class Board {

    private static final int TOTAL = 64;
    private ArrayList<Cell> cases;

    public Board(Menu menu) {
        cases = new ArrayList<>();
        initialiser(menu);
    }

    private void initialiser(Menu menu) {
        for (int i = 0; i < TOTAL; i++) {
            switch (i % 4) {
                case 0: cases.add(new EmptyCell(menu));        break;
                case 1: cases.add(new EnemyCell(menu, i + 1)); break;
                case 2: cases.add(new WeaponCell(menu));       break;
                case 3: cases.add(new PotionCell(menu));       break;
            }
        }
    }

    public Cell getCase(int position) {
        return cases.get(position - 1);
    }

    public int getTotalCases() {
        return TOTAL;
    }

    @Override
    public String toString() {
        return "Plateau de " + TOTAL + " cases";
    }
}