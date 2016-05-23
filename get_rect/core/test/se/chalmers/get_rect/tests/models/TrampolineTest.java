package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.worldObjects.model.Trampoline;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;

import static org.junit.Assert.*;


public class TrampolineTest {

    private Player player;
    private Trampoline trampoline;

    @Before
    public void setup(){
        this.player = Mockito.mock(Player.class);
        Mockito.when(player.getVelocity()).thenReturn(new Point(10,10));
        this.trampoline = new Trampoline(new Point(), new RectangleFactoryAdapterStub());
    }

    @Test
    public void testCollison(){
        assertFalse("Should be false, no one jumped yet", trampoline.isGotHit());
        CollisionData data = new CollisionData();
        data.set(Side.LEFT);
        trampoline.onCollision(player, data, false);
        assertFalse("Should be false, collided wrong side", trampoline.isGotHit());
        data.set(Side.TOP);
        trampoline.onCollision(player, data, false);
        assertTrue("Should be true, something jumped", trampoline.isGotHit());
        trampoline.update(16);
        assertFalse("Updated trampoline, should be false", trampoline.isGotHit());
    }

    @Test
    public void testSetPostion(){
        Point oldPos = trampoline.getPosition();
        trampoline.setPosition(oldPos.add(10,10));
        assertNotEquals("Postion shuld not be equal after moved", oldPos, trampoline.getPosition());
    }

}
