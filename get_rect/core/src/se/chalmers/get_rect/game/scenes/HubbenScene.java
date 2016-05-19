package se.chalmers.get_rect.game.scenes;

import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.player.PlayerRepository;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;

public class HubbenScene extends AbstractScene {
    public HubbenScene(IPhysicsEntity playerEntity, IRectangleFactoryAdapter rectangleFactory, ICamera camera, SceneLoader sceneLoader, QuestManager quests, PlayerRepository playerRepository) {
        super("hubben", playerEntity, rectangleFactory, camera, sceneLoader, quests, playerRepository);
    }


    @Override
    public void enteringState(Integer previousState) {
        super.enteringState(previousState);

        // Set the player position
        addPlayerAtPosition(22, 500);
    }

    @Override
    public void respawn() {
        getPlayer().getModel().setPosition(new Point(30, 480));
    }
}

