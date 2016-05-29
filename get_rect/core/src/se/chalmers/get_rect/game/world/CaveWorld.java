package se.chalmers.get_rect.game.world;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.game.window.ErrorHandler;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class CaveWorld extends AbstractWorld {
    public CaveWorld(IEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, WorldLoader worldLoader, QuestManager quests, IAudioManagerAdapter audioManager, ErrorHandler error) {
        super("cave", playerEntity, rectangleFactory, camera, worldLoader, quests, audioManager, error);
    }

    @Override
    public void enteringState(Integer previousStateName) {
        super.enteringState(previousStateName);
        addPlayerAtPosition(520, 1450);
    }

    @Override
    public void respawn() {
        getPlayer().setPosition(new Point(520, 1450));
    }
}
