package se.chalmers.get_rect.game.world;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.game.window.ErrorHandler;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.utilities.Point;

public class TestWorld extends AbstractWorld {

    public TestWorld(IEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, WorldLoader worldLoader, QuestManager quests, IAudioManagerAdapter audioManager, ErrorHandler error) {
        super("test", playerEntity, rectangleFactory, camera, worldLoader, quests, audioManager, error);
    }

    @Override
    public void enteringState(Integer previousState) {
        super.enteringState(previousState);

        // Set the player position
        addPlayerAtPosition(1200, 150);
    }

    @Override
    public void respawn() {
        getPlayer().setPosition(new Point(670, 265));
    }
}
