package se.chalmers.get_rect.game.entities.overlays;

import se.chalmers.get_rect.game.CameraManager;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.overlays.model.CombatList;
import se.chalmers.get_rect.game.entities.overlays.view.HealthbarView;
import se.chalmers.get_rect.game.entities.overlays.debug.Debug;
import se.chalmers.get_rect.game.entities.overlays.debug.DebugView;
import se.chalmers.get_rect.game.entities.overlays.view.InteractionHintsView;
import se.chalmers.get_rect.game.entities.overlays.model.NpcList;
import se.chalmers.get_rect.game.entities.overlays.view.QuestMarkersView;
import se.chalmers.get_rect.game.entities.overlays.hud.PlayerHealthbarView;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IPhysicsEngine;

import java.util.List;

public class OverlayFactory {
    private List<IModel> models;
    private Player player;
    private CameraManager camera;
    private IPhysicsEngine physics;

    public OverlayFactory(List<IModel> models, Player player, CameraManager camera, IPhysicsEngine physics) {
        this.models = models;
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
        if(type.equals("playerHealthbar")){
            return makePlayerHealthbar();
        }

        throw new EntityNotFoundException("overlay", type);
    }

    private IEntity makePlayerHealthbar(){
        IView view = new PlayerHealthbarView(player,camera);
        return new Entity(null,view);
    }

    private IEntity makeInteractionHints() {
        IView view = new InteractionHintsView(player);

        return new Entity(null, view);
    }

    private IEntity makeQuestMarkers() {
        NpcList model = new NpcList(models);
        IView view = new QuestMarkersView(model);

        return new Entity(model, view);
    }

    private IEntity makeDebug() {
        Debug model = new Debug(player, camera, physics);
        IView view = new DebugView(model);

        return new Entity(model, view);
    }

    private IEntity makeHealthbar(){
        CombatList model = new CombatList(models);
        IView view = new HealthbarView(model);
        return new Entity(model, view);
    }
}