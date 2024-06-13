import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class gameOver extends World 
{
    private boolean isGameOver = false;
    Label hiScoreLabel;

    public gameOver(boolean newHigh) 
    {    
        // Create a new world with 800x800 cells with a cell size of 1x1 pixels.
        super(800, 800, 1, false);
        prepare();
        
        Greenfoot.setSpeed(45);
        displayGameOver(newHigh);
    }

    private void displayGameOver(boolean newHigh) 
    {
        if (!isGameOver) 
        {
            isGameOver = true;
            reset();
            Label gameOverLabel = new Label("Game Over", 150);
            gameOverLabel.setFillColor(Color.BLUE);
            gameOverLabel.setLineColor(Color.BLUE);
            addObject(gameOverLabel, 400, 500);
            if (newHigh)
            {
                hiScoreLabel = new Label("But you got a new High Score of " + MyWorld.hiScore + "!", 60);
                hiScoreLabel.setFillColor(Color.BLACK);
                hiScoreLabel.setLineColor(Color.BLUE);
                addObject(hiScoreLabel, 400, 700);
            }
            Greenfoot.stop();
        }
    }

    public class Location 
    {
        private int x;
        private int y;

        public Location(int x, int y) 
        {
            this.x = x;
            this.y = y;
        }

        public int getX() 
        {
            return x;
        }

        public int getY() 
        {
            return y;
        }
    }

    public void prepare() 
    {
    }

    public void reset() 
    {
    }

    public void act() 
    {
    }
}
