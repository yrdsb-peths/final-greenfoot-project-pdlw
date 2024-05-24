import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int vSpeed = 0;
    private int acceleration =1;
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        mover();
        checkFalling();
    }
    private void fall()
    {
        setLocation(getX(), getY() + vSpeed);
        vSpeed += acceleration;
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
    boolean onGround()
    {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class);
        return under != null;
    }
    public void checkFalling()
    {
        if(onGround() == false)
        {
            fall();
        }
    }
}
