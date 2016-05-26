package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.ICombatModel;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.IEntityHolder;
import se.chalmers.get_rect.game.entities.item.projectile.Projectile;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.physics.CollisionData;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;
import se.chalmers.get_rect.utilities.Side;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProjectileTest {
    private IRectangleFactoryAdapter rectangleFactory;
    private ProjectileFactory projectileFactory;
    private ICombatModel owner;
    private ICombatModel enemy;
    private IEntityHolder holder;

    @Before
    public void setup() {
        rectangleFactory = mock(IRectangleFactoryAdapter.class);
        projectileFactory = mock(ProjectileFactory.class);
        owner = mock(ICombatModel.class);
        enemy = mock(ICombatModel.class);
        holder = mock(IEntityHolder.class);
        when(enemy.getPosition()).thenReturn(new Point());
        when(enemy.getVelocity()).thenReturn(new Point());
    }

    @Test
    public void testProjectile() {
        Projectile projectile = new Projectile(new Point(0, 0), new Point(5, 0), 5, rectangleFactory, owner, false);

        projectile.update(0);
        assertFalse("Should not be removed", projectile.shouldBeRemoved());

        projectile.setVelocity(new Point(0, 0));
        projectile.update(0);
        assertTrue("Should be removed", projectile.shouldBeRemoved());
    }

    @Test
    public void testFriction() {
        Projectile projectile = new Projectile(new Point(0, 0), new Point(5, 0), 5, rectangleFactory, owner, false);

        CollisionData side = new CollisionData();
        side.set(Side.BOTTOM);
        projectile.onCollision(enemy, side, true);
        assertEquals("Friction should be applied", projectile.getVelocity(), new Point(0, 0));
    }

    @Test
    public void testClusterProjectile() {
        Projectile projectile = new Projectile(new Point(0, 0), new Point(5, 0), 5, rectangleFactory, owner, false, projectileFactory, "cluster");
        CollisionData data = new CollisionData();
        projectile.setScene(holder);
        projectile.onCollision(enemy, data, false);
        verify(projectileFactory, times(10)).make(anyString(), any(Point.class), any(Point.class), anyInt(), any(ICombatModel.class));
        verify(holder, times(10)).add(any(IEntity.class));
    }
}
