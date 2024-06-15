import greenfoot.*;  // Greenfoot classes
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * Platform class represents a platform in the game world.
 * Platforms can move horizontally and respawn when they reach the edge of the screen.
 */
public class Platform extends Actor
{

    public static boolean ease; // Static flag to control movement ease
    private static final int DEFAULT_MIN_Y = 445; // Default minimum Y coordinate for platforms
    private static final int MOVE_SPEED = 6; // Movement speed of platforms

    private static List<Platform> platforms = new ArrayList<>(); // List to hold all platform instances

    /**
     * Constructor for Platform objects.
     * Initializes the platform image and adds the platform to the list of platforms.
     */
    public Platform() 
    {
        getImage().scale(100, 35); // Scale the platform image
        platforms.add(this); // Add this platform to the list
    }

    /**
     * Act method for the platform.
     * Handles platform movement and checks if the platform has reached the edge of the world.
     */
    public void act() 
    {
        movePlatform(); // Move the platform
        checkEdge(); // Check if the platform is at the edge of the world
    }

    /**
     * Moves the platform horizontally based on the current movement mode (ease or default).
     */
    private void movePlatform() 
    {
        if (ease) 
        { // If ease mode is enabled
            if (Greenfoot.isKeyDown("right")) 
            {
                move(-MOVE_SPEED); // Move left when right key is pressed
            }
            if (Greenfoot.isKeyDown("left")) 
            {
                move(MOVE_SPEED); // Move right when left key is pressed
            }
        } 
        else 
        {
            move(-MOVE_SPEED); // Default movement to the left
        }
    }

    /**
     * Calculates the minimum Y coordinate for spawning a new platform.
     * Ensures platforms are spaced out vertically enough for the player to jump between them.
     *
     * @return The minimum Y coordinate for platform spawning.
     */
    public static int calculateMinY() 
    {
        if (platforms.isEmpty()) 
        {
            return DEFAULT_MIN_Y; // Return default if no platforms exist yet
        }
        int minY = DEFAULT_MIN_Y;
        for (Platform platform : platforms) 
        {
            minY = Math.max(minY, platform.getY() - 120); // Adjust minY based on existing platforms
        }
        return minY;
    }

    /**
     * Checks if the platform has reached the left edge of the world and respawns it on the right edge.
     */
    public void checkEdge() 
    {
        int rightEdge = getX() + getImage().getWidth() / 2; // Calculate right edge of the platform
        if (rightEdge <= 0) 
        { // If platform is completely off the left edge
            respawnPlatform(); // Respawn the platform on the right edge
        }
    }

    /**
     * Respawns the platform on the right edge of the world at a random Y coordinate within valid bounds.
     */
    private void respawnPlatform() 
    {
        int maxY = 730; // Maximum Y coordinate for platform spawning
        int minY = calculateMinY(); // Minimum Y coordinate based on existing platforms
        Random random = new Random(); // Random object for generating random Y coordinate
        int randomY = random.nextInt(maxY - minY + 1) + minY; // Calculate random Y coordinate
        setLocation(800 + getImage().getWidth() / 2, randomY); // Set platform location on the right edge
    }
}
