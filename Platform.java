import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
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
        int rightEdge = getX() + getImage().getWidth() / 2;
            if(rightEdge <= 0)
            {
                Random random = new Random();
                setLocation(800 + getImage().getWidth()/2, random.nextInt((350) + 350));
            }
    }
    public int getPlatformX() 
    {
        return getX();
    }
    public int getPlatformY() 
    {
        return getY();
    }
}
