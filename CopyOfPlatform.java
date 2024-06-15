import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class CopyOfPlatform extends Actor {

    private static final int WIDTH = 250;
    private static final int HEIGHT = 35;
    private static final int MOVE_SPEED = 6;

    public CopyOfPlatform() {
        getImage().scale(WIDTH, HEIGHT);
    }

    public void act() {
        movePlatform();
        checkEdge();
    }

    private void movePlatform() {
        move(-MOVE_SPEED);
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
