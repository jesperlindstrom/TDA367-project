package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.game.entities.enemies.model.Zombie;
import se.chalmers.get_rect.game.entities.npc.model.SawmillExpress;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;

import static org.junit.Assert.*;


public class ZombeTest {

    private Zombie zombie;
    private Player player;

    @Before
    public void setup(){
        this.player = new Player(new RectangleFactoryAdapterStub());
        this.zombie = new Zombie(new Point(10,0), new RectangleFactoryAdapterStub(), player,10,10);
    }

    @Test
    public void testCollision(){
        CollisionData zombieData = new CollisionData();
        zombieData.set(Side.LEFT);
        int oldHelath = player.getCurrentHealth();
        SawmillExpress sawmillExpress = Mockito.mock(SawmillExpress.class);
        zombie.onCollision(player,zombieData,false);
        assertNotEquals("Health should be smaller because damage given", oldHelath, player.getCurrentHealth());
        oldHelath = player.getCurrentHealth();
        zombie.onCollision(sawmillExpress,zombieData,false);
        assertEquals("Health should not be changed", oldHelath, player.getCurrentHealth());



    }

    @Test
    public void testUpdate(){
        zombie.update(16.0);
        assertTrue("Should be true, velocity should be negativ beacuse player is to the left", player.getPosition().getX() < zombie.getPosition().getX() && zombie.getVelocity().getX() < 0);
        player.setPosition(new Point(zombie.getPosition().addX(20)));
        zombie.update(16.0);
        assertTrue("Should be true, velocity should be positiv beacuse player is to the right", zombie.getPosition().getX() < player.getPosition().getX()  &&  0 < zombie.getVelocity().getX());

    }

    @Test
    public void testDie(){
        zombie.takeDamage(zombie.getCurrentHealth());
        assertTrue("Should be removed, should be true", zombie.shouldBeRemoved());
    }

}
