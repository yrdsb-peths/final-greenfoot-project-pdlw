import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The gameOver class represents the screen displayed when the game ends.
 * It shows a "Game Over" message, indicates if a new high score was achieved,
 * and prompts the player to press the spacebar to play again.
 */
public class gameOver extends World 
{
    private Label hiScoreLabel; // Label to display the high score message
    private Label playAgainLabel = new Label("Press spacebar to play again", 70); // Label to prompt replay
    private boolean soundFinished = false; // Flag to track if the sound has finished playing
    private GreenfootSound pointSound = new GreenfootSound("sad.mp3"); // Sound to play on game over

    /**
     * Constructor for the gameOver class.
     * Sets up the game over screen and plays the game over sound.
     *
     * @param newHigh A boolean indicating if a new high score was achieved.
     */
    public gameOver(boolean newHigh) 
    {    
        super(800, 800, 1, false);
        Greenfoot.setSpeed(45); // Set the game speed
        displayGameOver(newHigh); // Display game over messages
        pointSound.play(); // Play the game over sound
    }

    /**
     * The act method is called repeatedly to perform actions.
     * Handles the play again functionality.
     */
    public void act() 
    {
        handlePlayAgain(); // Check if the player wants to play again
    }

    /**
     * Displays the game over messages on the screen.
     *
     * @param newHigh A boolean indicating if a new high score was achieved.
     */
    private void displayGameOver(boolean newHigh) 
    {
        Label gameOverLabel = new Label("Game Over", 150); // Create game over label
        gameOverLabel.setFillColor(Color.BLUE);
        gameOverLabel.setLineColor(Color.BLACK);
        addObject(gameOverLabel, 400, 500); // Add game over label to the world

        if (newHigh) 
        {
            hiScoreLabel = new Label("But you got a new High Score of " + MyWorld.hiScore + "!", 60); // Create high score label
            hiScoreLabel.setFillColor(Color.BLUE);
            hiScoreLabel.setLineColor(Color.CYAN);
            addObject(hiScoreLabel, 400, 600); // Add high score label to the world
        }
    }

    /**
     * Handles the logic for playing again.
     * Displays the prompt and checks for spacebar press to restart the game.
     */
    private void handlePlayAgain() 
    {
        if (!soundFinished && !pointSound.isPlaying()) 
        {
            soundFinished = true; // Mark the sound as finished
            addObject(playAgainLabel, getWidth() / 2, 700); // Add play again prompt to the world
            playAgainLabel.setFillColor(Color.WHITE);
            playAgainLabel.setLineColor(Color.BLACK);
        }

        if (Greenfoot.isKeyDown("space")) 
        {
            TitleScreen titleScreen = new TitleScreen(); // Create a new title screen
            Greenfoot.setWorld(titleScreen); // Switch to the title screen
        }
    }
}
