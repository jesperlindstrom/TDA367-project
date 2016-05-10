package se.chalmers.get_rect.game.scenes;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.IRepository;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.overlays.OverlayFactory;
import se.chalmers.get_rect.physics.IPhysicsEngine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SceneLoader {
    private IRepository worldObjectRepository;
    private IRepository npcRepository;
    private IRepository enemyRepository;
    private OverlayFactory overlayFactory;

    @Inject
    public SceneLoader(@Named("worldObject") IRepository worldObjectRepository, @Named("npc") IRepository npcRepository, @Named("enemy") IRepository enemyRepository, OverlayFactory overlayFactory) {
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
    public List<IPhysicsEntity> getEntities(String sceneName) throws FileNotFoundException {
        List<IPhysicsEntity> entities = new ArrayList<>();

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

        return entities;
    }
}
