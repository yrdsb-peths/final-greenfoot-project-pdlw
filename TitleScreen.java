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
    Label hardMode = new Label("Hard Mode", 70);
    Label menu = new Label("Normal Mode", 70);
    Label instructions = new Label("^ press enter to select ^ \n \u2191 \u2193 to navigate", 70);
    private boolean hardSelected = true;
    private boolean pressed1 = false;
    private boolean pressed2 = false;
    Label instructions3 = new Label("Press space to begin", 70);
    Label instructions2 = new Label("use \u2191 \u2190 \u2192 To move", 60);
    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1); 
        prepare();
    }

    public void act()
    {
        checkKeys();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Background background = new Background();
        addObject(background, getWidth()/2, getHeight()/2);
        addObject(titleLabel, getWidth()/2, 310);
        titleLabel.setFillColor(Color.WHITE);
        titleLabel.setLineColor(Color.PINK);
        addObject(menu, getWidth()/2, 540);
        menu.setFillColor(Color.WHITE);
        menu.setLineColor(Color.BLACK);
        addObject(instructions, getWidth()/2, 650);
        instructions.setFillColor(Color.WHITE);
        instructions.setLineColor(Color.BLACK);
        addObject(hardMode, getWidth()/2, 475);
        hardMode.setFillColor(Color.YELLOW);
        hardMode.setLineColor(Color.BLACK);
        Greenfoot.setSpeed(45);
    }
    public void checkKeys()
    {
        if(Greenfoot.isKeyDown("down") && hardSelected == true)
        {
           hardSelected = !hardSelected;
        }
        if(Greenfoot.isKeyDown("up") && hardSelected == false)
        {
            hardSelected = !hardSelected;
        }
        
        if(!hardSelected)
        {
            hardMode.setFillColor(Color.WHITE);
            hardMode.setLineColor(Color.BLACK);
            menu.setFillColor(Color.YELLOW);
            menu.setLineColor(Color.BLACK);
            if(Greenfoot.isKeyDown("enter"))
            {
                removeObject(menu);
                removeObject(hardMode);
                removeObject(instructions);
                Label explain = new Label("In normal mode, you control the cat. \n Your objective is to get 15 berries. \n Good luck!", 50);
                addObject(explain, getWidth()/2, 475);
                addObject(instructions3, getWidth()/2 , 720);
                instructions3.setFillColor(Color.WHITE);
                instructions3.setLineColor(Color.BLUE);
                addObject(instructions2, getWidth()/2, 650);
                instructions2.setFillColor(Color.WHITE);
                instructions2.setLineColor(Color.BLUE);
                pressed2 = true;
            }
            if(pressed2=true)
            {
            if(Greenfoot.isKeyDown("space"))
                {
                    MyWorld gameWorld = new MyWorld();
                    Greenfoot.setWorld(gameWorld);
                }
            }
        }
        else
        {
            menu.setFillColor(Color.WHITE);
            menu.setLineColor(Color.BLACK);
            hardMode.setFillColor(Color.YELLOW);
            hardMode.setLineColor(Color.BLACK);
            if(Greenfoot.isKeyDown("enter"))
            {
                removeObject(menu);
                removeObject(hardMode);
                removeObject(instructions);
                titleLabel.setValue("Hard Mode");
                Label explain = new Label("In hard mode, \n you can't control your movement except for jumping. \n Your objective is to get as many berries as possible. \n you have only have one life here \n Good luck!", 40);
                addObject(explain, getWidth()/2, 475);
                instructions2.setValue("Use \u2191 to jump");
                addObject(instructions3, getWidth()/2 , 720);
                instructions3.setFillColor(Color.WHITE);
                instructions3.setLineColor(Color.BLUE);
                addObject(instructions2, getWidth()/2, 650);
                instructions2.setFillColor(Color.WHITE);
                instructions2.setLineColor(Color.BLUE);
                pressed1 = true;
            }
            if(pressed1=true)
            {
            if(Greenfoot.isKeyDown("space"))
                {
                    MyWorld gameWorld = new MyWorld();
                    Greenfoot.setWorld(gameWorld);
                    gameWorld.player.easy = false;
                }
            }
        }
    }
}