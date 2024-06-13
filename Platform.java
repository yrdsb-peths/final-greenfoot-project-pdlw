import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Platform extends Actor
{
    public static boolean ease;
    public static List<Platform> platforms = new ArrayList<>();
    
    public Platform()
    {
        getImage().scale(100, 35);
        platforms.add(this);
    }
    
    public void act() 
    {
        mover();
        checkEdge();
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

    public void checkEdge() 
    {
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
    
    public void mover() 
    {
        if(ease)
        {
            if (Greenfoot.isKeyDown("right")) 
            {
                move(-6);
            }
            if (Greenfoot.isKeyDown("left")) 
            {
                move(6);
            }
        }
        else
        {
            move(-6);
        }
    }
}
