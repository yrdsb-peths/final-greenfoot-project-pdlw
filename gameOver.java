import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
public class gameOver extends World 
{
    Label hiScoreLabel;
    Label l2 = new Label("Press spacebar to play again", 70);
    private boolean soundFinished = false;
    GreenfootSound pointSound = new GreenfootSound("sad.mp3");
    public gameOver(boolean newHigh) 
    {    
        // Create a new world with 800x800 cells with a cell size of 1x1 pixels.
        super(800, 800, 1, false);
        Greenfoot.setSpeed(45);
        displayGameOver(newHigh);
        pointSound.play();
    }
    public void act() 
    {
        if (!soundFinished && !pointSound.isPlaying()) 
        {
            soundFinished = true;
            addObject(l2, getWidth()/2, 700);
            l2.setFillColor(Color.WHITE);
            l2.setLineColor(Color.BLACK);
        }
        MyWorld gameWorld = new MyWorld();
        TitleScreen title = new TitleScreen();
        if(gameWorld.player.easy)
        {
            if(soundFinished && Greenfoot.isKeyDown("space")) 
            {
                Greenfoot.setWorld(title);
            }
        }
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
}
