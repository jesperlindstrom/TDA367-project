package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.mockito.Mockito;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.npc.model.SawmillExpress;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Side;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CombatCollisionTest {

    private Player player;
    private ICombatModel combatModel;

    @Before
    public void setup(){
        this.player = new Player(new RectangleFactoryAdapterStub());
    }

    protected void setEnemy(ICombatModel model) {
        this.combatModel = model;
    }

    public Player getPlayer() {
        return player;
    }

    public ICombatModel getEnemy() {
        return combatModel;
    }

    protected void testCollision(){
        CollisionData zombieData = new CollisionData();
        zombieData.set(Side.LEFT);
        int oldHelath = player.getCurrentHealth();
        SawmillExpress sawmillExpress = Mockito.mock(SawmillExpress.class);
        combatModel.onCollision(player,zombieData,false);
        assertNotEquals("Health should be smaller because damage given", oldHelath, player.getCurrentHealth());
        oldHelath = player.getCurrentHealth();
        combatModel.onCollision(sawmillExpress,zombieData,false);
        assertEquals("Health should not be changed", oldHelath, player.getCurrentHealth());
    }

}
