import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Actor
{
    private int vSpeed = 0;
    private int acceleration = 1;
    private int jumpHeight = -15;
    private int lives = 2;
    private int collect = 0;
    GreenfootImage[] idleLeft = new GreenfootImage[4];
    GreenfootImage[] idleRight = new GreenfootImage[4];
    GreenfootImage[] moveLeft = new GreenfootImage[6];
    GreenfootImage[] moveRight = new GreenfootImage[6];
    String facing = "right";
    Timer timer = new Timer();

    private boolean isMoving = false;

    public Player()
    {
        for (int i = 0; i < 4; i++)
        {
            idleRight[i] = new GreenfootImage("idle0" + (i + 1) + ".png");
        }
        for (int i = 0; i < 4; i++)
        {
            idleLeft[i] = new GreenfootImage("idle0" + (i + 1) + ".png");
            idleLeft[i].mirrorHorizontally();
        }
        for (int i = 0; i < 6; i++)
        {
            moveRight[i] = new GreenfootImage("tile0" + (i + 1) + ".png");
        }
        for (int i = 0; i < 6; i++)
        {
            moveLeft[i] = new GreenfootImage("tile0" + (i + 1) + ".png");
            moveLeft[i].mirrorHorizontally();
        }
        setImage(idleRight[0]);
        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                animatePlayer();
            }
        }, 100, 100);
    }

    public void act()
    {
        
        MyWorld world = (MyWorld) getWorld();
        if (getY() >= 799)
        {
            lives--;
            world.decreaseLives();
            checkLives();
        }
        else
        {
            mover();
            checkFalling();
            changeLives();
        }
        collect();
    }

    int imageIndex = 0;
    public void animatePlayer()
    {
        GreenfootImage[] images;

        if (isMoving)
        {
            if (facing.equals("right"))
            {
                images = moveRight;
            }
            else
            {
                images = moveLeft;
            }
        }
        else
        {
            if (facing.equals("right"))
            {
                images = idleRight;
            }
            else
            {
                images = idleLeft;
            }
        }
        if (imageIndex >= images.length)
        {
            imageIndex = 0;
        }

        setImage(images[imageIndex]);
        imageIndex = (imageIndex + 1) % images.length;
    }

    private void fall()
    {
        setLocation(getX(), getY() + vSpeed);
        vSpeed += acceleration;
    }
    public void mover()
    {
        isMoving = false;
        if (Greenfoot.isKeyDown("right"))
        {
            //move(1);
            facing = "right";
            isMoving = true;
        }
        if (Greenfoot.isKeyDown("left"))
        {
            //move(-1);
            facing = "left";
            isMoving = true;
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

    public void changeLives()
    {
        if (isTouching(Spike.class))
        {
            lives--;
            checkLives();
        }
    }

    public void checkLives()
    {
        if (lives == 0)
        {
            MyWorld world = (MyWorld) getWorld();
            world.gameOver();
            world.removeObject(this);
        }
        else if (lives > 0)
        {
            respawn();
        }
    }
    public void respawn()
    {
        MyWorld world = (MyWorld) getWorld();
        this.setLocation(world.spawn.getX(), world.spawn.getY());
        world.reset();
    }
    public void collect()
    {
        if (getWorld() == null) 
        {
            return;
        }

        Actor coin = getOneIntersectingObject(Coin.class);
        if (coin != null)
        {
            MyWorld world = (MyWorld) getWorld();
            getWorld().removeObject(coin);
            collect++;
            world.increaseScore();
        }
        if(collect==10)
        {
            MyWorld world = (MyWorld) getWorld();
            world.gameOver();
        }
    }
}
