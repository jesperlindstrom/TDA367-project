package se.chalmers.get_rect.tests.UC;
import com.google.inject.Inject;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.game.entities.enemies.model.Zombie;
import se.chalmers.get_rect.game.entities.npc.NpcFactory;
import se.chalmers.get_rect.game.entities.npc.model.SawmillExpress;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;
import se.chalmers.get_rect.physics.CollisionData;

import static org.junit.Assert.*;


public class UC2InteractTest {

    private Player player;
    @Inject private NpcFactory npcFactory;
    SawmillExpress sawmillExpress;
    private CollisionData playerSide;
    private CollisionData otherSide;


    @Before
    public void setup(){
        player = new Player(new RectangleFactoryAdapterStub());
        sawmillExpress = (SawmillExpress)npcFactory.make("sawmillExpress", new Point(10, 10)).getModel();
        playerSide = new CollisionData();
        playerSide.set(Side.RIGHT);
        otherSide = new CollisionData();
        otherSide.set(Side.LEFT);
    }

    /**
     * checks if player can interact with intractable entity
     */
    @Test
    public void testInteraction(){
        player.onCollision(sawmillExpress,playerSide,false);
        sawmillExpress.onCollision(player, otherSide,false);
        assertFalse("Should not be flying", sawmillExpress.isFlying());
        player.interact();
        assertTrue("Should be flying", sawmillExpress.isFlying());
    }

    /**
     * Alternate flow of events, Case 1-3a: Player tries to interact with a non-interactable entity
     */
    @Test
    public void testAlternateFlow(){
        Zombie zombie = Mockito.mock(Zombie.class);
        CollisionData zombieCollisionData = new CollisionData();
        zombieCollisionData.set(Side.LEFT);
        zombie.onCollision(player, zombieCollisionData,false);
        player.onCollision(zombie, playerSide, false);
        assertNull("Should be null", player.getCurrentNpc());
    }

    /**
     * Test if dialog with sawmill works
     */
    @Test
    public void testDialog(){
        player.onCollision(sawmillExpress,playerSide,false);
        sawmillExpress.onCollision(player,otherSide,false);
        assertFalse("Should be false", sawmillExpress.isDialogVisible());
        assertNull("Should be null", sawmillExpress.getDialog());
        player.interact();
        assertTrue("Should be true", sawmillExpress.isDialogVisible());
        assertEquals("Should be equal", sawmillExpress.getDialog(), "Wäääh!");
    }
}
