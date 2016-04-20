package se.chalmers.get_rect.UC;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import se.chalmers.get_rect.adapters.IRectangleFactoryAdapter;
import se.chalmers.get_rect.adapters.RectangleFactoryAdapterStub;
import se.chalmers.get_rect.game.entities.player.Player;

public class UC4JumpTest {

    private Player player;

    @Before
    public void setup() {
        IRectangleFactoryAdapter rectFactory = new RectangleFactoryAdapterStub();
        this.player = new Player(rectFactory);
    }

    //Normal flow of events
    @Test
    public void testJump() {
        assertTrue("player should be able to jump", player.canJump());
        player.jump();
        assertTrue("Should jump", player.getVelocity().getY() > 0);
    }

    // Normal flow of events: jump while in the air
    @Test
    public void testDoubleJump() {
        player.jump();
        assertFalse("Player should not be able to jump again", player.canJump());
        player.setVelocity(player.getVelocity().setY(0));
        player.jump();
        assertTrue("The player should have 0 as a Y velocity but has " + player.getVelocity(), player.getVelocity().getY() == 0);
    }

    // Alternate flow of events 3-4a: Path obstructed
    @Test
    public void testPathObstructed() {
        // Not necessary. Physics tests will check this.
    }

    // Alternate flow of events 3-6b: Enemy in the way
    @Test
    public void testEnemyInTheWay() {
        // Not necessary. Physics tests and Test "UC 8 : Entity takes damage" will check this.
    }

}
