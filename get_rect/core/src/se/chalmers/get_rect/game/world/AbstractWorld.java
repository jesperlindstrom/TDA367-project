package se.chalmers.get_rect.game.world;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.adapters.IGraphicsAdapter;
import se.chalmers.get_rect.event.IEventSource;
import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.game.window.controller.GridController;
import se.chalmers.get_rect.game.window.model.ErrorWindow;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
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

public abstract class AbstractWorld implements IWorld {
    private String folderName;
    private IEntity playerEntity;
    private IPhysicsModel playerModel;
    private IRectangleFactoryAdapter rectangleFactory;
    private ICamera camera;
    private IPhysicsEngine physics;
    private QuestManager quests;
    private ArrayList<IView> views;
    private ArrayList<IModel> models;
    private boolean setupDone;
    private Queue<IEntity> additions;
    private WorldLoader worldLoader;
    private IAudioManagerAdapter audioManager;
    private StateManager<GridController> windowManager;

    protected AbstractWorld(String folderName, IEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, WorldLoader worldLoader, QuestManager quests, IAudioManagerAdapter audioManager, StateManager windowManager) {
        this.folderName = folderName;
        this.playerEntity = playerEntity;
        this.playerModel = (IPhysicsModel) playerEntity.getModel();
        this.rectangleFactory = rectangleFactory;
        this.camera = camera;
        this.worldLoader = worldLoader;
        this.quests = quests;
        this.audioManager = audioManager;
        this.windowManager = windowManager;
        additions = new LinkedList<>();
    }

    protected ICamera getCamera() {
        return camera;
    }

    protected IRectangleFactoryAdapter getRectangleFactory() {
        return rectangleFactory;
    }

    protected IPhysicsModel getPlayer() {
        return playerModel;
    }

    protected void addPlayerAtPosition(int x, int y) {
        playerModel.setPosition(new Point(x, y));
        addEntity(playerEntity);
        camera.snapToPosition(playerModel.getPosition());
    }

    @Override
    public void update(double delta) {
        processAdditions();
        models.removeIf(IModel::shouldBeRemoved);
        models.forEach(m -> m.update(delta));
        physics.update(delta);
    }

    @Override
    public void draw(IGraphicsAdapter graphics) {
        views.removeIf(IView::shouldBeRemoved);
        views.forEach(v -> v.draw(graphics));
    }

    @Override
    public void enteringState(Integer previousStateName) {
        setupDone = false;
        setupPhysics();
        setupEntities();
        setupOverlays();
        sortViewsByDrawOrder();
        setupDone = true;
        processAdditions();
        additions.clear();

    }

    @Override
    public void leavingState(Integer nextStateName) {
        // Remove quest manager from entities
        models.stream()
            .filter(model -> model instanceof IEventSource)
            .forEach(model -> ((IEventSource) model).removeListener(quests));

        audioManager.stopAllMusic();
    }

    private void setupEntities() {
        views = new ArrayList<>();
        models = new ArrayList<>();
        try {
            worldLoader.getEntities(folderName).forEach(this::addEntity);
        } catch (FileNotFoundException e) {
            ((ErrorWindow)windowManager.getState(GameConfig.ERROR_WINDOW).getModel()).setMessage(e.getMessage());
            windowManager.set(GameConfig.ERROR_WINDOW);
            System.out.println(e.getMessage());
        }
    }

    private void setupOverlays() {
        worldLoader.getOverlays(models, physics).forEach(this::addEntity);
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

        if (model instanceof IEventSource) {
            ((IEventSource) model).addListener(quests);
        }
    }

    private void setupView(IView view) {
        if (view != null) {
            views.add(view);
        }
    }
}