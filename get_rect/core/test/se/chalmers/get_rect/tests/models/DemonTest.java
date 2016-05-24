package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.enemies.model.Demon;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import static org.junit.Assert.*;

public class DemonTest extends modelUtilsTest {


    @Before
    public void setup(){
        super.setup();
        super.setEnemy(new Demon(new Point(10,0), new RectangleFactoryAdapterStub(), getPlayer(),10,10));
    }

    @Test
    public void testCollsion(){
        super.testCollision();
    }

    @Test
    public void testUpdate(){
        Demon model = (Demon) getEnemy();
        Player player = getPlayer();

        model.update(16);


        assertTrue("Should be true, velocity should be negativ beacuse player is to the left", player.getPosition().getX() < model.getPosition().getX() && model.getVelocity().getX() < 0);
        player.setPosition(model.getPosition().addX(Math.abs(model.getVelocity().getX()) + 300));
        model.update(16);
        assertTrue("Should be true, velocity should be positiv beacuse player is to the right", model.getPosition().getX() < player.getPosition().getX()  &&  0 < model.getVelocity().getX());

        assertFalse("Should be false, is not attacking", model.isAttacking());
        player.setPosition(model.getPosition().addX(150));
        model.update(16);
        assertTrue("Should be true, is attacking", model.isAttacking());
    }

    @Test
    public void testDie(){
        ICombatModel model = getEnemy();

        model.takeDamage(model.getCurrentHealth());
        assertTrue("Should be removed, should be true", model.shouldBeRemoved());
    }

}
