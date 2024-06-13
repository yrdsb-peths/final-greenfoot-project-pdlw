import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class MyWorld extends World 
{
    public boolean newHigh = false;
    public int score;
    public static int hiScore;
    public int lives = 2;
    Label scoreLabel;
    Label livesLabel;
    Label hiScoreLabel;
    private boolean isGameOver = false;
    Location spawn = new Location(100, 690);
    private Platform platform1;
    private Platform platform2;
    private Platform platform3;
    private Platform platform4;
    private Platform platform5;
    private CopyOfPlatform platform9;
    private Cloud cloud0;
    private Cloud cloud1;
    private Cloud cloud2;
    private Cloud cloud3;
    private Cloud cloud4;
    private Cloud cloud5;
    public Player player;

    public MyWorld() 
    {    
        // Create a new world with 800x800 cells with a cell size of 1x1 pixels.
        super(800, 800, 1, false);
        prepare();
        if(score>=hiScore)
        {
           hiScore = score;
        }
        scoreLabel = new Label("Berries Collected: " + score, 60);
        scoreLabel.setFillColor(Color.BLUE);
        addObject(scoreLabel, 250, 300);
        hiScoreLabel = new Label("High Score: " + hiScore, 60);
        hiScoreLabel.setFillColor(Color.BLUE);
        addObject(hiScoreLabel, 179, 420);
        livesLabel = new Label("Lives Left: " + lives, 60);
        livesLabel.setFillColor(Color.ORANGE);
        addObject(livesLabel, 165, 360);
        Greenfoot.setSpeed(45);
        
        player = new Player();
        addObject(player, spawn.getX(), spawn.getY());
        player.getImage().scale(35,35);
        if(Platform.ease==false)
        {
            platform9 = new CopyOfPlatform();
            addObject(platform9, 70, 718);
        }
        if(Platform.ease==false)
        {
            lives=1;
        }
    }
    public void increaseScore() 
    {
        score++;
        if(player.easy)
        {
            if(score<=14)
            {
                GreenfootSound pointSound = new GreenfootSound("pointer.mp3");
                pointSound.play();
            }
            else
            {
                GreenfootSound pointSound = new GreenfootSound("complete.mp3");
                pointSound.play();
            }
        }
        else
        {
            GreenfootSound pointSound = new GreenfootSound("pointer.mp3");
            pointSound.play();
        }
        scoreLabel.setValue("Berries Collected: " + score);
        if (score > hiScore) 
        {
            newHigh = true;
            hiScore = score;
            hiScoreLabel.setLocation(245,420);
            hiScoreLabel.setValue("New High Score!: " + hiScore);
            hiScoreLabel.setFillColor(Color.RED);
        }
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
            gameOver over = new gameOver(newHigh);
            Greenfoot.setWorld(over);
        }
    }

    public void gameWin() 
    {
        WinWorld gameWorld = new WinWorld();
        Greenfoot.setWorld(gameWorld);
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

    public void act() 
    {   
        if(player.easy)
        {
            if(Greenfoot.isKeyDown("right"))
            {
                coinSpawn();
            }
        }
        else
        {
            coinSpawn();
        }
    }
    public void coinSpawn()
    {
            if (Greenfoot.getRandomNumber(100) <= 1) 
            {
                Random random = new Random();
                int minY = Platform.calculateMinY();
                int maxY = 700;
                int randomY = random.nextInt(maxY - minY + 1) + minY;
                if(randomY > player.getY())
                {
                    randomY = random.nextInt(maxY - minY + 1) + minY;
                }
                addObject(new Coin(), getWidth()-1, randomY);
            }
    }
}
