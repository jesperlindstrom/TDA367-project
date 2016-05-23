package se.chalmers.get_rect.tests.UC;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.item.model.MeleeWeapon;
import se.chalmers.get_rect.game.world.IWorld;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.world.TestWorld;
import se.chalmers.get_rect.utilities.Point;


public class UC3Attack {

    private Player player;
    private IWorld scene;

    @Before
    public void setup(){
        this.scene = mock(TestWorld.class);
        this.player = mock(Player.class);
        IWeapon weapon = mock(MeleeWeapon.class);
        this.player.setScene(scene);
        player.setActiveWeapon(weapon);
    }

    @Test
    public void testAttack(){
        player.use(new Point(1, 0));
        verify(player, times(1)).use(new Point(1,0));
    }
}
