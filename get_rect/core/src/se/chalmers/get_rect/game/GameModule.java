package se.chalmers.get_rect.game;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import se.chalmers.get_rect.game.entities.EntityCamera;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.game.window.IWindowController;
import se.chalmers.get_rect.states.StateManager;

public class GameModule extends AbstractModule {
    private IPhysicsEntity playerEntity;

    public GameModule(IPhysicsEntity playerEntity) {
        this.playerEntity = playerEntity;
    }

    @Override
    protected void configure() {
        bind(IPhysicsEntity.class).annotatedWith(Names.named("Player")).toInstance(playerEntity);
        bind(IPhysicsModel.class).annotatedWith(Names.named("Player")).toInstance(playerEntity.getModel());
        bind(ICamera.class).to(EntityCamera.class);
        bind(new TypeLiteral<StateManager<IScene>>() {}).toInstance(new StateManager<>());
        bind(new TypeLiteral<StateManager<IWindowController>>() {}).toInstance(new StateManager<>());
    }
}
