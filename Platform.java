import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform extends Actor
{
    public static List<Platform> platforms = new ArrayList<>();
    /**
     * Act - do whatever the Platform wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Platform()
    {
        getImage().scale(100, 35);
        platforms.add(this);
    }
    public void act() 
    {
        MyWorld world = new MyWorld();
        if(world.ease())
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
                int maxY = 730;
                int minY = calculateMinY();
                Random random = new Random();
                int randomY = random.nextInt(maxY - minY + 1) + minY;
                setLocation(800 + getImage().getWidth() / 2, randomY);
            }
        }
        
        else if(world.ease()==false)
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
                int maxY = 730;
                int minY = calculateMinY();
                Random random = new Random();
                int randomY = random.nextInt(maxY - minY + 1) + minY;
                setLocation(800 + getImage().getWidth() / 2, randomY);
            }
        }
    }

    public static int calculateMinY() 
    {
        if (platforms.isEmpty()) 
        {
            return 445;
        }
        int minY = 445;
        for (Platform platform : platforms) 
        {
            minY = Math.max(minY, platform.getY() - 120); // Ensure jumpable distance
        }
        return minY;
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
