package se.chalmers.get_rect.tests.models;


import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.enemies.model.GhostBroccoli;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;

import static org.junit.Assert.*;


public class GhostBroccoliTest {

    private GhostBroccoli ghostBroccoli;
    private Player player;

    @Before
    public void setup(){
        this.player = new Player(new RectangleFactoryAdapterStub());
        this.ghostBroccoli = new GhostBroccoli(new Point(), new RectangleFactoryAdapterStub(), player);
    }

    @Test
    public void testCollision(){
        CollisionData data = new CollisionData();
        data.set(Side.LEFT);
        int oldHealth = player.getCurrentHealth();
        ghostBroccoli.onCollision(player,data, false);
        assertNotEquals("Player should have take damage", oldHealth, player.getCurrentHealth());
    }

    @Test
    public void testTakeDamage(){
        int oldHelath = ghostBroccoli.getCurrentHealth();
        ghostBroccoli.takeDamage(10);
        assertNotEquals("Should have took dmg ", oldHelath, ghostBroccoli.getCurrentHealth());
        ghostBroccoli.takeDamage(ghostBroccoli.getCurrentHealth());
        assertEquals("Should have died", 0, ghostBroccoli.getCurrentHealth());
    }

}
