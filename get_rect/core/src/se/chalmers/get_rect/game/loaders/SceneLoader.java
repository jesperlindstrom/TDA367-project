package se.chalmers.get_rect.game.loaders;

import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.enemies.EnemyDataStore;
import se.chalmers.get_rect.game.entities.enemies.EnemyFactory;
import se.chalmers.get_rect.game.entities.npc.NpcDataStore;
import se.chalmers.get_rect.game.entities.npc.NpcFactory;
import se.chalmers.get_rect.game.entities.worldObjects.WorldObjectDataStore;
import se.chalmers.get_rect.game.entities.worldObjects.WorldObjectFactory;
import se.chalmers.get_rect.game.IScene;
import se.chalmers.get_rect.io.IOFacade;
import se.chalmers.get_rect.states.StateManager;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SceneLoader {
    private String folderName;
    private IPhysicsModel player;
    private IRectangleFactoryAdapter rectangleFactory;
    private StateManager<IScene> stateManager;

    public SceneLoader(String folderName, IPhysicsEntity player, IRectangleFactoryAdapter rectangleFactory, StateManager<IScene> stateManager) {
        this.folderName = folderName;
        this.player = player.getModel();
        this.rectangleFactory = rectangleFactory;
        this.stateManager = stateManager;
    }

    /**
     * Get all foreground entities
     * @return NPCs and zombies
     * @throws FileNotFoundException
     */
    public List<IPhysicsEntity> getAllEntities() throws FileNotFoundException {
        List<IPhysicsEntity> entities = new ArrayList<>();

        loadEnemies(entities);
        loadNpcs(entities);
        loadWorldObjects(entities);

        return entities;
    }

    /**
     * Add world objects to the provided list
     * @param entities
     * @throws FileNotFoundException
     */
    private void loadWorldObjects(List<IPhysicsEntity> entities) throws FileNotFoundException {
        IOFacade<WorldObjectDataStore> worldObjectData  = new IOFacade<>("scenes/" + folderName + "/worldObjects.json", WorldObjectDataStore.class);
        List<WorldObjectDataStore> worldObjectDataList = worldObjectData.load();

        WorldObjectFactory worldObject = new WorldObjectFactory(rectangleFactory, stateManager);

        if (worldObjectDataList != null) {
            for (WorldObjectDataStore data : worldObjectDataList) {
                entities.add(worldObject.make(data));
            }
        }
    }

    /**
     * Add NPCs to the provided list
     * @param entities
     * @throws FileNotFoundException
     */
    private void loadNpcs(List<IPhysicsEntity> entities) throws FileNotFoundException {
        IOFacade<NpcDataStore> npcData  = new IOFacade<>("scenes/" + folderName + "/npcs.json", NpcDataStore.class);
        List<NpcDataStore> npcDataList = npcData.load();

        NpcFactory npc = new NpcFactory(rectangleFactory);

        if (npcDataList != null) {
            for (NpcDataStore data : npcDataList) {
                entities.add(npc.make(data));
            }
        }
    }

    /**
     * Add enemies to the provided list
     * @param entities
     * @throws FileNotFoundException
     */
    private void loadEnemies(List<IPhysicsEntity> entities) throws FileNotFoundException {
        IOFacade<EnemyDataStore> enemyData = new IOFacade<>("scenes/" + folderName + "/enemies.json", EnemyDataStore.class);
        List<EnemyDataStore> enemyDataList = enemyData.load();

        EnemyFactory enemy = new EnemyFactory(player, rectangleFactory);

        if (enemyDataList != null) {
            for (EnemyDataStore data : enemyDataList) {
                entities.add(enemy.make(data));
            }
        }
    }
}
