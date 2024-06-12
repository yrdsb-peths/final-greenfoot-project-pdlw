import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class MyWorld extends World 
{
    public int score;
    public int lives = 2;
    Label scoreLabel;
    Label livesLabel;
    private boolean isGameOver = false;
    Location spawn = new Location(100, 690);
    private Platform platform1;
    private Platform platform2;
    private Platform platform3;
    private Platform platform4;
    private Platform platform5;
    private Cloud cloud0;
    private Cloud cloud1;
    private Cloud cloud2;
    private Cloud cloud3;
    private Cloud cloud4;
    private Cloud cloud5;
    private Player player;

    public MyWorld() 
    {    
        // Create a new world with 800x800 cells with a cell size of 1x1 pixels.
        super(800, 800, 1, false);
        prepare();
        scoreLabel = new Label("Berries Collected: " + score, 60);
        scoreLabel.setFillColor(Color.BLUE);
        addObject(scoreLabel, 250, 300);
        livesLabel = new Label("Lives Left: " + lives, 60);
        livesLabel.setFillColor(Color.ORANGE);
        addObject(livesLabel, 165, 360);
        Greenfoot.setSpeed(45);
        
        player = new Player();
        addObject(player, spawn.getX(), spawn.getY());
        player.getImage().scale(35,35);
    }

    public void increaseScore() 
    {
        score++;
        scoreLabel.setValue("Berries Collected: " + score);
    }

    public void decreaseLives() 
    {
        lives--;
        livesLabel.setValue("Lives Left: " + lives);
    }

    public void gameOver() 
    {
        if (!isGameOver) 
        {
            isGameOver = true;
            reset();
            Label gameOverLabel = new Label("Game Over", 150);
            gameOverLabel.setFillColor(Color.RED);
            gameOverLabel.setLineColor(Color.RED);
            addObject(gameOverLabel, 400, 500);
            Greenfoot.stop();
        }
    }

    public void gameWin() 
    {
        respawn();
        Label winLabel = new Label("You Won!", 150);
        winLabel.setFillColor(Color.RED);
        winLabel.setLineColor(Color.RED);
        addObject(winLabel, 400, 500);
        Greenfoot.stop();
    }

    public void respawn() 
    {
        player.setLocation(spawn.getX(), spawn.getY());
        reset();
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
        cloud0 = new Cloud();
        addObject(cloud0, 107, 310);
        cloud1 = new Cloud();
        addObject(cloud1, 322, 381);
        cloud1.getImage().scale(70, 40);
        cloud2 = new Cloud();
        addObject(cloud2, 569, 290);
        cloud2.getImage().scale(130, 100);
        cloud3 = new Cloud();
        addObject(cloud3, 590, 426);
        cloud4 = new Cloud();
        addObject(cloud4, 775, 358);
        cloud5 = new Cloud();
        addObject(cloud5, 15, 422);

        platform1 = new Platform();
        addObject(platform1, 70, 718);
        platform2 = new Platform();
        addObject(platform2, 225, 658);
        platform3 = new Platform();
        addObject(platform3, 408, 595);
        platform4 = new Platform();
        addObject(platform4,576,642);
        platform5 = new Platform();
        addObject(platform5,709,708);
    }

    public void reset() 
    {
        platform1.setLocation(70, 718);
        platform2.setLocation(225, 658);
        platform3.setLocation(408, 595);
        platform4.setLocation(576,642);
        platform5.setLocation(709,708);
        
        cloud0.setLocation(107, 310);
        cloud1.setLocation(322, 381);
        cloud2.setLocation(569, 290);
        cloud3.setLocation(590, 426);
        cloud4.setLocation(775, 358);
        cloud5.setLocation(15, 422);
    }

    public void act() {
        if (Greenfoot.getRandomNumber(100) <= 1) 
        {
            Random random = new Random();
            int minY = 445;
            int maxY = 730;
            int randomY = random.nextInt(maxY - minY + 1) + minY;
            addObject(new Coin(), getWidth()-1, randomY);
        }
    }
}
