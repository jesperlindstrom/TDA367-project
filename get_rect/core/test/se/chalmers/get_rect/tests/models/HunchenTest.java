package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.game.entities.npc.model.Hunchen;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.worldObjects.model.BoundingBox;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.tests.physics.RectangleAdapterStub;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class HunchenTest {

    private Hunchen hunchen;
    private Player player;
    private RectangleFactoryAdapterStub recFac;
    private RectangleAdapterStub recPlay;


    @Before
    public void setup(){
        player = Mockito.mock(Player.class);
        recFac = new RectangleFactoryAdapterStub();
        recPlay = Mockito.mock(RectangleAdapterStub.class);
        hunchen = new Hunchen(new Point(), recFac, player);
    }

    @Test
    public void testOnInteract(){
        Mockito.when(player.hasFoundHunch()).thenReturn(false);
        assertFalse("Player not found hunchen", player.hasFoundHunch() && player.isRiding());
        hunchen.onInteract(player);
        verify(player, times(1)).setHasFoundHunch(true);

        when(player.isRiding()).thenReturn(true);
        hunchen.onInteract(player);
        assertEquals("Should have 0 y velocity", hunchen.getVelocity().getY(), 0);
        verify(player, times(1)).setRiding(false);
    }

    @Test
    public void testUpdateNotRiding(){
        //Checks hunchen moves to player when init
        Mockito.when(player.hasFoundHunch()).thenReturn(true);
        Mockito.when(player.getPosition()).thenReturn(new Point(100,0));
        Mockito.when(player.getBoundingBox()).thenReturn(recPlay);
        when(recPlay.intersects(hunchen.getBoundingBox())).thenReturn( new CollisionData());
        Point oldPos = hunchen.getPosition();
        hunchen.update(16);
        assertTrue("New x-coord should be greater from old, hunchen has moved, true", oldPos.getX() < hunchen.getPosition().getX());

        //Checks if huncen movment AI works when player not riding)
        Mockito.when(player.getPosition()).thenReturn(hunchen.getPosition().addX(-40));
        hunchen.update(16.0);
        assertTrue("Should be true, velocity should be negativ beacuse player is to the left", player.getPosition().getX() < hunchen.getPosition().getX() && hunchen.getVelocity().getX() < 0);
        Mockito.when(player.getPosition()).thenReturn(hunchen.getPosition().addX(40));
        hunchen.update(16.0);
        assertTrue("Should be true, velocity should be positiv beacuse player is to the right", hunchen.getPosition().getX() < player.getPosition().getX()  &&  0 < hunchen.getVelocity().getX());

        //Checks if hunchen stop when intersect with player
        CollisionData sideData = new CollisionData();
        sideData.set(Side.LEFT);
        when(recPlay.intersects(any(IRectangleAdapter.class))).thenReturn(sideData);
        hunchen.update(16);
        assertEquals("Hunchen velocity in x should be 0",0,hunchen.getVelocity().getX());
    }

    @Test
    public void testUpdateRiding(){
        Mockito.when(player.hasFoundHunch()).thenReturn(true);
        Mockito.when(player.isRiding()).thenReturn(true);
        Mockito.when(player.getPosition()).thenReturn(new Point(100,0));
        Mockito.when(player.getBoundingBox()).thenReturn(recPlay);
        when(recPlay.intersects(hunchen.getBoundingBox())).thenReturn( new CollisionData());
        Point oldPos = hunchen.getPosition();
        hunchen.update(16);
        assertNotEquals("hunchen should not have same positon", oldPos, hunchen.getPosition());
        assertEquals("Hunchen should be found on player x and 100 on y", hunchen.getPosition(), player.getPosition().addY(100));
    }

    @Test
    public void testCollision(){
        BoundingBox boundingBox = new BoundingBox(new Point(),10,10,recFac);
        CollisionData data = new CollisionData();
        data.set(Side.LEFT);
        Point startVel = hunchen.getVelocity();
        Point oldVel = startVel;
        hunchen.onCollision(boundingBox, data, false);
        assertEquals("Hunchen velocity should be the same, non solid object", oldVel.getY(), hunchen.getVelocity().getY());
        oldVel = hunchen.getVelocity();
        hunchen.onCollision(boundingBox, data, true);
        assertNotEquals("Hunchen velocity should not be the same, solid object", oldVel.getY(), hunchen.getVelocity().getY());


        hunchen.setVelocity(startVel);
        oldVel = hunchen.getVelocity();
        data = new CollisionData();
        data.set(Side.RIGHT);
        hunchen.onCollision(boundingBox, data, false);
        assertEquals("Hunchen velocity should be the same, non solid object", oldVel.getY(), hunchen.getVelocity().getY());
        oldVel = hunchen.getVelocity();
        hunchen.onCollision(boundingBox, data, true);
        assertNotEquals("Hunchen velocity should not be the same, solid object", oldVel.getY(), hunchen.getVelocity().getY());


    }

}
