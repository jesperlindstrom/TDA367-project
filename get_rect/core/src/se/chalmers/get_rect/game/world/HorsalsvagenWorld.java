package se.chalmers.get_rect.game.world;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.Point;

public class HorsalsvagenWorld extends AbstractWorld {
    public HorsalsvagenWorld(IEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, WorldLoader worldLoader, QuestManager quests, IAudioManagerAdapter audioManager, StateManager windowManager) {
        super("horsalsvagen", playerEntity, rectangleFactory, camera, worldLoader, quests, audioManager, windowManager);
    }

    @Override
    public void enteringState(Integer previousState) {
        super.enteringState(previousState);

        // Set the player position
        addPlayerAtPosition(1200, 500);
    }

    @Override
    public void respawn() {
        getPlayer().setPosition(new Point(3300, 360));
    }
}
