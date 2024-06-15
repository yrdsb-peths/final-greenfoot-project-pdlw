import greenfoot.*;  // Greenfoot classes
import java.util.Timer;
import java.util.TimerTask;

/**
 * Player class represents the main character in the game.
 * Handles player movement, interactions, animations, and game logic.
 */
public class Player extends Actor 
{

    private MyWorld world; // Reference to the game world
    private int vSpeed = 0; // Vertical speed (for jumping and falling)
    private final int acceleration = 1; // Acceleration due to gravity
    private final int jumpHeight = -15; // Initial jump velocity
    public int lives = 2; // Number of lives the player has
    private int collect = 0; // Number of coins collected
    public boolean easy = true; // Flag indicating the difficulty mode
    private final GreenfootImage[] idleLeft = new GreenfootImage[4]; // Array of idle images facing left
    private final GreenfootImage[] idleRight = new GreenfootImage[4]; // Array of idle images facing right
    private final GreenfootImage[] moveLeft = new GreenfootImage[6]; // Array of moving images facing left
    private final GreenfootImage[] moveRight = new GreenfootImage[6]; // Array of moving images facing right
    private String facing = "right"; // Current facing direction of the player
    private final Timer timer = new Timer(); // Timer for player animation
    private boolean isMoving = false; // Flag indicating if the player is moving
    private int imageIndex = 0; // Index for current animation frame

    /**
     * Constructor for Player objects.
     * Loads player images and starts the animation timer.
     */
    public Player() 
    {
        loadImages(); // Load player images
        setImage(idleRight[0]); // Set initial image
        scheduleAnimation(); // Start animation timer
    }

    /**
     * Loads all images needed for the player animations.
     * Includes idle and movement images for both left and right directions.
     */
    private void loadImages() 
    {
        // Load idle images
        for (int i = 0; i < 4; i++) 
        {
            idleRight[i] = new GreenfootImage("idle0" + (i + 1) + ".png");
            idleLeft[i] = new GreenfootImage("idle0" + (i + 1) + ".png");
            idleLeft[i].mirrorHorizontally();
        }

        // Load movement images
        for (int i = 0; i < 6; i++) 
        {
            moveRight[i] = new GreenfootImage("tile0" + (i + 1) + ".png");
            moveLeft[i] = new GreenfootImage("tile0" + (i + 1) + ".png");
            moveLeft[i].mirrorHorizontally();
        }
    }

    /**
     * Schedules the player animation using a timer.
     * Animates the player based on current movement state (moving or idle).
     */
    private void scheduleAnimation() 
    {
        timer.scheduleAtFixedRate(new TimerTask() 
        {
            @Override
            public void run() 
            {
                animatePlayer(); // Animate the player
            }
        }, 100, 100); // Animation interval (milliseconds)
    }

    /**
     * Called when the player is added to the world.
     * Initializes the world reference.
     * 
     * @param world The game world in which the player is added.
     */
    public void addedToWorld(World world) 
    {
        this.world = (MyWorld) world; // Cast the world to MyWorld
    }

    /**
     * Act method for the player.
     * Handles player movement, gravity, jumping, falling, and interaction with other objects.
     */
    public void act() 
    {
        // Check if the player has fallen off the screen
        if (getY() >= 799) 
        {
            lives--; // Decrease player lives
            world.decreaseLives(); // Update world display
            checkLives(); // Check if player is out of lives
        } 
        else 
        {
            handleMovement(); // Handle player movement
            checkFalling(); // Check if player is falling
        }
        collectCoins(); // Check for coin collection
    }

    /**
     * Handles the collection of coins by the player.
     * Increases the collection count and updates the game world score.
     * Checks if the game win condition is met based on the number of collected coins.
     */
    private void collectCoins() 
    {
        if (getWorld() == null) return; // Exit method if player is not in a world

        Actor coin = getOneIntersectingObject(Coin.class); // Check for coin intersection
        if (coin != null) 
        {
            getWorld().removeObject(coin); // Remove the collected coin
            collect++; // Increment coin collection count
            world.increaseScore(); // Update the game score
        }

        Platform.ease = easy; // Update platform ease mode
        Coin.ease = easy; // Update coin ease mode

        // Check if win condition is met in easy mode
        if (easy && collect == 15) 
        {
            world.gameWin(); // Player wins the game
        } 
        else if (!easy) 
        {
            lives = 1; // Reduce lives to 1 in hard mode
        }
    }

    /**
     * Checks if the player is on the ground (standing on a platform).
     * 
     * @return true if the player is on the ground, false otherwise.
     */
    private boolean onGround() 
    {
        int imageHeight = getImage().getHeight(); // Get player image height
        int imageWidth = getImage().getWidth(); // Get player image width

        // Check if there is a platform directly below the player
        return getOneObjectAtOffset(-imageWidth / 2, imageHeight / 2, Platform.class) != null ||
               getOneObjectAtOffset(imageWidth / 2, imageHeight / 2, Platform.class) != null ||
               getOneObjectAtOffset(-imageWidth / 2, imageHeight / 2, CopyOfPlatform.class) != null ||
               getOneObjectAtOffset(imageWidth / 2, imageHeight / 2, CopyOfPlatform.class) != null;
    }

    /**
     * Checks if the player is falling (not standing on any platform).
     * If falling, continues falling based on current vertical speed.
     */
    private void checkFalling() 
    {
        if (!onGround()) 
        {
            fall(); // Continue falling
        }
    }

    /**
     * Handles player falling due to gravity.
     * Updates player vertical position based on current vertical speed.
     */
    private void fall() 
    {
        setLocation(getX(), getY() + vSpeed); // Update player position
        vSpeed += acceleration; // Apply gravity
    }

    /**
     * Handles player movement based on user input.
     * Updates player facing direction and movement state (moving or idle).
     */
    private void handleMovement() 
    {
        isMoving = false; // Reset movement flag

        // Check for right movement input
        if (Greenfoot.isKeyDown("right")) 
        {
            facing = "right"; // Set facing direction
            isMoving = true; // Set moving flag
        }

        // Check for left movement input (only when in easy mode)
        if (Greenfoot.isKeyDown("left") && easy) 
        {
            facing = "left"; // Set facing direction
            isMoving = true; // Set moving flag
        }

        // Check for jump input
        if (Greenfoot.isKeyDown("up") && onGround()) 
        {
            new GreenfootSound("jump.mp3").play(); // Play jump sound
            vSpeed = jumpHeight; // Set initial jump velocity
            fall(); // Start jumping/falling
        }
    }

    /**
     * Animates the player based on current movement state (moving or idle).
     * Updates the player image to create animation effects.
     */
    private void animatePlayer() 
    {
        GreenfootImage[] images;

        // Determine which set of images to use based on movement state
        if (isMoving) 
        {
            images = facing.equals("right") ? moveRight : moveLeft;
        } 
        else 
        {
            images = facing.equals("right") ? idleRight : idleLeft;
        }

        // Ensure image index stays within bounds
        if (imageIndex >= images.length) 
        {
            imageIndex = 0;
        }

        // Set the player image and increment image index for next frame
        setImage(images[imageIndex]);
        imageIndex = (imageIndex + 1) % images.length;
    }

    /**
     * Checks if the player has run out of lives.
     * If no lives remain, ends the game; otherwise, respawns the player.
     */
    private void checkLives() 
    {
        if (lives <= 0) {
            world.gameOver(); // End the game
            world.removeObject(this); // Remove player from world
        } else 
        {
            new GreenfootSound("death.mp3").play(); // Play death sound
            respawn(); // Respawn the player
        }
    }

    /**
     * Respawns the player at the spawn point.
     * Resets the game world to its initial state.
     */
    private void respawn() 
    {
        setLocation(world.spawn.getX(), world.spawn.getY()); // Set player location to spawn point
        world.reset(); // Reset the game world
    }
}
