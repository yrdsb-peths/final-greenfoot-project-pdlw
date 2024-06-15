import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Coin extends Actor 
{
    public static boolean ease;

    /**
     * Act - do whatever the Coin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        mover();
        
        // Check if the coin has moved off the screen to the left, then remove it
        int rightEdge = getX() + getImage().getWidth() / 2;
        if (rightEdge <= 0) 
        {
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Move the coin based on the ease mode.
     */
    public void mover() 
    {
        if (ease) 
        {
            if (Greenfoot.isKeyDown("right")) 
            {
                move(-6);
            }
            if (Greenfoot.isKeyDown("left")) 
            {
                move(6);
            }
        } else {
            move(-6);
        }
    }
}
