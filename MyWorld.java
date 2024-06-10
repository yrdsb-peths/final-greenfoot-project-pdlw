import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
public class MyWorld extends World
{
    private boolean isGameOver = false;
    Location spawn = new Location(100, 690);
    private Platform platform1;
    private Platform platform2;
    private Platform platform3;
    private Platform platform5;
    private Platform platform7;
    private Cloud cloud0;
    private Cloud cloud1;
    private Cloud cloud2;
    private Cloud cloud3;
    private Cloud cloud4;
    private Cloud cloud5;

    public MyWorld()
    {    
        // Create a new world with 800x800 cells with a cell size of 1x1 pixels.
        super(800, 800, 1, false);
        prepare();
        Greenfoot.setSpeed(45);
        Player player = new Player();
        addObject(player, spawn.getX(), spawn.getY());
        player.getImage().scale(35,35);
    }

    public void gameOver()
    {
        if (!isGameOver) {
            isGameOver = true;
            Label gameOverLabel = new Label("Game Over", 100);
            addObject(gameOverLabel, 400, 400);
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
        platform5 = new Platform();
        addObject(platform5,576,642);
        platform7 = new Platform();
        addObject(platform7,709,708);
    }

    public void reset()
    {
        platform1.setLocation(70, 718);
        platform2.setLocation(225, 658);
        platform3.setLocation(408, 595);
        platform5.setLocation(576,642);
        platform7.setLocation(709,708);
        
        cloud0.setLocation(107, 310);
        cloud1.setLocation(322, 381);
        cloud2.setLocation(569, 290);
        cloud3.setLocation(590, 426);
        cloud4.setLocation(775, 358);
        cloud5.setLocation(15, 422);
    }
    
    public void act()
    {
        if(Greenfoot.getRandomNumber(300)<=1)
        {
            Random random = new Random();
                int minY = 445;
                int maxY = 730;
                int randomY = random.nextInt(maxY - minY + 1) + minY;
                addObject(new Coin(), getWidth()-1, randomY);
        }
    }
}
