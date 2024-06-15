import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WinWorld extends World 
{

    private static final int TITLE_FONT_SIZE = 150;
    private static final int OPTION_FONT_SIZE = 70;
    private static final int INSTRUCTIONS_FONT_SIZE = 70;

    private Label titleLabel = new Label("You Win!", TITLE_FONT_SIZE);
    private Label hardMode = new Label("Hard Mode", OPTION_FONT_SIZE);
    private Label menu = new Label("Back To Menu", OPTION_FONT_SIZE);
    private Label instructions = new Label("^ press enter to select ^ \n \u2191 \u2193 to navigate", INSTRUCTIONS_FONT_SIZE);

    private boolean hardSelected = true;

    /**
     * Constructor for objects of class WinWorld.
     */
    public WinWorld() 
    {
        super(800, 800, 1, false); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * Create initial objects and add them to the world.
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

        Greenfoot.setSpeed(45);
    }

    /**
     * Act method for handling user input.
     */
    public void act() 
    {
        checkKeys();
    }

    /**
     * Check keyboard input and handle menu navigation and selection.
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
            handleBackToMenu();
        } 
        else 
        {
            handleHardMode();
        }
    }

    /**
     * Handle actions and transitions for Back To Menu option.
     */
    private void handleBackToMenu() 
    {
        menu.setFillColor(Color.YELLOW);
        menu.setLineColor(Color.BLACK);
        hardMode.setFillColor(Color.WHITE);
        hardMode.setLineColor(Color.BLACK);

        if (Greenfoot.isKeyDown("enter")) 
        {
            TitleScreen gameWorld = new TitleScreen();
            Greenfoot.setWorld(gameWorld);
            Greenfoot.delay(25); // Delay to prevent immediate key press detection
        }
    }

    /**
     * Handle actions and transitions for Hard Mode option.
     */
    private void handleHardMode() 
    {
        hardMode.setFillColor(Color.YELLOW);
        hardMode.setLineColor(Color.BLACK);
        menu.setFillColor(Color.WHITE);
        menu.setLineColor(Color.BLACK);

        if (Greenfoot.isKeyDown("enter")) 
        {
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
            gameWorld.player.easy = false;
        }
    }
}
