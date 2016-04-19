package se.chalmers.get_rect.game.entities.overlays;

import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.overlays.debug.Debugger;
import se.chalmers.get_rect.game.entities.overlays.quests.NpcList;
import se.chalmers.get_rect.game.entities.overlays.quests.QuestMarkersView;
import se.chalmers.get_rect.physics.IPhysicsEngine;

public class OverlayFactory {
    private EntityManager foreground;
    private IPhysicsModel player;
    private CameraManager camera;
    private IPhysicsEngine physics;

    public OverlayFactory(EntityManager foreground, IPhysicsModel player, CameraManager camera, IPhysicsEngine physics) {
        this.foreground = foreground;
        this.player = player;
        this.camera = camera;
        this.physics = physics;
    }

    public IEntity make(String type) {
        if (type.equals("questMarkers"))
            return makeQuestMarkers();

        if (type.equals("debug"))
            return makeDebug();

        throw new EntityNotFoundException("overlay", type);
    }

    private IEntity makeQuestMarkers() {
        NpcList model = new NpcList(foreground);
        IView view = new QuestMarkersView(model);

        return new Entity(model, view);
    }

    private IEntity makeDebug() {
        Debugger model = new Debugger(player, camera, physics);

        // todo: using model for view is not good.
        return new Entity(model, model);
    }
}