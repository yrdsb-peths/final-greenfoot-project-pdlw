import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform extends Actor
{
    /**
     * Act - do whatever the Platform wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Platform()
    {
        getImage().scale(100, 35);
    }
    public void act()
    {
        if (Greenfoot.isKeyDown("right"))
        {
            move(-4);
        }
        if (Greenfoot.isKeyDown("left"))
        {
            move(4);
        }
    }
}
