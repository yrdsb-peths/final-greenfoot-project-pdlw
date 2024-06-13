import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class WinWorld extends World 
{
    Label titleLabel = new Label("You Win!", 150);
    Label hardMode = new Label("Hard Mode", 70);
    Label menu = new Label("Back To Menu", 70);
    Label instructions = new Label("^ press enter to select ^ \n \u2191 \u2193 to navigate", 70);
    private boolean hardSelectedted = true;
    public WinWorld() 
    {    
        // Create a new world with 800x800 cells with a cell size of 1x1 pixels.
        super(800, 800, 1, false);
        addObject(titleLabel, getWidth()/2, 380);
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
    public void act()
    {
        checkKeys();
    }
    public void checkKeys()
    {
        if(Greenfoot.isKeyDown("down") && hardSelectedted == false)
        {
           hardSelectedted = !hardSelectedted;
        }
        if(Greenfoot.isKeyDown("up") && hardSelectedted == true)
        {
            hardSelectedted = !hardSelectedted;
        }
        
        if(!hardSelectedted)
        {
            menu.setFillColor(Color.WHITE);
            menu.setLineColor(Color.BLACK);
            hardMode.setFillColor(Color.YELLOW);
            hardMode.setLineColor(Color.BLACK);
            if(Greenfoot.isKeyDown("enter"))
            {
                TitleScreen gameWorld = new TitleScreen();
                Greenfoot.setWorld(gameWorld);
            }
        }
        else
        {
            hardMode.setFillColor(Color.WHITE);
            hardMode.setLineColor(Color.BLACK);
            menu.setFillColor(Color.YELLOW);
            menu.setLineColor(Color.BLACK);
            if(Greenfoot.isKeyDown("enter"))
            {
                MyWorld gameWorld = new MyWorld();
                Greenfoot.setWorld(gameWorld);
                gameWorld.player.easy = false;
            }
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
}
