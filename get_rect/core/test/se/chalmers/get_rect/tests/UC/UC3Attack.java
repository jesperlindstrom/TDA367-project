package se.chalmers.get_rect.tests.UC;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.item.model.MeleeWeapon;
import se.chalmers.get_rect.game.entities.item.swing.SwingFactory;
import se.chalmers.get_rect.game.world.IWorld;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.world.TestWorld;
import se.chalmers.get_rect.tests.physics.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.utilities.Point;


public class UC3Attack {

    private Player player;
    private IWorld scene;
    private IWeapon weapon;

    @Before
    public void setup(){
        this.scene = mock(TestWorld.class);
        this.player = new Player(new RectangleFactoryAdapterStub());
        weapon = Mockito.mock(IWeapon.class);
        this.player.setScene(scene);
        player.setActiveWeapon(weapon);
    }

    @Test
    public void testAttack(){
        player.use(new Point(1, 0));
        verify(weapon, times(1)).use(new Point(1,0), scene);
    }
}
