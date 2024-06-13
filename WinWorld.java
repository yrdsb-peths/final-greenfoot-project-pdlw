import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class WinWorld extends World 
{
    Label titleLabel = new Label("You Win!", 150);
    Label endless = new Label("Play Endless", 70);
    Label l2 = new Label("Play Level 2", 70);
    Label instructions = new Label("^ press enter to select ^ \n \u2191 \u2193 to navigate", 70);
    private boolean endlessSelected = true;
    public WinWorld() 
    {    
        // Create a new world with 800x800 cells with a cell size of 1x1 pixels.
        super(800, 800, 1, false);
        addObject(titleLabel, getWidth()/2, 380);
        titleLabel.setFillColor(Color.WHITE);
        titleLabel.setLineColor(Color.PINK);
        addObject(l2, getWidth()/2, 540);
        l2.setFillColor(Color.WHITE);
        l2.setLineColor(Color.BLACK);
        addObject(instructions, getWidth()/2, 650);
        instructions.setFillColor(Color.WHITE);
        instructions.setLineColor(Color.BLACK);
        addObject(endless, getWidth()/2, 475);
        endless.setFillColor(Color.YELLOW);
        endless.setLineColor(Color.BLACK);
        Greenfoot.setSpeed(45);
    }
    public void act()
    {
        checkKeys();
    }
    public void checkKeys()
    {
        if(Greenfoot.isKeyDown("up") && endlessSelected == false)
        {
           endlessSelected = !endlessSelected;
        }
        if(Greenfoot.isKeyDown("down") && endlessSelected == true)
        {
            endlessSelected = !endlessSelected;
        }
        
        if(!endlessSelected)
        {
            endless.setFillColor(Color.WHITE);
            endless.setLineColor(Color.BLACK);
            l2.setFillColor(Color.YELLOW);
            l2.setLineColor(Color.BLACK);
            if(Greenfoot.isKeyDown("enter"))
            {
                gameOver gameWorld = new gameOver(false);
                Greenfoot.setWorld(gameWorld);
            }
        }
        else
        {
            l2.setFillColor(Color.WHITE);
            l2.setLineColor(Color.BLACK);
            endless.setFillColor(Color.YELLOW);
            endless.setLineColor(Color.BLACK);
            if(Greenfoot.isKeyDown("enter"))
            {
                TitleScreen gameWorld = new TitleScreen();
                Greenfoot.setWorld(gameWorld);
            }
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
}
