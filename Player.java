import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 */
public class Player extends Actor
{
    private int vSpeed = 0;
    private int acceleration = 1;
    private int jumpHeight = -15;

    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        if (getY() >= 799)
        {
            world.gameOver();
            world.removeObject(this);
        }
        else
        {
            mover();
            checkFalling();
        }
    }

    private void fall()
    {
        setLocation(getX(), getY() + vSpeed);
        vSpeed += acceleration;
    }

    public void mover()
    {
        if (Greenfoot.isKeyDown("right"))
        {
            move(4);
        }
        if (Greenfoot.isKeyDown("left"))
        {
            move(-4);
        }
        if (Greenfoot.isKeyDown("space") && onGround())
        {
            vSpeed = jumpHeight;
            fall();
        }
    }

    private boolean onGround()
    {
        int imageHeight = getImage().getHeight();
        int imageWidth = getImage().getWidth();

        Actor underLeft = getOneObjectAtOffset(-imageWidth / 2, imageHeight / 2, Platform.class);
        Actor underRight = getOneObjectAtOffset(imageWidth / 2, imageHeight / 2, Platform.class);

        return underLeft != null || underRight != null;
    }

    private void checkFalling()
    {
        if (!onGround())
        {
            fall();
        }
    }
}
