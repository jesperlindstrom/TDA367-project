package se.chalmers.get_rect.game.scenes;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.game.entities.IRepository;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SceneEntityLoader {
    private IRepository worldObjectRepository;
    private IRepository npcRepository;
    private IRepository enemyRepository;

    @Inject
    public SceneEntityLoader(@Named("worldObject") IRepository worldObjectRepository, @Named("npc") IRepository npcRepository, @Named("enemy") IRepository enemyRepository) {
        this.worldObjectRepository = worldObjectRepository;
        this.npcRepository = npcRepository;
        this.enemyRepository = enemyRepository;
    }

    /**
     * Get all foreground entities
     * @return NPCs and zombies
     * @throws FileNotFoundException
     */
    public List<IPhysicsEntity> getAllEntities(String sceneName) throws FileNotFoundException {
        List<IPhysicsEntity> entities = new ArrayList<>();

        entities.addAll(enemyRepository.get(sceneName));
        entities.addAll(npcRepository.get(sceneName));
        entities.addAll(worldObjectRepository.get(sceneName));

        return entities;
    }
}
