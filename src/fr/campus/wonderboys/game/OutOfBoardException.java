package fr.campus.wonderboys.game;

/**
 * Exception levée quand un personnage sort du plateau (position > 64).
 * Interrompt la partie si dépassement détecté dans Game.startGame().
 *
 * @author Romain D
 * @version 1.0
 */
public class OutOfBoardException extends Exception {

    /**
     * Crée l'exception avec un message d'erreur détaillé.
     *
     * @param message explication du dépassement ("Position 66 hors du plateau...")
     */
    public OutOfBoardException(String message) {
        super(message
        );
    }
}
