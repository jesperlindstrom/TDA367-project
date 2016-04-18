package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.GameConfig;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.IGameComponent;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.loaders.SceneLoader;
import se.chalmers.get_rect.physics.IPhysicsEngine;
import se.chalmers.get_rect.physics.frostbite.PhysicsEngine;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.debug.Debugger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractScene implements IScene {
    private String name;
    private IPhysicsEntity playerEntity;
    private IRectangleFactoryAdapter rectangleFactory;
    private CameraManager camera;
    private IPhysicsEngine physics;
    private Map<layer, EntityManager> layers;
    private List<IGameComponent> overlays;
    private String backgroundImage;

    /**
     * Create a new scene
     * @param name The scene name
     * @param playerEntity The player entity
     * @param rectangleFactory A rectangle factory
     * @param camera A camera manager
     */
    protected AbstractScene(String name, IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, CameraManager camera) {
        this.name = name;
        this.playerEntity = playerEntity;
        this.rectangleFactory = rectangleFactory;
        this.camera = camera;
    }

    /**
     * Get the scene name
     * @return The scene name
     */
    protected String getName() {
        return name;
    }

    /**
     * Get the camera manager
     * @return The camera manager
     */
    protected CameraManager getCamera() {
        return camera;
    }

    /**
     * Set the scene background image
     * @param backgroundImage Image path
     */
    protected void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * Get the rectangle factory
     * @return Rectangle factory
     */
    protected IRectangleFactoryAdapter getRectangleFactory() {
        return rectangleFactory;
    }

    /**
     * Get the player entity
     * @return The player entity
     */
    protected IPhysicsEntity getPlayer() {
        return playerEntity;
    }

    /**
     * Add the player to the scene and physics, at a specified position
     * @param x X coordinate
     * @param y Y coordinate
     */
    protected void addPlayerAtPosition(int x, int y) {
        playerEntity.getModel().setPosition(new Point(x, y));
        addPhysicsEntity(layer.FOREGROUND, playerEntity);
    }

    /**
     * Add an entity to the scene
     * @param layer Which layer to add to
     * @param entity The entity to add
     */
    @Override
    public void addEntity(layer layer, IEntity entity) {
        layers.get(layer).add(entity);
        IModel model = entity.getModel();
        model.setScene(this);
    }

    /**
     * Add a physics entity to the scene and physics
     * @param layer Which layer to add to
     * @param entity The physics entity to add
     */
    @Override
    public void addPhysicsEntity(layer layer, IPhysicsEntity entity) {
        layers.get(layer).add(entity);
        IPhysicsModel model = entity.getModel();
        model.setScene(this);
        physics.add(model);
    }

    /**
     * Update all components
     * @param delta Time since last update in tenths of second.
     */
    @Override
    public void update(double delta) {
        layers.forEach((k, v) -> v.update(delta));
        overlays.forEach((v) -> v.update(delta));
        physics.update(delta);
    }

    /**
     * Draw all components
     * @param graphics The graphics adapter
     */
    @Override
    public void draw(IGraphicsAdapter graphics) {
        // todo: view code in model'ish. This doesn't belong here.
        if (backgroundImage != null) {
            Point pos = camera.getPosition();
            graphics.draw(backgroundImage, pos, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT, pos);
        }

        layers.forEach((k, v) -> v.draw(graphics));
        overlays.forEach((v) -> v.draw(graphics));
    }

    /**
     * Load entities, reset and setup all components
     * @param previousStateName The state we're coming from
     */
    @Override
    public void enteringState(String previousStateName) {
        setupEntityLayers();
        setupPhysics();
        setupOverlays();
        loadEntities();
    }

    /**
     * Unload or hide components before leaving the state
     * @param nextStateName The state we're going into
     */
    @Override
    public void leavingState(String nextStateName) {

    }

    private void loadEntities() {
        SceneLoader loader = new SceneLoader(name, playerEntity, rectangleFactory);

        try {
            loadBackground(loader);
            loadForeground(loader);
        } catch (FileNotFoundException e) {
            // todo: handle error
            System.out.println(e.getMessage());
        }
    }

    private void setupOverlays() {
        overlays = new ArrayList<>();
        overlays.add(new Debugger(playerEntity.getModel(), camera, physics));
    }

    private void setupPhysics() {
        physics = new PhysicsEngine();
    }

    private void setupEntityLayers() {
        layers = new HashMap<>();
        layers.put(layer.BACKGROUND, new EntityManager());
        layers.put(layer.BACKGROUND_EFFECTS, new EntityManager());
        layers.put(layer.FOREGROUND, new EntityManager());
        layers.put(layer.FOREGROUND_EFFECTS, new EntityManager());
    }

    private void loadBackground(SceneLoader loader) throws FileNotFoundException {
        for (IPhysicsEntity entity : loader.getBackground()) {
            addPhysicsEntity(layer.BACKGROUND, entity);
        }
    }

    private void loadForeground(SceneLoader loader) throws FileNotFoundException {
        for (IPhysicsEntity entity : loader.getForeground()) {
            addPhysicsEntity(layer.FOREGROUND, entity);
        }
    }
}