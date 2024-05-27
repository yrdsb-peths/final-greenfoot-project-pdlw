import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
                int oldY = getY();
                setLocation(800 + getImage().getWidth()/2, oldY);
            }
    }
    public Cloud()
    {
        getImage().scale(100, 65);
    }
}
