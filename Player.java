import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Actor
{
    private int vSpeed = 0;
    private int acceleration = 1;
    private int jumpHeight = -15;

    GreenfootImage[] idleLeft = new GreenfootImage[6];
    GreenfootImage[] idleRight = new GreenfootImage[6];
    String facing = "right";
    Timer timer = new Timer();

    public Player()
    {
        for(int i = 0; i < 6; i++)
        {
            idleRight[i] = new GreenfootImage("tile0" + (i+1) + ".png");
        }
        for(int i = 0; i < 6; i++)
        {
            idleLeft[i] = new GreenfootImage("tile0" + (i+1) + ".png");
            idleLeft[i].mirrorHorizontally();
        }
        setImage(idleRight[0]);
        timer.scheduleAtFixedRate(new TimerTask() 
        {
            @Override
            public void run() 
            {
                animateElephant();
            }
        }, 100, 100);
    }

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

    int imageIndex = 0;
    public void animateElephant()
    {
        if(facing.equals("right"))
        {
            setImage(idleRight[imageIndex]);
            imageIndex = (imageIndex + 1) % idleRight.length;
        }
        else
        {
            setImage(idleLeft[imageIndex]);
            imageIndex = (imageIndex + 1) % idleLeft.length;
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
            facing = "right";
        }
        if (Greenfoot.isKeyDown("left"))
        {
            move(-4);
            facing = "left";
        }
        if (Greenfoot.isKeyDown("up") && onGround())
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
