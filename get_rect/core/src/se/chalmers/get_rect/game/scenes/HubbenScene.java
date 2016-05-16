package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;

public class HubbenScene extends AbstractScene {
    public HubbenScene(IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, SceneLoader sceneLoader, QuestManager quests) {
        super("hubben", playerEntity, rectangleFactory, camera, sceneLoader, quests);
    }


    @Override
    public void enteringState(Integer previousState) {
        super.enteringState(previousState);

        // Set the player position
        addPlayerAtPosition(22, 500);
    }

}

