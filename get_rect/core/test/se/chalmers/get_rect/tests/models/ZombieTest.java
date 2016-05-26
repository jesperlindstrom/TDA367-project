package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.enemies.model.Zombie;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import static org.junit.Assert.*;


public class ZombieTest extends ModelUtilsTest {
    @Before
    public void setup(){
        super.setup();
        super.setEnemy(new Zombie(new Point(10,0), new RectangleFactoryAdapterStub(), getPlayer(),10,10));
    }

    @Test
    public void testCollision(){
        super.testCollision();
    }

    @Test
    public void testUpdate(){
        Player player = getPlayer();
        ICombatModel zombie = getEnemy();

        zombie.update(16.0);
        assertTrue("Should be true, velocity should be negativ beacuse player is to the left", player.getPosition().getX() < zombie.getPosition().getX() && zombie.getVelocity().getX() < 0);
        player.setPosition(new Point(zombie.getPosition().addX(20)));
        zombie.update(16.0);
        assertTrue("Should be true, velocity should be positiv beacuse player is to the right", zombie.getPosition().getX() < player.getPosition().getX()  &&  0 < zombie.getVelocity().getX());

    }

    @Test
    public void testDie(){
        ICombatModel zombie = getEnemy();

        zombie.takeDamage(zombie.getCurrentHealth());
        assertTrue("Should be removed, should be true", zombie.shouldBeRemoved());
    }

}
