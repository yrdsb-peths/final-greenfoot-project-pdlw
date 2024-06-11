import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Coin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Coin extends Actor
{
    /**
     * Act - do whatever the Coin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("right"))
        {
            move(-6);
        }
        if (Greenfoot.isKeyDown("left"))
        {
            move(6);
        }
        int rightEdge = getX() + getImage().getWidth() / 2;
        if (rightEdge <= 0) 
            {
                getWorld().removeObject(this);
            }
    }
}
