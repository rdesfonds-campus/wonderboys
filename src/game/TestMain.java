package game;

public class TestMain {

    public static void main(String[] args) {

        // Test Dice
        Dice de = new Dice(6);
        System.out.println("=== TEST DÉ ===");
        System.out.println("Lancer 1 : " + de.roll());
        System.out.println("Lancer 2 : " + de.roll());
        System.out.println("Lancer 3 : " + de.roll());

        // Test Board
        System.out.println("\n=== TEST PLATEAU ===");
        Board plateau = new Board(new Menu());
        System.out.println(plateau);
        System.out.println("Case 1 : " + plateau.getCase(1));
        System.out.println("Case 2 : " + plateau.getCase(2));
        System.out.println("Case 3 : " + plateau.getCase(3));
        System.out.println("Case 4 : " + plateau.getCase(4));

    }
}