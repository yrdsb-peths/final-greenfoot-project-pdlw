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
    public Location spawn = new Location(100, 690);
    private Platform[] platforms = new Platform[5];
    private Cloud[] clouds = new Cloud[6];
    private CopyOfPlatform platform9;
    public Player player;
    private GreenfootSound pointerSound = new GreenfootSound("pointer.mp3");
    private GreenfootSound completeSound = new GreenfootSound("complete.mp3");


    public MyWorld() 
    {    
        super(800, 800, 1, false);
        prepare();
        initializeLabels();
        Greenfoot.setSpeed(45);

        player = new Player();
        addObject(player, spawn.getX(), spawn.getY());
        player.getImage().scale(35, 35);

        if(Platform.ease==false)

        {

            platform9 = new CopyOfPlatform();
            addObject(platform9, 70, 718);
        }
    }

    private void initializeLabels() 
    {
        scoreLabel = createLabel("Berries Collected: " + score, Color.BLUE, 250, 300);
        hiScoreLabel = createLabel("High Score: " + hiScore, Color.BLUE, 179, 420);
        livesLabel = createLabel("Lives Left: " + lives, Color.ORANGE, 165, 360);
    }

    private Label createLabel(String text, Color color, int x, int y) 
    {
        Label label = new Label(text, 60);
        label.setFillColor(color);
        addObject(label, x, y);
        return label;
    }

    public void increaseScore() 
    {
        score++;
        playPointSound();
        scoreLabel.setValue("Berries Collected: " + score);
        if (score > hiScore) 
        {
            newHigh = true;
            hiScore = score;
            hiScoreLabel.setLocation(245, 420);
            hiScoreLabel.setValue("New High Score!: " + hiScore);
            hiScoreLabel.setFillColor(Color.RED);
        }
    }

    private void playPointSound() 
    {
        if (player.easy && score > 14) 
        {
            completeSound.play();
        } 
        else 
        {
            pointerSound.play();
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

    public void prepare() 
    {
        clouds[0] = createCloud(107, 310, 70, 40);
        clouds[1] = createCloud(322, 381, 70, 40);
        clouds[2] = createCloud(569, 290, 130, 100);
        clouds[3] = createCloud(590, 426, 70, 40);
        clouds[4] = createCloud(775, 358, 70, 40);
        clouds[5] = createCloud(15, 422, 70, 40);

        platforms[0] = createPlatform(70, 718);
        platforms[1] = createPlatform(225, 658);
        platforms[2] = createPlatform(408, 595);
        platforms[3] = createPlatform(576, 642);
        platforms[4] = createPlatform(709, 708);
    }

    private Cloud createCloud(int x, int y, int width, int height) 
    {
        Cloud cloud = new Cloud();
        addObject(cloud, x, y);
        cloud.getImage().scale(width, height);
        return cloud;
    }

    private Platform createPlatform(int x, int y) 
    {
        Platform platform = new Platform();
        addObject(platform, x, y);
        return platform;
    }

    public void reset() 
    {
        setObjectLocations(platforms, new int[][]{{70, 718}, {225, 658}, {408, 595}, {576, 642}, {709, 708}});
        setObjectLocations(clouds, new int[][]{{107, 310}, {322, 381}, {569, 290}, {590, 426}, {775, 358}, {15, 422}});
    }

    private void setObjectLocations(Actor[] actors, int[][] locations) 
    {
        for (int i = 0; i < actors.length; i++) 
        {
            actors[i].setLocation(locations[i][0], locations[i][1]);
        }
    }

    public void act() 
    {   
        if (player.easy && Greenfoot.isKeyDown("right")) 
        {
            coinSpawn();
        } 
        else if (!player.easy) 
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
            if (randomY > player.getY()) 
            {
                randomY = random.nextInt(maxY - minY + 1) + minY;
            }
            addObject(new Coin(), getWidth() - 1, randomY);
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
