import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Actor
{
    MyWorld world = (MyWorld) getWorld();
    private int vSpeed = 0;
    private int acceleration = 1;
    private int jumpHeight = -15;
    private int lives = 2;
    private int collect = 0;
    public boolean easy = true;
    GreenfootImage[] idleLeft = new GreenfootImage[4];
    GreenfootImage[] idleRight = new GreenfootImage[4];
    GreenfootImage[] moveLeft = new GreenfootImage[6];
    GreenfootImage[] moveRight = new GreenfootImage[6];
    String facing = "right";
    Timer timer = new Timer();

    private boolean isMoving = false;

    public Player()
    {
        if(easy==false)
        {
            lives = 1;
        }
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
        if (getY() >= 799)
        {
            MyWorld world = (MyWorld) getWorld();
            lives--;
            world.decreaseLives();
            checkLives();
        }
        else
        {
            mover();
            checkFalling();
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
            if(easy)
            {
                facing = "left";
                isMoving = true;
                }
        }
        if (Greenfoot.isKeyDown("up") && onGround())
        {
            GreenfootSound pointSound = new GreenfootSound("jump.mp3");
            pointSound.play();
            vSpeed = jumpHeight;
            fall();
        }
    }

    private boolean onGround() 
    {
        int imageHeight = getImage().getHeight();
        int imageWidth = getImage().getWidth();
    
        Actor underLeftPlatform = getOneObjectAtOffset(-imageWidth / 2, imageHeight / 2, Platform.class);
        Actor underRightPlatform = getOneObjectAtOffset(imageWidth / 2, imageHeight / 2, Platform.class);
        Actor underLeftCopyOfPlatform = getOneObjectAtOffset(-imageWidth / 2, imageHeight / 2, CopyOfPlatform.class);
        Actor underRightCopyOfPlatform = getOneObjectAtOffset(imageWidth / 2, imageHeight / 2, CopyOfPlatform.class);
    
        return underLeftPlatform != null || underRightPlatform != null || underLeftCopyOfPlatform != null || underRightCopyOfPlatform != null;
    }


    private void checkFalling()
    {
        if (!onGround())
        {
            fall();
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
            GreenfootSound pointSound = new GreenfootSound("death.mp3");
            pointSound.play();
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
        MyWorld world = (MyWorld) getWorld();
        if (getWorld() == null) 
        {
            return;
        }

        Actor coin = getOneIntersectingObject(Coin.class);
        if (coin != null)
        {
            getWorld().removeObject(coin);
            collect++;
            world.increaseScore();
        }
        if(easy)
        {
            Platform.ease = true;
            Coin.ease = true;
            if(collect==15)
            {
                world.gameWin();
            }
        }
        else if(easy==false)
        {
            Platform.ease = false;
            Coin.ease = false;
        }
    }
}
