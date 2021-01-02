import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * The GuessingGameGUI is designed to select a number at random from 1-1000.
 * The game gives a button for reset, a button to enter the guess, and a text
 * field to enter the guess into. The Textfield will only accept numbers and any
 * other characters will display a message of invalid input.
 * The message field will tell you if your guess was too low or if guess was too
 * high. The background color will update based on a warmer/colder system. Warmer(red)
 * if your guess is closer than the last guess, Colder(blue) if your guess if further
 * than the last guess.
 */
public class GuessingGameGUI extends JFrame{

    /**
     * The int solution is used to hold the randomly
     * generated number that is the correct answer for
     * the game.
     */
    private static int solution = -1;

    /**
     * The int guess is used to hold the user's guess
     * input and used to compare to solution.
     */
    private static int guess = -1;

    /**
     * The int lastDistance is used to tell if the current
     * guess is closer to the solution value than the previous
     * guess to allow for the warmer/colder feature.
     */
    private static int lastDistance = -1;

    /**
     * The Color background is used to change the background of
     * the GUI and allows it to be updated as needed.
     */
    private static Color background = Color.WHITE;

    /**
     * The String guessText is used to pull the text from the text
     * field. This allows any text to be pulled.
     */
    private static String guessText=null;

    /**
     * The String regex is used to hold the regular expression
     * for numeric characters only and is used to check the user
     * input.
     */
    private static final String regex = ".*[0-9].*";

    /**
     * The GuessingGameGUI creates an object of GuessingGameGUI.
     * The constructor sets up the game panel and then loads it
     * to the frame.
     */
    public GuessingGameGUI(){
        //Call super constructor to set title
        super("Guess the Number GUI");

        /**
         * The JPanel panel is used to load other content
         * before adding to the Frame.
         */
        JPanel panel = new JPanel();

        /**
         * The JLabel label is used to display text to the user.
         * It will update throughout the game as needed.
         */
        JLabel label = new JLabel("I have Number between 1 and 1000, can you guess it?");

        /**
         * The JButton newGameButton is used to allow the user to
         * reset the game after the actionListener is added.
         */
        JButton newGameButton = new JButton("Reset");

        /**
         * The JButton guessButton is what allows the user to submit
         * their guess for validation after the actionListerer is added.
         */
        JButton guessButton = new JButton("Guess");

        /**
         * The JTextField textfield is for the user to input text
         * to allow them to make a guess at the solution.
         */
        JTextField textfield = new JTextField(10);

        /**
         * The Random generator is used to get random values or the
         * game to use as solutions.
         */
        Random generator = new Random();

        //add action listeners
        newGameButton.addActionListener(new ActionListener() {
            /**
             * The actionListener causes the background,
             * JTextField, and JLabel to set up values and rolls
             * another value between 1 and 1000.
             *
             * @param e  The parameter only comes from the
             *           newGameButton being activated.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                background = Color.WHITE;
                solution = generator.nextInt(1000) + 1;
                textfield.setText("");
                textfield.setEditable(true);
                label.setText("I have Number between 1 and 1000, can you guess it?");
                getContentPane().setBackground(background);
            }//end actionlistener
        });
        guessButton.addActionListener(new ActionListener() {
            /**
             * This action listener contains the logic for the game
             * itself and also handles the checks for if the
             * text is a valid guess.
             *
             *  @param e  The parameter only comes from
             *            the guess button being activated
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //check to make sure there is text
                if (textfield.getText().isEmpty() == false) {
                    guessText = (textfield.getText());
                    //guess only contains numbers
                    if(guessText.matches(regex)) {
                        //pull the number into an integer
                        guess = Integer.parseInt(guessText);
                       //Check to see if its the solution
                        if (guess == solution) {
                            //set background to white, display correct, turn off JTextField
                            background = Color.WHITE;
                            textfield.setText("");
                            label.setText("Correct!");
                            textfield.setEditable(false);
                            getContentPane().setBackground(background);
                        } else {
                            //Check to see if the guess is warmer
                            if (Math.abs(guess - solution) < Math.abs(lastDistance)) {
                                //set background red, update last distance
                                background = Color.RED;
                                lastDistance = guess - solution;
                                textfield.setText("");
                                //check to see if the guess is high or low and set label
                                if (guess - solution < 0) {
                                    label.setText("You're too low.");
                                } else if (guess - solution > 0) {
                                    label.setText("You're to high.");
                                }
                                //display background update
                                getContentPane().setBackground(background);
                            //check to see if guess is colder
                            }else if (Math.abs(guess - solution) > Math.abs(lastDistance)) {
                                //set background to blue, update last distance
                                background = Color.BLUE;
                                lastDistance = guess - solution;
                                textfield.setText("");
                                //check to see if guess is high or low and set label
                                if (guess - solution < 0) {
                                    label.setText("You're too low.");
                                } else if (guess - solution > 0) {
                                    label.setText("You're to high.");
                                }//end else if loop
                                getContentPane().setBackground(background);
                            }//end else if loop
                        }//end else if loop
                    }else{
                        //invalid text input
                        label.setText("Invalid Input. Try again?");
                    }//end else if loop
                } else {
                    //no text input
                    label.setText("You didn't guess! Try again?");
                }//end else if loop
            }//end actionListener
        });

        //set initial value for the game to use as the solution
        solution = generator.nextInt(1000) + 1;

        //add items to the panel
        panel.add(label);
        panel.add(newGameButton);
        panel.add(guessButton);
        panel.add(textfield);
        //add panel to frame
        add(panel);

        //set frame attributes
        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().setBackground(background);
        this.getContentPane().add(panel);
    }//end constructor
}
