package se.chalmers.get_rect.UC;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import se.chalmers.get_rect.adapters.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.game.IScene;
import se.chalmers.get_rect.game.entities.IEntity;
import se.chalmers.get_rect.game.entities.player.Player;
import se.chalmers.get_rect.game.scenes.test.TestScene;
import se.chalmers.get_rect.physics.IRectangleFactoryAdapter;
import se.chalmers.get_rect.utilities.Point;


public class UC3Attack {

    private Player player;
    private IScene scene;

    @Before
    public void setup(){
        scene = mock(TestScene.class);
        IRectangleFactoryAdapter rectangleFactory = new RectangleFactoryAdapterStub();
        this.player = new Player(rectangleFactory);
        this.player.setScene(scene);
    }

    @Test
    public void testAttack(){
        player.shoot(new Point(1, 0));
        verify(scene, times(1)).add(any(IEntity.class));
    }
}
