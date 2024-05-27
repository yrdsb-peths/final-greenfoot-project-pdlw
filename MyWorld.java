import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    private boolean isGameOver = false;
    private Cloud[] clouds = new Cloud[6];
    Location spawn = new Location(52, 690);
    
    public MyWorld()
    {    
        // Create a new world with 800x800 cells with a cell size of 1x1 pixels.
        super(800, 800, 1, false);
        prepare();
        Greenfoot.setSpeed(45);
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

    public void act()
    {
        for(int i = 0; i < clouds.length; i++)
        {
            int cloudRightEdge = clouds[i].getX() + clouds[i].getImage().getWidth() / 2;
            if(cloudRightEdge <= 0)
            {
                int oldY = clouds[i].getY();
                removeObject(clouds[i]);
                clouds[i] = new Cloud();
                addObject(clouds[i], 1000, oldY);
            }
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

        clouds[0] = new Cloud();
        addObject(clouds[0], 107, 310);
        clouds[1] = new Cloud();
        addObject(clouds[1], 322, 381);
        clouds[1].getImage().scale(70, 40);
        clouds[2] = new Cloud();
        addObject(clouds[2], 569, 290);
        clouds[2].getImage().scale(130, 100);
        clouds[3] = new Cloud();
        addObject(clouds[3], 590, 426);
        clouds[4] = new Cloud();
        addObject(clouds[4], 775, 358);
        clouds[5] = new Cloud();
        addObject(clouds[5], 15, 422);
    }
}
