
package se.chalmers.get_rect.game.screens;


public class GameScreen /*implements IScreen, IGameStatus*/ {}/*    private CameraManager cameraManager;
    private IInputAdapter input;
    private PlayerController playerController;
    private IGame game;
    private boolean pause = false;


    public GameScreen(IGame game) {
        System.out.println("GameScreen is initialized");

        // get reference to game
        this.game = game;

        input = game.getInput();

        // Initialize player
        IPhysicsEntity player = createPlayer(game.getRectangleFactory());

        // Create the CameraManager
        cameraManager = createCamera(game.getCameraFactory(), player.getModel());

        // Add all scenes
        addScenes(player, game.getRectangleFactory());

        // Add GUI windows
        addWindows();
    }

    private CameraManager createCamera(ICameraFactoryAdapter cameraFactory, IPhysicsModel entity) {
        ICameraAdapter camera = cameraFactory.make(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
        return new CameraManager(camera, entity);
    }

    private IPhysicsEntity createPlayer(IRectangleFactoryAdapter rectangleFactory) {
        playerController = new PlayerController(input);
        ProjectileFactory projectileFactory = new ProjectileFactory(rectangleFactory);
        PlayerFactory playerFactory = new PlayerFactory(playerController, rectangleFactory, projectileFactory);

        return playerFactory.make();
    }

    private void addScenes(IPhysicsEntity player, IRectangleFactoryAdapter rectangleFactory) {
        // Register scenes
        sceneManager.add(TEST, new TestScene(player, rectangleFactory, cameraManager, sceneManager));
        sceneManager.add(HORSALSLANGAN, new HorsalsvagenScene(player, rectangleFactory, cameraManager, sceneManager));

        // Set starting scene
        sceneManager.set(HORSALSLANGAN);
    }

    private void addWindows() {
        windowManager.add(22, new inGameMenuWindow(this, input, cameraManager));
    }

    @Override
    public void enteringState(Integer previousStateName) {
        System.out.println("Entering GameScreen");
    }

    @Override
    public void leavingState(Integer nextStateName) {
        System.out.println("Leaving GameScreen");
    }

    /**
     * Will set update for the correct scene
     * and check if the menu button is pressed
     * @param delta time since last draw.
     */
/*    @Override
    public void update(double delta) {
        handleInput();
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        cameraManager.draw(graphics);
        sceneManager.getState().draw(graphics);

        if (pause) {
            windowManager.getState().draw(graphics);
        }
    }

    private void handleInput() {
        if (input.isKeyJustPressed(IInputAdapter.Keys.ESC)) {
            if (pause) {
                resume();
            } else {
                openWindow(22);
            }
        }
    }

    public void openWindow(Integer name) {
        windowManager.set(name);
        pause();
    }

    public void exit() {
        game.exit();
    }

    public void pause() {
        pause = true;
    }

    public void resume() {
        pause = false;
    }


}
*/