import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title Screen
 * 
 * @author Micah Waddell
 * @version v1.0
 */
public class TitleScreen extends World
{
    Label titleLabel = new Label("Berry Blast!", 100);
    Label instructions = new Label("Press space to begin", 40);
    Label instructions2 = new Label("\u2191 \u2190 \u2192 To move", 60);
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1); 
        Background background = new Background();
        addObject(background, getWidth()/2, getHeight()/2);
        addObject(titleLabel, getWidth()/2, 310);
        addObject(instructions, getWidth()/4 , 700);
        addObject(instructions2, getWidth()/4 +15, 650);
        instructions.setFillColor(Color.WHITE);
        instructions.setLineColor(Color.BLUE);
        instructions2.setFillColor(Color.WHITE);
        instructions2.setLineColor(Color.BLUE);
        titleLabel.setFillColor(Color.WHITE);
        titleLabel.setLineColor(Color.PINK);
        prepare();
    }

    public void act()
    {
        if(Greenfoot.isKeyDown("space"))
        {
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}