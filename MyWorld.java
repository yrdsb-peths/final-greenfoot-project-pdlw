import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    private boolean isGameOver = false;
    Location spawn = new Location(52, 690);
    
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

        Cloud cloud0 = new Cloud();
        addObject(cloud0, 107, 310);
        Cloud cloud1 = new Cloud();
        addObject(cloud1, 322, 381);
        cloud1.getImage().scale(70, 40);
        Cloud cloud2 = new Cloud();
        addObject(cloud2, 569, 290);
        cloud2.getImage().scale(130, 100);
        Cloud cloud3 = new Cloud();
        addObject(cloud3, 590, 426);
        Cloud cloud4 = new Cloud();
        addObject(cloud4, 775, 358);
        Cloud cloud5 = new Cloud();
        addObject(cloud5, 15, 422);
    }
}
