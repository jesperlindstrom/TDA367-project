package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.model.Wand;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.utilities.Point;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class WandTest {

    private Wand wand;
    private IPhysicsModel model;
    private IEntityHolder holder;
    private ProjectileFactory projectileFactory;
    private Point point;
    private IEntity projectileEntity;

    @Before
    public void setup() {
        point = new Point(1, 1);
        model = mock(IPhysicsModel.class);
        when(model.getVelocity()).thenReturn(new Point());
        holder = mock(IEntityHolder.class);
        projectileFactory = mock(ProjectileFactory.class);
        projectileEntity = mock(IEntity.class);
        wand = new Wand(model, projectileFactory, 5, 5, 5 , "arcane");
        when(model.getPosition()).thenReturn(point);
        when(projectileFactory.make(anyString(), any(Point.class), any(Point.class), anyInt(), any(IModel.class))).thenReturn(projectileEntity);
        wand.update(1);
    }

    @Test
    public void testUse() {
        assertEquals("Should be no cooldown", wand.getCooldownFrames(), 0);
        wand.use(point, holder);
        verify(holder).add(projectileEntity);
    }
    @Test
    public void testGetSpawnPoint() {
        wand.use(point, holder);
        assertNotEquals("Aimdirection shouldn't be 0 ",wand.getAimDirection().getX(), 0);
    }
}
