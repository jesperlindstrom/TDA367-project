package se.chalmers.get_rect.UC;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.adapters.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.enemies.model.Zombie;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.projectile.model.Projectile;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.game.scenes.test.TestScene;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.SideData;

import static org.junit.Assert.*;

public class UC8EntityTakesDamageTest {

    private ICombatModel target;
    private IPhysicsModel projectile;
    private IPhysicsModel melee;
    private SideData collision;



    @Before
    public void setup() {
        IModel target = Mockito.mock(Zombie.class);
        IModel owner = Mockito.mock(Player.class);
        Point point = Mockito.mock(Point.class);
        collision = Mockito.mock(SideData.class);
        IScene scene = Mockito.mock(TestScene.class);
        IRectangleFactoryAdapter rectangleFactoryAdapterStub = new RectangleFactoryAdapterStub();
        this.target = new Zombie(point, rectangleFactoryAdapterStub, target);
        projectile = new Projectile(point, point, rectangleFactoryAdapterStub, owner, false);
        melee = new Projectile(point, point, rectangleFactoryAdapterStub, owner, true);
        projectile.setScene(scene);

    }

    // An effected entity should take damage
    @Test
    public void testNormalFlow() {
        assertEquals("Entity's health should be max", target.getCurrentHealth(), target.getCurrentHealth());
        projectile.onCollision(target, collision, false);
        assertNotEquals("Entity's health should have been lowered", target.getCurrentHealth(), target.getMaxHealth());
    }

    @Test
    public void testMeleeFlow() {
        assertEquals("Entity's health should be max", target.getCurrentHealth(), target.getCurrentHealth());
        melee.onCollision(target, collision, false);
        assertNotEquals("Entity's health should have been lowered", target.getCurrentHealth(), target.getMaxHealth());
    }
}
