package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;

public class HorsalsvagenScene extends AbstractScene {
    private IAssetManagerAdapter assetManager;

    public HorsalsvagenScene(IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, SceneLoader sceneLoader, IAssetManagerAdapter assetManager, QuestManager quests) {
        super("horsalsvagen", playerEntity, rectangleFactory, camera, sceneLoader, quests);
        this.assetManager = assetManager;
    }

    @Override
    public void enteringState(Integer previousState) {
        super.enteringState(previousState);

        // Set the player position
        addPlayerAtPosition(1200, 500);
    }

}
