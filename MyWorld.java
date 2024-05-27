import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 */
public class MyWorld extends World
{
    private boolean isGameOver = false;
    
    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld()
    {    
        // Create a new world with 800x800 cells with a cell size of 1x1 pixels.
        super(800, 800, 1);
        prepare();
        Player player = new Player();
        addObject(player, spawn.getX(), spawn.getY());
        player.getImage().scale(40,40);
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

    Location spawn = new Location(52, 690);

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Platform platform1 = new Platform();
        addObject(platform1, 52, 718);
        Platform platform2 = new Platform();
        addObject(platform2, 225, 658);
        Platform platform3 = new Platform();
        addObject(platform3, 408, 595);
        Platform platform4 = new Platform();
        addObject(platform4, 231, 534);
    }
}
