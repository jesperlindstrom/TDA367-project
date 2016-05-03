package se.chalmers.get_rect;

import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.game.IGame;
import se.chalmers.get_rect.game.IScreen;
import se.chalmers.get_rect.game.screens.*;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.states.*;

public class Game implements IGame {
    private IGraphicsAdapter graphics;
    private IInputAdapter input;
    private IAssetManagerAdapter assetManager;
    private IGameLoopAdapter gameLoop;
    private IRectangleFactoryAdapter rectangleFactory;
    private StateManager<IScreen> screens;
    private ICameraFactoryAdapter cameraFactory;

    private static final int SPLASH = 11;
    private static final int MENU = 12;
    private static final int GAME = 13;

    /**
     * Initialize a new RPG game
     * @param graphics Graphics adapter
     * @param input Input adapter
     * @param assetManager AssetManager adapter
     * @param cameraFactory CameraFactory adapter
     * @param gameLoop GameLoop adapter
     * @param rectangleFactory RectangleFactory adapter
     */
    public Game(IGraphicsAdapter graphics, IInputAdapter input, IAssetManagerAdapter assetManager, ICameraFactoryAdapter cameraFactory, IGameLoopAdapter gameLoop, IRectangleFactoryAdapter rectangleFactory) {
        // Store game engine adapters
        this.graphics = graphics;
        this.input = input;
        this.assetManager = assetManager;
        this.cameraFactory = cameraFactory;
        this.gameLoop = gameLoop;
        this.rectangleFactory = rectangleFactory;

        // Initialize components
        screens = new StateManager<>();

        // Add screens
        screens.add(SPLASH, new SplashScreen(this));
        screens.add(MENU, new StartMenuScreen(this));
        screens.add(GAME, new GameScreen(this));

        // Set the active state
        screens.set(SPLASH);
    }

    /**
     * Tell current state to draw
     */
    public void draw() {
        graphics.clear();
        graphics.start();
        screens.getState().draw(graphics);
        graphics.end();
    }

    /**
     * Tell current state to update
     * @param delta Time since last draw
     */
    public void update(double delta) {
        screens.getState().update(delta);
    }

    /**
     * Get the input instance
     * @return Input adapter
     */
    @Override
    public IInputAdapter getInput() {
        return input;
    }

    /**
     * Get the assetManager instance
     * @return assetManager adapter
     */
    @Override
    public IAssetManagerAdapter getAssetManager() {
        return assetManager;
    }

    /**
     * Get the rectangle factory
     * @return rectangleFactory adapter
     */
    @Override
    public IRectangleFactoryAdapter getRectangleFactory() {
        return rectangleFactory;
    }

    /**
     * Get the state manager instance
     * @return State manager
     */
    public StateManager<IScreen> getScreens() {
        return screens;
    }

    @Override
    public ICameraFactoryAdapter getCameraFactory() {
        return cameraFactory;
    }

    @Override
    public void exit() {
        assetManager.dispose();
        gameLoop.exit();
    }

    @Override
    public void load() {
        screens.set(GAME);
    }

    @Override
    public void save() {

    }

    @Override
    public void startNew() {
        screens.set(GAME);
    }

    @Override
    public boolean loadAvailable() {
        return true;
    }


}