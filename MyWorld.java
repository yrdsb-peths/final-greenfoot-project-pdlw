import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * MyWorld is the main world where the game takes place.
 * It contains platforms, clouds, a player, and handles game logic such as scoring, lives, and game state.
 */
public class MyWorld extends World 
{
    public boolean newHigh = false; // Flag indicating if a new high score is achieved
    public int score; // Current score of the player
    public static int hiScore; // Highest score achieved
    public int lives = 2; // Number of lives the player has
    Label scoreLabel; // Label displaying the current score
    Label livesLabel; // Label displaying the number of lives left
    Label hiScoreLabel; // Label displaying the highest score achieved
    private boolean isGameOver = false; // Flag indicating if the game is over
    public Location spawn = new Location(100, 690); // Spawn location of the player
    private Platform[] platforms = new Platform[5]; // Array of platforms in the world
    private Cloud[] clouds = new Cloud[6]; // Array of clouds in the world
    private CopyOfPlatform platform9; // Additional platform in the world
    public Player player; // The player character
    private GreenfootSound pointerSound = new GreenfootSound("pointer.mp3"); // Sound played on various actions
    private GreenfootSound completeSound = new GreenfootSound("complete.mp3"); // Sound played on completing certain actions

    /**
     * Constructor for MyWorld sets up the initial state of the game.
     * Creates platforms, clouds, initializes labels, and sets up the player.
     */
    public MyWorld() 
    {    
        super(800, 800, 1, false);
        prepare();
        initializeLabels();
        Greenfoot.setSpeed(45); // Set the game speed

        player = new Player();
        addObject(player, spawn.getX(), spawn.getY());
        player.getImage().scale(35, 35);

        if(Platform.ease == false) // Check the platform ease condition
        {
            platform9 = new CopyOfPlatform();
            addObject(platform9, 70, 718);
        }
    }

    /**
     * Initializes the score, lives, and high score labels on the screen.
     */
    private void initializeLabels() 
    {
        scoreLabel = createLabel("Berries Collected: " + score, Color.BLUE, 250, 300);
        hiScoreLabel = createLabel("High Score: " + hiScore, Color.BLUE, 179, 420);
        livesLabel = createLabel("Lives Left: " + lives, Color.ORANGE, 165, 360);
    }

    /**
     * Creates a label with specified text, color, and position on the screen.
     *
     * @param text The text to display on the label.
     * @param color The color of the text.
     * @param x The x-coordinate of the label.
     * @param y The y-coordinate of the label.
     * @return The created Label object.
     */
    private Label createLabel(String text, Color color, int x, int y) 
    {
        Label label = new Label(text, 60);
        label.setFillColor(color);
        addObject(label, x, y);
        return label;
    }

    /**
     * Increases the score by 1 and updates the score label.
     * Checks if a new high score is achieved and updates the corresponding label.
     */
    public void increaseScore() 
    {
        score++;
        playPointSound();
        scoreLabel.setValue("Berries Collected: " + score);
        if (score > hiScore) 
        {
            newHigh = true;
            hiScore = score;
            hiScoreLabel.setLocation(245, 420);
            hiScoreLabel.setValue("New High Score!: " + hiScore);
            hiScoreLabel.setFillColor(Color.RED);
        }
    }

    /**
     * Plays a sound based on game conditions.
     * If the player is in 'easy' mode and the score is over 14, plays a completion sound.
     * Otherwise, plays a pointer sound.
     */
    private void playPointSound() 
    {
        if (player.easy && score > 14) 
        {
            completeSound.play();
        } 
        else 
        {
            pointerSound.play();
        }
    }

    /**
     * Decreases the number of lives by 1 and updates the lives label.
     */
    public void decreaseLives() 
    {
        lives--;
        livesLabel.setValue("Lives Left: " + lives);
    }

    /**
     * Initiates the game over sequence by switching to the Game Over screen.
     * Only performs this action if the game is not already over.
     */
    public void gameOver() 
    {
        if (!isGameOver) 
        {
            gameOver over = new gameOver(newHigh);
            Greenfoot.setWorld(over);
        }
    }

    /**
     * Initiates the game win sequence by switching to the Win World screen.
     */
    public void gameWin() 
    {
        WinWorld gameWorld = new WinWorld();
        Greenfoot.setWorld(gameWorld);
    }

    /**
     * Prepares the initial setup of clouds and platforms in the world.
     */
    public void prepare() 
    {
        clouds[0] = createCloud(107, 310, 70, 40);
        clouds[1] = createCloud(322, 381, 70, 40);
        clouds[2] = createCloud(569, 290, 130, 100);
        clouds[3] = createCloud(590, 426, 70, 40);
        clouds[4] = createCloud(775, 358, 70, 40);
        clouds[5] = createCloud(15, 422, 70, 40);

        platforms[0] = createPlatform(70, 718);
        platforms[1] = createPlatform(225, 658);
        platforms[2] = createPlatform(408, 595);
        platforms[3] = createPlatform(576, 642);
        platforms[4] = createPlatform(709, 708);
    }

    /**
     * Creates a cloud actor with specified position and size.
     *
     * @param x The x-coordinate of the cloud.
     * @param y The y-coordinate of the cloud.
     * @param width The width of the cloud.
     * @param height The height of the cloud.
     * @return The created Cloud object.
     */
    private Cloud createCloud(int x, int y, int width, int height) 
    {
        Cloud cloud = new Cloud();
        addObject(cloud, x, y);
        cloud.getImage().scale(width, height);
        return cloud;
    }

    /**
     * Creates a platform actor with specified position.
     *
     * @param x The x-coordinate of the platform.
     * @param y The y-coordinate of the platform.
     * @return The created Platform object.
     */
    private Platform createPlatform(int x, int y) 
    {
        Platform platform = new Platform();
        addObject(platform, x, y);
        return platform;
    }

    /**
     * Resets the positions of all actors (platforms and clouds) to their initial locations.
     */
    public void reset() 
    {
        setObjectLocations(platforms, new int[][]{{70, 718}, {225, 658}, {408, 595}, {576, 642}, {709, 708}});
        setObjectLocations(clouds, new int[][]{{107, 310}, {322, 381}, {569, 290}, {590, 426}, {775, 358}, {15, 422}});
    }

    /**
     * Sets the locations of given actors based on provided coordinates.
     *
     * @param actors The array of actors to set locations for.
     * @param locations The 2D array of coordinates to set for each actor.
     */
    private void setObjectLocations(Actor[] actors, int[][] locations) 
    {
        for (int i = 0; i < actors.length; i++) 
        {
            actors[i].setLocation(locations[i][0], locations[i][1]);
        }
    }

    /**
     * Handles the game logic during each frame.
     * Spawns coins based on certain conditions.
     */
    public void act() 
    {   
        if (player.easy && Greenfoot.isKeyDown("right")) 
        {
            coinSpawn();
        } 
        else if (!player.easy) 
        {
            coinSpawn();
        }
    }

    /**
     * Spawns a coin randomly on the screen based on certain conditions.
     */
    public void coinSpawn()
    {
        if (Greenfoot.getRandomNumber(100) <= 1) 
        {
            Random random = new Random();
            int minY = Platform.calculateMinY();
            int maxY = 700;
            int randomY = random.nextInt(maxY - minY + 1) + minY;
            if (randomY > player.getY()) 
            {
                randomY = random.nextInt(maxY - minY + 1) + minY;
            }
            addObject(new Coin(), getWidth() - 1, randomY);
        }
    }

    /**
     * Location class represents a coordinate pair (x, y).
     */
    public class Location 
    {
        private int x;
        private int y;

        /**
         * Constructor for Location class initializes x and y coordinates.
         *
         * @param x The x-coordinate.
         * @param y The y-coordinate.
         */
        public Location(int x, int y) 
        {
            this.x = x;
            this.y = y;
        }

        /**
         * Returns the x-coordinate.
         *
         * @return The x-coordinate.
         */
        public int getX() 
        {
            return x;
        }

        /**
         * Returns the y-coordinate.
         *
         * @return The y-coordinate.
         */
        public int getY() 
        {
            return y;
        }
    }
}
