import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class CopyOfPlatform extends Actor
{

    public CopyOfPlatform()
    {
        getImage().scale(250, 35);
    }
    
    public void act() 
    {
        mover();
        checkEdge();
    }
    
    public void checkEdge() 
    {
        int rightEdge = getX() + getImage().getWidth() / 2;
        if (rightEdge <= 0) 
        {
            MyWorld world = new MyWorld();
            world.removeObject(this);
        }
    }
    
    public void mover() 
    {
        move(-4);
    }
}
