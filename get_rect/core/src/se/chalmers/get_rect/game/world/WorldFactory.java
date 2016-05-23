package se.chalmers.get_rect.game.world;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.adapters.IAudioManagerAdapter;
import se.chalmers.get_rect.game.entities.ICamera;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.quests.QuestManager;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;

public class WorldFactory {
    @Inject @Named("Player") private IEntity playerEntity;
    @Inject private IRectangleFactoryAdapter rectangleFactory;
    @Inject private ICamera camera;
    @Inject private WorldLoader worldLoader;
    @Inject private QuestManager quests;
    @Inject private IAudioManagerAdapter audioManager;

    public IWorld make(String name) {
        if (name.equals("horsalsvagen"))
            return makeHorsalsvagen();

        if (name.equals("test"))
            return makeTest();

        if (name.equals("hubben"))
            return makeHubben();

        throw new WorldNotFoundException(name);
    }

    private IWorld makeHorsalsvagen() {
        return new HorsalsvagenWorld(playerEntity, rectangleFactory, camera, worldLoader, quests, audioManager);
    }


    private IWorld makeTest() {
        return new TestWorld(playerEntity, rectangleFactory, camera, worldLoader, quests, audioManager);
    }

    private IWorld makeHubben(){
        return new HubbenWorld(playerEntity,rectangleFactory,camera, worldLoader, quests, audioManager);
    }
}
