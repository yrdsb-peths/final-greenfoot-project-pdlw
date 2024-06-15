import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class gameOver extends World 
{
    private Label hiScoreLabel;
    private Label playAgainLabel = new Label("Press spacebar to play again", 70);
    private boolean soundFinished = false;
    private GreenfootSound pointSound = new GreenfootSound("sad.mp3");

    public gameOver(boolean newHigh) 
    {    
        super(800, 800, 1, false);
        Greenfoot.setSpeed(45);
        displayGameOver(newHigh);
        pointSound.play();
    }

    public void act() 
    {
        handlePlayAgain();
    }

    private void displayGameOver(boolean newHigh) 
    {
        Label gameOverLabel = new Label("Game Over", 150);
        gameOverLabel.setFillColor(Color.BLUE);
        gameOverLabel.setLineColor(Color.BLACK);
        addObject(gameOverLabel, 400, 500);

        if (newHigh) 
        {
            hiScoreLabel = new Label("But you got a new High Score of " + MyWorld.hiScore + "!", 60);
            hiScoreLabel.setFillColor(Color.BLUE);
            hiScoreLabel.setLineColor(Color.CYAN);
            addObject(hiScoreLabel, 400, 600);
        }
    }

    private void handlePlayAgain() 
    {
        if (!soundFinished && !pointSound.isPlaying()) 
        {
            soundFinished = true;
            addObject(playAgainLabel, getWidth() / 2, 700);
            playAgainLabel.setFillColor(Color.WHITE);
            playAgainLabel.setLineColor(Color.BLACK);
        }

        if (Greenfoot.isKeyDown("space")) 
        {
            TitleScreen titleScreen = new TitleScreen();
            Greenfoot.setWorld(titleScreen);
        }
    }
}
