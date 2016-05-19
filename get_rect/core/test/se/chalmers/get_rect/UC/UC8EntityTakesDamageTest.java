package se.chalmers.get_rect.UC;

import com.google.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import se.chalmers.get_rect.adapters.MeleeStud;
import se.chalmers.get_rect.game.entities.Entity;
import se.chalmers.get_rect.game.entities.IPhysicsModel;
import se.chalmers.get_rect.game.entities.item.ItemFactory;
import se.chalmers.get_rect.game.entities.item.model.IMelee;
import se.chalmers.get_rect.game.entities.item.model.MeleeWeapon;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.entities.item.swing.ISwinger;
import se.chalmers.get_rect.game.entities.item.swing.Swing;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.game.entities.item.view.OpSwordView;
import se.chalmers.get_rect.physics.IRectangleAdapter;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.adapters.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IModel;
import se.chalmers.get_rect.game.entities.enemies.model.Zombie;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.entities.item.projectile.Projectile;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.game.scenes.TestScene;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.CollisionData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

public class UC8EntityTakesDamageTest {

    private ICombatModel target;
    private CollisionData collision;
    private Projectile projectile;
    private Swing swing;



    @Before
    public void setup() {
        IPhysicsModel owner = Mockito.mock(Player.class);
        Point point = Mockito.mock(Point.class);
        when(owner.getPosition()).thenReturn(new Point(0,0));
        collision = Mockito.mock(CollisionData.class);
        IScene scene = Mockito.mock(TestScene.class);
        IRectangleFactoryAdapter rectangleFactory = new RectangleFactoryAdapterStub();
        IMelee meleeWeapon = mock(MeleeWeapon.class);
        this.target = new Zombie(point, rectangleFactory, owner);
        projectile = new Projectile(new Point(), new Point(), 10, rectangleFactory, owner);
        swing = new Swing(10,10,10,5, rectangleFactory,owner,meleeWeapon);
        scene.add(new Entity(projectile, null));

    }

    // An effected entity should take damage
    @Test
    public void testNormalFlow() {

        assertEquals("Entity's health should be max", target.getCurrentHealth(), target.getMaxHealth());
        projectile.onCollision(target, collision, false);
        assertNotEquals("Entity's health should have been lowered", target.getCurrentHealth(), target.getMaxHealth());
    }

    @Test
    public void testMeleeFlow() {
        assertEquals("Entity's health should be max", target.getCurrentHealth(), target.getCurrentHealth());
        swing.onCollision(target, collision, false);
        assertNotEquals("Entity's health should have been lowered", target.getCurrentHealth(), target.getMaxHealth());
    }
}
