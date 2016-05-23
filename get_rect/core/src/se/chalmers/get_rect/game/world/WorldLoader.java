package se.chalmers.get_rect.game.world;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.IRepository;
import se.chalmers.get_rect.game.entities.overlays.OverlayFactory;
import se.chalmers.get_rect.physics.IPhysicsEngine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class WorldLoader {
    private IRepository<IEntity> worldObjectRepository;
    private IRepository<IEntity> npcRepository;
    private IRepository<IEntity> enemyRepository;
    private OverlayFactory overlayFactory;

    @Inject
    public WorldLoader(@Named("worldObject") IRepository<IEntity> worldObjectRepository, @Named("npc") IRepository<IEntity> npcRepository, @Named("enemy") IRepository<IEntity> enemyRepository, OverlayFactory overlayFactory) {
        this.worldObjectRepository = worldObjectRepository;
        this.npcRepository = npcRepository;
        this.enemyRepository = enemyRepository;
        this.overlayFactory = overlayFactory;
    }

    /**
     * Get all foreground entities
     * @return NPCs and zombies
     * @throws FileNotFoundException
     */
    public List<IEntity> getEntities(String sceneName) throws FileNotFoundException {
        List<IEntity> entities = new ArrayList<>();

        entities.addAll(enemyRepository.get(sceneName));
        entities.addAll(npcRepository.get(sceneName));
        entities.addAll(worldObjectRepository.get(sceneName));

        return entities;
    }

    public List<IEntity> getOverlays(List<IModel> models, IPhysicsEngine physics) {
        List<IEntity> entities = new ArrayList<>();

        overlayFactory.setModels(models);
        overlayFactory.setPhysics(physics);

        entities.add(overlayFactory.make("questMarkers"));
        entities.add(overlayFactory.make("interactionHints"));
        entities.add(overlayFactory.make("debug"));
        entities.add(overlayFactory.make("healthbar"));
        entities.add(overlayFactory.make("playerHealthbar"));
        entities.add(overlayFactory.make("weaponSlots"));
        entities.add(overlayFactory.make("dialog"));
        entities.add(overlayFactory.make("activeQuests"));

        return entities;
    }
}
