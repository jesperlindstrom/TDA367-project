package se.chalmers.get_rect;

import se.chalmers.get_rect.adapters.*;
import se.chalmers.get_rect.game.screens.*;
import se.chalmers.get_rect.log.GameLog;
import se.chalmers.get_rect.states.*;

public class GameManager implements IGame {
    private IGraphicsAdapter graphics;
    private IInputAdapter input;
    private IAssetManagerAdapter assetManager;
    private IGameLoopAdapter gameLoop;
    private IRectangleFactoryAdapter rectangleFactory;
    private GameLog gameLog;
    private StateManager<IScreen> screenManager;
    private ICameraFactoryAdapter cameraFactory;

    /**
     *
     * @param graphics
     * @param input
     * @param assetManager
     * @param cameraFactory
     * @param gameLoop
     * @param rectangleFactory
     */
    public GameManager(IGraphicsAdapter graphics, IInputAdapter input, IAssetManagerAdapter assetManager, ICameraFactoryAdapter cameraFactory, IGameLoopAdapter gameLoop, IRectangleFactoryAdapter rectangleFactory) {
        // Store game engine adapters
        this.graphics = graphics;
        this.input = input;
        this.assetManager = assetManager;
        this.cameraFactory = cameraFactory;
        this.gameLoop = gameLoop;
        this.rectangleFactory = rectangleFactory;

        // Initialize components
        gameLog = new GameLog();
        screenManager = new StateManager<>();

        // Add screens
        screenManager.add("splash", new SplashScreen(this));
        screenManager.add("startMenu", new StartMenuScreen(this));
        screenManager.add("game", new GameScreen(this));

        // Set the active state
        screenManager.set("splash");
    }

    /**
     * Tell current state to draw
     */
    public void draw() {
        graphics.clear();
        graphics.start();
        screenManager.getState().draw(graphics);
        graphics.end();
    }

    /**
     * Tell current state to update
     * @param delta Time since last draw
     */
    public void update(long delta) {
        if(input.isKeyPressed(IInputAdapter.Keys.S)) {
            exit();
        }
        screenManager.getState().update(delta);
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
    @Override
    public StateManager<IScreen> getScreenManager() {
        return screenManager;
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

    /**
     * Get the log instance
     * @return Game log
     */
    @Override
    public GameLog getGameLog() {
        return gameLog;
    }
}