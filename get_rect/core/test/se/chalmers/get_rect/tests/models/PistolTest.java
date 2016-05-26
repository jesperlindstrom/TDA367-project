package se.chalmers.get_rect.tests.models;

import org.junit.Before;
import org.junit.Test;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.model.Pistol;
import se.chalmers.get_rect.game.entities.item.projectile.Projectile;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.tests.physics.RectangleAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class PistolTest {
    private Player player;
    private ProjectileFactory projectileFactory;
    private IEntity entity;
    private IView view;
    private Pistol pistol;
    private IEntityHolder entityHolder;
    private Projectile projectile;

    @Before
    public void setup() {
        player = mock(Player.class);
        when(player.getPosition()).thenReturn(new Point());
        when(player.getVelocity()).thenReturn(new Point());
        when(player.getBoundingBox()).thenReturn(new RectangleAdapterStub(1,1,1,1));
        projectileFactory = mock(ProjectileFactory.class);
        view = mock(IView.class);
        projectile = mock(Projectile.class);
        entity = new Entity(projectile,view);
        when(projectileFactory.make(anyString(),any(Point.class),any(Point.class),anyInt(),any(IModel.class))).thenReturn(entity);
        pistol = new Pistol(player,projectileFactory,10,10,1);
        entityHolder = mock(IEntityHolder.class);
    }

    @Test
    public void testUse(){
        pistol.use(new Point(),entityHolder);
        verify(entityHolder, times(1)).add(entity);

        pistol.use(new Point(10,10),entityHolder);
        verify(entityHolder,times(1)).add(entity);

        pistol.use(new Point(10,-30),entityHolder);
        verify(entityHolder,times(1)).add(entity);
    }

    @Test
    public void testSpawnPoint() {
        pistol.use(new Point(0, 0), entityHolder);
        Point noMod = pistol.getSpawnPoint();
        pistol.update(1);
        pistol.update(1);
        pistol.use(new Point(-1, 0), entityHolder);
        Point aimLeft = pistol.getSpawnPoint();
        pistol.update(1);
        pistol.update(1);
        pistol.use(new Point(0, -1), entityHolder);
        Point aimDown = pistol.getSpawnPoint();
        pistol.update(1);
        pistol.update(1);
        pistol.use(new Point(1, 0), entityHolder);
        Point aimRight = pistol.getSpawnPoint();
        pistol.update(1);
        pistol.update(1);
        pistol.use(new Point(0, 1), entityHolder);
        Point aimUp = pistol.getSpawnPoint();
        assertTrue(noMod.getX() > aimLeft.getX());
        assertTrue(noMod.getX() < aimRight.getX());
        assertTrue(noMod.getY() > aimDown.getY());
        assertTrue(noMod.getY() < aimUp.getY());

    }
}
