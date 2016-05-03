package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.IScene;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.overlays.OverlayFactory;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.loaders.SceneLoader;
import se.chalmers.get_rect.physics.IPhysicsEngine;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.frostbite.PhysicsEngine;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.Point;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public abstract class AbstractScene implements IScene {
    private String name;
    private IPhysicsEntity playerEntity;
    private IRectangleFactoryAdapter rectangleFactory;
    private CameraManager camera;
    private IPhysicsEngine physics;
    private StateManager<IScene> sceneManager;
    private ArrayList<IView> views;
    private ArrayList<IModel> models;
    private boolean setupDone;
    private Queue<IEntity> additions;

    /**
     * Create a new scene
     * @param name The scene name
     * @param playerEntity The player entity
     * @param rectangleFactory A rectangle factory
     * @param camera A camera manager
     */
    protected AbstractScene(String name, IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, CameraManager camera, StateManager<IScene> sceneManager) {
        this.name = name;
        this.playerEntity = playerEntity;
        this.rectangleFactory = rectangleFactory;
        this.camera = camera;
        this.sceneManager = sceneManager;
    }

    /**
     * Get the camera manager
     * @return The camera manager
     */
    protected CameraManager getCamera() {
        return camera;
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
        addEntity(playerEntity);
    }

    /**
     * Update all components
     * @param delta Time since last update in tenths of second.
     */
    @Override
    public void update(double delta) {
        processAdditions();
        models.removeIf(IModel::shouldBeRemoved);
        models.forEach(m -> m.update(delta));
        physics.update(delta);

    }

    /**
     * Draw all components
     * @param graphics The graphics adapter
     */
    @Override
    public void draw(IGraphicsAdapter graphics) {
        views.removeIf(IView::shouldBeRemoved);
        views.forEach(v -> v.draw(graphics));
    }

    /**
     * Load entities, reset and setup all components
     * @param previousStateName The state we're coming from
     */
    @Override
    public void enteringState(String previousStateName) {
        setupDone = false;
        setupEntities();
        setupPhysics();
        setupOverlays();
        views.sort(Comparator.comparing(IView::getDrawPriority));
        additions = new LinkedList<>();
        setupDone = true;

    }

    /**
     * Unload or hide components before leaving the state
     * @param nextStateName The state we're going into
     */
    @Override
    public void leavingState(String nextStateName) {

    }

    /**
     * Load all entities from JSON data
     */
    protected void loadEntities() {
        SceneLoader loader = new SceneLoader(name, playerEntity, rectangleFactory, sceneManager);

        try {
            loadEntities(loader);
        } catch (FileNotFoundException e) {
            // todo: handle error
            System.out.println(e.getMessage());
        }
    }

    private void setupEntities() {
        views = new ArrayList<>();
        models = new ArrayList<>();
    }

    private void setupOverlays() {
        if(playerEntity.getModel() instanceof Player) {
            Player player = (Player) playerEntity.getModel();
            OverlayFactory overlay = new OverlayFactory(models, player, camera, physics);
            addEntity(overlay.make("questMarkers"));
            addEntity(overlay.make("interactionHints"));
            addEntity(overlay.make("debug"));
            addEntity(overlay.make("healthbar"));
        }
    }

    private void setupPhysics() {
        physics = new PhysicsEngine();
    }


    private void loadEntities(SceneLoader loader) throws FileNotFoundException {
        loader.getAllEntities().forEach(this::addEntity);
    }

    public void add(IEntity entity) {
        additions.add(entity);
    }

    private void processAdditions() {
        while (additions.size() > 0) {
            addEntity(additions.poll());
        }
    }


    protected void addEntity(IEntity entity) {
        IModel model = entity.getModel();
        IView view = entity.getView();

        if (model != null) {
            models.add(model);
            model.setScene(this);

            if (model instanceof IPhysicsObject) {
                physics.add((IPhysicsObject) model);
            }
        }

        if (view != null) {
            views.add(view);
        }

        if (setupDone) {
            views.sort(Comparator.comparing(IView::getDrawPriority));
        }
    }
}