import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class Cloud here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cloud extends Actor
{
    /**
     * Act - do whatever the Cloud wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("right"))
        {
            move(-1);
        }
        if (Greenfoot.isKeyDown("left"))
        {
            move(1);
        }
        int cloudRightEdge = getX() + getImage().getWidth() / 2;
            if(cloudRightEdge <= 0)
            {
                Random random = new Random();
                setLocation(800 + getImage().getWidth()/2, random.nextInt((160) + 1) + 290);
            }
    }
    public Cloud()
    {
        getImage().scale(100, 65);
    }
    public int getCloudX() 
    {
        return getX();
    }
    public int getCloudY() 
    {
        return getY();
    }

}
