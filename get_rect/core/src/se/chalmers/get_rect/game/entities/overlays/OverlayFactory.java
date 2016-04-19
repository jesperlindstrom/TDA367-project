package se.chalmers.get_rect.game.entities.overlays;

import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.overlays.combat.CombatList;
import se.chalmers.get_rect.game.entities.overlays.combat.HealthbarView;
import se.chalmers.get_rect.game.entities.overlays.debug.Debug;
import se.chalmers.get_rect.game.entities.overlays.debug.DebugView;
import se.chalmers.get_rect.game.entities.overlays.interactable.InteractableList;
import se.chalmers.get_rect.game.entities.overlays.interactable.InteractionHintsView;
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

        if (type.equals("interactionHints"))
            return makeInteractionHints();

        if (type.equals("debug"))
            return makeDebug();

        if(type.equals("healthbar")){
            return makeHealthbar();
        }

        throw new EntityNotFoundException("overlay", type);
    }

    private IEntity makeInteractionHints() {
        InteractableList model = new InteractableList(foreground);
        IView view = new InteractionHintsView(model);

        return new Entity(model, view);
    }

    private IEntity makeQuestMarkers() {
        NpcList model = new NpcList(foreground);
        IView view = new QuestMarkersView(model);

        return new Entity(model, view);
    }

    private IEntity makeDebug() {
        Debug model = new Debug(player, camera, physics);
        IView view = new DebugView(model);

        return new Entity(model, view);
    }

    private IEntity makeHealthbar(){
        CombatList model = new CombatList(foreground);
        IView view = new HealthbarView(model);
        return new Entity(model, view);
    }
}