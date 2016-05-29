package se.chalmers.get_rect.game.world;

import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.game.ErrorHandler;
import se.chalmers.get_rect.game.GameConfig;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.states.StateManager;
import se.chalmers.get_rect.utilities.Point;

public class HubbenWorld extends AbstractWorld {

    public HubbenWorld(IEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, WorldLoader worldLoader, QuestManager quests, IAudioManagerAdapter audioManager, ErrorHandler error) {
        super("hubben", playerEntity, rectangleFactory, camera, worldLoader, quests, audioManager, error);
    }


    @Override
    public void enteringState(Integer previousState) {
        super.enteringState(previousState);
        if (previousState != null) {
            if (previousState == GameConfig.CAVE) {
                addPlayerAtPosition(1760, 380);
            } else if (previousState == GameConfig.TEST) {
                addPlayerAtPosition(2280, 380);
            } else {
                addPlayerAtPosition(380, 380);
            }
        } else {
            addPlayerAtPosition(380, 380);
        }
        // Set the player position
    }

    @Override
    public void respawn() {
        getPlayer().setPosition(new Point(30, 480));
    }
}

