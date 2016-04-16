package se.chalmers.get_rect.game.loaders;

import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.game.entities.IPhysicsEntity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.enemies.EnemyDataStore;
import se.chalmers.get_rect.game.entities.enemies.EnemyFactory;
import se.chalmers.get_rect.game.entities.npc.NpcDataStore;
import se.chalmers.get_rect.game.entities.npc.NpcFactory;
import se.chalmers.get_rect.io.IOFacade;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SceneLoader {
    private String sceneName;
    private IPhysicsModel player;
    private IRectangleFactoryAdapter rectangleFactory;

    public SceneLoader(String sceneName, IPhysicsEntity player, IRectangleFactoryAdapter rectangleFactory) {
        this.sceneName = sceneName;
        this.player = player.getModel();
        this.rectangleFactory = rectangleFactory;
    }

    /**
     * Get all foreground entities
     * @return NPCs and zombies
     * @throws FileNotFoundException
     */
    public List<IPhysicsEntity> getForeground() throws FileNotFoundException {
        List<IPhysicsEntity> entities = new ArrayList<>();

        loadEnemies(entities);
        loadNpcs(entities);

        return entities;
    }

    /**
     * Add NPCs to the provided list
     * @param entities
     * @throws FileNotFoundException
     */
    private void loadNpcs(List<IPhysicsEntity> entities) throws FileNotFoundException {
        IOFacade<NpcDataStore> npcData  = new IOFacade<>("scenes/" + sceneName + "/npcs.json", NpcDataStore.class);

        // Load NPCs
        NpcFactory npc = new NpcFactory(rectangleFactory);

        List<NpcDataStore> npcDataList = npcData.load();

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
        IOFacade<EnemyDataStore> enemyData = new IOFacade<>("scenes/" + sceneName + "/enemies.json", EnemyDataStore.class);

        // Load enemies
        EnemyFactory enemy = new EnemyFactory(player, rectangleFactory);

        List<EnemyDataStore> enemyDataList = enemyData.load();

        if (enemyDataList != null) {
            for (EnemyDataStore data : enemyDataList) {
                entities.add(enemy.make(data));
            }
        }
    }
}
