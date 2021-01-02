import javax.swing.*;

/**
 * The GuessingGameGUI_Main is a class run the
 * GuessingGameGUI class for a user to play the
 * game.
 *
 * @author Dylan McNamara
 */
public class GuessingGameGUI_Main {
    /**
     * The main method is to run the game itself.
     */
    public static void main(String[] args) {

        /**
         * The GuessingGameGUI game is called to create
         * an object of GuessingGameGUI. The game then
         * runs for the user to play.
         */
        GuessingGameGUI game = new GuessingGameGUI();
        //set attributes
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setSize(800,200);
        game.setVisible(true);
    }
}
