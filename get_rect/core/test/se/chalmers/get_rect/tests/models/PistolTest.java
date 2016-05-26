package se.chalmers.get_rect.tests.models;

import org.junit.Test;
import se.chalmers.get_rect.game.entities.*;
import se.chalmers.get_rect.game.entities.item.model.Pistol;
import se.chalmers.get_rect.game.entities.item.model.Rock;
import se.chalmers.get_rect.game.entities.item.projectile.Projectile;
import se.chalmers.get_rect.game.entities.item.projectile.ProjectileFactory;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.tests.physics.RectangleAdapterStub;
import se.chalmers.get_rect.utilities.Point;

import static org.mockito.Mockito.*;


public class PistolTest {

    @Test
    public void testUse(){
        Player player = mock(Player.class);
        when(player.getPosition()).thenReturn(new Point());
        when(player.getVelocity()).thenReturn(new Point());
        when(player.getBoundingBox()).thenReturn(new RectangleAdapterStub(1,1,1,1));
        ProjectileFactory projectileFactory = mock(ProjectileFactory.class);
        IView view = mock(IView.class);
        Projectile projectile = mock(Projectile.class);
        IEntity entity = new Entity(projectile,view);
        when(projectileFactory.make(anyString(),any(Point.class),any(Point.class),anyInt(),any(IModel.class))).thenReturn(entity);
        Pistol pistol = new Pistol(player,projectileFactory,10,10,10);
        IEntityHolder entityHolder = mock(IEntityHolder.class);
        pistol.use(new Point(),entityHolder);
        verify(entityHolder, times(1)).add(entity);
    }
}
