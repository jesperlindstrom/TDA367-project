package se.chalmers.get_rect.game.entities.overlays;

import com.google.inject.Inject;
import se.chalmers.get_rect.adapters.IInputAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.ItemFactory;
import se.chalmers.get_rect.game.entities.overlays.model.CombatList;
import se.chalmers.get_rect.game.entities.overlays.view.*;
import se.chalmers.get_rect.game.entities.overlays.model.Debug;
import se.chalmers.get_rect.game.entities.overlays.model.NpcList;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.IPhysicsEngine;

import java.util.List;

public class OverlayFactory {
    @Inject private Player player;
    @Inject private ICamera camera;
    @Inject private IInputAdapter input;
    @Inject private ItemFactory itemFactory;
    private List<IModel> models;
    private IPhysicsEngine physics;

    public void setModels(List<IModel> models) {
        this.models = models;
    }

    public void setPhysics(IPhysicsEngine physics) {
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
        if (type.equals("weaponSlots")){
            return makePlayerWeaponSlot();
        }
        if (type.equals("dialog")){
            return makeDialog();
        }

        throw new EntityNotFoundException("overlay", type);
    }

    private IEntity makeDialog() {
        IView view = new DialogView(player);
        return new Entity(null,view);
    }

    private IEntity makePlayerWeaponSlot() {
        IView view = new PlayerWeaponSlotsView(player, camera, itemFactory);
        return new Entity(null, view);
    }

    private IEntity makePlayerHealthbar() {
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
        Debug model = new Debug(player, camera, input, physics);
        IView view = new DebugView(model);

        return new Entity(model, view);
    }

    private IEntity makeHealthbar() {
        CombatList model = new CombatList(models);
        IView view = new HealthbarView(model);
        return new Entity(model, view);
    }
}