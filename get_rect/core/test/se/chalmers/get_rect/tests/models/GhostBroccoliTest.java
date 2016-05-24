package se.chalmers.get_rect.tests.models;


import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.enemies.model.GhostBroccoli;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import static org.junit.Assert.*;


public class GhostBroccoliTest extends modelUtilsTest {

    @Before
    public void setup(){
        super.setup();
        super.setEnemy(new GhostBroccoli(new Point(10,0), new RectangleFactoryAdapterStub(), getPlayer()));
    }

    @Test
    public void testCollision(){
        super.testCollision();
    }

    @Test
    public void testTakeDamage(){
        ICombatModel ghostBroccoli = getEnemy();
        int oldHelath = ghostBroccoli.getCurrentHealth();
        ghostBroccoli.takeDamage(10);
        assertNotEquals("Should have took dmg ", oldHelath, ghostBroccoli.getCurrentHealth());
        ghostBroccoli.takeDamage(ghostBroccoli.getCurrentHealth());
        assertEquals("Should have died", 0, ghostBroccoli.getCurrentHealth());
    }

}
