package se.chalmers.get_rect.UC;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;
import se.chalmers.get_rect.adapters.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.game.entities.enemies.zombie.Zombie;
import se.chalmers.get_rect.game.entities.npc.sawmillExpress.SawmillExpress;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;
import se.chalmers.get_rect.utilities.SideData;

import static org.junit.Assert.*;


public class UC2InteractTest {

    private Player player;
    private SawmillExpress sawmillExpress;
    private SideData playerSide;
    private SideData otherSide;


    @Before
    public void setup(){
        player = new Player(new RectangleFactoryAdapterStub());
        sawmillExpress = new SawmillExpress(new Point(10,10), new RectangleFactoryAdapterStub());
        playerSide = new SideData();
        playerSide.set(Side.RIGHT);
        otherSide = new SideData();
        otherSide.set(Side.LEFT);
    }

    /**
     * checks if entity can show it's indication
     */
    @Test
    public void testIndication(){
        player.onCollision(sawmillExpress,playerSide, false);
        sawmillExpress.onCollision(player, otherSide, false);
        assertTrue("Should show interaction hint", sawmillExpress.showInteractionHint());
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
        SideData zombieSideData = new SideData();
        zombieSideData.set(Side.LEFT);
        zombie.onCollision(player,zombieSideData,false);
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
