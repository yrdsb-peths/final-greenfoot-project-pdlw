import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
    public void mover()
    {
        if(Greenfoot.isKeyDown("right"))
        {
            move(4);
        }
        if(Greenfoot.isKeyDown("left"))
        {
            move(-4);
        }
    }
}
