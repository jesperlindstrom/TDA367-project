package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.physics.IPhysicsEngine;
import se.chalmers.get_rect.physics.IPhysicsObject;
import se.chalmers.get_rect.physics.frostbite.PhysicsEngine;
import se.chalmers.get_rect.utilities.Point;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public abstract class AbstractScene implements IScene {
    private String folderName;
    private IPhysicsEntity playerEntity;
    private IRectangleFactoryAdapter rectangleFactory;
    private ICamera camera;
    private IPhysicsEngine physics;
    private ArrayList<IView> views;
    private ArrayList<IModel> models;
    private boolean setupDone;
    private Queue<IEntity> additions;
    private SceneLoader sceneLoader;

    protected AbstractScene(String folderName, IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, SceneLoader sceneLoader) {
        this.folderName = folderName;
        this.playerEntity = playerEntity;
        this.rectangleFactory = rectangleFactory;
        this.camera = camera;
        this.sceneLoader = sceneLoader;
    }

    /**
     * Get the camera manager
     *
     * @return The camera manager
     */
    protected ICamera getCamera() {
        return camera;
    }

    /**
     * Get the rectangle factory
     *
     * @return Rectangle factory
     */
    protected IRectangleFactoryAdapter getRectangleFactory() {
        return rectangleFactory;
    }

    /**
     * Get the player entity
     *
     * @return The player entity
     */
    protected IPhysicsEntity getPlayer() {
        return playerEntity;
    }

    /**
     * Add the player to the scene and physics, at a specified position
     *
     * @param x X coordinate
     * @param y Y coordinate
     */
    protected void addPlayerAtPosition(int x, int y) {
        playerEntity.getModel().setPosition(new Point(x, y));
        addEntity(playerEntity);
    }

    /**
     * Update all components
     *
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
     *
     * @param graphics The graphics adapter
     */
    @Override
    public void draw(IGraphicsAdapter graphics) {
        views.removeIf(IView::shouldBeRemoved);
        views.forEach(v -> v.draw(graphics));
    }

    /**
     * Load entities, reset and setup all components
     *
     * @param previousStateName The state we're coming from
     */
    @Override
    public void enteringState(Integer previousStateName) {
        setupDone = false;
        setupPhysics();
        setupEntities();
        setupOverlays();
        sortViewsByDrawOrder();
        additions = new LinkedList<>();
        setupDone = true;
    }

    /**
     * Unload or hide components before leaving the state
     *
     * @param nextStateName The state we're going into
     */
    @Override
    public void leavingState(Integer nextStateName) {

    }

    private void setupEntities() {
        views = new ArrayList<>();
        models = new ArrayList<>();
        try {
            sceneLoader.getEntities(folderName).forEach(this::addEntity);
        } catch (FileNotFoundException e) {
            // todo: handle error, window?
            System.out.println(e.getMessage());
        }
    }

    private void setupOverlays() {
        sceneLoader.getOverlays(models, physics).forEach(this::addEntity);
    }

    private void setupPhysics() {
        physics = new PhysicsEngine();
    }

    public void add(IEntity entity) {
        additions.add(entity);
    }

    private void sortViewsByDrawOrder() {
        views.sort(Comparator.comparing(IView::getDrawPriority));
    }

    private void processAdditions() {
        while (additions.size() > 0) {
            addEntity(additions.poll());
        }
    }

    protected void addEntity(IEntity entity) {
        IModel model = entity.getModel();
        IView view = entity.getView();

        setupModel(model);
        setupView(view);

        if (setupDone) {
            sortViewsByDrawOrder();
        }
    }

    private void setupModel(IModel model) {
        if (model == null) return;

        models.add(model);
        model.setScene(this);

        if (model instanceof IPhysicsObject) {
            physics.add((IPhysicsObject) model);
        }
    }

    private void setupView(IView view) {
        if (view != null) {
            views.add(view);
        }
    }
}