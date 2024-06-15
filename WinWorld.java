import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * WinWorld represents the screen displayed when the player wins the game.
 * It offers options to return to the main menu or switch to hard mode.
 * Navigation and selection are handled through keyboard input.
 */
public class WinWorld extends World 
{
    private static final int TITLE_FONT_SIZE = 150;
    private static final int OPTION_FONT_SIZE = 70;
    private static final int INSTRUCTIONS_FONT_SIZE = 70;

    private Label titleLabel = new Label("You Win!", TITLE_FONT_SIZE);
    private Label hardMode = new Label("Hard Mode", OPTION_FONT_SIZE);
    private Label menu = new Label("Back To Menu", OPTION_FONT_SIZE);
    private Label instructions = new Label("^ press enter to select ^ \n \u2191 \u2193 to navigate", INSTRUCTIONS_FONT_SIZE);

    private boolean hardSelected = true; // Flag indicating if hard mode option is selected

    /**
     * Constructor for WinWorld initializes the world with labels and prepares the screen.
     */
    public WinWorld() 
    {
        super(800, 800, 1, false); 
        prepare();
    }

    /**
     * Prepares the world by adding labels to display "You Win!" and menu options.
     */
    private void prepare() 
    {
        addObject(titleLabel, getWidth() / 2, 380);
        titleLabel.setFillColor(Color.WHITE);
        titleLabel.setLineColor(Color.PINK);

        addObject(menu, getWidth() / 2, 540);
        menu.setFillColor(Color.WHITE);
        menu.setLineColor(Color.BLACK);

        addObject(instructions, getWidth() / 2, 650);
        instructions.setFillColor(Color.WHITE);
        instructions.setLineColor(Color.BLACK);

        addObject(hardMode, getWidth() / 2, 475);
        hardMode.setFillColor(Color.YELLOW);
        hardMode.setLineColor(Color.BLACK);

        Greenfoot.setSpeed(45); // Set the game speed
    }

    /**
     * Act method is called repeatedly to handle user input for menu navigation and selection.
     */
    public void act() 
    {
        checkKeys(); // Check for keyboard input
    }

    /**
     * Checks keyboard input for navigating between menu options and making selections.
     */
    public void checkKeys() 
    {
        if (Greenfoot.isKeyDown("down") && hardSelected) 
        {
            hardSelected = false;
        }
        if (Greenfoot.isKeyDown("up") && !hardSelected) 
        {
            hardSelected = true;
        }

        if (!hardSelected) 
        {
            handleBackToMenu(); // Handle selection of Back To Menu option
        } 
        else 
        {
            handleHardMode(); // Handle selection of Hard Mode option
        }
    }

    /**
     * Handles actions and transitions when Back To Menu option is selected.
     */
    private void handleBackToMenu() 
    {
        menu.setFillColor(Color.YELLOW);
        menu.setLineColor(Color.BLACK);
        hardMode.setFillColor(Color.WHITE);
        hardMode.setLineColor(Color.BLACK);

        if (Greenfoot.isKeyDown("enter")) 
        {
            TitleScreen gameWorld = new TitleScreen(); // Create new TitleScreen world
            Greenfoot.setWorld(gameWorld); // Switch to TitleScreen
            Greenfoot.delay(25); // Delay to prevent immediate key press detection
        }
    }

    /**
     * Handles actions and transitions when Hard Mode option is selected.
     * Sets the game world to MyWorld with hard mode activated.
     */
    private void handleHardMode() 
    {
        hardMode.setFillColor(Color.YELLOW);
        hardMode.setLineColor(Color.BLACK);
        menu.setFillColor(Color.WHITE);
        menu.setLineColor(Color.BLACK);

        if (Greenfoot.isKeyDown("enter")) 
        {
            MyWorld gameWorld = new MyWorld(); // Create new MyWorld world
            Greenfoot.setWorld(gameWorld); // Switch to MyWorld
            gameWorld.player.easy = false; // Set the game to hard mode
        }
    }
}
