
/**
 * Create a game of pig which has two six-sided dice.
 *
 * @author Trang Nguyen
 * @version October 2017
 */
public class PigGame {

    /** the first six-sided dice */
    private GVdie d1;

    /** the second six-sided dice */
    private GVdie d2;

    /** the player's score */
    private int playerScore;

    /** the computer's score */
    private int compScore;

    /** message throughout the game */
    private String message;

    /** the current round score */
    private int roundScore;

    /** to indicate if it is currently the player's turn */
    private boolean playerTurn;

    /** the winning score */
    private final int WIN = 100;

    /**
     * Constructor for objects of class PigGame
     */
    public PigGame()
    {
        // initialise instance variables
        d1 = new GVdie();
        d2 = new GVdie();
        playerTurn = true;
        playerScore = 0;
        compScore = 0;
        roundScore = 0;
        message = "Welcome to the game!";

    }

    /**
     * returns the current round score
     * @return int current round score
     */

    public int getRoundScore() {
        return roundScore;
    }

    /**
     * returns the player' score
     * @return int player' score
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * returns the computer' score
     * @return int computer' score
     */
    public int getComputerScore() {
        return compScore;
    }

    /**
     * returns true if the player wins
     * @return boolean if the player wins
     */
    public boolean playerWon() {
        return (playerScore >= WIN);
    }

    /**
     * returns true if the computer wins
     * @return boolean if the computer wins
     */
    public boolean computerWon () {
        return (compScore >= WIN);
    }

    /**
     * Roll both dice. Both the player and computer use this method
     */
    private void rollDice() {
        d1.roll();
        d2.roll();

        if (d1.getValue() == 1 || d2.getValue() == 1) { 
            roundScore = 0;
        }
        else {
            // calculate the round score
            roundScore += d1.getValue();
            roundScore += d2.getValue();
        }

        System.out.println(d1.getValue() + " " + d2.getValue() + " " 
            + "Round score: " + roundScore);            
    }

     /**
     * Performs the first half of the player turn
     */
    public void playerRolls() {
        rollDice();

        if (d1.getValue() == 1 || d2.getValue() == 1) {
            if (d1.getValue() == 1 && d2.getValue() == 1 ) {
                playerTurn = false;
                playerScore = 0; 
            }
            playerTurn = false;
            System.out.println("---- Your score: " + playerScore + "\n");          
        }

        if (playerScore + roundScore >= WIN){
            playerScore += roundScore;
            System.out.println("---- Your score: " + playerScore + "\n");
            System.out.println("You won!");
        }
    }

     /**
     * performs the second half of the player's turn
     */
    public void playerHolds() {
        playerScore += roundScore;
        roundScore = 0;
        playerTurn = false;
        System.out.println("---- Your score: " + playerScore + "\n");

    }

     /**
     * performs the computer's entire turn
     */
    public void computerTurn() {

        do {
            rollDice();
            
            if(compScore + roundScore >= WIN){
                compScore += roundScore;
                System.out.println("---- Computer score: " + compScore + "\n");
                System.out.println("Computer won!");
                break;
            }
        }
        while (d1.getValue() != 1 && d2.getValue() != 1 && roundScore <= 19);

        if (!computerWon()) {
            if (d1.getValue() == 1 || d2.getValue() == 1 || roundScore > 19) {                
                if (d1.getValue() == 1 && d2.getValue() == 1){
                    compScore = 0;
                }
                compScore += roundScore;
                roundScore = 0;
                playerTurn = true;
                System.out.println("---- Computer score: " + compScore + "\n");
            }
        }
    }

     /**
     * Restart the game
     */
    public void restart () {
        d1.setBlank();
        d2.setBlank();
        playerScore = 0;
        compScore = 0;
        roundScore = 0;
        message = "Welcome to the game!";
    }

     /**
     * simulates the player's entire turn
     */
    private void playerTurn() {
        do {
            playerRolls();
        }
        while (d1.getValue() != 1 && d2.getValue() != 1 && roundScore <= 19 && !playerWon());

        if (!playerWon() && roundScore >= 19) {
            playerHolds();
        }
    }

     /**
     * automates an entire game by alternating between a player's turn and computer's turn
     */
    public void autoGame() {
        int turn = 0;
        restart();
        while (!playerWon() && !computerWon()){
            playerTurn();
            
            if (!playerWon()) {
                computerTurn();
            }
            
            turn ++;
        }
        System.out.println("Total turns: " + turn);
    }

     /**
     * return true if it's the player's turn
     * @return boolean if it's the player's turn
     */
    public boolean isPlayerTurn () {
        return (playerTurn);
    }

     /**
     * return the requested die by the parameter num
     * @param int - the die
     */
    public GVdie getDie (int num) {
        switch (num) {
            case 1: return d1;
            case 2: return d2;
            default: return d1;
        }
    }
}