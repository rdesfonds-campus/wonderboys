import fr.campus.wonderboys.game.Game;
import fr.campus.wonderboys.game.Menu;

public class Main {

    public static void main(String[] args) {
        //Tests.runAll();

        Menu menu = new Menu();

        Game game = new Game(menu);

        game.start();

    }

}

