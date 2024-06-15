import greenfoot.*;  // Import necessary Greenfoot classes

/**
 * TitleScreen class represents the main menu screen of the game.
 * Players can choose between Normal Mode and Hard Mode.
 * Provides instructions and handles transitions to game worlds based on user input.
 * 
 * @author Micah Waddell
 * @version v1.0
 */
public class TitleScreen extends World
{
    // Labels for title, menu options, and instructions
    private Label titleLabel = new Label("Berry Blast!", 100);
    private Label hardMode = new Label("Hard Mode", 70);
    private Label menu = new Label("Normal Mode", 70);
    private Label instructions = new Label("^ press enter to select ^ \n \u2191 \u2193 to navigate", 60);
    private Label instructions2 = new Label("use \u2191 \u2190 \u2192 To move", 60);
    private Label instructions3 = new Label("Press space to begin", 70);

    // Boolean flags to track menu selection and button presses
    private boolean hardSelected = true;
    private boolean pressed1 = false;
    private boolean pressed2 = false;

    /**
     * Constructor for objects of class TitleScreen.
     * Initializes the world with specified dimensions and prepares initial objects.
     */
    public TitleScreen()
    {    
        super(800, 800, 1); // Create a world with 800x800 cells and cell size of 1x1 pixels
        prepare(); // Set up initial objects in the world
    }

    /**
     * Act method for handling user input.
     * Checks for key presses and responds accordingly.
     */
    public void act()
    {
        checkKeys(); // Check for user input and update state accordingly
    }
    
    /**
     * Prepare the world by adding initial objects and setting their properties.
     * This method is called once at the start of the program.
     */
    private void prepare()
    {
        addObject(new Background(), getWidth()/2, getHeight()/2);

        // Setup title label
        titleLabel.setFillColor(Color.WHITE);
        titleLabel.setLineColor(Color.PINK);
        addObject(titleLabel, getWidth()/2, 310);

        // Setup menu options
        menu.setFillColor(Color.YELLOW);
        menu.setLineColor(Color.BLACK);
        addObject(menu, getWidth()/2, 540);

        // Setup instructions
        instructions.setFillColor(Color.WHITE);
        instructions.setLineColor(Color.BLACK);
        addObject(instructions, getWidth()/2, 650);

        // Setup hard mode option
        hardMode.setFillColor(Color.WHITE);
        hardMode.setLineColor(Color.BLACK);
        addObject(hardMode, getWidth()/2, 475);

        Greenfoot.setSpeed(45); // Set the speed of Greenfoot execution
    }

    /**
     * Check keyboard input and handle menu navigation and selection.
     * Responds to arrow keys and enter/space keys to navigate and select options.
     */
    public void checkKeys()
    {
        // Handle down arrow key press to switch selection to Normal Mode
        if (Greenfoot.isKeyDown("down") && hardSelected == true)
        {
            hardSelected = !hardSelected; // Toggle hardSelected to switch modes
        }
        
        // Handle up arrow key press to switch selection to Hard Mode
        if (Greenfoot.isKeyDown("up") && hardSelected == false)
        {
            hardSelected = !hardSelected; // Toggle hardSelected to switch modes
        }
        
        // Handle Normal Mode selection
        if (!hardSelected)
        {
            // Highlight Normal Mode menu option
            hardMode.setFillColor(Color.WHITE);
            hardMode.setLineColor(Color.BLACK);
            menu.setFillColor(Color.YELLOW);
            menu.setLineColor(Color.BLACK);
            
            // Check if Enter key is pressed to proceed to Normal Mode instructions
            if (Greenfoot.isKeyDown("enter"))
            {
                removeObject(menu);
                removeObject(hardMode);
                removeObject(instructions);
                
                // Display instructions for Normal Mode
                Label explain = new Label("In normal mode, you control the cat. \n Your objective is to get 15 berries. \n Good luck!", 50);
                addObject(explain, getWidth()/2, 475);
                addObject(instructions3, getWidth()/2 , 720);
                instructions3.setFillColor(Color.WHITE);
                instructions3.setLineColor(Color.BLUE);
                addObject(instructions2, getWidth()/2, 650);
                instructions2.setFillColor(Color.WHITE);
                instructions2.setLineColor(Color.BLUE);
                pressed2 = true; // Set flag to indicate instructions are displayed
            }
            
            // Check if Space key is pressed to start the game in Normal Mode
            if (pressed2 && Greenfoot.isKeyDown("space"))
            {
                MyWorld gameWorld = new MyWorld(); // Create new game world instance
                Greenfoot.setWorld(gameWorld); // Set the world to the new game world
            }
        }
        else // Handle Hard Mode selection
        {
            // Highlight Hard Mode menu option
            menu.setFillColor(Color.WHITE);
            menu.setLineColor(Color.BLACK);
            hardMode.setFillColor(Color.YELLOW);
            hardMode.setLineColor(Color.BLACK);
            
            // Check if Enter key is pressed to proceed to Hard Mode instructions
            if (Greenfoot.isKeyDown("enter"))
            {
                removeObject(menu);
                removeObject(hardMode);
                removeObject(instructions);
                
                titleLabel.setValue("Hard Mode"); // Update title label for Hard Mode
                
                // Display instructions for Hard Mode
                Label explain = new Label("In hard mode, \n you can't control your movement except for jumping. \n Your objective is to get as many berries as possible. \n you have only have one life here \n Good luck!", 40);
                addObject(explain, getWidth()/2, 475);
                instructions2.setValue("Use \u2191 to jump");
                addObject(instructions3, getWidth()/2 , 720);
                instructions3.setFillColor(Color.WHITE);
                instructions3.setLineColor(Color.BLUE);
                addObject(instructions2, getWidth()/2, 650);
                instructions2.setFillColor(Color.WHITE);
                instructions2.setLineColor(Color.BLUE);
                pressed1 = true; // Set flag to indicate instructions are displayed
            }
            
            // Check if Space key is pressed to start the game in Hard Mode
            if (pressed1 && Greenfoot.isKeyDown("space"))
            {
                MyWorld gameWorld = new MyWorld(); // Create new game world instance
                Greenfoot.setWorld(gameWorld); // Set the world to the new game world
                gameWorld.player.easy = false; // Set game difficulty to hard mode
            }
        }
    }
}