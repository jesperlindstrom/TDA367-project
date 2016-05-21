package se.chalmers.get_rect.game.scenes;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.adapters.IAssetManagerAdapter;
import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.player.PlayerRepository;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;

public class SceneFactory {
    @Inject @Named("Player") private IPhysicsEntity playerEntity;
    @Inject private IRectangleFactoryAdapter rectangleFactory;
    @Inject private ICamera camera;
    @Inject private SceneLoader sceneLoader;
    @Inject private PlayerRepository playerRepository;
    @Inject private QuestManager quests;
    @Inject private IAudioManagerAdapter audioManager;

    public IScene make(String name) {
        if (name.equals("horsalsvagen"))
            return makeHorsalsvagen();

        if (name.equals("test"))
            return makeTest();

        if (name.equals("hubben"))
            return makeHubben();

        throw new SceneNotFoundException(name);
    }

    private IScene makeHorsalsvagen() {
        return new HorsalsvagenScene(playerEntity, rectangleFactory, camera, sceneLoader, quests, audioManager);
    }


    private IScene makeTest() {
        return new TestScene(playerEntity, rectangleFactory, camera, sceneLoader, quests, audioManager);
    }

    private IScene makeHubben(){
        return new HubbenScene(playerEntity,rectangleFactory,camera,sceneLoader, quests, audioManager);
    }
}
