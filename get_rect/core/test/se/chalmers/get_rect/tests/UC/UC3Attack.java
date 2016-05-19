package se.chalmers.get_rect.tests.UC;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import se.chalmers.get_rect.game.entities.item.model.IWeapon;
import se.chalmers.get_rect.game.entities.item.model.MeleeWeapon;
import se.chalmers.get_rect.game.scenes.IScene;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.scenes.TestScene;
import se.chalmers.get_rect.utilities.Point;


public class UC3Attack {

    private Player player;
    private IScene scene;

    @Before
    public void setup(){
        this.scene = mock(TestScene.class);
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
