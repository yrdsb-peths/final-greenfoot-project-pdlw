import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class WinWorld extends World 
{
    public int score;
    public int lives; 
    Label scoreLabel;
    Label livesLabel;
    Label titleLabel = new Label("You Win!", 100);
    private boolean isGameOver = false;

    public WinWorld() 
    {    
        // Create a new world with 800x800 cells with a cell size of 1x1 pixels.
        super(800, 800, 1, false);
        addObject(titleLabel, getWidth()/2, 400);
        titleLabel.setFillColor(Color.WHITE);
        titleLabel.setLineColor(Color.PINK);
        scoreLabel = new Label("Berries Collected: " + score, 60);
        scoreLabel.setFillColor(Color.BLUE);
        addObject(scoreLabel, 231, 700);
        livesLabel = new Label("Lives Left: " + lives, 60);
        livesLabel.setFillColor(Color.ORANGE);
        addObject(livesLabel, 148, 750);
        Greenfoot.setSpeed(45);
    }

    public void gameOver() 
    {
        if (!isGameOver) 
        {
            isGameOver = true;
            Label gameOverLabel = new Label("Game Over", 150);
            gameOverLabel.setFillColor(Color.BLUE);
            gameOverLabel.setLineColor(Color.BLUE);
            addObject(gameOverLabel, 400, 500);
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
}
