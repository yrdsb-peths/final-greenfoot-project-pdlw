import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CopyOfPlatform extends Actor 
{
    public CopyOfPlatform() 
    {
        getImage().scale(250, 35);
    }

    public void act() 
    {
        movePlatform();
        checkEdge();
    }

    private void movePlatform() 
    {
        move(-6);
    }

    private void checkEdge() 
    {   
        int rightEdge = getX() + getImage().getWidth() / 2;
        if (rightEdge <= 0) 
        {
            getWorld().removeObject(this);
        }
    }
}
