import fr.campus.wonderboys.game.Dice;

public class TestDice {

    // main = le point d'entrée pour lancer ce petit test
    public static void main (String [] args) {
        // On créé un dé à 6 faces (constructeur par défaut)
        Dice dice = new Dice();

        // On lance 3 fois le dé et on affiche les résultats
        System.out.println (dice.roll());
        System.out.println (dice.roll());
        System.out.println (dice.roll());

    }
}
